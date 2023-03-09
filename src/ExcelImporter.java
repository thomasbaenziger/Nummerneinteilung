import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

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
      int[][] exceldata = new int[numRows-1][numCols];
      
      // Loop through each row in the sheet
      // Start with int=1 because we want to cut the headers in the excel
      for (int i = 1; i < numRows; i++) {
        Row row = sheet.getRow(i);
        // Loop through each column in the row
        for (int j = 0; j < numCols; j++) {
          Cell cell = row.getCell(j);
          // Get the cell value as a string and add it to the array
          int value = (int)cell.getNumericCellValue();
          exceldata[j][i-1] = value;
        }
      }
      
      // Close the input stream and workbook objects to free up resources
      inputStream.close();
      workbook.close();
      
      // Print the Array
//      for (int[] row : exceldata) {
//        for (int value : row) {
//          System.out.print(value + "\t");
//        }
//        System.out.println();
//      }
      
    	    
      // Find the two arrays that match on the most values
      int maxMatches = 0;
      int[] bestMatch1 = null;
      int[] bestMatch2 = null;
    
      for (int i = 0; i < exceldata.length; i++) {
    	  for (int j = i + 1; j < exceldata.length; j++) {
    		  int matches = countMatches(exceldata[i], exceldata[j]);
	        
    		  if (matches > maxMatches) {
    			  maxMatches = matches;
    			  bestMatch1 = exceldata[i];
    			  bestMatch2 = exceldata[j];
    		  }
    	  }
      }
    
      // Print the results
      System.out.println("The two arrays that match on the most values are:");
      System.out.println(Arrays.toString(bestMatch1));
      System.out.println(Arrays.toString(bestMatch2));

      
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

