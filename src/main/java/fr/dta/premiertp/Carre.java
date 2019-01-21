package fr.dta.premiertp;

public class Carre extends Rectangle {

	public Carre(Point point, int cote) {

		super(point, cote, cote);
	}

	
	public Carre(Point point, int cote, Couleur c) {

		super(point, cote, cote, c);
		
	}

	protected String getType() {

		return "CARRE";
	}

	/*
	 * @Override public String toString() {
	 * 
	 * return "CARRE [" + getPointBasGauche() + "][" + getPointBasDroit() + "][" +
	 * getPointHautGauche() + "][" + getPointHautDroit() + "]"; }
	 * 
	 * public void affiche() {
	 * 
	 * System.out.println(toString()); }
	 */
}
