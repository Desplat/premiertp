package fr.dta.premiertp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rond extends Figure implements Surfacable {

	private Point p;
	private int r;

	public Rond(Point point, int rayon) {

		p = point;
		r = rayon;
		this.couleur = Couleur.getCouleurDefaut();
	}

	public Rond(Point point, int rayon, Couleur c) {

		this(point, rayon);
		this.couleur = c;
	}
	
	public String toString() {

		return "[" + getType() + " " + p + ", " + r + ", " + this.couleur + "]";
	}

	protected String getType() {

		return "ROND";
	}

	public double surface() {

		return Math.PI * Math.pow(this.r, 2.0);
	}

	public Collection<Point> getPoints() {

		List<Point> l = new ArrayList<Point>();
		l.add(this.p);

		return l;
	}

	public boolean couvre(Point pt) {

		return this.p.distance(pt) <= this.r;
		// return Math.sqrt( Math.pow(pt.getX() - this.p.getX(), 2) + Math.pow( pt.getY() - this.p.getY(), 2) ) < this.r;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((p == null) ? 0 : p.hashCode());
		result = prime * result + r;
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
		Rond other = (Rond) obj;
		if (p == null) {
			if (other.p != null)
				return false;
		} else if (!p.equals(other.p))
			return false;
		if (r != other.r)
			return false;
		return true;
	}
}
