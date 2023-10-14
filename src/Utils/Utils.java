package Utils;

public class Utils {

    // Player X nilainya 1 di board,
    // Player Y nilainya -1.
    public static int evaluateBoard(int[][] board) {
        int sum = 0;

        for (int[] ints : board) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }

        return sum;
    }

    public static int countPlayers(int[][] board) {
        int count = 0;
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt != 0) {
                    count += 1;
                }
            }
        }
        return count;
    }

    public static boolean isTerminal(int maxPiece, int[][] board) {
        return countPlayers(board) == maxPiece + 8;
    }

}
