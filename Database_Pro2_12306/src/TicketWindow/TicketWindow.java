package TicketWindow;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicketWindow extends Application {
    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ticketWindow.fxml"));
        primaryStage.setTitle("我的订单");
        primaryStage.setScene(new Scene(root, 500, 800));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    public void showWindow() throws Exception {
        start(stage);
    }


}
