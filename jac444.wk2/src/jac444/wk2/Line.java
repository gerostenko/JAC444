package jac444.wk2;

/**
 * Author: Galina Erostenko
 */

public class Line extends Shape implements Measurable {
	private Point from;
	private Point to;
	
	/**Constructor
	 * @param from
	 * @param to
	 */
	public Line (Point from, Point to) {
		super(null);
		this.from = from;
		this.to = to;
	}
	
	/* 
	 * @return Point - line's center
	 */
	@Override
	public Point getCentre() {
		double centerX = (this.from.getX() + this.to.getX())/2;
		double centerY = (this.from.getY() + this.to.getY())/2;
		return new Point(centerX, centerY);
	}
	
	/* 
	 * @return String - calculate the distance between two points, which is also an area
	 */
	public String getArea() {
		double area = Math.sqrt(Math.pow((to.getX() - from.getX()), 2) + Math.pow((to.getY() - from.getY()), 2));
		return "Area of the line is " + area + " units square";
	}
}
