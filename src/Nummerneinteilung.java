public class Nummerneinteilung {
	
	private String nummer1;
	private String nummer2;
	private String nummer3;

	public String getNummer1() {
		return nummer1;
	}
	
	public void setNummer1(String nummer1) {
		this.nummer1 = nummer1;
	}
	
	public String getNummer2() {
		return nummer2;
	}
	
	public void setNummer2(String nummer2) {
		this.nummer2 = nummer2;
	}

	public String getNummer3() {
		return nummer3;
	}
	
	public void setNummer3(String nummer3) {
		this.nummer3 = nummer3;
	}

	@Override
	public String toString() {
		return "Nummerneinteilung [nummer1=" + nummer1 + ", nummer2=" + nummer2 + ", nummer3=" + nummer3 + "]";
	}
}
