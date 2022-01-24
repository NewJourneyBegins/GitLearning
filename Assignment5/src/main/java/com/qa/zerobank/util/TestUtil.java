package com.qa.zerobank.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.zerobank.base.TestBase;

public class TestUtil extends TestBase {
	
	static JavascriptExecutor js;
	Connection con;
	
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+
			"\\src\\test\\resources\\com\\qa\\zerobank\\testdata\\" + 
			prop.getProperty("testdata");
	
	static Workbook book;
	static Sheet sheet;
			
	public static void takeScreenshotAtEndOfTest(String pageName) {
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String homeDir = System.getProperty("user.dir");
		try {
			FileUtils.copyFile(scrFile, new File(homeDir +"/screenshots/"+pageName + System.currentTimeMillis()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		
		public static Object[][] getTestData(String sheetName){
			
			FileInputStream file = null;
			
			try {
				file = new FileInputStream(TESTDATA_SHEET_PATH);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				book = WorkbookFactory.create(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			sheet = book.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			int columnCount = sheet.getRow(0).getLastCellNum();
			
			Object[][] data = new Object[rowCount][columnCount];
			
			//System.out.println(Sheet.getLastRowNum() + "--------------" +
			//sheet.getRow(0).getLastCellNum());
			
			for(int i = 0; i<sheet.getLastRowNum();i++) {
				for(int k = 0; k < sheet.getRow(0).getLastCellNum();k++) {
					data[i][k] = sheet.getRow(i +1).getCell(k).toString();//converting in to string as we have to access it and its coming in object form , object to string.
					//System.out.println(data[i][k]);
				}
			}
			return data;
		}
}
