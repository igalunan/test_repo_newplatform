package eltautomation.Tests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

import eltautomation.Utils.*;

public class MainTest {
	private static int runID;
	private static String runtags = "DEV", runlabel = "TestOnly", executiontable = "scenarios_tbl";
	public static ConfigFile cfile;
	
	public static void main(String[] args) {
		/* String jsontext = "";
		
		try {
			jsontext = new String(Files.readAllBytes(Paths.get("file")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return;
		}
		
		Gson gson = new Gson();
		ConfigFile cf = gson.fromJson(jsontext, ConfigFile.class); */
		
		RunLogger logger = new RunLogger(MainTest.runlabel, MainTest.runtags);
		List<TestScenario> tslist, ielist, edgelist, safarilist;
		
		try {
			MainTest.initializeDB();
			tslist = MainTest.getScenarios(MainTest.executiontable);
			ielist = MainTest.getScenarios(MainTest.executiontable, "ie");
			edgelist = MainTest.getScenarios(MainTest.executiontable, "edge");
			safarilist = MainTest.getScenarios(MainTest.executiontable, "safari");
			logger.logStartRun();
			MainTest.runID = logger.getRunID();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to run scripts. Prerequisites not met.");
			DBConnectionPool.closePool();
			return;
		}
		
		ExecutorService parallelExecutor = MainTest.putOnThreadQueue(tslist, 2);
		ExecutorService ieExecutor = MainTest.putOnThreadQueue(ielist, 1);
		ExecutorService edgeExecutor = MainTest.putOnThreadQueue(edgelist, 1);
		ExecutorService safariExecutor = MainTest.putOnThreadQueue(safarilist, 1);
		
		boolean pnull = true, ienull = false, edgenull = false, safnull = false;
		
		while(pnull || ienull || edgenull || safnull) {
			pnull = (parallelExecutor == null) ? false : !parallelExecutor.isTerminated();
			ienull = (ieExecutor == null) ? false : !ieExecutor.isTerminated();
			edgenull = (edgeExecutor == null) ? false : !edgeExecutor.isTerminated();
			safnull = (safariExecutor == null) ? false : !safariExecutor.isTerminated();
			
			if (pnull || ienull || edgenull || safnull) {
				System.out.println("ExecutorServices are running...");
				try {
					Thread.sleep(3000);			
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		DBConnectionPool.closePool();
		System.out.println("Connection pool is closed.");
		System.out.println("All threads done!");
	}
	
	public static void initializeDB() throws Exception {
		try {
			DBConnectionPool.createDBPool("org.mariadb.jdbc.Driver",
					"jdbc:mariadb://CUP-MNL-ELT1/new_platform",
					"remoteuser", "passw0rd", "remote-cup-mnl-elt1",
					5, 180, 2);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException:");
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			System.out.println("SQLException:");
			e.printStackTrace();
			throw e;
		} catch (InstantiationException e) {
			System.out.println("InstantiationException:");
			e.printStackTrace();
			throw e;
		} catch (IllegalAccessException e) {
			System.out.println("IllegalAccessException:");
			e.printStackTrace();
			throw e;
		}
	}
	
	public static List<TestScenario> getScenarios(String tablename, String browser) throws SQLException {
		if (browser.length() > 0) {
			browser = "WHERE W_BROWSER = '" + browser + "' ";
		} else {
			browser = "WHERE W_BROWSER IN ('chrome', 'firefox') ";
		}
		
		Connection conn = DBConnectionPool.getConnection();
		String sql = "SELECT SCENARIO_ID, US_ID, SCENARIO_DESC, PKG_CLASS_METHOD, " +
				"APP_MODULE, W_BROWSER, SCENARIO_TAGS, START_URL, BASE_URL, STORY_PTS, RUN_COMPLEXITY FROM " + tablename + " " + browser + "ORDER BY SCENARIO_NO";
		List<TestScenario> tslist = new ArrayList<TestScenario>();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet res = pstmt.executeQuery();
		
		while (res.next()) {
			tslist.add(new TestScenario(res.getString(1), res.getString(2), res.getString(3),
					res.getString(4), res.getString(5), res.getString(6), res.getString(7),
					res.getString(8), res.getString(9), res.getInt(10), res.getInt(11)));
		}
		
		res.close();
		pstmt.close();
		conn.close();
		
		return tslist;
	}
	
	public static List<TestScenario> getScenarios(String tablename) throws SQLException {
		return MainTest.getScenarios(tablename, "");
	}
	
	public static ExecutorService putOnThreadQueue(List<TestScenario> tslist, int nthreads) {
		ExecutorService parallelExecutor = null;
		
		if (tslist.size() > 0) {
			parallelExecutor = Executors.newFixedThreadPool(nthreads);
			for (TestScenario ts : tslist) {
				parallelExecutor.execute(new TestDriver(ts));
			}
			parallelExecutor.shutdown();
		}
		
		return parallelExecutor;
	}
	
	public static int getRunID() {
		return MainTest.runID;
	}
	
	public static String getRunLabel() {
		return MainTest.runlabel;
	}
	
	public static String getRunTags() {
		return MainTest.runtags;
	}
	
	public static boolean areTestsStopped() throws SQLException {
		Connection conn = DBConnectionPool.getConnection();
		String sql = "SELECT RUN_STATUS FROM run_status WHERE RUN_ID = " + MainTest.runID;
		boolean stop = true;
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet res = pstmt.executeQuery();
		
		if (res.next()) {
			stop = res.getString(1).equals("STOP");
		}
		
		res.close();
		pstmt.close();
		conn.close();
		
		return stop;
	}
}
