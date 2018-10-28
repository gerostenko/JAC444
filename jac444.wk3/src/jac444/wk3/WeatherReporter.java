package jac444.wk3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Galina Erostenko
 *
 */
public class WeatherReporter {
	private WeatherQueue weatherQueue;
	private String inputFile;
	private Logger logger;
	private String loggerLocation;
	
	/**
	 * WeatherReporter Constructor
	 * @param weatherQueue
	 * @param inputFile
	 * @param logger
	 * @param loggerLocation
	 */
	public WeatherReporter(WeatherQueue weatherQueue, String inputFile, Logger logger, String loggerLocation) {
		this.weatherQueue = weatherQueue;
		this.inputFile = inputFile;
		this.logger = logger;
		this.loggerLocation = loggerLocation;
	}
	
	/**
	 * Reads the WeatherReadings from a file, put each of them in the queue
	 */
	public void reportReadings () {
		//create file handler, set handler level to finest
		FileHandler handler;
		try {
			handler = new FileHandler(this.loggerLocation, true);
			handler.setLevel(Level.FINER);
			logger.addHandler(handler);
		}
		//catch all
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		//create buffered reader, until reach EoF (readline == null), read each line separating data with comma delimiter
		//convert the datetime and the temperature into respective data types, put the reading in the queue
		//handle possible exceptions
		try (BufferedReader in = new BufferedReader(new FileReader(this.inputFile))) {
			String str;
			while ((str = in.readLine())!= null) {
				String[] data = str.split(",");
                LocalDateTime dateTime = LocalDateTime.from(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").parse(data[0]));
                WeatherReading reading = new WeatherReading(Double.parseDouble(data[1]), data[2], dateTime);
                this.weatherQueue.put(reading);
                this.logger.log(Level.FINER,"WeatherReading added to the WeatherQueue");
			}
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		catch (WeatherQueueFull ex) {
			this.logger.log(Level.WARNING, ex.getMessage(), ex.getReading());
		}
	}
    /**
     * prints the reading in the console, removing them from the queue, here for the testing purposes
     */
    public void printReadings() {
        for (int i = 0; i < 10; i++) {
            try {
                WeatherReading reading = weatherQueue.get();
                System.out.println(reading.toString());
            } catch (WeatherQueueEmpty e) {
                this.logger.log(Level.SEVERE, e.getMessage());
            }
        }
}
}
