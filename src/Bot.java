import Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Bot {
    private String type;
    private Integer maxPiece = 0;

    public Bot(String type) {
        this.type = type;
    }
    public int[] move() {
        // create random move
        return new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};
    }

    void debug_log(int[][] board) {
        System.out.println("Evaluation current state: " + Utils.evaluateBoard(board));
        System.out.println("Possible Moves:");
        List<int[]> possibleMoves = getPossibleMoves(board);
        for (int[] move : possibleMoves) {
            System.out.println("Row: " + move[0] + ", Col: " + move[1]);
        }
        System.out.println("Is board terminal:");
        System.out.println(Utils.isTerminal(maxPiece, board));
//        System.out.println("Is board terminal after this move:");
//        int[] movee = {2,3};
//        System.out.println(isTerminal(transition(board, movee, false)));
    }


    public int[] move(int[][] board, int roundsLeft) {
        // Kita mau tau terminal state itu berapa piece di board (baik musuh maupun kita)
        // 1 ronde = 2 pemain.
        if (maxPiece == 0) {
            maxPiece = roundsLeft * 2;
        }

        debug_log(board);

        int[] move = new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};

        if (this.type.equalsIgnoreCase("minimax")) {
            move = this.moveMinimax(board, roundsLeft);
        } else if (this.type.equalsIgnoreCase("local")) {
            move = this.moveLocal(board);
        } else if (this.type.equalsIgnoreCase("genetic")){
            move = this.moveGenetic(board,roundsLeft);
        }

        return move;
    }

    private int[] moveGenetic(int[][] board, int roundsLeft) {
        return new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};
    }

    private int[] moveLocal(int[][] board) {
        return new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};
    }

    private int[] moveMinimax(int[][] board, int roundsLeft) {
        return new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};
    }

    public static int[][] transition(int[][] board, int[] move, boolean max) {

        // Fill the specified move with 1 or -1 based on the 'max' parameter
        int fillValue = max ? 1 : -1;
        int row = move[0];
        int col = move[1];

        board[row][col] = fillValue;

        return board;
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
}
