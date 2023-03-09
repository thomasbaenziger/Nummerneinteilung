import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelImporter {

	public static void main(String[] args) {

		try {
			// Create a File object that represents the Excel file to be imported
			File file = new File("C:\\Users\\CH00BZS\\git\\Nummerneinteilung\\src\\Nummerneinteilung.xlsx");

			// Create a FileInputStream to read the file
			FileInputStream inputStream = new FileInputStream(file);

			// Create a XSSFWorkbook object from the file input stream
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

			// Get the first sheet from the workbook
			Sheet sheet = workbook.getSheet("Tabelle1");

			// Get the number of rows and columns in the sheet
			int numRows = sheet.getLastRowNum() + 1;
			int numCols = sheet.getRow(0).getLastCellNum();

			// Create a 2-dimensional array to hold the cell values
			List<String> headers = new ArrayList<>();
			int[][] exceldata = new int[numRows - 1][numCols];

			// Loop through each row in the sheet
			// Get the headers from the excel and store them in a list
			Row headerrow = sheet.getRow(0);
			// Loop through each column in the row
			for (int j = 0; j < numCols; j++) {
				Cell cell = headerrow.getCell(j);
				// Get the cell value as a string and add it to the list
				headers.add(cell.getStringCellValue());
			}

			// Get the Excel values
			// Start with int=1 because we want to cut the headers in the excel
			for (int i = 1; i < numRows; i++) {
				Row row = sheet.getRow(i);
				// Loop through each column in the row
				for (int j = 0; j < numCols; j++) {
					Cell cell = row.getCell(j);
					// Get the cell value as a string and add it to the array
					int value = (int) cell.getNumericCellValue();
					exceldata[j][i - 1] = value;
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

			// Find the two arrays that match on the most values
			int[][] maxMatches = new int[3][3];

			for (int i = 0; i < exceldata.length; i++) {
				for (int j = i + 1; j < exceldata.length; j++) {
					int matches = countMatches(exceldata[i], exceldata[j]);

					if (matches > maxMatches[0][0]) {
						// Store the current Matches Array to an temp Array
						int[][] tempMatches = new int[3][3];
						for (int k = 0; k < maxMatches.length; k++) {
						    for (int l= 0; l < maxMatches[k].length; l++) {
						        tempMatches[k][l] = maxMatches[k][l];
						    }
						}

						// Store the amount of matches
						maxMatches[0][0] = matches;
						//Store the row and colum
						maxMatches[0][1] = i;
						maxMatches[0][2] = j;
						// Move the current number one down
						maxMatches[1] = tempMatches[0];
						maxMatches[2] = tempMatches[1];
					} else if(matches > maxMatches[1][0]) {
						// Store the current Matches Array to an temp Array
						int[][] tempMatches = new int[3][3];
						for (int k = 0; k < maxMatches.length; k++) {
						    for (int l= 0; l < maxMatches[k].length; l++) {
						        tempMatches[k][l] = maxMatches[k][l];
						    }
						}
						
						// Store the amount of matches and move the other ranks
						maxMatches[1][0] = matches;
						maxMatches[2] = tempMatches[1];
						//Store the row and colum
						maxMatches[1][1] = i;
						maxMatches[1][2] = j;
					} else if(matches > maxMatches[2][0]) {
						// Store the amount of matches
						maxMatches[2][0] = matches;
						//Store the row and colum
						maxMatches[2][1] = i;
						maxMatches[2][2] = j;
					}
				}
			}
			
			// Print the results
			System.out.println("The two arrays that match on the most values are:");
			System.out.println(headers.get(maxMatches[0][1]) + " " + headers.get(maxMatches[0][2]) + 
					"\n" + Arrays.toString(exceldata[maxMatches[0][1]]) + 
					"\n" + Arrays.toString(exceldata[maxMatches[0][2]]));
			System.out.println("Matching on " + maxMatches[0][0] + " values.");
			System.out.println("\n" + "\n");
			
			System.out.println("The two arrays that match on the secound most values are:");
			System.out.println(headers.get(maxMatches[1][1]) + " " + headers.get(maxMatches[1][2]) + 
					"\n" + Arrays.toString(exceldata[maxMatches[1][1]]) + 
					"\n" + Arrays.toString(exceldata[maxMatches[1][2]]));
			System.out.println("Matching on " + maxMatches[1][0] + " values.");
			System.out.println("\n" + "\n");
			
			System.out.println("The two arrays that match on the third most values are:");
			System.out.println(headers.get(maxMatches[2][1]) + " " + headers.get(maxMatches[2][2]) + 
					"\n" + Arrays.toString(exceldata[maxMatches[2][1]]) + 
					"\n" + Arrays.toString(exceldata[maxMatches[2][2]]));
			System.out.println("Matching on " + maxMatches[2][0] + " values.");
			System.out.println("\n" + "\n");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static int countMatches(int[] array1, int[] array2) {
		int count = 0;

		for (int value : array1) {
			if (contains(array2, value)) {
				count++;
			}
		}

		return count;
	}

	public static boolean contains(int[] array, int value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return true;
			}
		}

		return false;
	}
}
