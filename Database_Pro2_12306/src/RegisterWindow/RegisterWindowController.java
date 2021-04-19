package RegisterWindow;

import LoginWindow.LoginWindow;
import MainWindow.MainWindow;
import Start.ProgrammeStart;
import UserExistWindow.UserExitWindow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

public class RegisterWindowController implements Initializable {

    public RegisterWindowController(){

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){

    }

    @FXML
    public javafx.scene.control.TextField firstNameTextField;

    @FXML
    public javafx.scene.control.TextField lastNameTextField;

    @FXML
    public javafx.scene.control.TextField phoneNumberTextField;

    @FXML
    public javafx.scene.control.TextField idCardTextField;

    @FXML
    private javafx.scene.control.Button yesButton;

    @FXML
    public void changeToMainWindow() throws Exception {
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String idCard = idCardTextField.getText();

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
            try{
                String sql = "insert into public.user (first_name, last_name, phone_number, id_card) VALUES " +
                        "('" + firstName +"','" + lastName + "','" + phoneNumber + "','" + idCard + "');";
                executeLine = stmt0.executeUpdate(sql);
                ProgrammeStart.con.commit();
            } catch (SQLException se){
                executeLine = 0;
            }

        }

        if(executeLine == 0){       // 添加失败
            UserExitWindow userExitWindow = new UserExitWindow();
            userExitWindow.showWindow();

            System.out.println("User exist");
        }else{                      // 添加成功
            ProgrammeStart.userName = firstName + " " + lastName;
            ProgrammeStart.userID = idCard;
            System.out.println("User added, " + ProgrammeStart.userName);

            MainWindow mainWindow = new MainWindow();
            mainWindow.showWindow();
            Stage stage = (Stage) yesButton.getScene().getWindow();
            stage.close();
        }

        stmt0.close();
        ProgrammeStart.closeDB();

    }

    @FXML
    private javafx.scene.control.Button noButton;

    @FXML
    public void changeToLoginWindow() throws Exception {
        LoginWindow loginWindow = new LoginWindow();
        loginWindow.showWindow();
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();
    }
}
