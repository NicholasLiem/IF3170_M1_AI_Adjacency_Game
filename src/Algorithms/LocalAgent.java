package Algorithms;

import Utils.Utils;
import java.util.List;

public class LocalAgent {
    public int[] move (int[][] board) {
        List<int[]> possibleMoves = Utils.getPossibleMoves(board, false, 64);
        double maxScore = Double.MAX_VALUE;
        int[] bestMove = new int[2];

        long startTime = System.currentTimeMillis();
        long endTime = startTime + 5000; //Set timeout

        for (int[] move : possibleMoves) {
            int[][] newState = Utils.transition(Utils.copyBoard(board), move, false);

            double score = Utils.evaluateBoard(newState);
            if (score < maxScore) {
                maxScore = score;
                bestMove[0] = move[0];
                bestMove[1] = move[1];
            }

            long currentTime = System.currentTimeMillis();
            if (currentTime >= endTime) {
                break;
            }
        }

        return new int[]{bestMove[0], bestMove[1]};
    }
}
