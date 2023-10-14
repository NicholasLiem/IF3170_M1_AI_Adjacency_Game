package Algorithms.DataStructure;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T> {
    private T data;
    private List<TreeNode<T>> children;
    private boolean isMaxPlayer;
    private int score;

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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
}
