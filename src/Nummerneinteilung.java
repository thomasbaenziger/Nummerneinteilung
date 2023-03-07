public class Nummerneinteilung {
	
	private int nummer1;
	private int nummer2;
	private int nummer3;

	public int getNummer1() {
		return nummer1;
	}
	
	public void setNummer1(int nummer1) {
		this.nummer1 = nummer1;
	}
	
	public int getNummer2() {
		return nummer2;
	}
	
	public void setNummer2(int nummer2) {
		this.nummer2 = nummer2;
	}

	public int getNummer3() {
		return nummer3;
	}
	
	public void setNummer3(int nummer3) {
		this.nummer3 = nummer3;
	}

	@Override
	public String toString() {
		return "Nummerneinteilung [nummer1=" + nummer1 + ", nummer2=" + nummer2 + ", nummer3=" + nummer3 + "]";
	}
}
