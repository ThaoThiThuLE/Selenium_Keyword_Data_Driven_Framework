package configs;

/**
 * Config values
 * @author thao.le
 *
 */
public class TestConfigs {
	
	//System Variables
	public static final String URL = "https://msrobotassistant.com/login";
	public static String workingDir=System.getProperty("user.dir");
	public static final String Path_TestData = workingDir + "//src//test//resources//testData//DataEngine.xlsx";
	public static final String Path_OR = workingDir + "//src//test//resources//Locators//OR.txt";
	public static final String File_TestData = "DataEngine.xlsx";
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";
	
	//Data Sheet Column Numbers
	public static final int Col_TestCaseID = 0;	
	public static final int Col_TestScenarioID =1 ;
	public static final int Col_PageObject =4 ;
	public static final int Col_ActionKeyword =5 ;
	public static final int Col_RunMode =2 ;
	public static final int Col_Result =3 ;
	public static final int Col_DataSet =6 ;
	public static final int Col_TestStepResult =7 ;
		
	// Data Engine Excel sheets
	public static final String Sheet_TestSteps = "Test Steps";
	public static final String Sheet_TestCases = "Test Cases";
	
	// Test Data
	public static final String UserName = "test";
	public static final String Password = "123456";


	

}
