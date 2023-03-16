import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long> {

    private Node node;

    public FolderSizeCalculator(Node node) {
        this.node = node;
    }

    protected Long compute() {
        File folder = node.getFolder();
        if (folder.isFile()) {
            long length = folder.length();
            node.setSize(length);
            return length;
        }
        long sum = 0;
        List<FolderSizeCalculator> subTusk = new LinkedList<>();
        File[] files = folder.listFiles();
        for (File file : files) {
            Node child = new Node(file,node.getLimit());
            FolderSizeCalculator task = new FolderSizeCalculator(child);
            task.fork();
            subTusk.add(task);
            node.addChild(child);
        }

        for (FolderSizeCalculator task : subTusk) {
            sum += task.join();
        }
        node.setSize(sum);
        return sum;
    }
}
