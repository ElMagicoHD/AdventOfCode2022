package at.elmagico.days.day7;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Node {
    private String name;
    private Integer size;
    private Node parent;
    private List<Node> children;

    private Boolean isDirectory;
    private String id;

    public Node() {
        name = null;
        size = null;
        parent = null;
        children = new ArrayList<>();
        isDirectory = null;
        id = UUID.randomUUID().toString();
    }

    public Boolean isLeaf() {
        return children.isEmpty();
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", parent=" + (parent == null ? "" : parent.name) +
                ", isDirectory=" + isDirectory +
                '}';
    }
}
