package executionManager;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import actionKeywords.WebActionKeywords;
import configs.TestConfigs;
import dataEngine.ExcelHandler;
import utility.Log;
 
public class ExcelExecution {
	
	public static Properties OR;
	public static WebActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];
		
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String sData;
	public static boolean bResult;
	
	
	public ExcelExecution() throws NoSuchMethodException, SecurityException{
		actionKeywords = new WebActionKeywords();
		method = actionKeywords.getClass().getMethods();	
	}
	
    public static void main(String[] args) throws Exception {
		System.out.println("tests main - "+ TestConfigs.Path_TestData);
    	ExcelHandler.setExcelFile(TestConfigs.Path_TestData);
    	DOMConfigurator.configure(TestConfigs.workingDir + "\\log4j.xml");
    	String Path_OR = TestConfigs.Path_OR;
		FileInputStream fs = new FileInputStream(Path_OR);
		OR= new Properties(System.getProperties());
		OR.load(fs);

		ExcelExecution startEngine = new ExcelExecution();
		startEngine.execute_TestCase();
		
    }
		
    private void execute_TestCase() throws Exception {
	    	int iTotalTestCases = ExcelHandler.getRowCount(TestConfigs.Sheet_TestCases);
			for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
				bResult = true;
				sTestCaseID = ExcelHandler.getCellData(iTestcase, TestConfigs.Col_TestCaseID, TestConfigs.Sheet_TestCases);
				sRunMode = ExcelHandler.getCellData(iTestcase, TestConfigs.Col_RunMode, TestConfigs.Sheet_TestCases);
				if (sRunMode.equals("Yes")){
					Log.startTestCase(sTestCaseID);
					iTestStep = ExcelHandler.getRowContains(sTestCaseID, TestConfigs.Col_TestCaseID, TestConfigs.Sheet_TestSteps);
					iTestLastStep = ExcelHandler.getTestStepsCount(TestConfigs.Sheet_TestSteps, sTestCaseID, iTestStep);
					bResult=true;
					for (;iTestStep<iTestLastStep;iTestStep++){
			    		sActionKeyword = ExcelHandler.getCellData(iTestStep, TestConfigs.Col_ActionKeyword, TestConfigs.Sheet_TestSteps);
			    		sPageObject = ExcelHandler.getCellData(iTestStep, TestConfigs.Col_PageObject, TestConfigs.Sheet_TestSteps);
			    		sData = ExcelHandler.getCellData(iTestStep, TestConfigs.Col_DataSet, TestConfigs.Sheet_TestSteps);
			    		execute_Actions();
						if(bResult==false){
							ExcelHandler.setCellData(TestConfigs.KEYWORD_FAIL,iTestcase, TestConfigs.Col_Result, TestConfigs.Sheet_TestCases);
							Log.endTestCase(sTestCaseID,bResult);
							break;
							}						
						}
						if(bResult==true){
							ExcelHandler.setCellData(TestConfigs.KEYWORD_PASS,iTestcase, TestConfigs.Col_Result, TestConfigs.Sheet_TestCases);
							Log.endTestCase(sTestCaseID,bResult);
						}					
					}
				}
    		}	
     
     private static void execute_Actions() throws Exception {
	
		for(int i=0;i<method.length;i++){
			
			if(method[i].getName().equals(sActionKeyword)){
				method[i].invoke(actionKeywords,sPageObject, sData);
				if(bResult==true){
					ExcelHandler.setCellData(TestConfigs.KEYWORD_PASS, iTestStep, TestConfigs.Col_TestStepResult, TestConfigs.Sheet_TestSteps);
					break;
				}else{
					ExcelHandler.setCellData(TestConfigs.KEYWORD_FAIL, iTestStep, TestConfigs.Col_TestStepResult, TestConfigs.Sheet_TestSteps);
					WebActionKeywords.closeBrowser("","");
					break;
					}
				}
			}
     }
     
}