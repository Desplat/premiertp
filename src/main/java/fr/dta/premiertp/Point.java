package fr.dta.premiertp;

public class Point {

	private int x;
	private int y;

	private final int INIT_X = 25;
	private final int INIT_Y = 25;

	public Point() {

		x = INIT_X;
		y = INIT_Y;
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

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	public double distance(Point pt) {
		
		return Math.sqrt( Math.pow(pt.getX() - this.x, 2) + Math.pow(pt.getY() - this.y, 2) );
	}
	
	public double distanceOrigin() {
		
		return Math.sqrt( Math.pow(INIT_X - this.x, 2) + Math.pow(INIT_Y - this.y, 2) );
	}
}
