import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Day 2 Puzzles: What do you get if you multiply your final horizontal position by your final depth?
 * 
 * @author Alyssa Motas - B00858853
 */
public class Day2 {
    private static void Puzzle1() throws FileNotFoundException {
        File file = new File("day2\\input.txt");
        Scanner reader = new Scanner(file);

        int depth = 0;
        int horizontalPos = 0;

        while (reader.hasNextLine()) {
        
            // down and up affects depth
            // forward affects horizontal position
            String direction = reader.next();
            int value = reader.nextInt();

            if (direction.equals("up")) {
                depth = depth - value;
            } else if (direction.equals("down")) {
                depth = depth + value;
            } else if (direction.equals("forward")) {
                horizontalPos = horizontalPos + value;
            }

        }

        reader.close();

        System.out.println(depth * horizontalPos);
    }

    private static void Puzzle2() throws FileNotFoundException {
        File file = new File("day2\\input.txt");
        Scanner reader = new Scanner(file);

        int depth = 0;
        int horizontalPos = 0;
        int aim = 0;

        while (reader.hasNextLine()) {
            // down increases aim by X units 
            // up decreases aim by X units
            // foward increases horizontalPos by X units and increases depth by aim * X

            String direction = reader.next();
            int value = reader.nextInt();

            if (direction.equals("up")) {
                aim = aim - value;
            } else if (direction.equals("down")) {
                aim = aim + value;
            } else if (direction.equals("forward")) {
                horizontalPos = horizontalPos + value;
                depth = depth + (aim * value);
            }
        }

        reader.close();

        System.out.println(horizontalPos * depth);
    }
    public static void main(String[] args) throws FileNotFoundException {
        Puzzle1();
        Puzzle2();
    }
}
