package Algorithms.DataStructure;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    private T data;
    private List<TreeNode<T>> children;
    private boolean isMaxPlayer;
    private double score;
    public int[][] board;

    public TreeNode(T data, boolean isMaxPlayer) {
        this.data = data;
        this.isMaxPlayer = isMaxPlayer;
        this.score = 0;
        this.children = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public boolean isMaxPlayer() {
        return isMaxPlayer;
    }

    public double getScore() {
        return score;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void addChild(TreeNode<T> child) {
        children.add(child);
    }

    public void removeChild(TreeNode<T> child) {
        children.remove(child);
    }

    public boolean isLeaf() {return children.isEmpty();}
    public void printNodeScores() {
        printNodeScoresHelper(this, 0);
    }

    private void printNodeScoresHelper(TreeNode<T> node, int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("\t");
        }
        sb.append(", Score: ").append(node.getScore());
        System.out.println(sb.toString());
        // Print board
        if (node.board != null){
            for (int[] row : node.board) {
                for (int cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        }
        for (TreeNode<T> child : node.getChildren()) {
            printNodeScoresHelper(child, depth + 1);
        }
    }
}
