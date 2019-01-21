package fr.dta.premiertp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class FigureUtil {

	private static final int MIN = 0;
	private static final int MAX = 99;

	private static Map<String, Figure> map = new HashMap<String, Figure>();

	private static Point getRandomPoint() {

		return new Point(ThreadLocalRandom.current().nextInt(MIN, MAX), ThreadLocalRandom.current().nextInt(200));
	}

	public static Rond getRandomRond() {

		Rond rond = new Rond(getRandomPoint(), ThreadLocalRandom.current().nextInt(MIN, MAX),
				Couleur.getRandomCouleur());
		map.put(rond.getId(), rond);
		return rond;

	}

	public static Rectangle getRandomRectangle() {

		Rectangle rect = new Rectangle(getRandomPoint(), ThreadLocalRandom.current().nextInt(MIN, MAX),
				ThreadLocalRandom.current().nextInt(MIN, MAX), Couleur.getRandomCouleur());
		map.put(rect.getId(), rect);
		return rect;
	}

	public static Rectangle getRandomCarre() {
		Carre carre = new Carre(getRandomPoint(), ThreadLocalRandom.current().nextInt(MIN, MAX),
				Couleur.getRandomCouleur());
		map.put(carre.getId(), carre);
		return carre;
	}

	public static Segment getRandomSegment() {

		Segment segment = new Segment(getRandomPoint(), ThreadLocalRandom.current().nextInt(MIN, MAX),
				ThreadLocalRandom.current().nextBoolean(), Couleur.getRandomCouleur());
		map.put(segment.getId(), segment);
		return segment;
	}

	public static Figure getRandomFigure() {

		int x = ThreadLocalRandom.current().nextInt(4);

		switch (x) {
		case 0:
			return getRandomRond();

		case 1:
			return getRandomRectangle();

		case 2:
			return getRandomCarre();

		case 3:
		default:
			return getRandomSegment();
		}
	}

	public static Surfacable getRandomSurfacable() {

		int x = ThreadLocalRandom.current().nextInt(3);

		switch (x) {
		case 0:
			return getRandomRond();

		case 1:
			return getRandomRectangle();

		case 2:
		default:
			return getRandomCarre();
		}
	}

	public static Collection<Point> getPoints(Figure... f) {

		List<Point> l = new ArrayList<Point>();

		for (int i = 0; i < f.length; i++) {

			l.addAll(f[i].getPoints());
		}

		return l;
	}

	public static Collection<Figure> genere(int x) {

		List<Figure> l = new ArrayList<Figure>();

		for (int i = 0; i < x; i++) {

			l.add(getRandomFigure());
		}

		return l;
	}

	public static Figure getFigureEn(Point pt, Dessin s) {

		Iterator<Figure> iter = s.getFigures().iterator();

		while (iter.hasNext()) {

			Figure f = iter.next();

			if (f.couvre(pt)) {
				return f;
			}
		}

		return null;
	}

	public static Collection<Figure> trieProcheOrigine(Dessin dessin) {

		List<Figure> figures = new ArrayList<>(dessin.getFigures());
		return figures.stream().sorted().collect(Collectors.toList());
		// Collections.sort(figures);
	}

	public static Collection<Figure> triSurface(Dessin dessin) {

		return dessin.getFigures().stream().filter(f -> f instanceof Surfacable).sorted((Figure o1, Figure o2) -> {
			Surfacable s1 = (Surfacable) o1;
			Surfacable s2 = (Surfacable) o2;
			if (s1.surface() > s2.surface()) {
				return 1;
			}
			if (s1.surface() < s2.surface()) {
				return -1;
			}
			return 0;
		}).collect(Collectors.toList());

//		List<Figure> figures = dessin.getFigures().stream().filter(f -> f instanceof Surfacable)
//				.collect(Collectors.toList());
//
//		Collections.sort(figures, new Comparator<Figure>() {
//			@Override
//			public int compare(Figure o1, Figure o2) {
//				Surfacable s1 = (Surfacable) o1;
//				Surfacable s2 = (Surfacable) o2;
//				if (s1.surface() > s2.surface()) {
//					return 1;
//				}
//				if (s1.surface() < s2.surface()) {
//					return -1;
//				}
//				return 0;
//			}
//
//		});
//
//		return figures;
	}

	public static Figure get(String id) {

		return map.get(id);
	}

	public static void afficheMap() {

		System.out.println(FigureUtil.map);
	}

	public static void impression() throws ImpressionHorsLimiteException {

		for (Map.Entry<String, Figure> item : map.entrySet()) {
			for (Point pt : item.getValue().getPoints()) {

				if (pt.getX() < MIN || pt.getX() > MAX || pt.getY() < MIN || pt.getY() > MAX) {

					throw new ImpressionHorsLimiteException(
							"Hors limite " + item.getKey() + " " + pt.getX() + " " + pt.getY());
				}
			}
		}
	}

	public static void stockTab(Figure fig, char[][] tab) {

		for (Point pt : fig.getPoints()) {
			if (!(pt.getX() < MIN || pt.getX() > MAX || pt.getY() < MIN || pt.getY() > MAX))

				tab[pt.getX()][pt.getY()] = fig.getCouleur().getCode();
		}
	}

	public static String imprime(Dessin dessin) {

		StringBuilder str = new StringBuilder();

		for (Figure fig : dessin.getFigures()) {

			str.append(fig.toString() + System.lineSeparator());
		}

		str.append(
				"===================================================================================================="
						+ System.lineSeparator());

		char[][] tab = new char[100][100];

		for (int i = 0; i < tab.length; i++) {
			for (int j = 0; j < tab[i].length; j++) {

				tab[i][j] = ' ';
			}
		}

		for (Figure fig : dessin.getFigures()) {

			stockTab(fig, tab);
		}

		for (int k = 0; k < tab.length; k++) {

			str.append(tab[k]);
			str.append(System.lineSeparator());
		}

		return str.toString();
	}

	public static void sauvegarde(Dessin dessin) throws IOException {

		File fichier = new File("C:\\Users\\formation\\Desktop\\TP10\\dessin_save.txt");
		try (FileWriter writer = new FileWriter(fichier)) {
			// BufferedWriter bf = new BufferedWriter(writer);
			// PrintWriter pw = new PrintWriter(bf);

			writer.write(imprime(dessin));
			writer.close();
		}
	}

	public static void charge() throws IOException {

		File fichier = new File("C:\\Users\\formation\\Desktop\\TP10\\dessin_save.txt");
		FileReader reader = new FileReader(fichier);
		try (BufferedReader bf = new BufferedReader(reader)) {

			Optional<String> ligne;
			boolean findSeparator = false;
			while (true) {

				ligne = Optional.ofNullable(bf.readLine());

				if (!ligne.isPresent())
					break;

				if (ligne.get().startsWith("=")) {
					findSeparator = true;
					continue;
				}
				if (findSeparator) {
					for (int i = 0; i < 99; i++) {
						System.out.print(ligne.get().charAt(i));
					}
					System.out.println("");
				}
			}
			bf.close();
		}
	}
}
