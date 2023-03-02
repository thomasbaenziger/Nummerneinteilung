import java.io.*;
import java.util.*;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class AbstractExcelReader<T> {
	
	public static final String XLS_DATEIERWEITERUNG = "xls";
	public static final String XLSX_DATEIERWEITERUNG = "xlsx";
	
	protected abstract String getZuLesendesExcelBlatt();
	
	protected abstract T mappeZeileZuObjekt(Workbook workbook, Row zeile);
	
	public List<T> ermittleExcelZeilen(String dateipfad){
		
		List<T> excelZeilen = new ArrayList<>();
		Workbook workbook = oeffneDatei(dateipfad);
		Sheet sheet = workbook.getSheet(getZuLesendesExcelBlatt());
		
		Iterator<Row> iterator = sheet.iterator();
		//erste Zeile ignorieren, da Überschrift
		Row inhaltZeilen = iterator.next();
		
		while(iterator.hasNext()) {
			inhaltZeilen = iterator.next();
			T importDaten = mappeZeileZuObjekt(workbook, inhaltZeilen);
			if(importDaten != null) {
				excelZeilen.add(importDaten);
			}
		}
		return excelZeilen;
	}	
	
	private Workbook oeffneDatei(String dateipfad) {
		File excelDatei = new File (dateipfad);
		FileInputStream excelInput = null;
		Workbook workbook = null;
		try {
			excelInput = new FileInputStream(excelDatei);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String excelDateiendung = FilenameUtils.getExtension(excelDatei.getName());
		try {
			if(XLS_DATEIERWEITERUNG.equalsIgnoreCase(excelDateiendung)) {
				workbook = new HSSFWorkbook(excelInput);
			}
			if(XLSX_DATEIERWEITERUNG.equalsIgnoreCase(excelDateiendung)) {
				workbook = new XSSFWorkbook(excelInput);
			}
		} catch (IOException e) {
			
			e.printStackTrace();		
		}
		return workbook;
	}
}
