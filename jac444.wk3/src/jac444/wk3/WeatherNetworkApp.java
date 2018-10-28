package jac444.wk3;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Galina Erostenko
 */
public class WeatherNetworkApp {
	//the queue to hold the WeatherReadings
	private WeatherQueue queue;
	//number of reporters correspond to the number of files, add more reporters if needed
	private WeatherReporter reporter1;
	private WeatherReporter reporter2;
	private WeatherReporter reporter3;
	//logger for logging events while writing to the 
	private Logger logger = Logger.getLogger("wk3.jac444");
	private String masterOutput;
	
	/**
	 * WeatherNetworkApp Constructor
	 * @param queueSize
	 * @param masterOutput
	 */
	public WeatherNetworkApp(int queueSize, String masterOutput) {
		this.queue = new WeatherQueue(queueSize); 
		this.masterOutput = masterOutput;
	}
	
	/**
	 * runWeatherNetworkApp() reads WeatherReading from specified files (concurrently) and put them in WeatherQueue, 
	 * set the loggers, output all the WeatherReadings to the file all.txt
	 */
	public void runWeatherNetworkApp() {
		//set the full paths to the files, add more files here if needed
		String inputFile1 = "moscow.txt";
		String inputFile2 = "berlin.txt";
		String inputFile3 = "paris.txt";
		//set logger level to Finest
		this.logger.setLevel(Level.FINEST);
		String loggerFile = "logging.log";
		//create WeatherReporters per file
		this.reporter1 = new WeatherReporter(this.queue, inputFile1, this.logger, loggerFile);
		this.reporter2 = new WeatherReporter(this.queue, inputFile2, this.logger, loggerFile);
		this.reporter3 = new WeatherReporter(this.queue, inputFile3, this.logger, loggerFile);
		
		//concurrently create MyRunnables
		//I've used ExecutorService here to do the multi-threading
		//more about it here: https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html
		ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		executor.execute(new MyRunnable(reporter1));
		executor.execute(new MyRunnable(reporter2));
		executor.execute(new MyRunnable(reporter3));
		
		//in order to join() the threads, we do the shutdown() and then, within try, awaitTermination(), more about it
		//here https://stackoverflow.com/questions/1250643/how-to-wait-for-all-threads-to-finish-using-executorservice
		executor.shutdown(); 
		try (PrintWriter writer = new PrintWriter(this.masterOutput, "UTF-8")) {
			  executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			  //output each WeatherReading to the all.txt
			  for(int i = 0; i < 9; i++) {
				  writer.println(queue.get().toString());
			  }
		} 
		//catch all possible exceptions here
		catch (Exception ex) {
			  ex.printStackTrace();
		}
	}
	
	/**
	 * Main, test here
	 */
	public static void main(String[] args) {
		WeatherNetworkApp app = new WeatherNetworkApp(9, "all.txt");
		app.runWeatherNetworkApp();
	}
}

/**
 * MyRunnable defined to be able to run reportReadings() concurrently
 */
class MyRunnable implements Runnable{
    public WeatherReporter reporter;
    /**
     * MyRunnable Constructor
     * @param reporter
     */
    public MyRunnable(WeatherReporter reporter){
        this.reporter = reporter;
    }
    /* implement run(), which invokes reportReadings()
     * @see java.lang.Runnable#run()
     */
    public void run(){
        try{
           this.reporter.reportReadings();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
