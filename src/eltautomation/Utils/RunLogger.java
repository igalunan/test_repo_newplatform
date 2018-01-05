package eltautomation.Utils;

import java.io.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;

import java.time.*;

public class RunLogger {	
	private DateTimeFormatter dtf;
	
	private int runID;
	private String runlabel, runtags = "DEV";
	
	private int runscenarioID, storypts, complexity;
	private String scenarioID, scenariodesc, pkgclassmethod, appmodule, wbrowser;
	
	private String stepdesc, expected, actual, stepmark;
	private long filesize, filesize2;
	private FileInputStream fstream, fstream2;
	
	public static final String PASS = "PASSED";
	public static final String FAIL = "FAILED";
	public static final String ERR = "ERROR";
	
	private void reset() {
		this.dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		this.runscenarioID = 0;
		this.runID = 0;
		this.filesize = 0;
		this.filesize2 = 0;
	}
	
	public RunLogger(String runlabel) {
		this.reset();
		this.runlabel = runlabel;
	}
	
	public RunLogger(String runlabel, String runtags) {
		this(runlabel);
		this.runtags = runtags;
	}
	
	public RunLogger(int runID, String runlabel) {
		this(runlabel);
		this.runID = runID;
	}
	
	public RunLogger(int runID, String runlabel, String runtags) {
		this(runlabel, runtags);
		this.runID = runID;
	}
	
	public void logStartRun() throws SQLException {
		LocalDateTime now = LocalDateTime.now();
		String sql = "INSERT INTO run_instance(RUN_LABEL, RUN_TAGS, START_DATETIME) VALUES(?,?,'" + this.dtf.format(now) + "')";
		Connection conn = DBConnectionPool.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, this.runlabel);
		pstmt.setString(2, this.runtags);
		
		int affectedrows = pstmt.executeUpdate();
		
		if (affectedrows == 0) {
			throw new SQLException("Run instance not inserted.");
		} else {
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	this.runID = (int) generatedKeys.getLong(1);
	            } else {
	                throw new SQLException("Run instance ID not obtained.");
	            }
	        } catch (SQLException e) {
				throw e;
			} finally {
				pstmt.close();
				conn.close();
				this.logRunning();
			}
		}
	}
	
	private void logRunning() throws SQLException {
		String sql = "REPLACE INTO run_status(RUN_ID, RUN_STATUS, RUN_LABEL) VALUES(?,?,?)";
		Connection conn = DBConnectionPool.getConnection();
		int affectedrows = 0;
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, this.runID);
		pstmt.setString(2, "RUNNING");
		pstmt.setString(3, this.runlabel);
		
		try {
			affectedrows = pstmt.executeUpdate();
		} catch (SQLException e) {
		}
		
		if (affectedrows == 0) {
			throw new SQLException("Run status not inserted.");
		}
		
		pstmt.close();
		conn.close();
	}
	
	public void logScenario() throws SQLException {
		LocalDateTime now = LocalDateTime.now();
		String sql = "INSERT INTO run_scenarios(RUN_ID, SCENARIO_ID, PKG_CLASS_METHOD, SCENARIO_DESC, " +
				"APP_MODULE, STORY_PTS, RUN_COMPLEXITY, W_BROWSER, START_DATETIME) " +
				"VALUES(?,?,?,?,?," + this.storypts + "," + this.complexity + ",?,'" + this.dtf.format(now) + "')";
		Connection conn = DBConnectionPool.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, this.runID);
		pstmt.setString(2, this.scenarioID);
		pstmt.setString(3, this.pkgclassmethod);
		pstmt.setString(4, this.scenariodesc);
		pstmt.setString(5, this.appmodule);
		pstmt.setString(6, this.wbrowser);
		
		int affectedrows = pstmt.executeUpdate();
		
		if (affectedrows == 0) {
			throw new SQLException("Run scenario not inserted.");
		} else {
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	this.runscenarioID = (int) generatedKeys.getLong(1);
	            } else {
	            	throw new SQLException("Run scenario ID not obtained.");
	            }
	        } catch (SQLException e) {
				throw e;
			} finally {
				pstmt.close();
				conn.close();
			}
		}
	}
	
	public void logUpdateScenario() throws SQLException {
		if (this.runscenarioID == 0) {
			return;
		}
		
		LocalDateTime now, startscenario, tempdt;
		int hours, mins, secs;
		
		now = LocalDateTime.now();
		String newmark = this.getScenarioMark();
		startscenario = this.getScenarioStartTime();
		
		tempdt = LocalDateTime.from(startscenario);
		hours = (int)tempdt.until(now, ChronoUnit.HOURS);
		tempdt = tempdt.plusHours(hours);
		mins = (int)tempdt.until(now, ChronoUnit.MINUTES);
		tempdt = tempdt.plusMinutes(mins);
		secs = (int)tempdt.until(now, ChronoUnit.SECONDS);
		
		String sql = "UPDATE run_scenarios SET SCENARIO_MARK = ?, END_DATETIME = '" + this.dtf.format(now) +
				"', TIME_ELAPSED = '" + hours + ":" + mins + ":" + secs + "' WHERE RUN_SCENARIO_ID = ?";
		Connection conn = DBConnectionPool.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, newmark);
		pstmt.setInt(2, this.runscenarioID);
		
		if (!pstmt.execute()) {
			System.out.println("logUpdateScenario not successful.");
		}
		
		pstmt.close();
		conn.close();
		
		this.logUpdateRun(now);
	}
	
	private void logUpdateRun(LocalDateTime now) throws SQLException {
		LocalDateTime startrun, tempdt;
		int hours, mins, secs;
		
		startrun = this.getRunStartTime();
		
		tempdt = LocalDateTime.from(startrun);
		hours = (int)tempdt.until(now, ChronoUnit.HOURS);
		tempdt = tempdt.plusHours(hours);
		mins = (int)tempdt.until(now, ChronoUnit.MINUTES);
		tempdt = tempdt.plusMinutes(mins);
		secs = (int)tempdt.until(now, ChronoUnit.SECONDS);
		
		String sql = "UPDATE run_instance SET END_DATETIME = '" + this.dtf.format(now) +
				"', TIME_ELAPSED = '" + hours + ":" + mins + ":" + secs + "' WHERE RUN_ID = ?";
		Connection conn = DBConnectionPool.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, this.runID);
		
		if (!pstmt.execute()) {
			System.out.println("logUpdateRun not successful.");
		}
		
		pstmt.close();
		conn.close();
	}
	
	private String getScenarioMark() throws SQLException {
		String stepmark = "PASSED";
		String sql = "SELECT COUNT(RUN_STEP_ID) FROM run_steps WHERE RUN_SCENARIO_ID = ? AND STEP_MARK = 'FAILED' OR STEP_MARK = 'ERROR'";
		Connection conn = DBConnectionPool.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, this.runscenarioID);
		ResultSet res = pstmt.executeQuery();
		
		res.next();
		if (res.getInt(1) > 0) {
			stepmark = "FAILED";
		}
		
		res.close();
		pstmt.close();
		conn.close();
		
		return stepmark;
	}
	
	private LocalDateTime getScenarioStartTime() throws SQLException {
		String sql = "SELECT START_DATETIME FROM run_scenarios WHERE RUN_SCENARIO_ID = ?";
		Connection conn = DBConnectionPool.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, this.runscenarioID);
		ResultSet res = pstmt.executeQuery();
		
		res.next();
		LocalDateTime ldt = LocalDateTime.from(this.dtf.parse(res.getString(1).substring(0, 19)));
		
		res.close();
		pstmt.close();
		conn.close();
		
		return ldt;
	}
	
	private LocalDateTime getRunStartTime() throws SQLException {
		String sql = "SELECT START_DATETIME FROM run_instance WHERE RUN_ID = ?";
		Connection conn = DBConnectionPool.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, this.runID);
		ResultSet res = pstmt.executeQuery();
		
		res.next();
		LocalDateTime ldt = LocalDateTime.from(this.dtf.parse(res.getString(1).substring(0, 19)));
		
		res.close();
		pstmt.close();
		conn.close();
		
		return ldt;
	}
	
	public void logScenario(String scenarioID, String desc) throws SQLException {
		this.setScenarioID(scenarioID);
		this.setScenarioDesc(desc);
		
		this.logScenario();
	}
	
	public void setScenarioID(String scenarioID) {
		this.scenarioID = scenarioID;
	}
	
	public void setScenarioDesc(String desc) {
		this.scenariodesc = desc;
	}
	
	public void setScenarioPkgClassMethod(String pkgclassmethod) {
		this.pkgclassmethod = pkgclassmethod;
	}
	
	public String getPkgClassMethod() {
		return this.pkgclassmethod;
	}
	
	public void setScenarioAppModule(String appmodule) {
		this.appmodule = appmodule;
	}
	
	public void setScenarioStoryPts(int storypts) {
		this.storypts = storypts;
	}
	
	public void setScenarioComplexity(int complexity) {
		this.complexity = complexity;
	}
	
	public void setBrowser(String wbrowser) {
		this.wbrowser = wbrowser;
	}
	
	public String getBrowser() {
		return this.wbrowser;
	}
	
	public void logStep() throws SQLException, IOException {
		LocalDateTime now = LocalDateTime.now();
		String sql = "INSERT INTO run_steps(RUN_SCENARIO_ID, STEP_DESC, EXPECTED_RESULT, " +
				"ACTUAL_RESULT, STEP_MARK, STEP_SCREEN1, STEP_SCREEN2, END_DATETIME) VALUES(?,?,?,?,?,?,?,'" + this.dtf.format(now) + "')";
		Connection conn = DBConnectionPool.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, this.runscenarioID);
		pstmt.setString(2, this.stepdesc);
		pstmt.setString(3, this.expected);
		pstmt.setString(4, this.actual);
		pstmt.setString(5, this.stepmark);
		
		if (this.filesize > 0 || this.fstream != null) {
			pstmt.setBinaryStream(6, this.fstream, this.filesize);
		} else {
			pstmt.setNull(6, Types.BLOB);
		}
		
		if (this.filesize2 > 0 || this.fstream2 != null) {
			pstmt.setBinaryStream(7, this.fstream2, this.filesize2);
		} else {
			pstmt.setNull(7, Types.BLOB);
		}
		
		int affectedrows = pstmt.executeUpdate();
		
		if (affectedrows == 0) {
			throw new SQLException("Run step not inserted.");
		}
		
		pstmt.close();
		conn.close();
		
		this.clearPreStepScreenshot();
		this.clearPostStepScreenshot();
	}
	
	public void logStep(String desc, String expected, String actual, String stepmark) throws SQLException, IOException {
		this.setStepDesc(desc);
		this.setExpectedResult(expected);
		this.setActualResult(actual);
		this.setStepMark(stepmark);
		this.clearPreStepScreenshot();
		this.clearPostStepScreenshot();
		
		this.logStep();
	}
	
	public void logStep(String desc, String expected, String actual, String stepmark, WebDriver poststepimg) throws SQLException, IOException {
		this.setStepDesc(desc);
		this.setExpectedResult(expected);
		this.setActualResult(actual);
		this.setStepMark(stepmark);
		this.setPostStepScreenshot(poststepimg);
		
		this.logStep();
	}
	
	public void setStepDesc(String desc) {
		this.stepdesc = desc;
	}
	
	public void setExpectedResult(String expected) {
		this.expected = expected;
	}
	
	public void setActualResult(String actual) {
		this.actual = actual;
	}
	
	public void setStepMark(String stepmark) {
		this.stepmark = this.correctMark(stepmark);
	}
	
	public void setPreStepScreenshot(WebDriver prestepimg) throws IOException {
		File img = (File) ((TakesScreenshot) prestepimg).getScreenshotAs(OutputType.FILE);
		this.filesize = img.length();
		this.fstream = new FileInputStream(img);
	}
	
	public void setPostStepScreenshot(WebDriver poststepimg) throws IOException {
		File img = (File) ((TakesScreenshot) poststepimg).getScreenshotAs(OutputType.FILE);
		this.filesize2 = img.length();
		this.fstream2 = new FileInputStream(img);
	}
	
	public void clearPreStepScreenshot() throws IOException {
		if (this.filesize > 0 || this.fstream != null) {
			this.fstream.close();
			this.fstream = null;
			this.filesize = 0;
		}
	}
	
	public void clearPostStepScreenshot() throws IOException {
		if (this.filesize2 > 0 || this.fstream2 != null) {
			this.fstream2.close();
			this.fstream2 = null;
			this.filesize2 = 0;
		}
	}
	
	private String correctMark(String mark) {
		String newmark = "";
		CharSequence cs = "pa", cs2 = "er";
		
		newmark = mark.toLowerCase().contains(cs) ? RunLogger.PASS : RunLogger.FAIL;
		newmark = mark.toLowerCase().contains(cs2) ? RunLogger.ERR : newmark;
		
		return newmark;
	}
	
	public int getRunID() {
		return this.runID;
	}
}