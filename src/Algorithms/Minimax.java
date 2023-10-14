package Algorithms;

import Algorithms.DataStructure.Node;
import Algorithms.DataStructure.Tree;

import java.util.List;
//https://www.baeldung.com/java-minimax-algorithm
public class Minimax {
    Tree tree;
    public void constructTree(int test){
        tree = new Tree();
        Node root = new Node(test, true);
        tree.setRoot(root);
        constructTree(root);
    }

    public void constructTree(Node parentNode) {
//        List<Integer> listOfPossibleHeaps = ...
//        ischildMaxplayer = !parentNode.isMaxPlayer();
//        for each heaps create node, parentnode.addchild of this new node
//        if
    }
}
