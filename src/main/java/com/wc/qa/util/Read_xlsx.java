package com.wc.qa.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Read_xlsx {
	
	public String fileName;
	public FileInputStream ipstr = null;
	public FileOutputStream opitr = null;
	private XSSFWorkbook wb = null;
	private XSSFSheet ws = null;
	List<String> list = new ArrayList<String>();
	
	// This is a constructor. fileName: contains the file name of the sheet
	// relativePath: relative path of the project where the file is
	
	public Read_xlsx(String fileName, String relativePath) {
		this.fileName = fileName;
		
		try {
			ipstr = new FileInputStream(relativePath+fileName);
			wb = new XSSFWorkbook(ipstr);
			ws = wb.getSheetAt(0);
			ipstr.close();
	
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public int retrieveNoOfRows(String wsName) { // copy the method retrieveNoOfRows from DatBuilder
		int sheetIndex = wb.getSheetIndex(wsName);
		if(sheetIndex == -1)
			return 0;
		else {
			ws = wb.getSheetAt(sheetIndex);
			int rowCount = ws.getLastRowNum() + 1;
			return rowCount;
		}
		
		public int retrieveNoOfCols(String wsName) {
			int SheetIndex = wb.getSheetIndex(wsName);
			if(SheetIndex == -1)
				return 0;
			else {
				ws = wb.getSheetAt(SheetIndex);
				int colName = ws.getRow(0).getLastCellNum();
				return colName;
			}
		}
		
		public String retrieveToRunFlag(String wsName, String colName, String rowName) {
			int sheetIndex = wb.getSheetIndex(wsName);
			if(sheetIndex == -1)
				return null;
			
			else {
				int rowNum = retrieveNoOfRows(wsName);
				int colNum = retrieveNoOfCols(wsName);
				int rowNumber = -1;
				int colNumber = -1;
				
				XSSFRow suiteRow = ws.getRow(0);
				for(int i = 0; i<colNum; i++) {
					if(suiteRow.getCell(i).getStringCellValue().equals(colName.trim())) {
						colNumber = i;
						
					}
				}
				
				if (colNumber == -1) {
					return "";
				}
				
				for(int j = 0; j<rowNum; j++) {
					XSSFRow suiteCol = ws.getRow(j);
					if(suiteCol.getCell(0).getStringCellValue().equals(rowName.trim())) {
						rowNumber = j;
					}
				}
				if(rowNumber == -1) {
					return "";
				}
				
				XSSFRow row = ws.getRow(rowNumber);
				XSSFCell cell = row.getCell(colNumber);
				if(cell == null);
				return "";
			}
			
			String value = cellToString(cell);
			return value;
		}
		
	}
	
	@SuppressWarnings("deprecation")
	
	public List<String> retrieveTestData(String wsName){
		int sheeIndex = wb.getSheetIndex(wsName);
		if(sheeIndex == -1)
			return null;
		
		else {
			int rowNum = retrieveNoOfRows(wsName);
			int colNum = retrieveNoOfCols(wsName);
			
			for(int i = 0; i<rowNum; i++) {
				XSSFRow row = ws.getRow(i);
				
				for(int j = 0; j<colNum; j++){
					if(row == null) {
						list = null;
					}
					else {
						XSSFCell cell = row.getCell(j);
						if(cell == null) {
							list = null;
						}
						else {
							cell.setCellType(cell.CELL_TYPE_STRING);
							String value = cellToString(cell);
							System.out.println(value);
							list.add(value);
						}
					}
				}
			}
			return list;
		}
	}
	
	@SuppressWarnings("depcration")
	public Object[][] retrieveTestData1(String wsName) {
		int sheetIndex = wb.getSheetIndex(wsName);
		if(sheetIndex == -1)
			return null;
		
		else {
			int rowNum = retrieveNoOfRows(wsName);
			int colNum = retrieveNoOfCols(wsName);
			
			Object data[][] = new Object[rowNum -1][colNum];
			
			for(int i = 0; i<rowNum -1; i++) {
				XSSFRow row = ws.getRow(i +1);
				
				for(int j = 0; j<colNum; j++) {
					data[i][j] = "";
				}
				else {
					XSSFCell cell = row.getCell(i);
					
					if(cell == null) {
						data[i][i] = "";
					}
					
					else {
						cell.setCellType(cell.CELL_TYPE_STRING);
						String value = cellToString(cell);
						data[i][i] = value;
					}
				}
			}
		}
		
		return data;
	}

	
}

@SuppressWarnings("depcration")

public static String cellToString(XSSFCell cell) {
	
	int type;
	Object result;
	type = cell.getCellType();
	switch(type) {
	case 0:
	result = cell.getNumericCellValue();
	break;
	
	case1:
		result = cell.getStringCellValue();
	    break;
	    
	default:
		throw new RuntimeException("Unsupported cell.");
	
	}
	return result.toString();
}


}
