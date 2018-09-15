package jac444wk1;
import java.util.Random;
/**
 * @author Galina Erostenko
 *
 */
public class PrintRandonString {
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		long randomGeneratedLong = new Random().nextLong();
		System.out.printf("Random Generated Long in 36 base: %s", Long.toString(randomGeneratedLong, 36));
	}
}
