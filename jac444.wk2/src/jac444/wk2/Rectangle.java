package jac444.wk2;

/* 
 * Author:Galina Erostenko
 */

public class Rectangle extends Shape implements Measurable {
	private double width;
	private double height;
	
	/**Constructor
	 * @param topLeft
	 * @param width
	 * @param height
	 */
	public Rectangle (Point topLeft, double width, double height) {
		super(topLeft);
		this.width = width;
		this.height = height;
	}
	
	/* 
	 * @return Point - calculates and returns rectangle's center 
	 */
	@Override
	public Point getCentre() {
		double centerX = (super.point.getX() * 2 + width)/2;
		double centerY = (super.point.getY() * 2 + height)/2;
		return new Point(centerX, centerY);
	}
	
	/* 
	 * @return String - calculates and returns rectangle's area
	 */
	public String getArea() {
		return "Rectangle's area is " + width * height + " units squared";
	}
}
