package jac444.wk3;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherNetworkApp {
	//public WeatherQueue queue;
	
	//public WeatherNetworkApp() {
	//	this.queue = new WeatherQueue(9);
	//}
	
	public static void main(String[] args) {
	WeatherQueue queue = new WeatherQueue(9);
	String inputFile1 = "/home/gerostenko/workspace/jac444.wk3/files/moscow.txt";
	String inputFile2 = "/home/gerostenko/workspace/jac444.wk3/files/berlin.txt";
	String inputFile3 = "/home/gerostenko/workspace/jac444.wk3/files/paris.txt";
	Logger logger = Logger.getLogger("wk3.jac444");
	logger.setLevel(Level.FINEST);
	String loggerFile = "/home/gerostenko/workspace/jac444.wk3/files/logging.log";
	
	WeatherReporter reporter1 = new WeatherReporter(queue, inputFile1, logger, loggerFile);
	WeatherReporter reporter2 = new WeatherReporter(queue, inputFile2, logger, loggerFile);
	WeatherReporter reporter3 = new WeatherReporter(queue, inputFile3, logger, loggerFile);
	
	ExecutorService executor= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
	executor.execute(new MyRunnable(reporter1));
	executor.execute(new MyRunnable(reporter2));
	executor.execute(new MyRunnable(reporter3));
	
	executor.shutdown(); 
	try (PrintWriter writer = new PrintWriter("/home/gerostenko/workspace/jac444.wk3/files/all.txt", "UTF-8")) {
		  executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		  for(int i = 0; i < 9; i++) {
			  writer.println(queue.get().toString());
		  }
	} 
	catch (Exception ex) {
		  //whatever
	}
}
	
}

class MyRunnable implements Runnable{
    public WeatherReporter reporter;
    public MyRunnable(WeatherReporter reporter){
        this.reporter = reporter;
    }
    public void run(){
        try{
           this.reporter.reportReadings();
        }catch(Exception err){
            err.printStackTrace();
        }
    }
}
