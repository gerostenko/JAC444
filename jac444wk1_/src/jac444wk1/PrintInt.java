package jac444wk1;
import java.util.*;

/**
 * @author Galina Erostenko
 *
 */
public class PrintInt {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		
		Integer integer;
		System.out.print("Input an Integer: ");
		Scanner S = new Scanner(System.in);
		integer = S.nextInt();
		System.out.printf("Integer in Binary: %s \n", Integer.toString(integer, 2));
		System.out.printf("Integer in Octal: %s \n", Integer.toString(integer, 8));
		System.out.printf("Integer in Hexadecimal: %s \n", Integer.toString(integer, 16));
		System.out.printf("Integer in Reciprocal in Hex: %s", Float.toHexString((float)1.00/integer));
	}
}







