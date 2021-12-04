import java.util.*;
import java.io.*;

/**
 * Day 4: Giant Squid
 */
public class Day4 {

    /**
     * Puzzle 1: To guarantee victory against the giant squid, 
     * figure out which board will win first. What will your final 
     * score be if you choose that board?
     * 
     * @throws FileNotFoundException
     */
    private static void Puzzle1() throws FileNotFoundException {
        File file = new File("day4\\test.txt");
        Scanner reader = new Scanner(file);

        List<Integer> list = new ArrayList<>();

        String line = reader.nextLine();

        // Read the random numbers
        for (String s : line.split(",")) {
            list.add(Integer.parseInt(s));
        }

        List<String[][]> listOfBoards = new ArrayList<>();

        while (reader.hasNextLine()) {
            String[][] board = new String[5][5];

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    board[i][j] = reader.next();
                }

                reader.nextLine();
            }

            listOfBoards.add(board);

            if (reader.hasNextLine()) {
                reader.nextLine();
            }
        }

        reader.close();

        int winningNum = 0;
        String[][] winningBoard = null;

        for (int num : list) {
            for (int i = 0; i < listOfBoards.size(); i++) {
                String[][] board = listOfBoards.get(i);
                markX(board, num);
                enumerate(board);

                // Check if win
                if (bingo(board, num)) {
                    winningNum = num;
                    winningBoard = board;
                    break;
                } 
            }

            if (winningNum != 0) {
                break;
            }
        }

        System.out.println("Winning number: " + winningNum);

        int sum = sumBoard(winningBoard);

        System.out.println(winningNum * sum);
    }

    /**
     * Figure out which board will win last. Once it wins, what 
     * would its final score be?
     * 
     * @throws FileNotFoundException
     */
    private static void Puzzle2() throws FileNotFoundException {
        File file = new File("day4\\input.txt");
        Scanner reader = new Scanner(file);

        List<Integer> list = new ArrayList<>();

        String line = reader.nextLine();

        // Read the random numbers
        for (String s : line.split(",")) {
            list.add(Integer.parseInt(s));
        }

        List<String[][]> listOfBoards = new ArrayList<>();

        while (reader.hasNextLine()) {
            String[][] board = new String[5][5];

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    board[i][j] = reader.next();
                }

                reader.nextLine();
            }

            listOfBoards.add(board);

            if (reader.hasNextLine()) {
                reader.nextLine();
            }
        }

        reader.close();

        int winningCount = 0;
        int winningNum = 0;
        String[][] winningBoard = null;

        for (int num : list) {
            Iterator<String[][]> iter = listOfBoards.iterator();

            while (iter.hasNext()) {
                String[][] board = iter.next();
                markX(board, num);
                enumerate(board);

                if (bingo(board, num)) {
                    winningCount++;
                    winningNum = num;
                    winningBoard = board;
                    iter.remove();
                } 
            }

            if (winningCount == 3) {
                break;
            }
        }

        System.out.println("Winning number: " + winningNum);

        int sum = sumBoard(winningBoard);

        System.out.println(winningNum * sum);
    }

    private static int sumBoard(String[][] board) {
        int sum = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!board[i][j].equals("-")) {
                    sum += Integer.parseInt(board[i][j]);
                }
            }
        }

        return sum;
    }
    
    private static boolean bingo(String[][] board, int num) {
        boolean bingo = false;

        for (int i = 0; i < 5; i++) {
            String res = "";

            res += board[i][0] + board[i][1] + board[i][2] + board[i][3] + board[i][4];

            if (res.equals("-----")) {
                bingo = true;
                break;
            }
        }

        for (int i = 0; i < 5; i++) {
            String res = "";

            res += board[0][i] + board[1][i] + board[2][i] + board[3][i] + board[4][i];

            if (res.equals("-----")) {
                bingo = true;
                break;
            }
        }

        return bingo;
    }

    private static void markX(String[][] board, int num) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String elem = board[i][j];

                if (elem != "-") {
                    if (Integer.parseInt(elem) == num) {
                        board[i][j] = "-";
                    }
                }
            }
        }
    }

    private static void enumerate(String[][] board) {
        System.out.println();
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(board[i][j] + "\t");
            }

            System.out.println();
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        Puzzle1();
        Puzzle2();
    }
}