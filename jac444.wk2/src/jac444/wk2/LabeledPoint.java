package jac444.wk2;
import java.util.Objects;

/**
 * Author: Galina Erostenko
 */

public class LabeledPoint extends Point {
	private String label;
	
	/**Constructor
	 * @param label
	 * @param x
	 * @param y
	 */
	LabeledPoint (String label, double x, double y)  {
		super(x, y);
		this.label = label;
	}
	
	/**
	 * @return String (label)
	 */
	public String getLabel() {
		return label;
	}
	
	/* 
	 * @return String - Converts object to string (.toString() overload)
	 */
	@Override
	public String toString() {
		return super.toString() + "[label=" + label + "]";
	}
	
	/* 
	 * @return boolean - Compares objects, returns true if the same object or have the same x,y,label, equals func.overload
	 */
	@Override
	public boolean equals(Object otherObject) {
		if (!super.equals(otherObject)) return false;
		LabeledPoint other = (LabeledPoint) otherObject;
		return label == other.label;
	}
	
	/* 
	 * @return int - return hash code of x, y, label, hashCode() overload
	 */
	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), label);
	}
}
