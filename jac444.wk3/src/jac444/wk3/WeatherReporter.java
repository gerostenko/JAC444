package jac444.wk3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherReporter {
	private WeatherQueue weatherQueue;
	private String inputFile;
	private Logger logger;
	private String loggerLocation;
	
	public WeatherReporter(WeatherQueue weatherQueue, String inputFile, Logger logger, String loggerLocation) {
		this.weatherQueue = weatherQueue;
		this.inputFile = inputFile;
		this.logger = logger;
		this.loggerLocation = loggerLocation;
	}
	
	public void reportReadings () {
		FileHandler handler;
		try {
			handler = new FileHandler(this.loggerLocation, true);
			handler.setLevel(Level.FINER);
			logger.addHandler(handler);
		}
		catch(Exception ex) {
			//do nothing
		}
		
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
			//do nothing
		}
		catch (WeatherQueueFull ex) {
			this.logger.log(Level.WARNING, ex.getMessage());
		}
	}
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
