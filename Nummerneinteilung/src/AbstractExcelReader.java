import java.io.File;
import java.io.FileNotFoundException;

public abstract class AbstractExcelReader<T> {
	
	public static final String XLS_DATEIERWEITERUNG = "xls";
	public static final String XLSX_DATEIERWEITERUNG = "xlsx";
	
	protected abstract String getZuLesendesExcelBlatt();
	
	protected abstract T mappeZeileZuObjekt(Workbook workbook, Row zeile);
	
	public List<T> ermittleExcelZeilen(String dateipfad){
		return new ArrayList<T>();
	}	
	
	private Workbook oeffneDatei(String dateipfad) {
		File excelDatei = new File (dateipfad);
		FileInputStream excelInput = null;
		Workbook workbook = null;
		try {
			ExcelInput = new FileInputStream(excelDatei);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String excelDateiendung = FilenameUtils.getExtension(excelDatei.getName());
	}
}




https://www.youtube.com/watch?v=YBTHUFMOIwI