package TicketWindow;

import MainWindow.MainWindow;
import MainWindow.MainWindowController;
import RefundWindow.RefundWindow;
import RefundWindow.RefundWindowController;
import Start.ProgrammeStart;
import TravelDetailWindow.TravelDetailWindow;
import TravelDetailWindow.TravelDetailWindowController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class TicketWindowController implements Initializable {
    public static int currentPage;

    private boolean hasIt1;
    private boolean hasIt2;
    private boolean hasIt3;
    private boolean hasIt4;

    private String travel_id1;
    private String travel_id2;
    private String travel_id3;
    private String travel_id4;

    private String depart_id1;
    private String arrive_id1;
    private String depart_id2;
    private String arrive_id2;
    private String depart_id3;
    private String arrive_id3;
    private String depart_id4;
    private String arrive_id4;

    private String order_id1;
    private String order_id2;
    private String order_id3;
    private String order_id4;

    @FXML
    private javafx.scene.control.Label departTime1;
    @FXML
    private javafx.scene.control.Label departDate1;
    @FXML
    private javafx.scene.control.Label type1;
    @FXML
    private javafx.scene.control.Label seat1;
    @FXML
    private javafx.scene.control.Label price1;
    @FXML
    private javafx.scene.control.Label train1;
    @FXML
    private javafx.scene.control.Label departStation1;
    @FXML
    private javafx.scene.control.Label arriveStation1;
    @FXML
    private javafx.scene.control.Label departTime2;
    @FXML
    private javafx.scene.control.Label departDate2;
    @FXML
    private javafx.scene.control.Label type2;
    @FXML
    private javafx.scene.control.Label seat2;
    @FXML
    private javafx.scene.control.Label price2;
    @FXML
    private javafx.scene.control.Label train2;
    @FXML
    private javafx.scene.control.Label departStation2;
    @FXML
    private javafx.scene.control.Label arriveStation2;
    @FXML
    private javafx.scene.control.Label departTime3;
    @FXML
    private javafx.scene.control.Label departDate3;
    @FXML
    private javafx.scene.control.Label type3;
    @FXML
    private javafx.scene.control.Label seat3;
    @FXML
    private javafx.scene.control.Label price3;
    @FXML
    private javafx.scene.control.Label train3;
    @FXML
    private javafx.scene.control.Label departStation3;
    @FXML
    private javafx.scene.control.Label arriveStation3;
    @FXML
    private javafx.scene.control.Label departTime4;
    @FXML
    private javafx.scene.control.Label departDate4;
    @FXML
    private javafx.scene.control.Label type4;
    @FXML
    private javafx.scene.control.Label seat4;
    @FXML
    private javafx.scene.control.Label price4;
    @FXML
    private javafx.scene.control.Label train4;
    @FXML
    private javafx.scene.control.Label departStation4;
    @FXML
    private javafx.scene.control.Label arriveStation4;
    @FXML
    private javafx.scene.shape.Line line1;
    @FXML
    private javafx.scene.shape.Line line2;
    @FXML
    private javafx.scene.shape.Line line3;
    @FXML
    private javafx.scene.shape.Line line4;
    @FXML
    private javafx.scene.control.Button refund1;
    @FXML
    private javafx.scene.control.Button refund2;
    @FXML
    private javafx.scene.control.Button refund3;
    @FXML
    private javafx.scene.control.Button refund4;


    public static ArrayList<String[]> ticketResult;



    public TicketWindowController() {

    }

    @Override
    public void initialize(URL args0, ResourceBundle arg1) {

        PageNumber.setText(Integer.toString(currentPage));

        if (TicketWindowController.ticketResult.size() >= 4 * currentPage - 3) {
            String[] temp = TicketWindowController.ticketResult.get(4 * currentPage - 4);
            travel_id1 = temp[0];
            departStation1.setText(temp[1]);
            arriveStation1.setText(temp[2]);
            train1.setText(temp[3]);
            departDate1.setText(temp[4].substring(0,10));
            departTime1.setText(temp[4].substring(11,16));
            type1.setText(temp[6]);
            price1.setText("¥" + temp[7]);
            depart_id1 = temp[8];
            arrive_id1 = temp[9];
            order_id1 = temp[10];
            seat1.setText(temp[11]);

            hasIt1 = true;
        } else {
            departTime1.setText("");
            departStation1.setText("");
            departDate1.setText("");
            arriveStation1.setText("");
            type1.setText("");
            train1.setText("");
            line1.setStartX(0);
            line1.setEndX(0);
            price1.setText("");
            seat1.setText("");
            refund1.setVisible(false);
            travel_id1 = null;
            depart_id1 = null;
            arrive_id1 = null;
            order_id1 = null;

            hasIt1 = false;
        }

        if (TicketWindowController.ticketResult.size() >= 4 * currentPage - 2) {
            String[] temp = TicketWindowController.ticketResult.get(4 * currentPage - 3);
            travel_id2 = temp[0];
            departStation2.setText(temp[1]);
            arriveStation2.setText(temp[2]);
            train2.setText(temp[3]);
            departDate2.setText(temp[4].substring(0,10));
            departTime2.setText(temp[4].substring(11,16));
            type2.setText(temp[6]);
            price2.setText("¥" + temp[7]);
            depart_id2 = temp[8];
            arrive_id2 = temp[9];
            order_id2 = temp[10];
            seat2.setText(temp[11]);

            hasIt2 = true;
        } else {
            departTime2.setText("");
            departStation2.setText("");
            departDate2.setText("");
            arriveStation2.setText("");
            type2.setText("");
            train2.setText("");
            line2.setStartX(0);
            line2.setEndX(0);
            price2.setText("");
            seat2.setText("");
            refund2.setVisible(false);
            travel_id2 = null;
            depart_id2 = null;
            arrive_id2 = null;
            order_id2 = null;

            hasIt2 = false;
        }

        if (TicketWindowController.ticketResult.size() >= 4 * currentPage - 1) {
            String[] temp = TicketWindowController.ticketResult.get(4 * currentPage - 2);
            travel_id3 = temp[0];
            departStation3.setText(temp[1]);
            arriveStation3.setText(temp[2]);
            train3.setText(temp[3]);
            departDate3.setText(temp[4].substring(0,10));
            departTime3.setText(temp[4].substring(11,16));
            type3.setText(temp[6]);
            price3.setText("¥" + temp[7]);
            depart_id3 = temp[8];
            arrive_id3 = temp[9];
            order_id3 = temp[10];
            seat3.setText(temp[11]);

            hasIt3 = true;
        } else {
            departTime3.setText("");
            departStation3.setText("");
            departDate3.setText("");
            arriveStation3.setText("");
            type3.setText("");
            train3.setText("");
            line3.setStartX(0);
            line3.setEndX(0);
            price3.setText("");
            seat3.setText("");
            refund3.setVisible(false);
            travel_id3 = null;
            depart_id3 = null;
            arrive_id3 = null;
            order_id3 = null;

            hasIt3 = false;
        }

        if (TicketWindowController.ticketResult.size() >= 4 * currentPage) {
            String[] temp = TicketWindowController.ticketResult.get(4 * currentPage - 1);
            travel_id4 = temp[0];
            departStation4.setText(temp[1]);
            arriveStation4.setText(temp[2]);
            train4.setText(temp[3]);
            departDate4.setText(temp[4].substring(0,10));
            departTime4.setText(temp[4].substring(11,16));
            type4.setText(temp[6]);
            price4.setText("¥" + temp[7]);
            depart_id4 = temp[8];
            arrive_id4 = temp[9];
            order_id4 = temp[10];
            seat4.setText(temp[11]);

            hasIt4 = true;
        } else {
            departTime4.setText("");
            departStation4.setText("");
            departDate4.setText("");
            arriveStation4.setText("");
            type4.setText("");
            train4.setText("");
            line4.setStartX(0);
            line4.setEndX(0);
            price4.setText("");
            seat4.setText("");
            refund4.setVisible(false);
            travel_id4 = null;
            depart_id4 = null;
            arrive_id4 = null;
            order_id4 = null;

            hasIt4 = false;
        }


        //鼠标所选变灰
        travel1.setOnMouseEntered(event -> {
            if (hasIt1) {
                travel1.setStyle("-fx-background-color:#D7D7D7");
            }
        });
        travel2.setOnMouseEntered(event -> {
            if (hasIt2) {
                travel2.setStyle("-fx-background-color:#D7D7D7");
            }
        });
        travel3.setOnMouseEntered(event -> {
            if (hasIt3) {
                travel3.setStyle("-fx-background-color:#D7D7D7");
            }
        });
        travel4.setOnMouseEntered(event -> {
            if (hasIt4) {
                travel4.setStyle("-fx-background-color:#D7D7D7");
            }
        });
        travel1.setOnMouseExited(event -> {
            travel1.setStyle("-fx-background-color:#F4F4F4");
        });
        travel2.setOnMouseExited(event -> {
            travel2.setStyle("-fx-background-color:#F4F4F4");
        });
        travel3.setOnMouseExited(event -> {
            travel3.setStyle("-fx-background-color:#F4F4F4");
        });
        travel4.setOnMouseExited(event -> {
            travel4.setStyle("-fx-background-color:#F4F4F4");
        });

    }




    @FXML
    public javafx.scene.control.Button HomeButton;

    @FXML
    public void backToHome() throws Exception {
        MainWindow mainWindow = new MainWindow();
        mainWindow.showWindow();
        Stage stage = (Stage) HomeButton.getScene().getWindow();
        stage.close();
    }


    @FXML
    private javafx.scene.layout.AnchorPane travel1;
    @FXML
    private javafx.scene.layout.AnchorPane travel2;
    @FXML
    private javafx.scene.layout.AnchorPane travel3;
    @FXML
    private javafx.scene.layout.AnchorPane travel4;


    @FXML
    private javafx.scene.control.Button previousPageButton;

    @FXML
    private javafx.scene.control.Button nextPageButton;

    @FXML
    private javafx.scene.control.Label PageNumber;


    public void nextPage() throws Exception {

        if (hasIt4 == true && TicketWindowController.ticketResult.size() > 4 * currentPage) {
            currentPage += 1;
            TicketWindow ticketWindow = new TicketWindow();
            ticketWindow.showWindow();
            Stage stage = (Stage) nextPageButton.getScene().getWindow();
            stage.close();
        }


    }

    public void previousPage() throws Exception {
        if (currentPage != 1) {
            currentPage -= 1;
            TicketWindow ticketWindow = new TicketWindow();
            ticketWindow.showWindow();
            Stage stage = (Stage) previousPageButton.getScene().getWindow();
            stage.close();
        }

    }

    public void setRefund1() throws Exception{
        Properties defprop = new Properties();
        defprop.put("host", "localhost");
        defprop.put("user", "ilori");
        defprop.put("password", "990114");
        defprop.put("database", "project2_v2");
        Properties prop = new Properties(defprop);

        ProgrammeStart.openDB(prop.getProperty("host"), prop.getProperty("database"),
                prop.getProperty("user"), prop.getProperty("password"));
        Statement stmt0 = ProgrammeStart.con.createStatement();
        int executeLine = 0;

        if (ProgrammeStart.con != null) {
            String sql = "update public.order set order_status = 'N' where order_id = " + order_id1 + ";";
            executeLine = stmt0.executeUpdate(sql);
        }

        ProgrammeStart.con.commit();

        stmt0.close();
        ProgrammeStart.closeDB();

        ticketResult.remove(4 * currentPage - 4);

        if (executeLine == 1){
            RefundWindowController.success = true;

            Stage stage = (Stage) refund1.getScene().getWindow();
            stage.close();
            TicketWindow ticketWindow = new TicketWindow();
            ticketWindow.showWindow();
            RefundWindow refundWindow = new RefundWindow();
            refundWindow.showWindow();
        }else{
            RefundWindowController.success = false;

            Stage stage = (Stage) refund1.getScene().getWindow();
            stage.close();
            TicketWindow ticketWindow = new TicketWindow();
            ticketWindow.showWindow();
            RefundWindow refundWindow = new RefundWindow();
            refundWindow.showWindow();
        }
    }

    public void setRefund2() throws Exception{
        Properties defprop = new Properties();
        defprop.put("host", "localhost");
        defprop.put("user", "ilori");
        defprop.put("password", "990114");
        defprop.put("database", "project2_v2");
        Properties prop = new Properties(defprop);

        ProgrammeStart.openDB(prop.getProperty("host"), prop.getProperty("database"),
                prop.getProperty("user"), prop.getProperty("password"));
        Statement stmt0 = ProgrammeStart.con.createStatement();
        int executeLine = 0;

        if (ProgrammeStart.con != null) {
            String sql = "update public.order set order_status = 'N' where order_id = " + order_id2 + ";";
            executeLine = stmt0.executeUpdate(sql);
        }

        ProgrammeStart.con.commit();

        stmt0.close();
        ProgrammeStart.closeDB();

        ticketResult.remove(4 * currentPage - 3);

        if (executeLine == 1){
            RefundWindowController.success = true;

            Stage stage = (Stage) refund2.getScene().getWindow();
            stage.close();
            TicketWindow ticketWindow = new TicketWindow();
            ticketWindow.showWindow();
            RefundWindow refundWindow = new RefundWindow();
            refundWindow.showWindow();
        }else{
            RefundWindowController.success = false;

            Stage stage = (Stage) refund2.getScene().getWindow();
            stage.close();
            TicketWindow ticketWindow = new TicketWindow();
            ticketWindow.showWindow();
            RefundWindow refundWindow = new RefundWindow();
            refundWindow.showWindow();
        }
    }

    public void setRefund3() throws Exception{
        Properties defprop = new Properties();
        defprop.put("host", "localhost");
        defprop.put("user", "ilori");
        defprop.put("password", "990114");
        defprop.put("database", "project2_v2");
        Properties prop = new Properties(defprop);

        ProgrammeStart.openDB(prop.getProperty("host"), prop.getProperty("database"),
                prop.getProperty("user"), prop.getProperty("password"));
        Statement stmt0 = ProgrammeStart.con.createStatement();
        int executeLine = 0;

        if (ProgrammeStart.con != null) {
            String sql = "update public.order set order_status = 'N' where order_id = " + order_id3 + ";";
            executeLine = stmt0.executeUpdate(sql);
        }

        ProgrammeStart.con.commit();

        stmt0.close();
        ProgrammeStart.closeDB();

        ticketResult.remove(4 * currentPage - 2);

        if (executeLine == 1){
            RefundWindowController.success = true;

            Stage stage = (Stage) refund3.getScene().getWindow();
            stage.close();
            TicketWindow ticketWindow = new TicketWindow();
            ticketWindow.showWindow();
            RefundWindow refundWindow = new RefundWindow();
            refundWindow.showWindow();
        }else{
            RefundWindowController.success = false;

            Stage stage = (Stage) refund3.getScene().getWindow();
            stage.close();
            TicketWindow ticketWindow = new TicketWindow();
            ticketWindow.showWindow();
            RefundWindow refundWindow = new RefundWindow();
            refundWindow.showWindow();
        }
    }

    public void setRefund4() throws Exception{
        Properties defprop = new Properties();
        defprop.put("host", "localhost");
        defprop.put("user", "ilori");
        defprop.put("password", "990114");
        defprop.put("database", "project2_v2");
        Properties prop = new Properties(defprop);

        ProgrammeStart.openDB(prop.getProperty("host"), prop.getProperty("database"),
                prop.getProperty("user"), prop.getProperty("password"));
        Statement stmt0 = ProgrammeStart.con.createStatement();
        int executeLine = 0;

        if (ProgrammeStart.con != null) {
            String sql = "update public.order set order_status = 'N' where order_id = " + order_id4 + ";";
            executeLine = stmt0.executeUpdate(sql);
        }

        ProgrammeStart.con.commit();

        stmt0.close();
        ProgrammeStart.closeDB();

        ticketResult.remove(4 * currentPage - 1);

        if (executeLine == 1){
            RefundWindowController.success = true;

            Stage stage = (Stage) refund4.getScene().getWindow();
            stage.close();
            TicketWindow ticketWindow = new TicketWindow();
            ticketWindow.showWindow();
            RefundWindow refundWindow = new RefundWindow();
            refundWindow.showWindow();
        }else{
            RefundWindowController.success = false;

            Stage stage = (Stage) refund4.getScene().getWindow();
            stage.close();
            TicketWindow ticketWindow = new TicketWindow();
            ticketWindow.showWindow();
            RefundWindow refundWindow = new RefundWindow();
            refundWindow.showWindow();
        }
    }


}

