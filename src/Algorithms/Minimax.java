package Algorithms;


import Algorithms.DataStructure.TreeNode;

public class Minimax {
    public TreeNode<Double> minimax(TreeNode<Double> node, double alpha, double beta, boolean isMaximizingPlayer) {
        // Return if terminal node
        if (node.isLeaf()) {
            return node;
        }

        // Init best move and best score
        TreeNode<Double> bestMove = null;
        double bestScore;

        if (isMaximizingPlayer) {
            // Kalau maximizing then bestScorenya set -Inf, iterate for each childrennya dan sampai terminal node,
            // Kalau udah diterminal node nanti kan return nilainya di cek apakah nilainya lebih besar dari bestScore (kasusnya Maximizing)
            // If yes, then update bestScore dan set bestMove to it, also update the current alpha
            // Alpha --> Maximizing antara nilai sekarang dengan alpha, kalo nilai Alpha >= beta dipotong cabangnya.
            bestScore = Double.NEGATIVE_INFINITY;
            for (TreeNode<Double> child : node.getChildren()) {
                TreeNode<Double> nextMove = minimax(child, alpha, beta, false);
                double score = nextMove.getScore();
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = child;
                }
                alpha = Math.max(alpha, bestScore);
                if (alpha >= beta) {
                    break;
                }
            }
        } else {
            // Penjelasannya sama kek sebelumnya tapi kebalikan aja, tapi untuk pemotongan cabang ttp sama.
            bestScore = Double.POSITIVE_INFINITY;
            for (TreeNode<Double> child : node.getChildren()) {
                TreeNode<Double> nextMove = minimax(child, alpha, beta, true);
                double score = nextMove.getScore();
                if (score < bestScore) {
                    bestScore = score;
                    bestMove = child;
                }
                beta = Math.min(beta, bestScore);
                if (alpha >= beta) {
                    break;
                }
            }
        }

        node.setScore(bestScore);
        return bestMove;
    }

}
