package jac444.wk2;

/**
 * Author: Galina Erostenko
 */

public class Circle extends Shape implements Measurable {
	private double radius;
	
	/**Constructor
	 * @param center
	 * @param radius
	 */
	Circle (Point center, double radius) {
		super(center);
		this.radius = radius;
	}
	
	/* 
	 * @return Point (Cirle's center)
	 */
	@Override
	public Point getCentre() {
		return super.point;
	}
	
	/* 
	 * @return String (Circle's area calculated)
	 */
	public String getArea() {
		double area = Math.PI * radius * radius;
		return "Circle's area is " + area + " units squared";
	}
	
}
