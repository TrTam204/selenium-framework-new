package tests;

import framework.utils.ExcelReader;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;
import org.testng.ITestContext;

import java.io.FileInputStream;
import java.util.*;

public class DataProviderClass {

    @DataProvider(name = "loginData")
    public Object[][] getData(ITestContext context) throws Exception {

        String group = context.getIncludedGroups().length > 0 
                       ? context.getIncludedGroups()[0] 
                       : "regression";

        Workbook wb = WorkbookFactory.create(
            new FileInputStream("src/test/resources/data/login_data.xlsx")
        );

        List<Object[]> data = new ArrayList<>();

        for (Sheet sheet : wb) {

            // ?? CH? L?Y SHEET PHÙ H?P
            if (group.equals("smoke") && !sheet.getSheetName().equals("SmokeCases")) {
                continue;
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                data.add(new Object[]{
                    ExcelReader.getCellValue(row.getCell(0)),
                    ExcelReader.getCellValue(row.getCell(1)),
                    ExcelReader.getCellValue(row.getCell(2)),
                    ExcelReader.getCellValue(row.getCell(3))
                });
            }
        }

        return data.toArray(new Object[0][]);
    }
}
