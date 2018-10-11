package jac444.wk3;

public class WeatherQueueFull extends Exception {
	private static final long serialVersionUID = -238298645120179877L; //IDE suggestion
	public WeatherReading reading;
	    public WeatherQueueFull() {}
	    public WeatherQueueFull(String message, WeatherReading reading) {
	        super(message);
	        this.reading = reading;
	}
}
