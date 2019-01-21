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
		return pointBasDroit;
	}

	@Override
	public String toString() {
		return "[" + getType() + " " + pointBasGauche + pointBasDroit + pointHautGauche + pointHautDroit + ", " + this.couleur + "]";
	}

	protected String getType() {

		return "RECT";
	}

	public double surface() {
		return Math.abs(pointHautDroit.getX() - pointBasGauche.getX())
				* Math.abs(pointHautDroit.getY() - pointBasGauche.getY());

	}

	public Collection<Point> getPoints() {

		List<Point> l = new ArrayList<Point>();
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
		if (pointBasGauche == null) {
			if (other.pointBasGauche != null)
				return false;
		} else if (!pointBasGauche.equals(other.pointBasGauche))
			return false;
		if (pointHautDroit == null) {
			if (other.pointHautDroit != null)
				return false;
		} else if (!pointHautDroit.equals(other.pointHautDroit))
			return false;
		if (pointHautGauche == null) {
			if (other.pointHautGauche != null)
				return false;
		} else if (!pointHautGauche.equals(other.pointHautGauche))
			return false;
		if (couleur == null) {
			if (other.couleur != null)
				return false;
		} else if (!couleur.equals(other.couleur))
			return false;
		return true;
	}
}
