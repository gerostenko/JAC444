package jac444.wk3;
import java.util.concurrent.*;

public class WeatherQueue {
	private BlockingQueue<WeatherReading> queue;
	
	public WeatherQueue(int size) {
		this.queue = new ArrayBlockingQueue<WeatherReading>(size);
	}
	
	public void put (WeatherReading wr) throws WeatherQueueFull {
		try {
			this.queue.add(wr);
		}
		catch (IllegalStateException ex) {
			throw new WeatherQueueFull("The WeatherQueue is full", wr);
		}
	}
	
	public WeatherReading get() throws WeatherQueueEmpty {
		WeatherReading poppedObj = this.queue.poll();
		if (poppedObj == null)
			throw new WeatherQueueEmpty("No elements in the WeatherQueue!");
		return poppedObj;
	}
	
}
