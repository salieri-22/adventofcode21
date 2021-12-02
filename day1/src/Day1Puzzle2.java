import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Puzzle 2: Count the number of times the sum of measurements in this sliding window increases from the previous sum.
 * 
 * @author Alyssa Motas
 */
public class Day1Puzzle2 {
    public static void main(String[] args) throws FileNotFoundException {
        // Read from file
        File file = new File("day1\\input.txt");
        Scanner reader = new Scanner(file);

        // Create a list of integers and a variable to keep track of the counter
        ArrayList<Integer> depthMeasurementList = new ArrayList<>();
        int count = 0;

        ArrayList<Integer> sums = new ArrayList<>();

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            int firstValue = Integer.parseInt(line);

            depthMeasurementList.add(firstValue);

            // Check if the list is not empty and has more than 2 elements
            if (!depthMeasurementList.isEmpty() && depthMeasurementList.size() > 2) {

                // Get the two previous values and sum them
                int secondValue = depthMeasurementList.get(depthMeasurementList.size() - 2);
                int thirdValue = depthMeasurementList.get(depthMeasurementList.size() - 3);
                int sumOfValues = firstValue + secondValue + thirdValue;

                sums.add(sumOfValues);

                // Check if the sums of values is not empty and has more than 1 element
                if (!sums.isEmpty() && sums.size() > 1) {
                    int previousValue = sums.get(sums.size() - 2);

                    if (previousValue < sumOfValues) {
                        count++;
                    }
                }
            }
        }

        reader.close();

        System.out.print("The number of times the sum of measurements in this sliding window increases from the previous sum is " + count);
    }
}
