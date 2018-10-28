package jac444.wk3;
import java.util.concurrent.*;

/**
 * @author Galina Erostenko
 *
 */
public class WeatherQueue {
	private BlockingQueue<WeatherReading> queue;
	
	/**
	 * WeatherQueue constructor to set queue size
	 * @param size
	 */
	public WeatherQueue(int size) {
		this.queue = new ArrayBlockingQueue<WeatherReading>(size);
	}
	
	/**
	 * put the reading to queue
	 * @param wr
	 * @throws WeatherQueueFull
	 */
	public void put (WeatherReading wr) throws WeatherQueueFull {
		try {
			this.queue.add(wr);
		}
		catch (IllegalStateException ex) {
			throw new WeatherQueueFull("The WeatherQueue is full", wr);
		}
	}
	
	/**
	 * gets the WeatherReading, pops it out of the queue and returns it
	 * @return WeatherReading
	 * @throws WeatherQueueEmpty
	 */
	public WeatherReading get() throws WeatherQueueEmpty {
		WeatherReading poppedObj = this.queue.poll();
		if (poppedObj == null)
			throw new WeatherQueueEmpty("No elements in the WeatherQueue!");
		return poppedObj;
	}
	
}
