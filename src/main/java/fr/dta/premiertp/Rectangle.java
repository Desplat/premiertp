package fr.dta.premiertp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rectangle extends Figure implements Surfacable {

	private Point pointBasGauche;
	private Point pointBasDroit;
	private Point pointHautGauche;
	private Point pointHautDroit;

	public Rectangle(Point point, int x, int y) {

		pointBasGauche = point;
		pointHautGauche = new Point(pointBasGauche.getX(), pointBasGauche.getY() + y);
		pointBasDroit = new Point(pointBasGauche.getX() + x, pointBasGauche.getY());
		pointHautDroit = new Point(pointBasGauche.getX() + x, pointBasGauche.getY() + y);
		this.couleur = Couleur.getCouleurDefaut();
	}

	public Rectangle(Point point, int x, int y, Couleur c) {

		this(point, x, y);
		this.couleur = c;
	}

	public Point getPointBasGauche() {
		return pointBasGauche;
	}

	public Point getPointBasDroit() {
		return pointBasDroit;
	}

	public Point getPointHautGauche() {
		return pointHautGauche;
	}

	public Point getPointHautDroit() {
		return pointHautDroit;
	}

	@Override
	public String toString() {
		return "[" + getType() + " " + pointBasGauche + pointBasDroit + pointHautGauche + pointHautDroit + ", "
				+ this.couleur + "]";
	}

	protected String getType() {

		return "RECT";
	}

	public double surface() {
		return Math.abs((double) pointHautDroit.getX() - (double) pointBasGauche.getX())
				* Math.abs((double) pointHautDroit.getY() - (double) pointBasGauche.getY());

	}

	public Collection<Point> getPoints() {

		List<Point> l = new ArrayList<>();
		l.add(this.pointBasGauche);
		l.add(this.pointBasDroit);
		l.add(this.pointHautGauche);
		l.add(this.pointHautDroit);

		return l;
	}

	public boolean couvre(Point pt) {

		return (pt.getX() <= pointBasDroit.getX() && pt.getX() >= pointBasGauche.getX()
				&& pt.getY() <= pointHautGauche.getY() && pt.getY() >= pointBasGauche.getY());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pointBasDroit == null) ? 0 : pointBasDroit.hashCode());
		result = prime * result + ((pointBasGauche == null) ? 0 : pointBasGauche.hashCode());
		result = prime * result + ((pointHautDroit == null) ? 0 : pointHautDroit.hashCode());
		result = prime * result + ((pointHautGauche == null) ? 0 : pointHautGauche.hashCode());
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
		Rectangle other = (Rectangle) obj;
		if (pointBasDroit == null) {
			if (other.pointBasDroit != null)
				return false;
		} else if (!pointBasDroit.equals(other.pointBasDroit))
			return false;
		if (pointHautGauche == null) {
			if (other.pointHautGauche != null)
				return false;
		} else if (!pointHautGauche.equals(other.pointHautGauche))
			return false;
		return true;
	}
}
