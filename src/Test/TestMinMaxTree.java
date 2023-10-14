package Test;

import Algorithms.DataStructure.Tree;
import Algorithms.DataStructure.TreeNode;
import Algorithms.Minimax;

public class TestMinMaxTree {
    public static void main(String[] args) {
        /**
         * TreeNode<Double> nanti Doublenya diganti GameBoard / State biar bisa diitung scorenya ato apalah
         */
        TreeNode<Double> root = new TreeNode<>(0.0, true);
        new Tree<>(root);

        TreeNode<Double> child1 = new TreeNode<>(1.0, false);
        TreeNode<Double> child2 = new TreeNode<>(2.0, false);
        TreeNode<Double> child3 = new TreeNode<>(3.0, false);

        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);

        TreeNode<Double> child11 = new TreeNode<>(4.0, true);
        TreeNode<Double> child12 = new TreeNode<>(5.0, true);
        TreeNode<Double> child13 = new TreeNode<>(6.0, true);

        child1.addChild(child11);
        child1.addChild(child12);
        child1.addChild(child13);

        TreeNode<Double> child21 = new TreeNode<>(7.0, true);
        TreeNode<Double> child22 = new TreeNode<>(8.0, true);
        TreeNode<Double> child23 = new TreeNode<>(9.0, true);

        child2.addChild(child21);
        child2.addChild(child22);
        child2.addChild(child23);

        TreeNode<Double> child31 = new TreeNode<>(10.0, true);
        TreeNode<Double> child32 = new TreeNode<>(11.0, true);
        TreeNode<Double> child33 = new TreeNode<>(12.0, true);

        child3.addChild(child31);
        child3.addChild(child32);
        child3.addChild(child33);

        child11.setScore(13.0);
        child12.setScore(14.0);
        child13.setScore(15.0);

        child21.setScore(16.0);
        child22.setScore(17.0);
        child23.setScore(18.0);

        child31.setScore(19.0);
        child32.setScore(20.0);
        child33.setScore(21.0);

        System.out.println("Initial Game Tree:");
        printTreeNode(root);

        Minimax minimax = new Minimax();
        TreeNode<Double> bestMove = minimax.minimax(root, true);

        System.out.println("\nBest Move:");
        printTreeNode(bestMove);
    }

    private static void printTreeNode(TreeNode<Double> node) {
        System.out.println("Data=" + node.getData() + ", isMaxPlayer=" + node.isMaxPlayer() + ", Score=" + node.getScore());
        System.out.println("Child Nodes:");
        for (TreeNode<Double> child : node.getChildren()) {
            System.out.println("Data=" + child.getData() + ", isMaxPlayer=" + child.isMaxPlayer() + ", Score=" + child.getScore());
        }
    }
}
