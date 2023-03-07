import java.util.*;

public class ExcelStarter {
	
	public static void main(String[] args) {
		
		NummerneinteilungReader nummerneinteilungReader = new NummerneinteilungReader();
		
		List<Nummerneinteilung> listemitNummern = nummerneinteilungReader.ermittleExcelZeilen("C:\\Users\\CH00BZS\\git\\Nummerneinteilung\\src\\Nummerneinteilung.xlsx");
		
		for(Nummerneinteilung k : listemitNummern) {
			System.out.println(k);
		}
		
//		find the most matching lists
//		int MatchNumber12 = 0;
//		int MatchNumber13 = 0;
//		int MatchNumber34 = 0;
		
//		//loop the values from each row and compare them
//		for(int i = 0; i < listemitNummern.size(); i++) {
//			if(listemitNummern.get(i).getNumber1() == getNumber2()) {
//				
//				nummerneinteilung.getNummer1
//				
//				//Number 1 and Number 2 matching in first row
//				MatchNumber12++;
//			}
//		}
//	      
//	      // Ergebnis ausgeben
//	      System.out.println("Bestes Paar: " + bestesPaar);
//	      System.out.println("Größe der Schnittmenge: " + maxGroesse);		
	}
}
