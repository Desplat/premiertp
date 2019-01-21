package fr.dta.premiertp;

import java.util.ArrayList;
import java.util.Collection;

public class Dessin {

	Collection<Figure> liste;
	
	public Dessin() {
		this.liste = new ArrayList<>();
	}
	
	public boolean addFigure(Figure f) {
		
		return this.liste.add(f);
	}
	
	public Collection<Figure> getFigures(){
		
		return this.liste;
	}
}
