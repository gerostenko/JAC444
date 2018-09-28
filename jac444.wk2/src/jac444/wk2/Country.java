package jac444.wk2;

/**
 * AUthor: Galina Erostenko
 */

public class Country implements Measurable{

	private String name;
	private double area;
	
	/**Constructor
	 * @param name
	 * @param area
	 */
	public Country(String name, double area) {
		this.name = name;
		this.area = area;
	}
	
	/* 
	 * @return String (Country's area)
	 */
	public String getArea() {
		return "Area of" + name + " is " + area + "units squared";
	}
}
