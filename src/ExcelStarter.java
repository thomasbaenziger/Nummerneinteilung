import java.util.List;

public class ExcelStarter {
	
	public static void main(String[] args) {
		
		NummerneinteilungReader nummerneinteilungReader = new NummerneinteilungReader();
		
		List<Nummerneinteilung> listemitNummern = nummerneinteilungReader.ermittleExcelZeilen("C:\\Users\\CH00BZS\\git\\Nummerneinteilung\\src\\Nummerneinteilung.xlsx");
		
		for(Nummerneinteilung k : listemitNummern) {
			System.out.println(k);
		}
	}

}
