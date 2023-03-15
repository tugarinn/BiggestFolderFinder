import java.io.File;
import java.util.HashMap;

public class Main {
    private static String[] sizeNames = new String[]{"b", "kb", "Mb", "Gb"};

    public Main() {
    }

    public static void main(String[] args) {
        String folderPath = "C:\\Users\\Евгений\\java_basics";
        new File(folderPath);
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        } else {
            long sum = 0L;
            File[] files = folder.listFiles();
            File[] var4 = files;
            int var5 = files.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                File file = var4[var6];
                sum += getFolderSize(file);
            }

            return sum;
        }
    }

    public static String getHumanReadableSize(long size) {
        int power = (int)(Math.log((double)size) / Math.log(1024.0));
        double value = (double)size / Math.pow(1024.0, (double)power);
        double roundedValue = (double)Math.round(value * 100.0) / 100.0;
        return "" + roundedValue + " " + sizeNames[power];
    }

    public static long getSizeHumanReadable(String size) {
        String power = size.replaceAll("[0-9]+", "");
        long value = Long.parseLong(size.replaceAll("[^0-9]+", ""));
        HashMap<String, Long> valueMap = new HashMap();
        valueMap.put("Kb", value * 1024L);
        valueMap.put("Mb", (long)((double)value * Math.pow(1024.0, 2.0)));
        valueMap.put("Gb", (long)((double)value * Math.pow(1024.0, 3.0)));
        valueMap.put("Tb", (long)((double)value * Math.pow(1024.0, 4.0)));
        return (Long)valueMap.get(power);
    }
}

