import java.io.File;
import java.util.HashMap;

public class SizeCalculator {

    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T',};



    public static String getHumanReadableSize(long size) {
        for (int i =0; i < sizeMultipliers.length; i++) {
            double value = ((double) size) / Math.pow(1024,i);
            if (value < 1024) {
                return Math.round(value * 100)/100. + " " + sizeMultipliers[i] + (i > 0 ? "b" : "");
            }
        }
        return "Very big!";
    }

    public static long getSizeHumanReadable(String size) {
        String power = size.replaceAll("[0-9]+", "");
        long value = Long.parseLong(size.replaceAll("[^0-9]+", ""));
        HashMap<String, Long> valueMap = new HashMap<>();
        valueMap.put("Kb", value * 1024L);
        valueMap.put("Mb", (long) ((double) value * Math.pow(1024.0, 2.0)));
        valueMap.put("Gb", (long) ((double) value * Math.pow(1024.0, 3.0)));
        valueMap.put("Tb", (long) ((double) value * Math.pow(1024.0, 4.0)));
        return valueMap.get(power);
    }
}
