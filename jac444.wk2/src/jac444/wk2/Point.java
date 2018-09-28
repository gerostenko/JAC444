package jac444.wk2;
import java.util.Objects;

/**
 * Author: Galina Erostenko
 */

public class Point {
	private double x;
	private double y;
	
	/**
	 * Default Costructor
	 */
	public Point() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructor
	 * @param x
	 * @param y
	 */
	public Point (double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return double - X
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * @return double - Y
	 */
	public double getY() {
		return y;
	}
	
	/* 
	 *  @return String - Converts object to string (.toString() overload)
	 */
	@Override
	public String toString() {
		return getClass().getName()+ "[x=" + x + ",y=" + y + "]";
	}
	
	/* 
	 * @return boolean - Compares objects, returns true if the same object or have the same x,y, equals func.overload
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (this == otherObject) return true;
		
		if (otherObject == null) return false;
		
		if (getClass() != otherObject.getClass()) return false;
		
		Point other = (Point) otherObject;
		return other.getX() == this.getX() && other.getY() == this.getY();
	}
	
	/* 
	 * @return int - return hash code of x, y, hashCode() overload
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
