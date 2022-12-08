package at.elmagico.days.day7;

import at.elmagico.days.fileReader.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaySeven {

    Map<String, Node> tree;

    private void buildTree(final String filePath) throws IOException {
        final List<String> lines = InputReader.readInputFile(filePath);

        final Node root = new Node();
        root.setIsDirectory(Boolean.TRUE);
        root.setName("/");
        root.setSize(0);
        final Map<String, Node> tree = new HashMap<>();
        tree.put(root.getName(), root);
        Node currentNode = root;
        for (final String line : lines) {
            final String[] splitUpString = line.split(" ");
            if (isChangeDirectory(splitUpString)) {
                if (moveUpChangeDirectory(splitUpString)) {
                    currentNode = currentNode.getParent();
                } else {
                    if (splitUpString[2].equals("/")) {
                        currentNode = root;
                    } else {
                        currentNode = currentNode.getChildren().stream()
                                .filter(node -> node.getIsDirectory()
                                        && node.getName().equals(splitUpString[2]))
                                .findFirst()
                                .orElseThrow();
                    }
                }
            } else if (!isListFiles(splitUpString)) {
                final Node tmp = new Node();

                if (isDirectory(splitUpString)) {
                    tmp.setSize(0);
                    tmp.setIsDirectory(Boolean.TRUE);
                } else {
                    tmp.setSize(Integer.parseInt(splitUpString[0]));
                    tmp.setIsDirectory(Boolean.FALSE);
                }

                tmp.setName(splitUpString[1]);
                tmp.setParent(currentNode);
                currentNode.getChildren().add(tmp);
                tree.put(tmp.getId(), tmp);
            }
        }
        this.tree = tree;
    }

    private static boolean isChangeDirectory(final String[] splitUpString) {
        return splitUpString[0].equals("$") && splitUpString[1].equals("cd");
    }

    private static boolean moveUpChangeDirectory(final String[] splitUpString) {
        return (isChangeDirectory(splitUpString) && splitUpString[2].equals(".."));
    }

    private static boolean isListFiles(final String[] splitUpString) {
        return splitUpString[0].equals("$") && splitUpString[1].equals("ls");
    }


    private static boolean isDirectory(final String[] splitUpString) {
        return splitUpString[0].equals("dir");
    }

    private static Integer setNodeSizeWithDfs(final List<Node> visited, final Node currentNode) {
        visited.add(currentNode);

        if (!currentNode.isLeaf()) {

            final List<Node> children = currentNode.getChildren();
            Integer size = currentNode.getSize();
            for (final Node child : children) {
                if (!visited.contains(child)) {
                    size += setNodeSizeWithDfs(visited, child);
                }
            }
            if (currentNode.getIsDirectory()) {
                currentNode.setSize(size);
            }

        }
        return currentNode.getSize();
    }

    private void dfs() {
        final Node root = tree.get("/");
        final Integer fileSystemSize = setNodeSizeWithDfs(new ArrayList<>(), root);
        root.setSize(fileSystemSize);

    }

    public void readFile(final String filepath) throws IOException {
        buildTree(filepath);
        dfs();
    }

    public Integer getSizeOfDirectoriesUnderThreshold(final Integer threshold) {
        final List<Node> directoriesUnderThreshold = new ArrayList<>();
        for (final Node node : tree.values()) {
            if (node.getIsDirectory() && node.getSize() <= threshold) directoriesUnderThreshold.add(node);
        }
        return directoriesUnderThreshold.stream()
                .map(Node::getSize)
                .reduce(0, Integer::sum);
    }

    public Integer getRequiredSpaceOfSmallestDirectoryToFreeUpEnoughSpace(final Integer requiredSpace) {
        final Integer spaceToCleanUpAdditionally = requiredSpace - (70000000 - tree.get("/").getSize());
        final List<Node> directoriesOverThreshold = new ArrayList<>();
        tree.values().stream()
                .filter(Node::getIsDirectory)
                .filter(node -> node.getSize() >= spaceToCleanUpAdditionally)
                .forEach(directoriesOverThreshold::add);

        return directoriesOverThreshold.stream()
                .map(Node::getSize)
                .sorted()
                .findFirst()
                .orElse(Integer.MIN_VALUE);
    }

}
