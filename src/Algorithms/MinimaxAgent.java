package Algorithms;


import Algorithms.DataStructure.TreeNode;
import Utils.Utils;

import java.util.List;

public class MinimaxAgent {

    public int[] move(int[][] board, boolean maximizingPlayer, int roundsLeft) {
        TreeNode<int[]> root = new TreeNode<>(null, false);
        TreeNode<int[]> bestMove = calculate(root, board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0, 8, maximizingPlayer, roundsLeft);
//        // TO DEBUG MIN MAX TREE, UNCOMMENT THIS
//        root.printNodeScores();
//        System.out.println("Best data: " + bestMove.getScore());

        return bestMove.getData();
    }

    public TreeNode<int[]> calculate(TreeNode<int[]> node, int[][] board, double alpha, double beta, int depth, int maxDepth, boolean isMaximizingPlayer, int roundsLeft) {
        // Return if terminal node
        if (roundsLeft == 0 || depth == maxDepth || Utils.isTerminal(board)) {
            double score = Utils.evaluateBoard(board);
            node.setBoard(board);
            node.setScore(score);
            return node;
        }

        List<int[]> possibleMoves = Utils.getPossibleMoves(board, isMaximizingPlayer, 10);
        // Init best move and best score

        double bestScore;
        TreeNode<int[]> bestMove = null;
        if (isMaximizingPlayer) {
            // Kalau maximizing then bestScorenya set -Inf, iterate for each childrennya dan sampai terminal node,
            // Kalau udah diterminal node nanti kan return nilainya di cek apakah nilainya lebih besar dari bestScore (kasusnya Maximizing)
            // If yes, then update bestScore dan set bestMove to it, also update the current alpha
            // Alpha --> Maximizing antara nilai sekarang dengan alpha, kalo nilai Alpha >= beta dipotong cabangnya.
            bestScore = Double.NEGATIVE_INFINITY;

            for (int[] move : possibleMoves) {
//                System.out.println("Move: (" + move[0] + ", " + move[1] + ")");

                int[][] newState = Utils.transition(Utils.copyBoard(board), move, true);
                TreeNode<int[]> childNode = new TreeNode<>(move, false);
                node.addChild(childNode);

                TreeNode<int[]> result = calculate(childNode, newState, alpha, beta, depth + 1, maxDepth, false, roundsLeft - 1);
                double score = result.getScore();

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = childNode;
                }

                alpha = Math.max(alpha, bestScore);
                if (beta <= alpha) {
                    break;
                }
            }

        } else {
            // Penjelasannya sama kek sebelumnya tapi kebalikan aja, tapi untuk pemotongan cabang ttp sama.
            bestScore = Double.POSITIVE_INFINITY;

            for (int[] move : possibleMoves) {

                int[][] newState = Utils.transition(Utils.copyBoard(board), move, false);
                TreeNode<int[]> childNode = new TreeNode<>(move, true);
                node.addChild(childNode);

                TreeNode<int[]> result = calculate(childNode, newState, alpha, beta, depth + 1, maxDepth, true, roundsLeft -1);
                double score = result.getScore();

                if (score < bestScore) {
                    bestScore = score;
                    bestMove = childNode;
                }

                beta = Math.min(beta, bestScore);
                if (beta <= alpha) {
                    break;
                }
            }

        }
        node.setBoard(board);
        node.setScore(bestScore);
        return bestMove;
    }

}
