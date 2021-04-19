package MainWindow;

import LoginWindow.LoginWindow;
import SearchWindow.SearchWindow;
import SearchWindow.SearchWindowController;
import Start.ProgrammeStart;
import TicketWindow.TicketWindow;
import TicketWindow.TicketWindowController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

// import sun.applet.Main;

public class MainWindowController implements Initializable {
    public static String departCity;
    public static String arriveCity;
    public static String departDate;

    @FXML
    private Label userID;


    public MainWindowController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        userID.setText(ProgrammeStart.userName);

    }

    @FXML
    private Button logOutButton;

    @FXML
    public void logOut() throws Exception {
        ProgrammeStart.userName = null;
        ProgrammeStart.userID = null;
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.showWindow();
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private Button searchButton;

    @FXML
    private TextField depart;

    @FXML
    private TextField arrive;

    @FXML
    private TextField date;

    @FXML
    public void changeToSearchWindow() throws Exception {
        departCity = depart.getText();
        arriveCity = arrive.getText();
        departDate = date.getText();

        boolean isInjection1 = false;
        isInjection1 = ProgrammeStart.isSqlInjection(departCity);
        boolean isInjection2 = false;
        isInjection2 = ProgrammeStart.isSqlInjection(arriveCity);
        boolean isInjection3 = false;
        isInjection3 = ProgrammeStart.isSqlInjection(departDate);

        if(!isInjection1 && !isInjection2 && !isInjection3){
            Properties defprop = new Properties();
            defprop.put("host", "localhost");
            defprop.put("user", "ilori");
            defprop.put("password", "990114");
            defprop.put("database", "project2_v2");
            Properties prop = new Properties(defprop);

            ProgrammeStart.openDB(prop.getProperty("host"), prop.getProperty("database"),
                    prop.getProperty("user"), prop.getProperty("password"));
            Statement stmt0 = ProgrammeStart.con.createStatement();
            ResultSet travelSet = null;

            if (ProgrammeStart.con != null) {
                String sql = "select t1.travel_id as travel_id, s1.station_name as depart_station, cast(depart_time as varchar), s2.station_name as arrive_station, cast(arrive_time as varchar), (arrive_time-depart_time) as time, t.train_id as train_id\n" +
                        "from travel_search('" + departCity + "','" + arriveCity + "','" + departDate + "') t1\n" +
                        "    join station s1 on depart_station_id = s1.station_id\n" +
                        "    join station s2 on arrive_station_id = s2.station_id\n" +
                        "    join travel t on t1.travel_id = t.travel_id;";
                travelSet = stmt0.executeQuery(sql);

            }

            SearchWindowController.searchResult = new ArrayList<String[]>();

            while (travelSet.next()){
                String[] temp = new String[7];
                temp[0] = Integer.toString(travelSet.getInt("travel_id"));
                temp[1] = travelSet.getString("depart_station");
                temp[2] = travelSet.getString("depart_time").substring(11,16);
                temp[3] = travelSet.getString("arrive_station");
                temp[4] = travelSet.getString("arrive_time").substring(11,16);
                temp[5] = travelSet.getString("time").substring(0,5);
                temp[6] = travelSet.getString("train_id");

                SearchWindowController.searchResult.add(temp);
            }

            travelSet.close();
            stmt0.close();
            ProgrammeStart.closeDB();

            SearchWindowController.currentPage = 1;
            SearchWindow searchWindow = new SearchWindow();
            searchWindow.showWindow();
            Stage stage = (Stage) searchButton.getScene().getWindow();
            stage.close();

        }else {
            MainWindow mainWindow = new MainWindow();
            mainWindow.showWindow();
            Stage stage = (Stage) searchButton.getScene().getWindow();
            stage.close();
        }


    }


    @FXML
    private Button myOrderButton;

    @FXML
    public void changeToOrderWindow() throws Exception{
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

        if (ProgrammeStart.con != null) {
            String sql = "select t.travel_id as travel_id ,s2.station_name as depart_station, s.station_name as arrive_station, t2.train_id, ps.depart_time, ps2.arrive_time, t.seat_type, t.ticket_price, t.depart_station_id, t.arrive_station_id, o.order_id\n" +
                    "from public.order o\n" +
                    "join public.ticket t on t.order_id = o.order_id\n" +
                    "join public.station s on t.arrive_station_id = s.station_id\n" +
                    "join public.station s2 on t.depart_station_id = s2.station_id\n" +
                    "join public.travel t2 on t.travel_id = t2.travel_id\n" +
                    "join public.passing_station ps on t.depart_station_id = ps.station_id and t.travel_id = ps.travel_id\n" +
                    "join public.passing_station ps2 on t.arrive_station_id = ps2.station_id and t.travel_id = ps2.travel_id\n" +
                    "where o.id_card = '" + ProgrammeStart.userID + "' and o.order_status = 'Y'\norder by ps.depart_time;";
            resultSet = stmt0.executeQuery(sql);
        }

        TicketWindowController.ticketResult = new ArrayList<String[]>();

        while (resultSet.next()){
            String[] tempt = new String[12];
            tempt[0] = resultSet.getString("travel_id");
            tempt[1] = resultSet.getString("depart_station");
            tempt[2] = resultSet.getString("arrive_station");
            tempt[3] = resultSet.getString("train_id");
            tempt[4] = resultSet.getString("depart_time");
            tempt[5] = resultSet.getString("arrive_time");
            tempt[6] = resultSet.getString("seat_type");
            tempt[7] = resultSet.getString("ticket_price");
            tempt[8] = resultSet.getString("depart_station_id");
            tempt[9] = resultSet.getString("arrive_station_id");
            tempt[10] = resultSet.getString("order_id");
            tempt[11] = ProgrammeStart.generatingIntegerBounded(1,15) + "车" + ProgrammeStart.generatingIntegerBounded(1,20) + "座";

            TicketWindowController.ticketResult.add(tempt);
        }
        resultSet.close();
        stmt0.close();
        ProgrammeStart.closeDB();

        TicketWindowController.currentPage = 1;
        TicketWindow ticketWindow = new TicketWindow();
        ticketWindow.showWindow();
        Stage stage = (Stage) myOrderButton.getScene().getWindow();
        stage.close();
    }


}
