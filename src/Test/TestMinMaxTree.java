package Test;

import Algorithms.DataStructure.TreeNode;
import Algorithms.MinimaxAgent;

public class TestMinMaxTree {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();


//        int[][] board = {
//                {0, 0, 0, 0, 0, 1, 1},
//                {0, 0, 0, 0, 0, 0, 1},
//                {0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0},
//                {-1, 0, 0, 0, 0, 0, 0},
//                {-1, -1, 0, 0, 0, 0, 0}
//        };

        int[][] board = {
                {0, 0, -1, 0},
                {0, -1, 0, -1},
                {0, 0, -1, 0},
                {0, 0, 0, 0},
        };

        // Harusnya jawabannya adalah 1, 2

        MinimaxAgent agent = new MinimaxAgent();

        int maxDepth = 8;
        TreeNode<int[]> root = new TreeNode<>(null, true);

        TreeNode<int[]> bestMoveNode = agent.calculate(root, board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0, maxDepth, true);

        int[] bestMove = bestMoveNode.getData();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("Best Move: (" + bestMove[0] + ", " + bestMove[1] + ")");
        System.out.println("Execution Time: " + executionTime + "ms");

    }
}
