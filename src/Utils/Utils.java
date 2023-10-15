package Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {
    public static int firstMove = 1;

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

    public static int[][] copyBoard(int[][] board) {
        int numRows = board.length;
        int numCols = board[0].length;
        int[][] copy = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, numCols);
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


    public static List<int[]> getPossibleMoves(int[][] board, boolean isMax, int topK) {

        List<int[]> possibleMoves = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    int heuristicValue = calculateHeuristic(board, i, j, isMax);
                    possibleMoves.add(new int[]{i, j, heuristicValue});
                }
            }
        }

        possibleMoves.sort((a, b) -> Integer.compare(b[2], a[2]));

        List<int[]> sortedMoves = possibleMoves.stream()
                .limit(topK)
                .map(move -> new int[]{move[0], move[1]})
                .collect(Collectors.toList());


        return sortedMoves;
    }

    private static int calculateHeuristic(int[][] board, int row, int col, boolean isMax) {
        int heuristicValue = 0;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int k = 0; k < 4; k++) {
            int newRow = row + dx[k];
            int newCol = col + dy[k];

            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                int enemy = isMax ? -1 : 1;
                int self = enemy == -1 ? 1 : -1;

                if (board[newRow][newCol] == enemy) {
                    heuristicValue++;
                }
                if (heuristicValue != 0) {
                    if (newRow == 0 || newCol == 0 || newRow == 7 || newCol == 7) {
                        heuristicValue += 0.5;
                    }
                }
            }
        }
        return heuristicValue;
    }

    public static boolean isTerminal(int[][] board) {
        for (int[] row : board) {
            for (int cell : row) {
                if (cell == 0) {
                    return false;
                }
            }
        }
        return true;
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
                if (board[newRow][newCol] != 0) {
                    board[newRow][newCol] = fillValue;
                }
            }
        }

        return board;
    }

}
