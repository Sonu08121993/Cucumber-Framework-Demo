package Utility;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        String path = ".\\testData\\DataDriven.xlsx";
        ExcelUtility xlutil = new ExcelUtility(path);
        int totalrows = xlutil.getRowCount("LoginTest");
        int totalcols = xlutil.getCellCount("LoginTest", 1);

        String[][] logindata = new String[totalrows][totalcols];

        for (int i = 1; i <= totalrows; i++) {
            for (int j = 0; j < totalcols; j++) {
                logindata[i - 1][j] = xlutil.getCellData("LoginTest", i, j);
            }
        }
        return logindata;
    }
}
