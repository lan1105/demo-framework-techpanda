package commons;

public class GlobalConstants {
	//System infor
	public static final String PROJECT_PATH =System.getProperty("user.dir");
	public static final String OS_NAME =System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	//App Infor
	public static final String LIVE_USER_URL ="http://live.techpanda.org/";
	public static final String LIVE_ADMIN_URL ="http://live.techpanda.org/index.php/backendlogin";
	
	public static final String ADMIN_USERNAME ="user01";
	public static final String ADMIN_PASSWORD ="guru99com";
	
	//Wait Infor
	public static final long SHORT_TIMEOUT = 10;
	public static final long LONG_TIMEOUT = 60;
	
	//Download / Upload
	public static final String UPLOAD_PATH = PROJECT_PATH + "\\uploadFiles\\";
	public static final String DOWNLOAD_PATH = PROJECT_PATH + "\\downloadFiles\\";
	
	public static final int RETRY_NUMBER = 3;
	
	//Browser Log
	public static final String BROWSER_LOG_PATH = PROJECT_PATH + "\\browserLogs\\";
	public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + "\\browserExtensions\\";
	
	//HTML Report Folder
	public static final String REPORTING_SCREENSHOT_PATH = PROJECT_PATH + "\\screenshotReportNG\\";
	public static final String EXTENT_PATH = PROJECT_PATH + "\\htmlExtent\\";
	public static final String ALLURE_PATH = PROJECT_PATH + "\\htmlAllure\\";
	

}
