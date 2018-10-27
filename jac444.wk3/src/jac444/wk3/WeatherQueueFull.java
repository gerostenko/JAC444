package jac444.wk3;

public class WeatherQueueFull extends Exception {
	private static final long serialVersionUID = -238298645120179877L; 
	public WeatherReading reading;
	    public WeatherQueueFull() {}
	    public WeatherQueueFull(String message, WeatherReading reading) {
	        super(message);
	        this.reading = reading;
	}
	public String getReading() {
		return this.reading == null ? "" : this.reading.toString();
	}
}
