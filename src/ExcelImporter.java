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
			File file = new File("C:\\Users\\CH00BZS\\git\\Nummerneinteilung\\src\\Nummerneinteilung_22_23.xlsx");

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
			int[][] maxMatches = new int[5][5];

			for (int i = 0; i < exceldataNLB.length; i++) {
				for (int j = 0; j < exceldata1L.length; j++) {
					int matches = countMatches(exceldataNLB[i], exceldata1L[j]);

					if (matches > maxMatches[0][0]) {
						// Store the current Matches Array to an temp Array
						int[][] tempMatches = new int[5][5];
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
						maxMatches[3] = tempMatches[2];
						maxMatches[4] = tempMatches[3];
						
					} else if(matches > maxMatches[1][0]) {
						// Store the current Matches Array to an temp Array
						int[][] tempMatches = new int[5][5];
						for (int k = 0; k < maxMatches.length; k++) {
						    for (int l= 0; l < maxMatches[k].length; l++) {
						        tempMatches[k][l] = maxMatches[k][l];
						    }
						}
						
						// Store the amount of matches
						maxMatches[1][0] = matches;
						
						//Store the row and colum
						maxMatches[1][1] = i;
						maxMatches[1][2] = j;
						
						// Move the other ranks
						maxMatches[2] = tempMatches[1];
						maxMatches[3] = tempMatches[2];
						maxMatches[4] = tempMatches[3];
						
					} else if(matches > maxMatches[2][0]) {
						// Store the current Matches Array to an temp Array
						int[][] tempMatches = new int[5][5];
						for (int k = 0; k < maxMatches.length; k++) {
						    for (int l= 0; l < maxMatches[k].length; l++) {
						        tempMatches[k][l] = maxMatches[k][l];
						    }
						}

						// Store the amount of matches
						maxMatches[2][0] = matches;
						
						//Store the row and colum
						maxMatches[2][1] = i;
						maxMatches[2][2] = j;
						
						// Move the other ranks
						maxMatches[3] = tempMatches[2];
						maxMatches[4] = tempMatches[3];
						
					}else if(matches > maxMatches[3][0]) {
						// Store the current Matches Array to an temp Array
						int[][] tempMatches = new int[5][5];
						for (int k = 0; k < maxMatches.length; k++) {
						    for (int l= 0; l < maxMatches[k].length; l++) {
						        tempMatches[k][l] = maxMatches[k][l];
						    }
						}

						// Store the amount of matches
						maxMatches[3][0] = matches;
						
						//Store the row and colum
						maxMatches[3][1] = i;
						maxMatches[3][2] = j;
						
						// Move the other ranks
						maxMatches[4] = tempMatches[3];
						
					} else if(matches > maxMatches[4][0]) {
						
						// Store the amount of matches
						maxMatches[4][0] = matches;
						
						//Store the row and colum
						maxMatches[4][1] = i;
						maxMatches[4][2] = j;
					}
				}
			}
			
			// Print the results
			System.out.println("The two arrays that match on the most values are:");
			System.out.println(headersNLB.get(maxMatches[0][1]) + " " + headers1L.get(maxMatches[0][2]) + 
					"\n" + Arrays.toString(exceldataNLB[maxMatches[0][1]]) + 
					"\n" + Arrays.toString(exceldata1L[maxMatches[0][2]]));
			System.out.println("Matching on " + maxMatches[0][0] + " values.");
			System.out.println("\n" + "\n");
			
			System.out.println("The two arrays that match on the 2. most values are:");
			System.out.println(headersNLB.get(maxMatches[1][1]) + " " + headers1L.get(maxMatches[1][2]) + 
					"\n" + Arrays.toString(exceldataNLB[maxMatches[1][1]]) + 
					"\n" + Arrays.toString(exceldata1L[maxMatches[1][2]]));
			System.out.println("Matching on " + maxMatches[1][0] + " values.");
			System.out.println("\n" + "\n");
			
			System.out.println("The two arrays that match on the 3. most values are:");
			System.out.println(headersNLB.get(maxMatches[2][1]) + " " + headers1L.get(maxMatches[2][2]) + 
					"\n" + Arrays.toString(exceldataNLB[maxMatches[2][1]]) + 
					"\n" + Arrays.toString(exceldata1L[maxMatches[2][2]]));
			System.out.println("Matching on " + maxMatches[2][0] + " values.");
			System.out.println("\n" + "\n");

			System.out.println("The two arrays that match on the 4. most values are:");
			System.out.println(headersNLB.get(maxMatches[3][1]) + " " + headers1L.get(maxMatches[3][2]) + 
					"\n" + Arrays.toString(exceldataNLB[maxMatches[3][1]]) + 
					"\n" + Arrays.toString(exceldata1L[maxMatches[3][2]]));
			System.out.println("Matching on " + maxMatches[3][0] + " values.");
			System.out.println("\n" + "\n");
			
			System.out.println("The two arrays that match on the 5. most values are:");
			System.out.println(headersNLB.get(maxMatches[4][1]) + " " + headers1L.get(maxMatches[4][2]) + 
					"\n" + Arrays.toString(exceldataNLB[maxMatches[4][1]]) + 
					"\n" + Arrays.toString(exceldata1L[maxMatches[4][2]]));
			System.out.println("Matching on " + maxMatches[4][0] + " values.");
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
