package Test;

import Algorithms.DataStructure.Node;
import Algorithms.DataStructure.Tree;

public class TestNodeAndTree {
    public static void main(String[] args) {
        Node rootNode = new Node(new int[]{1, 2}, true);
        Node child1 = new Node(new int[]{3, 4}, false);
        Node child2 = new Node(new int[]{5, 6}, true);

        System.out.println("Root node:" + rootNode.getPoint()[0] + rootNode.getPoint()[1]);

//        // Display information
//        System.out.println("Root Node Point: " + rootNode.getPoint()[0] + ", " + rootNode.getPoint()[1]);
//        System.out.println("Root Node isMaxPlayer: " + rootNode.isMaxPlayer());
//
//        System.out.println("Children of Root Node:");
//        for (Node child : rootNode.getChildren()) {
//            System.out.println("Child Point: " + child.getPoint()[0] + ", " + child.getPoint()[1]);
//            System.out.println("Child isMaxPlayer: " + child.isMaxPlayer());
//        }
    }
}
