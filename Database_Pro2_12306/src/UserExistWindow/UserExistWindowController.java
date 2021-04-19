package UserExistWindow;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserExistWindowController implements Initializable {

    public UserExistWindowController(){

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

    }

    @FXML
    private javafx.scene.control.Button ok;

    @FXML
    public void ok() throws Exception{

        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }


}
