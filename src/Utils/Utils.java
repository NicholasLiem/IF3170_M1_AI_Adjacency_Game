package Utils;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    // Player X nilainya 1 di board,
    // Player Y nilainya -1.
    public static int evaluateBoard(int[][] board) {
        int sum = 0;
//        Utils.printBoard(board);
        for (int[] ints : board) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }

        return sum;
    }

    public static int[][] copyBoard(int[][] board) {
        int numRows = board.length;
        int numCols = board[0].length;
        int[][] copy = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                copy[i][j] = board[i][j];
            }
        }

        return copy;
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

    public static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static boolean isTerminal(int maxPiece, int[][] board) {
        return countPlayers(board) == maxPiece + 8;
    }

    public static List<int[]> getPossibleMoves(int[][] board) {
        List<int[]> possibleMoves = new ArrayList<>();

        // Define the possible adjacent directions (up, down, left, right)
        int[] dx = {-1, 1, 0, 0,1,-1,1,-1};
        int[] dy = {0, 0, -1, 1,1,-1,-1,1};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    // Check adjacent cells
                    for (int k = 0; k < 8; k++) {
                        int newRow = i + dx[k];
                        int newCol = j + dy[k];

                        // Check if the adjacent cell is within the board bounds
                        if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[i].length) {
                            if (board[newRow][newCol] != 0) {
                                // Add the empty cell as a possible move
                                possibleMoves.add(new int[]{i, j});
                                break;  // No need to check other adjacent cells
                            }
                        }
                    }
                }
            }
        }

        return possibleMoves;
    }

    public static int[][] transition(int[][] board, int[] move, boolean max) {

        // Fill the specified move with 1 or -1 based on the 'max' parameter
        int fillValue = max ? 1 : -1;
        int row = move[0];
        int col = move[1];

        board[row][col] = fillValue;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < dx.length; i++) {
            int newRow = row + dx[i];
            int newCol = col + dy[i];

            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[row].length) {
                board[newRow][newCol] = fillValue;
            }
        }

        return board;
    }

}
