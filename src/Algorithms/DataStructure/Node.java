package Algorithms.DataStructure;

import java.util.List;

public class Node {

    private final int[] point;
    private final boolean isMaxPlayer;
    private int score;
    List<Node> children;

    public Node(int[] point, boolean isMaxPlayer) {
        this.point = point;
        this.isMaxPlayer = isMaxPlayer;
    }

    public int[] getPoint() {
        return point;
    }

    public boolean isMaxPlayer() {
        return isMaxPlayer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node child) {
        children.add(child);
    }
}