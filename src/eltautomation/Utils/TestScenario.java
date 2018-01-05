package eltautomation.Utils;

public class TestScenario {
	private String ID, userstoryID, pkgclassmethod, desc, appmodule,
		wbrowser, tags, classname, methodname, starturl, baseurl;
	private int storypts, complexity;
	
	public TestScenario(String scenarioID, String userstoryID, String scenariodesc,
			String pkgclassmethod, String appmodule, String wbrowser, String tags,
			String starturl, String baseurl, int storypts, int complexity) {
		
		this.initData();
		this.ID = scenarioID;
		this.userstoryID = userstoryID;
		this.desc = scenariodesc;
		this.pkgclassmethod = pkgclassmethod;
		this.appmodule = appmodule;
		this.wbrowser = wbrowser;
		this.tags = tags;
		this.storypts = storypts;
		this.complexity = complexity;
		this.starturl = starturl;
		this.baseurl = baseurl;
		this.setClassMethodName(pkgclassmethod);
	}
	
	private void initData() {
		this.ID = "";
		this.userstoryID = "";
		this.desc = "";
		this.pkgclassmethod = "";
		this.appmodule = "";
		this.wbrowser = "";
		this.tags = "";
		this.storypts = 0;
		this.complexity = 0;
		this.classname = "";
		this.methodname = "";
		this.starturl = "";
		this.baseurl = "";
	}
	
	public String getID() {
		return this.ID;
	}
	
	public String getUserStoryID() {
		return this.userstoryID;
	}
	
	public String getDescription() {
		return this.desc;
	}
	
	public String getPkgClassMethod() {
		return this.pkgclassmethod;
	}
	
	public String getAppModule() {
		return this.appmodule;
	}
	
	public String getBrowser() {
		return this.wbrowser;
	}
	
	public String getTags() {
		return this.tags;
	}
	
	public int getStoryPts() {
		return this.storypts;
	}
	
	public int getComplexity() {
		return this.complexity;
	}
	
	public String getMethodName() {
		return this.methodname;
	}
	
	public String getClassName() {
		return this.classname;
	}
	
	public String getStartURL() {
		return this.starturl;
	}
	
	public String getBaseURL() {
		return this.baseurl;
	}
	
	private void setClassMethodName(String pkgclassmethod) {
		String[] parts = pkgclassmethod.split("[.]");
		this.methodname = parts[parts.length - 1];
		
		for (int i = 0; i < (parts.length - 1); i++) {
			if (i > 0) {
				this.classname += ".";
			}
			this.classname += parts[i];
		}
	}
}
