package Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

//    public static List<int[]> getPossibleMoves(int[][] board) {
//        List<int[]> possibleMoves = new ArrayList<>();
//
//        // Define the possible adjacent directions (up, down, left, right)
//        int[] dx = {-1, 1, 0, 0,1,-1,1,-1};
//        int[] dy = {0, 0, -1, 1,1,-1,-1,1};
//
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                if (board[i][j] == 0) {
//                    // Check adjacent cells
//                    for (int k = 0; k < 8; k++) {
//                        int newRow = i + dx[k];
//                        int newCol = j + dy[k];
//
//                        // Check if the adjacent cell is within the board bounds
//                        if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[i].length) {
//                            if (board[newRow][newCol] != 0) {
//                                // Add the empty cell as a possible move
//                                possibleMoves.add(new int[]{i, j});
//                                break;  // No need to check other adjacent cells
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        return possibleMoves;
//    }

    public static List<int[]> getPossibleMoves(int[][] board) {
        List<int[]> possibleMoves = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0) {
                    int heuristicValue = calculateHeuristic(board, i, j);
                    possibleMoves.add(new int[]{i, j, heuristicValue});
                }
            }
        }

        possibleMoves.sort((a, b) -> Integer.compare(b[2], a[2]));

        List<int[]> sortedMoves = possibleMoves.stream()
                .limit(3)
                .map(move -> new int[]{move[0], move[1]})
                .collect(Collectors.toList());

        return sortedMoves;
    }

    private static int calculateHeuristic(int[][] board, int row, int col) {
        int heuristicValue = 0;

        int[] dx = {-1, 1, 0, 0, 1, -1, 1, -1};
        int[] dy = {0, 0, -1, 1, 1, -1, -1, 1};

        for (int k = 0; k < 8; k++) {
            int newRow = row + dx[k];
            int newCol = col + dy[k];

            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length) {
                if (board[newRow][newCol] != 0 && board[newRow][newCol] != 1) {
                    heuristicValue++;
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
                board[newRow][newCol] = fillValue;
            }
        }

        return board;
    }

}
