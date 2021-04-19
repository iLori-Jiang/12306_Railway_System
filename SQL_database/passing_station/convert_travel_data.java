import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class convert_travel_data {
    public static void main(String[] args) throws IOException {
//        System.out.println(Integer.parseInt("06"));
        BufferedReader in = new BufferedReader(new FileReader("info.csv"));
        File file = new File("converted_passing_station.csv");
        BufferedWriter out = new BufferedWriter(new FileWriter(file));
        String str;
        in.readLine();
        out.write("train_id,station_name,arrive_time,depart_time");
        out.newLine();
        int i;
        while ((str = in.readLine()) != null) {
            i = 17;
            //str是始发站
            ArrayList<String[]> arr = new ArrayList<>();
            arr.add(str.split(","));
            arr.get(0)[3] = arr.get(0)[4];
            while (true) {
                String[] temp = in.readLine().split(",");
                arr.add(temp);
                if (temp[4].equals("-99:00")) {
                    arr.get(arr.size() - 1)[4] = arr.get(arr.size() - 1)[3];
                    break;
                }
            }


            //如果该站时间<前一站时间，说明到了第二天
            arr.get(0)[3] = "2020-05-17 " + arr.get(0)[3];
            arr.get(0)[4] = "2020-05-17 " + arr.get(0)[4];
            for (int j = 1; j < arr.size(); j++) {
                int thisArr = Integer.parseInt(arr.get(j)[3].substring(0, 2));
                String lastDeptS = arr.get(j - 1)[4].substring(11, 13);
//                System.out.println(arr.get(j - 1)[4]);
//                for (String[] strings : arr) {
//                    System.out.print(Arrays.toString(strings) + " ");
//                }
//                System.out.println();
                int lastDept = Integer.parseInt(lastDeptS);
                if (thisArr < lastDept) {
                    //第二天了
                    arr.get(j)[3] = "2020-05-" + ++i + " " + arr.get(j)[3];
                } else {
                    arr.get(j)[3] = "2020-05-" + i + " " + arr.get(j)[3];
                }
                arr.get(j)[4] = "2020-05-" + i + " " + arr.get(j)[4];
            }

            for (String[] strings : arr) {
                out.write(strings[0] + ",");
                out.write(strings[2] + ",");
                out.write(strings[3] + ",");
                out.write(strings[4]);
                out.newLine();
            }

//            String[] split = str.split(",");
//            if (split.length == 7) {
//                out.write(split[0] + ",");
//                out.write(split[2] + ",");
//                if (split[3].equals("-99:00")) {
//                    //始发站
//                    split[3] = split[4];
//                } else if (split[4].equals("-99:00")) {
//                    //终点站
//                    split[4] = split[3];
//                }
//                out.write("2020-05-17 " + split[3] + ",");
//                out.write("2020-05-17 " + split[4]);
//                out.newLine();
//            }
        }

        in.close();
        out.close();
    }
}


