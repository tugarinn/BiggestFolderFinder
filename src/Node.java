import java.io.File;
import java.util.ArrayList;

public class Node {

    private final File folder;
    private final ArrayList<Node> children;
    private static final String[] sizeNames = new String[]{"b", "kb", "Mb", "Gb"};

    private  long limit;

    public long getLimit() {
        return limit;
    }

    private  int level  = 0;

    private long size;

    public  Node(File folder,long limit) {
        this.folder = folder;
        this.limit = limit;
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
            if (child.getSize() < limit) continue;
            builder.append(getLevel()).append(child);
        }
        return builder.toString();
    }

    public String getLevel() {
        String indent = "";
        for (int i = 0; i <= level; i++) {
            String space = "  ";
            indent = indent + space;
        }
        return indent;
    }
}
