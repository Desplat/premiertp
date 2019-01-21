package fr.dta.premiertp;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {

		List<Integer> number = new ArrayList<>();

		number.add(10);
		number.add(10);
		number.add(10);
		number.add(10);
		number.add(10);
		number.add(10);
		number.add(10);
		number.add(100);

		number.stream().filter(o -> o == 10).forEach(System.out::println);
		
		LOG.trace("{}", number.stream().filter(i -> i != null).reduce((n1, n2) -> n1 + n2).orElse(null));
		LOG.trace(number.stream().filter(i -> i != null).map(i -> i.toString())
				.reduce((n1, n2) -> n1 + " " + n2).orElse(null));

		LocalTime time = LocalTime.now();
		LocalDate quatre = LocalDate.now();
		quatre = quatre.plusDays(4);
		LocalDate noel = LocalDate.of(2018, 12, 25);

		DateTimeFormatter formatHeure = DateTimeFormatter.ofPattern("HH:mm:ss");
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMMM yyyy");

		String heure = time.format(formatHeure);
		String date = noel.format(formatDate);
		String date1 = quatre.format(formatDate);

		LOG.trace(heure);
		LOG.trace(date);
		LOG.trace(date1);

		/*
		 * ############################################################# Le point
		 * ##############################################################
		 *
		 * 
		 * Point p1 = new Point(0, 0); Point p2 = new Point(2, 0);
		 * 
		 * System.out.println("P1 : " + p1.toString());
		 * 
		 * /* ############################################################# Le rond
		 * ##############################################################
		 *
		 * 
		 * Rond r1 = new Rond(p1, 3);
		 * 
		 * r1.affiche();
		 * 
		 * /* ############################################################# Le rectangle
		 * ##############################################################
		 *
		 * 
		 * Rectangle rect1 = new Rectangle(p1, 4, 3);
		 * 
		 * rect1.affiche();
		 * 
		 * /* ############################################################# Le segment
		 * ##############################################################
		 *
		 * 
		 * Segment s1 = new Segment(p1, 5, true);
		 * 
		 * s1.affiche();
		 * 
		 * System.out.println(r1.couvre(p2)); System.out.println(rect1.couvre(p2));
		 * System.out.println(s1.couvre(p2));
		 * 
		 * /* ############################################################# Rond et
		 * Rectangle random
		 * ##############################################################
		 * 
		 * /*Rond r2 = FigureUtil.getRandomRond(); Rectangle rect2 =
		 * FigureUtil.getRandomRectangle();
		 * 
		 * r2.affiche(); rect2.affiche();
		 * 
		 * /*############################################################# Le carr�
		 * ##############################################################
		 * 
		 * Carre c = new Carre(p1, 5); c.affiche();
		 */

		/*
		 * ############################################################# Figure random
		 * ##############################################################
		 * 
		 * System.out.println("\nDix figures random\n");
		 * 
		 * Figure[] figures = new Figure[10]; for(int i = 0; i<10; i++) {
		 * 
		 * figures[i] = FigureUtil.getRandomFigure();
		 * System.out.println(figures[i].getType()); }
		 * 
		 * System.out.println("\n"); System.out.println(FigureUtil.getPoints(figures));
		 * 
		 * System.out.println(FigureUtil.genere(20));
		 */

		Dessin dessin = new Dessin();
		for (Figure f : FigureUtil.genere(10)) {
			dessin.addFigure(f);
		}

		try {
			FigureUtil.sauvegarde(dessin);
			FigureUtil.charge();

		} catch (IOException e) {
			LOG.trace(e.getMessage());
		}

		try {
			FigureUtil.impression();
		} catch (ImpressionHorsLimiteException e) {

			LOG.trace("Exception : " + e.getMessage());
		}

		Collection<Figure> ss = FigureUtil.trieProcheOrigine(dessin);

		for (Figure fbs : ss) {
			// System.out.println("surface : " + ((Surfacable) fbs).surface());
			LOG.trace("Figure : " + fbs.toString());
			fbs.affiche();
		}

	}
}
