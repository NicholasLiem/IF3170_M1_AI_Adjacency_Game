package Algorithms;


import Algorithms.DataStructure.TreeNode;

public class Minimax {
    public TreeNode<Double> minimax(TreeNode<Double> node, double alpha, double beta, boolean isMaximizingPlayer) {
        if (node.isLeaf()) {
            return node;
        }

        TreeNode<Double> bestMove = null;
        double bestScore;

        if (isMaximizingPlayer) {
            bestScore = Double.NEGATIVE_INFINITY;
            for (TreeNode<Double> child : node.getChildren()) {
                TreeNode<Double> nextMove = minimax(child, alpha, beta, false);
                double score = nextMove.getScore();
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = child;
                }
                alpha = Math.max(alpha, bestScore);
                if (beta <= alpha) {
                    break;  //
                }
            }
        } else {
            bestScore = Double.POSITIVE_INFINITY;
            for (TreeNode<Double> child : node.getChildren()) {
                TreeNode<Double> nextMove = minimax(child, alpha, beta, true);
                double score = nextMove.getScore();
                if (score < bestScore) {
                    bestScore = score;
                    bestMove = child;
                }
                beta = Math.min(beta, bestScore);
                if (beta <= alpha) {
                    break;
                }
            }
        }

        node.setScore(bestScore);
        return bestMove;
    }

}
