package fr.dta.premiertp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Segment extends Figure {

	private Point debut;
	private Point fin;

	public Segment(Point point, int longueur, boolean horizontal){
		
		this.debut = point;
		if(horizontal) {
			this.fin = new Point(debut.getX() + longueur, debut.getY());
		}
		else {
			this.fin = new Point(debut.getX(), debut.getY() + longueur);
		}
		
		this.couleur = Couleur.getCouleurDefaut();
	}
	
	public Segment(Point point, int longueur, boolean horizontal, Couleur c){
		
		this(point, longueur, horizontal);
		this.couleur = c;
	}

	public String toString() {
		return "[" + this.getType() + " " + debut + " � " + fin + ", " + this.couleur + "]";
	}

	protected String getType() {
		return "SEGMENT";
	}

	public Collection<Point> getPoints() {
		
		List<Point> l = new ArrayList<>();
		l.add(this.debut);
		l.add(this.fin);
		
		return l;
	}
	
	public boolean couvre(Point pt) {
		
		return (pt.getX() >= this.debut.getX() && pt.getX() <= fin.getX() && pt.getY() == this.debut.getY()
				|| pt.getY() >= this.debut.getY() && pt.getY() <= fin.getY() && pt.getX() == this.debut.getX());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((debut == null) ? 0 : debut.hashCode());
		result = prime * result + ((fin == null) ? 0 : fin.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Segment other = (Segment) obj;
		if (debut == null) {
			if (other.debut != null)
				return false;
		} else if (!debut.equals(other.debut))
			return false;
		if (fin == null) {
			if (other.fin != null)
				return false;
		} else if (!fin.equals(other.fin))
			return false;
		return true;
	}
}
