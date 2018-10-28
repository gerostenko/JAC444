package jac444.wk3;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Galina Erostenko
 *
 */
public class WeatherReading {
	public final double minTemperature = -40;
	public final double maxTemperature = 40;
	public final double defaultValueForInvalidTemperature = 666;
	private double temperature;
	private String location;
	private LocalDateTime time;
	
	/**
	 * WeatherReading Constructor
	 * @param temperature
	 * @param location
	 * @param time
	 */
	public WeatherReading(double temperature, String location, LocalDateTime time) {
		//check if temperature is out of allowed boundaries and set a default value if this is the case
		if (temperature < this.minTemperature || temperature > this.maxTemperature)
			this.temperature = this.defaultValueForInvalidTemperature;
		else
			this.temperature = temperature;
		this.location = location;
		//if time is after now(), set this.time to be now()
		if (time.isAfter(LocalDateTime.now()))
			this.time = LocalDateTime.now();
		else
			this.time = time;
	}
	
	/**
	 * @return temperature
	 */
	public double getTemperature() {
		return this.temperature;
	}
	
	/**
	 * @return location
	 */
	public String getLocation() {
		return this.location;
	}
	
	/**
	 * @return time
	 */
	public LocalDateTime getTime() {
		return this.time;
	}
	
    /* 
     * toString() override
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getName() + "[Temperature=" + this.temperature + 
        		", Location=" + this.location + ", Time" + this.time + "]";
    }

    /* 
     * equals() override
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        WeatherReading other = (WeatherReading) obj;
        return this.location.equals(other.getLocation()) && 
        		this.temperature == other.getTemperature() && 
        		this.time.equals(other.getTime());
    }

    /* 
     * hashCode() override
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.temperature, this.location, this.time);
}
}
