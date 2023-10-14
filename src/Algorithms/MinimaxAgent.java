package Algorithms;


import Algorithms.DataStructure.TreeNode;
import Utils.Utils;

import java.util.List;

public class MinimaxAgent {
    public TreeNode<int[]> calculate(TreeNode<int[]> node, int[][] board, double alpha, double beta, int depth, int maxDepth, boolean isMaximizingPlayer) {
        // Return if terminal node
        System.out.println("Depth: " + depth);
        // TODO: make sure the terminal node
        if (depth == maxDepth) {
            double score = Utils.evaluateBoard(board);
            System.out.println("Calculated Score: " + score);

            node.setScore(score);
            return node;
        }

        List<int[]> possibleMoves = Utils.getPossibleMoves(board);
        // Init best move and best score

        if (isMaximizingPlayer) {
            // Kalau maximizing then bestScorenya set -Inf, iterate for each childrennya dan sampai terminal node,
            // Kalau udah diterminal node nanti kan return nilainya di cek apakah nilainya lebih besar dari bestScore (kasusnya Maximizing)
            // If yes, then update bestScore dan set bestMove to it, also update the current alpha
            // Alpha --> Maximizing antara nilai sekarang dengan alpha, kalo nilai Alpha >= beta dipotong cabangnya.
            double bestScore = Double.NEGATIVE_INFINITY;
            TreeNode<int[]> bestMove = null;

            for (int[] move : possibleMoves) {
                System.out.println("Move: (" + move[0] + ", " + move[1] + ")");

                int[][] newState = Utils.transition(Utils.copyBoard(board), move, true);
                TreeNode<int[]> childNode = new TreeNode<>(move, false);
                node.addChild(childNode);

                TreeNode<int[]> result = calculate(childNode, newState, alpha, beta, depth + 1, maxDepth, false);
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

            node.setScore(bestScore);
            return bestMove;
        } else {
            // Penjelasannya sama kek sebelumnya tapi kebalikan aja, tapi untuk pemotongan cabang ttp sama.
            double bestScore = Double.POSITIVE_INFINITY;
            TreeNode<int[]> bestMove = null;

            for (int[] move : possibleMoves) {
                System.out.println("Move: (" + move[0] + ", " + move[1] + ")");

                int[][] newState = Utils.transition(Utils.copyBoard(board), move, false);
                TreeNode<int[]> childNode = new TreeNode<>(move, true);
                node.addChild(childNode);

                TreeNode<int[]> result = calculate(childNode, newState, alpha, beta, depth + 1, maxDepth, true);
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

            node.setScore(bestScore);
            return bestMove;
        }
    }

}