package fr.dta.premiertp;

public class Point {

	private int x;
	private int y;

	private static final int INITX = 25;
	private static final int INITY = 25;

	public Point() {

		x = INITX;
		y = INITY;
	}

	public Point(int abs, int ord) {

		x = abs;
		y = ord;
	}

	public int getX() {

		return x;
	}

	public int getY() {

		return y;
	}

	public String toString() {

		return "[" + x + ";" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Point other = (Point) obj;
		return (x != other.x);
	}

	public double distance(Point pt) {

		return Math.sqrt(Math.pow(pt.getX() - (double) this.x, 2) + Math.pow(pt.getY() - (double) this.y, 2));
	}

	public double distanceOrigin() {

		return Math.sqrt(Math.pow(INITX - (double) this.x, 2) + Math.pow(INITY - (double) this.y, 2));
	}
}
