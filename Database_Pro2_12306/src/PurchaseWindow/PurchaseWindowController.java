package PurchaseWindow;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PurchaseWindowController implements Initializable {
    public static boolean success;

    public PurchaseWindowController(){

    }

    @FXML
    private javafx.scene.control.Label words;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        if(success){
            words.setText("购买成功");
        }else {
            words.setText("购买失败，请检查余票后重试");
        }
    }

    @FXML
    private javafx.scene.control.Button ok;

    @FXML
    public void ok() throws Exception{

        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }


}
