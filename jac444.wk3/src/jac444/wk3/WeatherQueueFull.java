package jac444.wk3;

/**
 * @author Galina Erostenko
 *
 */
public class WeatherQueueFull extends Exception {
	private static final long serialVersionUID = -238298645120179877L; 
	public WeatherReading reading;
	    /**
	     * WeatherQueueFull Constructor
	     */
	    public WeatherQueueFull() {}
	    /**
	     * WeatherQueueFull Constructor to set the reading and message
	     * @param message
	     * @param reading
	     */
	    public WeatherQueueFull(String message, WeatherReading reading) {
	        super(message);
	        this.reading = reading;
	}
	/**
	 * @return reading
	 */
	public String getReading() {
		return this.reading == null ? "" : this.reading.toString();
	}
}
