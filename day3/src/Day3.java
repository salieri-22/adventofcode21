import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Day 3: Binary Diagnostic
 * 
 * Puzzle 1: Use the binary numbers in your diagnostic report to 
 *           calculate the gamma rate and epsilon rate, then multiply them 
 *           together. What is the power consumption of the submarine?
 */
public class Day3 {

    /**
     * Puzzle 1: Use the binary numbers in your diagnostic report to 
     * calculate the gamma rate and epsilon rate, then multiply them 
     * together. What is the power consumption of the submarine?
     * 
     * @throws FileNotFoundException
     */
    private static void Puzzle1() throws FileNotFoundException {
        ArrayList<Integer> gammaList = new ArrayList<>();
        ArrayList<Integer> epsilonList = new ArrayList<>();

        for (int j = 0; j < 12; j++) {
            File file = new File("day3\\input.txt");
            Scanner reader = new Scanner(file);

            int zeroBit = 0;
            int oneBit = 0;

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
    
                if (line.charAt(j) == '0') {
                    zeroBit++;
                } else {
                    oneBit++;
                }
            }

            if (zeroBit > oneBit) {
                gammaList.add(0);
                epsilonList.add(1);
            } else {
                gammaList.add(1);
                epsilonList.add(0);
            }

            reader.close();
        }

        String gammaBinary = "";
        String epsilonBinary = "";

        for (int i = 0; i < gammaList.size(); i++) {
            gammaBinary += gammaList.get(i);
            epsilonBinary += epsilonList.get(i);
        }

        int gammaInt = Integer.parseInt(gammaBinary, 2);
        int epsilonInt = Integer.parseInt(epsilonBinary, 2);

        int power = gammaInt * epsilonInt;

        System.out.println("The power consumption of the submarine is " + power);
    }

    /**
     * Puzzle 2: Use the binary numbers in your diagnostic report to 
     * calculate the oxygen generator rating and CO2 scrubber rating, 
     * then multiply them together. What is the life support rating of 
     * the submarine?
     * 
     * @throws FileNotFoundException
     */
    private static void Puzzle2() throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<>();

        File file = new File("day3\\input.txt");
        Scanner reader = new Scanner(file);

        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            list.add(line);
        }

        ArrayList<String> oxyNum = new ArrayList<>(list);
        ArrayList<String> co2Num = new ArrayList<>(list);

        int index = 0;

        while (oxyNum.size() != 1) {
            Iterator<String> iter = oxyNum.iterator();

            int zeroBit = 0;
            int oneBit = 0;

            for (String line : oxyNum) {
                if (line.charAt(index) == '0') {
                    zeroBit++;
                } else {
                    oneBit++;
                }
            }

            while (iter.hasNext()) {
                if (zeroBit > oneBit) {
                    if (iter.next().charAt(index) == '1') {
                        iter.remove();
                    }
                } else {
                    if (iter.next().charAt(index) == '0') {
                        iter.remove();
                    }
                }
            }

            index++;
        }

        index = 0;

        while (co2Num.size() != 1) {
            Iterator<String> iter = co2Num.iterator();

            int zeroBit = 0;
            int oneBit = 0;

            for (String line : co2Num) {
                if (line.charAt(index) == '0') {
                    zeroBit++;
                } else {
                    oneBit++;
                }
            }

            while (iter.hasNext()) {
                if (zeroBit <= oneBit) {
                    if (iter.next().charAt(index) == '1') {
                        iter.remove();
                    }
                } else if (iter.next().charAt(index) == '0'){
                    iter.remove();
                }
            }

            index++;
        }

        int oxyRating = Integer.parseInt(oxyNum.get(0), 2);
        int co2Rating = Integer.parseInt(co2Num.get(0), 2);

        System.out.println("The life support rating of the submarine is " 
                            + oxyRating * co2Rating);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Puzzle1();
        Puzzle2();
    }
}
