import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class test {
    public static void main(String[] args) throws IOException {
        BufferedReader info = new BufferedReader(new FileReader("info.csv"));
        File file = new File("converted_passing_station.csv");
        BufferedReader station = new BufferedReader(new FileReader("project_public_station.csv"));
        HashMap<String, Integer> stationname_stationid = new HashMap<>();
        BufferedWriter out = new BufferedWriter(new FileWriter(file));

        File station_name_csv = new File("station_name.csv");
        BufferedWriter out_station = new BufferedWriter(new FileWriter(station_name_csv));

        String str;
        station.readLine();//跳过第一行列名
        while ((str = station.readLine()) != null) {
            String[] arr = str.split(",");
            int station_id = Integer.parseInt(arr[0]);
            String station_name = arr[1];
            stationname_stationid.put(station_name, station_id);
        }


        info.readLine();
        out.write("travel_id,station_id,arrive_time,depart_time,distance");
        out_station.write("travel_id,train_id");
        out_station.newLine();
        out.newLine();
        int i;
        int count = 1;
        while ((str = info.readLine()) != null) {
            i = 17;
            //str是始发站
            ArrayList<String[]> arr = new ArrayList<>();
            arr.add(str.split(","));
            arr.get(0)[3] = arr.get(0)[4];
            while (true) {
                String[] temp = info.readLine().split(",");
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

            for (String[] strings : arr) {//写入converted_passing_station.csv
                out.write(count + ",");//travel id
//                out.write(strings[0] + ",");//train_id
//                out.write(strings[2] + ",");//station name
                out.write(stationname_stationid.get(strings[2]) + ",");//station id
//                out_station.write(strings[2]);
//                out_station.newLine();
                out.write(strings[3] + ",");//arrive time
                out.write(strings[4] + ",");//depart time
                out.write(strings[6]);//distance
                out.newLine();
                //写入travel
                out_station.write(count + ",");//travel id
                out_station.write(strings[0]);//train_id
                out_station.newLine();
                count++;
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

        info.close();
        out.close();
    }
}


