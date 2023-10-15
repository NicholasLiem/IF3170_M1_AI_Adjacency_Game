import Algorithms.DataStructure.TreeNode;
import Algorithms.MinimaxAgent;
import Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Bot {
    private final String type;
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
//        List<int[]> possibleMoves = Utils.getPossibleMoves(board, true);
//        for (int[] move : possibleMoves) {
//            System.out.println("Row: " + move[0] + ", Col: " + move[1]);
//        }
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

//        debug_log(board);

        int[] move = new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};

        if (this.type.equalsIgnoreCase("minimax")) {
            move = this.moveMinimax(board);
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

    public int[] moveMinimax(int[][] board) {
        long startTime = System.currentTimeMillis();

        Utils.firstMove = 1;
        MinimaxAgent minimaxAgent = new MinimaxAgent();

        int[] bestMove = minimaxAgent.move(board);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Thinking time: " + executionTime + "ms");

        return bestMove;
    }
}
