import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImporter {

	public static void main(String[] args) {

		try {
			// Create a File object that represents the Excel file to be imported
			File file = new File("C:\\Users\\CH00BZS\\git\\Nummerneinteilung\\src\\Nummerneinteilung_23_24.xlsx");

			// Create a FileInputStream to read the file
			FileInputStream inputStream = new FileInputStream(file);

			// Create a XSSFWorkbook object from the file input stream
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			

			//**************************************************************************//
			//Create a sheet for the NLB numbers											//
			//**************************************************************************//
			
			// Get the first sheet from the workbook
			Sheet sheetNLB = workbook.getSheet("NummernNLB");

			// Get the number of rows and columns in the sheet
			int numRowsNLB = sheetNLB.getLastRowNum() + 1;
			int numColsNLB = sheetNLB.getRow(0).getLastCellNum();
			
			// Create a 2-dimensional array to hold the cell values
			List<String> headersNLB = new ArrayList<>();
			int[][] exceldataNLB = new int[numRowsNLB][numColsNLB-1];

			// Loop through each row in the sheet
			// Get the headers from the excel and store them in a list
			Row headerrowNLB = sheetNLB.getRow(0);
			// Loop through each column in the row
			for (int j = 0; j < numColsNLB; j++) {
				Cell cell = headerrowNLB.getCell(j);
				// Get the cell value as a string and add it to the list
				headersNLB.add(cell.getStringCellValue());
			}

			// Get the Excel values
			// Start with int=1 because we want to cut the headers in the excel
			for (int i = 1; i < numRowsNLB; i++) {
				Row row = sheetNLB.getRow(i);
				// Loop through each column in the row
				for (int j = 0; j < numColsNLB; j++) {
					Cell cell = row.getCell(j);
					// Get the cell value as a string and add it to the array
					int value = (int) cell.getNumericCellValue();
					exceldataNLB[j][i - 1] = value;
				}
			}
			
			//**************************************************************************//
			//Create a sheet for the 1L numbers											//
			//**************************************************************************//
			
			// Get the first sheet from the workbook
			Sheet sheet1L = workbook.getSheet("Nummern1L");

			// Get the number of rows and columns in the sheet
			int numRows1L = sheet1L.getLastRowNum() + 1;
			int numCols1L = sheet1L.getRow(0).getLastCellNum();			
			
			// Create a 2-dimensional array to hold the cell values
			List<String> headers1L = new ArrayList<>();
			int[][] exceldata1L = new int[numRows1L][numCols1L-1];

			// Loop through each row in the sheet
			// Get the headers from the excel and store them in a list
			Row headerrow1L = sheet1L.getRow(0);
			// Loop through each column in the row
			for (int j = 0; j < numCols1L; j++) {
				Cell cell = headerrow1L.getCell(j);
				// Get the cell value as a string and add it to the list
				headers1L.add(cell.getStringCellValue());
			}

			// Get the Excel values
			// Start with int=1 because we want to cut the headers in the excel
			for (int i = 1; i < numRows1L; i++) {
				Row row = sheet1L.getRow(i);
				// Loop through each column in the row
				for (int j = 0; j < numCols1L; j++) {
					Cell cell = row.getCell(j);
					// Get the cell value as a string and add it to the array
					int value = (int) cell.getNumericCellValue();
					exceldata1L[j][i - 1] = value;
				}
			}


			// Close the input stream and workbook objects to free up resources
			inputStream.close();
			workbook.close();

			// Print the Array
//			for (int[] row : exceldata) {
//				for (int value : row) {
//					System.out.print(value + "\t");
//				}
//				System.out.println();
//			}

			// Find the list combinations
			int amountofMatches = 15;
			int[][] maxMatches = new int[amountofMatches][3];

			for (int i = 0; i < exceldataNLB.length; i++) {
				for (int j = 0; j < exceldata1L.length; j++) {
					int matches = CountMatches.countMatches(exceldataNLB[i], exceldata1L[j]);

					// Store the matches to the maxMatches Array
					maxMatches = StoreMatchingNumbers.storeMatchingNumbers(maxMatches, matches, i, j, amountofMatches );						

				}
			}
			
			// Print the results
			ResultPrinter.resultPrinter(headersNLB, headers1L, exceldataNLB, exceldata1L, maxMatches);	
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

