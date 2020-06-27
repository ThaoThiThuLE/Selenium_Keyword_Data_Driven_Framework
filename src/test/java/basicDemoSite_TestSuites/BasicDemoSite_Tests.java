package basicDemoSite_TestSuites;

import configs.TestConfigs;
import dataEngine.ExcelHandler;
import executionManager.ExcelExecution;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.util.Properties;

public class BasicDemoSite_Tests {
    @Test
    public void BasicDemoExecution() throws Exception {
        ExcelHandler.setExcelFile(TestConfigs.Path_TestData);
        DOMConfigurator.configure(TestConfigs.workingDir + "\\log4j.xml");
        ExcelExecution startEngine = new ExcelExecution();
        startEngine.execute_TestCase();
    }
}
