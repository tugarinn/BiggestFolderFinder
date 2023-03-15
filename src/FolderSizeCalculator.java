import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long> {
    private File folder;

    public FolderSizeCalculator(File folder) {
        this.folder = folder;
    }

    protected Long compute() {
        if (this.folder.isFile()) {
            return this.folder.length();
        } else {
            long sum = 0L;
            File[] files = this.folder.listFiles();
            List<FolderSizeCalculator> subTusk = new LinkedList();
            File[] var5 = files;
            int var6 = files.length;

            for (int var7 = 0; var7 < var6; ++var7) {
                File file = var5[var7];
                FolderSizeCalculator task = new FolderSizeCalculator(file);
                task.fork();
                subTusk.add(task);
            }

            FolderSizeCalculator task;
            for (Iterator var10 = subTusk.iterator(); var10.hasNext(); sum += (Long) task.join()) {
                task = (FolderSizeCalculator) var10.next();
            }

            return sum;
        }
    }
}
