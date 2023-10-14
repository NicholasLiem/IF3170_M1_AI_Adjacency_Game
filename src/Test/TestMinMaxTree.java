package Test;

import Algorithms.DataStructure.Tree;
import Algorithms.DataStructure.TreeNode;
public class TestMinMaxTree {
    public static void main(String[] args) {
        TreeNode<Integer> rootNode = new TreeNode<>(0, true);

        Tree<Integer> tree = new Tree<>(rootNode);

        TreeNode<Integer> child1 = new TreeNode<>(1, false);
        TreeNode<Integer> child2 = new TreeNode<>(2, false);
        TreeNode<Integer> child3 = new TreeNode<>(3, false);

        rootNode.addChild(child1);
        rootNode.addChild(child2);
        rootNode.addChild(child3);

        child1.setScore(5);
        child2.setScore(7);
        child3.setScore(3);

        System.out.println("Root Node: Data=" + rootNode.getData() + ", isMaxPlayer=" + rootNode.isMaxPlayer() + ", Score=" + rootNode.getScore());

        System.out.println("Child Nodes:");
        for (TreeNode<Integer> child : rootNode.getChildren()) {
            System.out.println("Data=" + child.getData() + ", isMaxPlayer=" + child.isMaxPlayer() + ", Score=" + child.getScore());
        }

        rootNode.removeChild(child2);

        System.out.println("After removing child2:");
        System.out.println("Child Nodes:");
        for (TreeNode<Integer> child : rootNode.getChildren()) {
            System.out.println("Data=" + child.getData() + ", isMaxPlayer=" + child.isMaxPlayer() + ", Score=" + child.getScore());
        }
    }
}
