package TravelDetailWindow;

import MainWindow.MainWindowController;
import PurchaseWindow.PurchaseWindow;
import PurchaseWindow.PurchaseWindowController;
import SearchWindow.SearchWindow;
import Start.ProgrammeStart;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class TravelDetailWindowController implements Initializable {
    public static String depart_time;
    public static String arrive_time;
    public static String depart_station;
    public static String arrive_station;
    public static String depart_date;
    public static String train_id;
    public static String travel_id;
    public static int depart_id;
    public static int arrive_id;

    public TravelDetailWindowController() {

    }

    @FXML
    private Button BackButton;

    @FXML
    private javafx.scene.control.Label departTime;

    @FXML
    private javafx.scene.control.Label arriveTime;

    @FXML
    private javafx.scene.control.Label date;

    @FXML
    private javafx.scene.control.Label train;

    @FXML
    private javafx.scene.control.Label departStation;

    @FXML
    private javafx.scene.control.Label arriveStation;

    @FXML
    private javafx.scene.control.Label type1;
    @FXML
    private javafx.scene.control.Label price1;
    @FXML
    private javafx.scene.control.Label available1;
    @FXML
    private javafx.scene.control.Button book1;
    @FXML
    private javafx.scene.control.Label type2;
    @FXML
    private javafx.scene.control.Label price2;
    @FXML
    private javafx.scene.control.Label available2;
    @FXML
    private javafx.scene.control.Button book2;
    @FXML
    private javafx.scene.control.Label type3;
    @FXML
    private javafx.scene.control.Label price3;
    @FXML
    private javafx.scene.control.Label available3;
    @FXML
    private javafx.scene.control.Button book3;
    @FXML
    private javafx.scene.control.Label type4;
    @FXML
    private javafx.scene.control.Label price4;
    @FXML
    private javafx.scene.control.Label available4;
    @FXML
    private javafx.scene.control.Button book4;
    @FXML
    private javafx.scene.shape.Line line1;
    @FXML
    private javafx.scene.shape.Line line2;
    @FXML
    private javafx.scene.shape.Line line3;

    @FXML
    private javafx.scene.control.Label passing_station2;
    @FXML
    private javafx.scene.control.Label departTime2;
    @FXML
    private javafx.scene.control.Label arriveTime2;
    @FXML
    private javafx.scene.control.Label stay;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        departTime.setText(depart_time);
        arriveTime.setText(arrive_time);
        departStation.setText(depart_station);
        arriveStation.setText(arrive_station);
        date.setText(depart_date);
        train.setText(train_id);

        Properties defprop = new Properties();
        defprop.put("host", "localhost");
        defprop.put("user", "ilori");
        defprop.put("password", "990114");
        defprop.put("database", "project2_v2");
        Properties prop = new Properties(defprop);

        ProgrammeStart.openDB(prop.getProperty("host"), prop.getProperty("database"),
                prop.getProperty("user"), prop.getProperty("password"));

        try {
            getStation_id();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            passingStationIni();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            seatIni();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ProgrammeStart.closeDB();

    }

    private void passingStationIni() throws Exception{
        Statement stmt0 = null;
        stmt0 = ProgrammeStart.con.createStatement();

        ResultSet travelSet = null;

        if (ProgrammeStart.con != null) {
            String sql = "select station, arrivetime, departtime, cast((departtime-arrivetime) as varchar) as stay \n"
                    + "from travel_consult(" + travel_id + ");";
            travelSet = stmt0.executeQuery(sql);

        }

        StringBuilder passing = new StringBuilder();
        StringBuilder depart = new StringBuilder();
        StringBuilder arrive = new StringBuilder();
        StringBuilder stay2 = new StringBuilder();

        while (travelSet.next()){
            String temp;
            temp = travelSet.getString("station");
            passing.append(temp).append("\n\n");
            depart.append(travelSet.getString("departtime").substring(11, 16)).append("\n\n");
            arrive.append(travelSet.getString("arrivetime").substring(11, 16)).append("\n\n");
            stay2.append(travelSet.getString("stay").substring(3, 8)).append("\n\n");
        }

        travelSet.close();
        stmt0.close();

        passing_station2.setText(passing.toString());
        departTime2.setText(depart.toString());
        arriveTime2.setText(arrive.toString());
        stay.setText(stay2.toString());

    }

    public void seatIni() throws Exception{
        Statement stmt0 = null;
        stmt0 = ProgrammeStart.con.createStatement();

        ResultSet seatSet = null;

        if (ProgrammeStart.con != null) {
            String sql = "select * from train_seat where train_id = '" + train_id + "';";
            seatSet = stmt0.executeQuery(sql);
        }

        String[] seat = new String[4];
        int i = 0;

        while (seatSet.next()){
            seat[i] = seatSet.getString("seat_type");
            i+=1;
        }

        stmt0.close();
        seatSet.close();


        if(seat[0] != null){
            type1.setText(seat[0]);
            stmt0 = ProgrammeStart.con.createStatement();
            ResultSet resultSet = null;
            if (ProgrammeStart.con != null) {
                String sql = "select * from ticket_remain(" + travel_id + ", '" + type1.getText() + "', " + depart_id + ", " + arrive_id + ");";
                resultSet = stmt0.executeQuery(sql);
            }

            while (resultSet.next()){
                price1.setText("¥" + resultSet.getString("totalprice"));
                available1.setText(resultSet.getString("seatremain") + "张票剩余");
            }
            stmt0.close();
            resultSet.close();
        }else{
            type1.setText("");
            price1.setText("");
            available1.setText("");
            book1.setVisible(false);
        }

        if(seat[1] != null){
            type2.setText(seat[1]);
            stmt0 = ProgrammeStart.con.createStatement();
            ResultSet resultSet = null;
            if (ProgrammeStart.con != null) {
                String sql = "select * from ticket_remain(" + travel_id + ", '" + type2.getText() + "', " + depart_id + ", " + arrive_id + ");";
                resultSet = stmt0.executeQuery(sql);
            }

            while (resultSet.next()){
                price2.setText("¥" + resultSet.getString("totalprice"));
                available2.setText(resultSet.getString("seatremain") + "张票剩余");
            }
            stmt0.close();
            resultSet.close();
        }else{
            type2.setText("");
            line1.setStartX(0);
            line1.setEndX(0);
            price2.setText("");
            available2.setText("");
            book2.setVisible(false);
        }

        if(seat[2] != null){
            type3.setText(seat[2]);
            stmt0 = ProgrammeStart.con.createStatement();
            ResultSet resultSet = null;
            if (ProgrammeStart.con != null) {
                String sql = "select * from ticket_remain(" + travel_id + ", '" + type3.getText() + "', " + depart_id + ", " + arrive_id + ");";
                resultSet = stmt0.executeQuery(sql);
            }

            while (resultSet.next()){
                price3.setText("¥" + resultSet.getString("totalprice"));
                available3.setText(resultSet.getString("seatremain") + "张票剩余");
            }
            stmt0.close();
            resultSet.close();
        }else{
            type3.setText("");
            line2.setStartX(0);
            line2.setEndX(0);
            price3.setText("");
            available3.setText("");
            book3.setVisible(false);
        }

        if(seat[3] != null){
            type4.setText(seat[3]);
            stmt0 = ProgrammeStart.con.createStatement();
            ResultSet resultSet = null;
            if (ProgrammeStart.con != null) {
                String sql = "select * from ticket_remain(" + travel_id + ", '" + type4.getText() + "', " + depart_id + ", " + arrive_id + ");";
                resultSet = stmt0.executeQuery(sql);
            }

            while (resultSet.next()){
                price4.setText("¥" + resultSet.getString("totalprice"));
                available4.setText(resultSet.getString("seatremain") + "张票剩余");
            }
            stmt0.close();
            resultSet.close();
        }else{
            type4.setText("");
            line3.setStartX(0);
            line3.setEndX(0);
            price4.setText("");
            available4.setText("");
            book4.setVisible(false);
        }

    }

    public void getStation_id() throws Exception{
        Statement stmt0 = null;
        stmt0 = ProgrammeStart.con.createStatement();

        ResultSet resultSet = null;

        if (ProgrammeStart.con != null) {
            String sql = "select station_id from station where station_name = '" + depart_station + "';";
            resultSet = stmt0.executeQuery(sql);
        }
        while (resultSet.next()){
            depart_id = Integer.parseInt(resultSet.getString("station_id"));

        }

        resultSet = null;
        if (ProgrammeStart.con != null) {
            String sql = "select station_id from station where station_name = '" + arrive_station + "';";
            resultSet = stmt0.executeQuery(sql);
        }
        while (resultSet.next()){
            arrive_id = Integer.parseInt(resultSet.getString("station_id"));

        }
        resultSet.close();
    }

    public void book1() throws Exception{
        Properties defprop = new Properties();
        defprop.put("host", "localhost");
        defprop.put("user", "ilori");
        defprop.put("password", "990114");
        defprop.put("database", "project2_v2");
        Properties prop = new Properties(defprop);

        ProgrammeStart.openDB(prop.getProperty("host"), prop.getProperty("database"),
                prop.getProperty("user"), prop.getProperty("password"));
        Statement stmt0 = ProgrammeStart.con.createStatement();
        ResultSet resultSet = null;

        if(ProgrammeStart.con != null){
            String sql = "begin;";
            stmt0.execute(sql);
        }

        if (ProgrammeStart.con != null) {
            String sql = "select * from create_order('" + ProgrammeStart.userID + "', " + travel_id + ", " + depart_id + ", " + arrive_id + ", '" + type1.getText() + "');";
            resultSet = stmt0.executeQuery(sql);
        }

        String created = "";
        while (resultSet.next()){
            created = resultSet.getString("create_order");
        }

        resultSet = null;

        if(created.equals("true")){
            if (ProgrammeStart.con != null){
                String sql = "select * from buy_ticket(" + travel_id + ", '" + type1.getText() + "', " + depart_id + ", " + arrive_id + ");";
                resultSet = stmt0.executeQuery(sql);
            }

            ArrayList<String[]> purchase = new ArrayList<String[]>();
            while (resultSet.next()){
                String[] tempt = new String[5];
                tempt[0] = resultSet.getString("traveldeid");
                tempt[1] = resultSet.getString("seatdetype");
                tempt[2] = resultSet.getString("seatdeleft");
                tempt[3] = resultSet.getString("departdestation_id");
                tempt[4] = resultSet.getString("arrivedestation_id");
                purchase.add(tempt);
            }

            int executeLine = 0;
            for(int i=0; i<purchase.size(); i++){
                String[] tempt = purchase.get(i);
                if(ProgrammeStart.con != null){
                    String sql = "update public.seat_remain set seat_left = " + (Integer.parseInt(tempt[2])-1) +
                            " where travel_id = " + tempt[0] + " and depart_station_id = " + tempt[3] + " and arrive_station_id = " + tempt[4] + " and seat_type = '" + tempt[1] + "';";
                    executeLine += stmt0.executeUpdate(sql);
                }
            }

            if(executeLine == purchase.size()){
                if(ProgrammeStart.con != null){
                    String sql = "commit;";
                    stmt0.execute(sql);
                }
                ProgrammeStart.con.commit();

                Stage stage = (Stage) book1.getScene().getWindow();
                stage.close();
                TravelDetailWindow travelDetailWindow = new TravelDetailWindow();
                travelDetailWindow.showWindow();
                PurchaseWindowController.success = true;
                PurchaseWindow purchaseWindow = new PurchaseWindow();
                purchaseWindow.showWindow();
            }else {
                PurchaseWindowController.success = false;
                PurchaseWindow purchaseWindow = new PurchaseWindow();
                purchaseWindow.showWindow();
            }
        }else{
            PurchaseWindowController.success = false;
            PurchaseWindow purchaseWindow = new PurchaseWindow();
            purchaseWindow.showWindow();
        }
    }

    public void backToPoemSelectWindow() throws Exception {

        SearchWindow searchWindow = new SearchWindow();
        searchWindow.showWindow();

        //关闭原来的window
        Stage stage = (Stage) BackButton.getScene().getWindow();
        stage.close();
    }

    public void book2() throws Exception{
        Properties defprop = new Properties();
        defprop.put("host", "localhost");
        defprop.put("user", "ilori");
        defprop.put("password", "990114");
        defprop.put("database", "project2_v2");
        Properties prop = new Properties(defprop);

        ProgrammeStart.openDB(prop.getProperty("host"), prop.getProperty("database"),
                prop.getProperty("user"), prop.getProperty("password"));
        Statement stmt0 = ProgrammeStart.con.createStatement();
        ResultSet resultSet = null;

        if(ProgrammeStart.con != null){
            String sql = "begin;";
            stmt0.execute(sql);
        }

        if (ProgrammeStart.con != null) {
            String sql = "select * from create_order('" + ProgrammeStart.userID + "', " + travel_id + ", " + depart_id + ", " + arrive_id + ", '" + type2.getText() + "');";
            resultSet = stmt0.executeQuery(sql);
        }

        String created = "";
        while (resultSet.next()){
            created = resultSet.getString("create_order");
        }

        resultSet = null;

        if(created.equals("true")){
            if (ProgrammeStart.con != null){
                String sql = "select * from buy_ticket(" + travel_id + ", '" + type2.getText() + "', " + depart_id + ", " + arrive_id + ");";
                resultSet = stmt0.executeQuery(sql);
            }

            ArrayList<String[]> purchase = new ArrayList<String[]>();
            while (resultSet.next()){
                String[] tempt = new String[5];
                tempt[0] = resultSet.getString("traveldeid");
                tempt[1] = resultSet.getString("seatdetype");
                tempt[2] = resultSet.getString("seatdeleft");
                tempt[3] = resultSet.getString("departdestation_id");
                tempt[4] = resultSet.getString("arrivedestation_id");
                purchase.add(tempt);
            }

            int executeLine = 0;
            for(int i=0; i<purchase.size(); i++){
                String[] tempt = purchase.get(i);
                if(ProgrammeStart.con != null){
                    String sql = "update public.seat_remain set seat_left = " + (Integer.parseInt(tempt[2])-1) +
                            " where travel_id = " + tempt[0] + " and depart_station_id = " + tempt[3] + " and arrive_station_id = " + tempt[4] + " and seat_type = '" + tempt[1] + "';";
                    executeLine += stmt0.executeUpdate(sql);
                }
            }

            if(executeLine == purchase.size()){
                if(ProgrammeStart.con != null){
                    String sql = "commit;";
                    stmt0.execute(sql);
                }
                ProgrammeStart.con.commit();

                Stage stage = (Stage) book2.getScene().getWindow();
                stage.close();
                TravelDetailWindow travelDetailWindow = new TravelDetailWindow();
                travelDetailWindow.showWindow();

                PurchaseWindowController.success = true;
                PurchaseWindow purchaseWindow = new PurchaseWindow();
                purchaseWindow.showWindow();
            }else {
                PurchaseWindowController.success = false;
                PurchaseWindow purchaseWindow = new PurchaseWindow();
                purchaseWindow.showWindow();
            }
        }else{
            PurchaseWindowController.success = false;
            PurchaseWindow purchaseWindow = new PurchaseWindow();
            purchaseWindow.showWindow();
        }
    }

    public void book3() throws Exception{
        Properties defprop = new Properties();
        defprop.put("host", "localhost");
        defprop.put("user", "ilori");
        defprop.put("password", "990114");
        defprop.put("database", "project2_v2");
        Properties prop = new Properties(defprop);

        ProgrammeStart.openDB(prop.getProperty("host"), prop.getProperty("database"),
                prop.getProperty("user"), prop.getProperty("password"));
        Statement stmt0 = ProgrammeStart.con.createStatement();
        ResultSet resultSet = null;

        if(ProgrammeStart.con != null){
            String sql = "begin;";
            stmt0.execute(sql);
        }

        if (ProgrammeStart.con != null) {
            String sql = "select * from create_order('" + ProgrammeStart.userID + "', " + travel_id + ", " + depart_id + ", " + arrive_id + ", '" + type3.getText() + "');";
            resultSet = stmt0.executeQuery(sql);
        }

        String created = "";
        while (resultSet.next()){
            created = resultSet.getString("create_order");
        }

        resultSet = null;

        if(created.equals("true")){
            if (ProgrammeStart.con != null){
                String sql = "select * from buy_ticket(" + travel_id + ", '" + type3.getText() + "', " + depart_id + ", " + arrive_id + ");";
                resultSet = stmt0.executeQuery(sql);
            }

            ArrayList<String[]> purchase = new ArrayList<String[]>();
            while (resultSet.next()){
                String[] tempt = new String[5];
                tempt[0] = resultSet.getString("traveldeid");
                tempt[1] = resultSet.getString("seatdetype");
                tempt[2] = resultSet.getString("seatdeleft");
                tempt[3] = resultSet.getString("departdestation_id");
                tempt[4] = resultSet.getString("arrivedestation_id");
                purchase.add(tempt);
            }

            int executeLine = 0;
            for(int i=0; i<purchase.size(); i++){
                String[] tempt = purchase.get(i);
                if(ProgrammeStart.con != null){
                    String sql = "update public.seat_remain set seat_left = " + (Integer.parseInt(tempt[2])-1) +
                            " where travel_id = " + tempt[0] + " and depart_station_id = " + tempt[3] + " and arrive_station_id = " + tempt[4] + " and seat_type = '" + tempt[1] + "';";
                    executeLine += stmt0.executeUpdate(sql);
                }
            }

            if(executeLine == purchase.size()){
                if(ProgrammeStart.con != null){
                    String sql = "commit;";
                    stmt0.execute(sql);
                }
                ProgrammeStart.con.commit();

                Stage stage = (Stage) book3.getScene().getWindow();
                stage.close();
                TravelDetailWindow travelDetailWindow = new TravelDetailWindow();
                travelDetailWindow.showWindow();

                PurchaseWindowController.success = true;
                PurchaseWindow purchaseWindow = new PurchaseWindow();
                purchaseWindow.showWindow();
            }else {
                PurchaseWindowController.success = false;
                PurchaseWindow purchaseWindow = new PurchaseWindow();
                purchaseWindow.showWindow();
            }
        }else{
            PurchaseWindowController.success = false;
            PurchaseWindow purchaseWindow = new PurchaseWindow();
            purchaseWindow.showWindow();
        }
    }

    public void book4() throws Exception{
        Properties defprop = new Properties();
        defprop.put("host", "localhost");
        defprop.put("user", "ilori");
        defprop.put("password", "990114");
        defprop.put("database", "project2_v2");
        Properties prop = new Properties(defprop);

        ProgrammeStart.openDB(prop.getProperty("host"), prop.getProperty("database"),
                prop.getProperty("user"), prop.getProperty("password"));
        Statement stmt0 = ProgrammeStart.con.createStatement();
        ResultSet resultSet = null;

        if(ProgrammeStart.con != null){
            String sql = "begin;";
            stmt0.execute(sql);
        }

        if (ProgrammeStart.con != null) {
            String sql = "select * from create_order('" + ProgrammeStart.userID + "', " + travel_id + ", " + depart_id + ", " + arrive_id + ", '" + type4.getText() + "');";
            resultSet = stmt0.executeQuery(sql);
        }

        String created = "";
        while (resultSet.next()){
            created = resultSet.getString("create_order");
        }

        resultSet = null;

        if(created.equals("true")){
            if (ProgrammeStart.con != null){
                String sql = "select * from buy_ticket(" + travel_id + ", '" + type4.getText() + "', " + depart_id + ", " + arrive_id + ");";
                resultSet = stmt0.executeQuery(sql);
            }

            ArrayList<String[]> purchase = new ArrayList<String[]>();
            while (resultSet.next()){
                String[] tempt = new String[5];
                tempt[0] = resultSet.getString("traveldeid");
                tempt[1] = resultSet.getString("seatdetype");
                tempt[2] = resultSet.getString("seatdeleft");
                tempt[3] = resultSet.getString("departdestation_id");
                tempt[4] = resultSet.getString("arrivedestation_id");
                purchase.add(tempt);
            }

            int executeLine = 0;
            for(int i=0; i<purchase.size(); i++){
                String[] tempt = purchase.get(i);
                if(ProgrammeStart.con != null){
                    String sql = "update public.seat_remain set seat_left = " + (Integer.parseInt(tempt[2])-1) +
                            " where travel_id = " + tempt[0] + " and depart_station_id = " + tempt[3] + " and arrive_station_id = " + tempt[4] + " and seat_type = '" + tempt[1] + "';";
                    executeLine += stmt0.executeUpdate(sql);
                }
            }

            if(executeLine == purchase.size()){
                if(ProgrammeStart.con != null){
                    String sql = "commit;";
                    stmt0.execute(sql);
                }
                ProgrammeStart.con.commit();

                Stage stage = (Stage) book4.getScene().getWindow();
                stage.close();
                TravelDetailWindow travelDetailWindow = new TravelDetailWindow();
                travelDetailWindow.showWindow();

                PurchaseWindowController.success = true;
                PurchaseWindow purchaseWindow = new PurchaseWindow();
                purchaseWindow.showWindow();
            }else {
                ProgrammeStart.con.rollback();
                PurchaseWindowController.success = false;
                PurchaseWindow purchaseWindow = new PurchaseWindow();
                purchaseWindow.showWindow();
            }
        }else{
            ProgrammeStart.con.rollback();
            PurchaseWindowController.success = false;
            PurchaseWindow purchaseWindow = new PurchaseWindow();
            purchaseWindow.showWindow();
        }
    }
}