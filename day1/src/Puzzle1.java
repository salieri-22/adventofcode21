import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Puzzle 1: Count the number of times a depth measurement increases from the previous measurement
 * 
 * @author Alyssa Motas
 */
public class Puzzle1 {
    public static void main(String[] args) throws FileNotFoundException {

        // Read from file
        File file = new File("day1\\input1.txt");
        Scanner reader = new Scanner(file);

        // Create a list of integers and a variable to keep track of the counter
        ArrayList<Integer> depthMeasurementList = new ArrayList<>();
        int count = 0;

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            int value = Integer.parseInt(line);

            depthMeasurementList.add(value);

            // Check if the list is not empty and has more than 1 element
            if (!depthMeasurementList.isEmpty() && depthMeasurementList.size() > 1) {
                int previousValue = depthMeasurementList.get(depthMeasurementList.size() - 2);

                if (previousValue < value) {
                    count++;
                }
            }
        }

        reader.close();

        System.out.print("The number of times a depth measurement increases from the previous measurement is: " + count);
    }
}