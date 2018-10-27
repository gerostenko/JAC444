package jac444.wk3;
import java.time.LocalDateTime;
import java.util.Objects;

public class WeatherReading {
	public final double minTemperature = -40;
	public final double maxTemperature = 40;
	public final double defaultValueForInvalidTemperature = 666;
	private double temperature;
	private String location;
	private LocalDateTime time;
	
	public WeatherReading(double temperature, String location, LocalDateTime time) {
		if (temperature < this.minTemperature || temperature > this.maxTemperature)
			this.temperature = this.defaultValueForInvalidTemperature;
		else
			this.temperature = temperature;
		this.location = location;
		if (time.isAfter(LocalDateTime.now()))
			this.time = LocalDateTime.now();
		else
			this.time = time;
	}
	
	public double getTemperature() {
		return this.temperature;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public LocalDateTime getTime() {
		return this.time;
	}
	
    @Override
    public String toString() {
        return getClass().getName() + "[Temperature=" + this.temperature + 
        		", Location=" + this.location + ", Time" + this.time + "]";
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(this.temperature, this.location, this.time);
}
}
