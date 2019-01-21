package fr.dta.premiertp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Figure implements Comparable<Figure>{
	
	protected Couleur couleur;
	
	public abstract String toString();
	protected abstract String getType();
	public abstract Collection<Point> getPoints();
	public abstract boolean couvre(Point pt);
	public abstract boolean equals(Object obj);
	
	private static int cle = 0;

	public void affiche() {
		
		System.out.println(toString());
	}
	
	public double distanceOrigin() {
		
		List<Double> list = new ArrayList<>();
		
		for(Point p : this.getPoints()) {
			list.add(p.distanceOrigin());
		}
		return Collections.min(list);
	}
	
	@Override
	public int compareTo(Figure o) {
		if (this.distanceOrigin() > o.distanceOrigin()) {
			return 1;
		} else if (this.distanceOrigin() < o.distanceOrigin()) {
			return -1;
		}
		return 0;
	}
	
	public String getId() {
		
		cle++;
		return this.getType() + cle;
	}
	
	public Couleur getCouleur() {
		
		return this.couleur;
	}
}
