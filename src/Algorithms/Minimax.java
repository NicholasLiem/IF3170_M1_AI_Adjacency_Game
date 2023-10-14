package Algorithms;

import Algorithms.DataStructure.Node;
import Algorithms.DataStructure.Tree;

import java.util.ArrayList;
import java.util.List;
//https://stackoverflow.com/questions/15447580/java-minimax-alpha-beta-pruning-recursion-return
//https://www.baeldung.com/java-minimax-algorithm
public class Minimax {
    Tree tree;
    public void constructTree(int test){
        tree = new Tree();
        int[] testPoint = {2,3};
        Node root = new Node(testPoint, true);
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
