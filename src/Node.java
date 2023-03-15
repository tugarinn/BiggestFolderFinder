import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Node {

    private final File folder;
    private final ArrayList<Node> children;
    private static final String[] sizeNames = new String[]{"b", "kb", "Mb", "Gb"};


    private  int level  = 0;

    private long size;

    public  Node(File folder) {
        this.folder = folder;
        children = new ArrayList<>();
    }

    public File getFolder() {
        return folder;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void addChild(Node node) {
        node.setLevel(level + 1);
        children.add(node);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    private  void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String size = SizeCalculator.getHumanReadableSize(getSize());
        builder.append(folder.getName()).append(" - ").append(size).append("\n");
        for (Node child : children) {
            builder.append("  ").append(child.toString());
        }
        return builder.toString();
    }
}
