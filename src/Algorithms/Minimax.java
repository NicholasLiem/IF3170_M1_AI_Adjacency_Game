package Algorithms;


import Algorithms.DataStructure.TreeNode;

//https://stackoverflow.com/questions/15447580/java-minimax-alpha-beta-pruning-recursion-return
//https://www.baeldung.com/java-minimax-algorithm
public class Minimax {
    public TreeNode<Double> minimax(TreeNode<Double> node, boolean isMaximizingPlayer){
        if (node.isLeaf()){
            return node;
        }

        TreeNode<Double> bestMove = null;
        double bestScore;

        if (isMaximizingPlayer) {
            bestScore = Double.NEGATIVE_INFINITY;
            for(TreeNode<Double> child : node.getChildren()){
                TreeNode<Double> nextMove = minimax(child, false);
                double score = nextMove.getScore();
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = child;
                }
            }
        } else {
            bestScore = Double.POSITIVE_INFINITY;
            for (TreeNode<Double> child : node.getChildren()) {
                TreeNode<Double> nextMove = minimax(child, true);
                double score = nextMove.getScore();
                if (score < bestScore) {
                    bestScore = score;
                    bestMove = child;
                }
            }
        }

        node.setScore(bestScore);
        return bestMove;
    }
}
