package Test;

import Algorithms.DataStructure.TreeNode;
import Algorithms.MinimaxAgent;

public class TestMinMaxTree {
    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},
                {-1, 0, 0, 0, 0, 0, 0},
                {-1, -1, 0, 0, 0, 0, 0}
        };

        MinimaxAgent agent = new MinimaxAgent();

        int maxDepth = 5;

        TreeNode<int[]> root = new TreeNode<>(null, true);

        TreeNode<int[]> bestMoveNode = agent.calculate(root, board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0, maxDepth, true);

        int[] bestMove = bestMoveNode.getData();

        System.out.println("Best Move: (" + bestMove[0] + ", " + bestMove[1] + ")");
    }
}
