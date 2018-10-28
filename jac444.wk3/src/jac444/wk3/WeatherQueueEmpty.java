package jac444.wk3;

/**
 * @author Galina Erostenko
 *
 */
public class WeatherQueueEmpty extends Exception{
	private static final long serialVersionUID = 6812653397174173825L;  
	/**
	 * WeatherQueueEmpty default constructor
	 */
	public WeatherQueueEmpty() {}
    /**
     * WeatherQueueEmpty constructor to set message
     * @param message
     */
    public WeatherQueueEmpty(String message) {
        super(message);
}
}
