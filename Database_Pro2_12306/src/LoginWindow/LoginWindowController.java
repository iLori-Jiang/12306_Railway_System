package LoginWindow;

import MainWindow.MainWindow;
import RegisterWindow.RegisterWindow;
import Start.ProgrammeStart;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoginWindowController implements Initializable {
    public static String userPhone;

    public LoginWindowController() {

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    //关闭本窗口并打开新窗口
    @FXML
    private javafx.scene.control.Button loginButton;
    //保存用户名
    @FXML
    public javafx.scene.control.TextField userNameTextField;

    @FXML
    public void loginCheck() throws Exception {
        userPhone = userNameTextField.getText();
        boolean userExist;

        boolean isInjection = false;
        isInjection = ProgrammeStart.isSqlInjection(userPhone);
        if(isInjection == false){
            Properties defprop = new Properties();
            defprop.put("host", "localhost");
            defprop.put("user", "ilori");
            defprop.put("password", "990114");
            defprop.put("database", "project2_v2");
            Properties prop = new Properties(defprop);

            ProgrammeStart.openDB(prop.getProperty("host"), prop.getProperty("database"),
                    prop.getProperty("user"), prop.getProperty("password"));
            Statement stmt0 = ProgrammeStart.con.createStatement();
            ResultSet userSet = null;

            if (ProgrammeStart.con != null) {
                String sql = "select * from public.User where phone_number = '" + userPhone + "';";
                userSet = stmt0.executeQuery(sql);

            }

            while (userSet.next()){
                ProgrammeStart.userName = userSet.getString("first_name") + " " + userSet.getString("last_name");
                ProgrammeStart.userID = userSet.getString("id_card");
            }

            if (ProgrammeStart.userName == null){
                userExist = false;
            }else {
                userExist = true;
            }

            if(userExist){
                System.out.println("Welcome back!");
                System.out.println(ProgrammeStart.userName);
                changeToMainWindow();
            }else if(!userExist){
                changeToRegisterWindow();
            }

            userSet.close();
            stmt0.close();
            ProgrammeStart.closeDB();

        }else{
            LoginWindow loginWindow = new LoginWindow();
            loginWindow.showWindow();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
        }


    }

    @FXML
    public void changeToMainWindow() throws Exception {
        MainWindow mainWindow = new MainWindow();
        mainWindow.showWindow();
        //关闭原来的window
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }



    @FXML
    public void changeToRegisterWindow() throws Exception {
        RegisterWindow registerWindow = new RegisterWindow();
        registerWindow.showWindow();
        //关闭原来的window
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();
    }



    @FXML
    private javafx.scene.control.Button quitButton;

    @FXML
    public void quitProgramme() {
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }







}
