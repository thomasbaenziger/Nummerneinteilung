import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

public class NummerneinteilungReader extends AbstractExcelReader<Nummerneinteilung> {
	
	private static final String NUMMERNEINTEILUNG_SHEETNAME = "Nummerneinteilung";
	
	@Override
	protected String getZuLesendesExcelBlatt() {
		return NUMMERNEINTEILUNG_SHEETNAME;
		
	}
	
	@Override
	protected Nummerneinteilung mappeZeileZuObjekt(Workbook workbook, Row zeile) {
		
		Nummerneinteilung nummerneinteilung = new Nummerneinteilung();
		
		Cell nummer1Zelle = zeile.getCell(NummerneinteilungSpalten.Nummer1.spaltenindex);
		Cell nummer2Zelle = zeile.getCell(NummerneinteilungSpalten.Nummer2.spaltenindex);
		Cell nummer3Zelle = zeile.getCell(NummerneinteilungSpalten.Nummer3.spaltenindex);
		
		nummerneinteilung.setNummer1(nummer1Zelle.getStringCellValue());
		nummerneinteilung.setNummer2(nummer2Zelle.getStringCellValue());
		nummerneinteilung.setNummer3(nummer3Zelle.getStringCellValue());
		
		return nummerneinteilung;
	}
	
	private enum NummerneinteilungSpalten{
		
		Nummer1 ("A"),
		Nummer2 ("B"),
		Nummer3 ("C");
		
		private final int spaltenindex;
		NummerneinteilungSpalten(String spalte) {
			this.spaltenindex = CellReference.convertColStringToIndex(spalte);
		}
	}
}
