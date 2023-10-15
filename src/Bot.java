import Algorithms.DataStructure.TreeNode;
import Algorithms.GeneticAgent;
import Algorithms.MinimaxAgent;
import Utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Bot {
    private final String type;
    private boolean maximizingPlayer = false;
    public Bot(String type){
        this.type = type;
        this.maximizingPlayer = false;
    }

    public Bot(String type, boolean maximizingPlayer){
        this.type = type;
        this.maximizingPlayer = maximizingPlayer;
    }
    public int[] move() {
        // create random move
        return new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};
    }


    public int[] move(int[][] board, int roundsLeft) {


        int[] move = new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};

        if (this.type.equalsIgnoreCase("minimax")) {
            move = this.moveMinimax(board, roundsLeft);
        } else if (this.type.equalsIgnoreCase("local")) {
            move = this.moveLocal(board, roundsLeft);
        } else if (this.type.equalsIgnoreCase("genetic")){
            move = this.moveGenetic(board, roundsLeft);
        }

        return move;
    }

    private int[] moveGenetic(int[][] board, int roundsLeft) {
        long startTime = System.currentTimeMillis();

        Utils.firstMove = 1;
        GeneticAgent geneticAgent = new GeneticAgent();

        int[] bestMove = geneticAgent.move(board, maximizingPlayer, roundsLeft);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Thinking time: " + executionTime + "ms");

        return bestMove;
    }

    private int[] moveLocal(int[][] board, int roundsLeft) {
        return new int[]{(int) (Math.random()*8), (int) (Math.random()*8)};
    }

    public int[] moveMinimax(int[][] board, int roundsLeft) {
        long startTime = System.currentTimeMillis();

        Utils.firstMove = 1;
        MinimaxAgent minimaxAgent = new MinimaxAgent();

        int[] bestMove = minimaxAgent.move(board, maximizingPlayer, roundsLeft);

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Thinking time: " + executionTime + "ms");

        return bestMove;
    }
}
