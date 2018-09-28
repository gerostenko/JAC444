package jac444.wk2;

/**
 * Author: Galina Erostenko 
 */

public abstract class Shape {
	protected Point point;
	
	/**
	 * Default Constructor
	 */
	public Shape() {
		this.point = new Point();
	}
	
	/**Constructor
	 * @param point
	 */
	public Shape(Point point) {
		this.point = point;
	}
	
	/**moves the point's x by dx and y by dy
	 * @param dx
	 * @param dy
	 */
	public void moveBy (double dx, double dy) {
		double newX = this.point.getX() + dx;
		double newY = this.point.getY() + dy;
		this.point = new Point(newX, newY);
	}
	
	/**
	 * @return Point - subclasses must implement this method
	 */
	public abstract Point getCentre();
}
