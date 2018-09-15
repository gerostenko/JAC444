package jac444wk1;
import java.util.*;

/**
 * @author Galina Erostenko
 *
 */
public class IsSquare {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner S = new Scanner(System.in);
		ArrayList<ArrayList<Integer>> twoDArray = new ArrayList<ArrayList<Integer>>();
		
		String readLine = S.nextLine();
		while(!readLine.isEmpty()) {
			String[] intsAsString = readLine.split(" ");
			ArrayList<Integer> row = new ArrayList<Integer>();
			for(String item : intsAsString) {
				row.add(Integer.valueOf(item));
			}
			twoDArray.add(new ArrayList<Integer>(row));
			readLine = S.nextLine();
		}
		
		if(isMagicSquare(twoDArray) == true) 
			System.out.print("Magic Square!");
		else {
			System.out.print("Not a Magic Square!");
		}
	}
	/**
	 * isMagicSquare func calculates the sum of all fields, rows, diagonals, returns true if they are the same, false otherwise
	 * @param myArray
	 * @return
	 */
	public static boolean isMagicSquare(ArrayList<ArrayList<Integer>> myArray) {
		int numberOfRows = myArray.size();
		boolean proceedWithCalcs = true;
		for(int i = 0; i < numberOfRows; i++) {
			if(numberOfRows != myArray.get(i).size()) {
				proceedWithCalcs = false;
				break;
			}
		}
		
		if(proceedWithCalcs == true) {
			ArrayList<Integer> sumOfAllRows = new ArrayList<Integer>();
			ArrayList<Integer> sumOfAllFields = new ArrayList<Integer>();
			int sumFirstDiagonal = 0;
			int sumSecondDiagonal = 0;
			int secondArrayIndex = numberOfRows-1;
			for(int i = 0; i < numberOfRows; i++) {
				int sumPerRaw = 0;
				int sumPerField = 0;
				for(int j = 0; j < numberOfRows; j++) {
					sumPerRaw += myArray.get(i).get(j);
					sumPerField += myArray.get(j).get(i);   
					
				}
				sumOfAllRows.add(sumPerRaw);
				sumOfAllFields.add(sumPerField);
				sumFirstDiagonal += myArray.get(i).get(i);
				sumSecondDiagonal += myArray.get(i).get(secondArrayIndex--);
			}
			if (sumOfAllRows.equals(sumOfAllFields) && sumOfAllRows.get(0) == sumFirstDiagonal
					&& sumFirstDiagonal == sumSecondDiagonal) {
				return true;
			}
		}
		return false;
	}
}
