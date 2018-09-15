package jac444wk1;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author Galina Erostenko
 *
 */
public class Pascal {
	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
        Scanner S = new Scanner(System.in);
        System.out.print("Input the number to create Pascal Triangle: ");
        int size = S.nextInt();
        ArrayList<ArrayList<Integer>> pascalTriangle = createPascalTriangle(size);
        for (ArrayList<Integer> row : pascalTriangle) {
            for (int num : row) {
                System.out.print(num + " ");
            }  
            System.out.println();
        }
    }
    
    /**
     * createPascalTriangle func accepts the Pascal triangle's size and returns the triangle as an array
     * @param size
     * @return
     */
    public static ArrayList<ArrayList<Integer>> createPascalTriangle(int size) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (size == 0)
            return result;
        else {
            for (int i = 0; i < size; i++) {
                ArrayList<Integer> row = new ArrayList<Integer>();
                if (i < 1) {
                    row.add(1);
                }
                if (i == 1) {
                    row.add(1);
                    row.add(1);
                }
                else if (i > 1) {
                    row.add(1);
                    ArrayList<Integer> previousRow = result.get(i-1); 
                    for (int j = 0; j < i-1; j++) {
                        row.add(previousRow.get(j) + previousRow.get(j+1));
                    }
                    row.add(1);
                }
                result.add(row);
            }
            return result;
        }
    }
}
