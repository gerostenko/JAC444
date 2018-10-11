package jac444.wk3;

public class WeatherQueueEmpty extends Exception{
	private static final long serialVersionUID = 6812653397174173825L;  //IDE suggestion
	public WeatherQueueEmpty() {}
    public WeatherQueueEmpty(String message) {
        super(message);
}
}
