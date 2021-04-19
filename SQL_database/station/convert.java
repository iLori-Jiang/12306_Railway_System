import java.io.*;
import java.util.regex.Pattern;

public class convert {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("stationList.csv"));
        File file = new File("converted_stationList.csv");
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        String str;
        while ((str = in.readLine()) != null) {
            String[] split = str.split(",");
            if (split.length >= 6) {
                out.write(split[0]);
                out.write(",");
                out.write(split[5]);
                out.newLine();
            } else break;
        }

        in.close();
        out.close();
    }
}
