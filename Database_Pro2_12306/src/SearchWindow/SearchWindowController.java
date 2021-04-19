package SearchWindow;

import MainWindow.MainWindow;
import MainWindow.MainWindowController;
import Start.ProgrammeStart;
import TravelDetailWindow.TravelDetailWindow;
import TravelDetailWindow.TravelDetailWindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchWindowController implements Initializable {
    public static int currentPage;

    private boolean hasIt1;
    private boolean hasIt2;
    private boolean hasIt3;
    private boolean hasIt4;

    private String travel_id1;
    private String travel_id2;
    private String travel_id3;
    private String travel_id4;

    @FXML
    private javafx.scene.control.Label departCity;

    @FXML
    private javafx.scene.control.Label arriveCity;

    @FXML
    private javafx.scene.control.Label date;

    @FXML
    private javafx.scene.control.Label departTime1;
    @FXML
    private javafx.scene.control.Label arriveTime1;
    @FXML
    private javafx.scene.control.Label time1;
    @FXML
    private javafx.scene.control.Label train1;
    @FXML
    private javafx.scene.control.Label departStation1;
    @FXML
    private javafx.scene.control.Label arriveStation1;
    @FXML
    private javafx.scene.control.Label departTime2;
    @FXML
    private javafx.scene.control.Label arriveTime2;
    @FXML
    private javafx.scene.control.Label time2;
    @FXML
    private javafx.scene.control.Label train2;
    @FXML
    private javafx.scene.control.Label departStation2;
    @FXML
    private javafx.scene.control.Label arriveStation2;
    @FXML
    private javafx.scene.control.Label departTime3;
    @FXML
    private javafx.scene.control.Label arriveTime3;
    @FXML
    private javafx.scene.control.Label time3;
    @FXML
    private javafx.scene.control.Label train3;
    @FXML
    private javafx.scene.control.Label departStation3;
    @FXML
    private javafx.scene.control.Label arriveStation3;
    @FXML
    private javafx.scene.control.Label departTime4;
    @FXML
    private javafx.scene.control.Label arriveTime4;
    @FXML
    private javafx.scene.control.Label time4;
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


    public static ArrayList<String[]> searchResult;



    public SearchWindowController() {

    }

    @Override
    public void initialize(URL args0, ResourceBundle arg1) {

        PageNumber.setText(Integer.toString(currentPage));

        departCity.setText(MainWindowController.departCity);
        arriveCity.setText(MainWindowController.arriveCity);
        date.setText(MainWindowController.departDate);


        if (SearchWindowController.searchResult.size() >= 4 * currentPage - 3) {
            String[] temp = SearchWindowController.searchResult.get(4 * currentPage - 4);
            departTime1.setText(temp[2]);
            departStation1.setText(temp[1]);
            arriveTime1.setText(temp[4]);
            arriveStation1.setText(temp[3]);
            time1.setText(temp[5]);
            train1.setText(temp[6]);
            travel_id1 = temp[0];

            hasIt1 = true;
        } else {
            departTime1.setText("");
            departStation1.setText("");
            arriveTime1.setText("");
            arriveStation1.setText("");
            time1.setText("");
            train1.setText("");
            line1.setStartX(0);
            line1.setEndX(0);
            travel_id1 = null;

            hasIt1 = false;
        }

        if (SearchWindowController.searchResult.size() >= 4 * currentPage - 2) {
            String[] temp = SearchWindowController.searchResult.get(4 * currentPage - 3);
            departTime2.setText(temp[2]);
            departStation2.setText(temp[1]);
            arriveTime2.setText(temp[4]);
            arriveStation2.setText(temp[3]);
            time2.setText(temp[5]);
            train2.setText(temp[6]);
            travel_id2 = temp[0];

            hasIt2 = true;
        } else {
            departTime2.setText("");
            departStation2.setText("");
            arriveTime2.setText("");
            arriveStation2.setText("");
            time2.setText("");
            train2.setText("");
            line2.setStartX(0);
            line2.setEndX(0);
            travel_id2 = null;

            hasIt2 = false;
        }

        if (SearchWindowController.searchResult.size() >= 4 * currentPage - 1) {
            String[] temp = SearchWindowController.searchResult.get(4 * currentPage - 2);
            departTime3.setText(temp[2]);
            departStation3.setText(temp[1]);
            arriveTime3.setText(temp[4]);
            arriveStation3.setText(temp[3]);
            time3.setText(temp[5]);
            train3.setText(temp[6]);
            travel_id3 = temp[0];

            hasIt3 = true;
        } else {
            departTime3.setText("");
            departStation3.setText("");
            arriveTime3.setText("");
            arriveStation3.setText("");
            time3.setText("");
            train3.setText("");
            line3.setStartX(0);
            line3.setEndX(0);
            travel_id3 = null;

            hasIt3 = false;
        }

        if (SearchWindowController.searchResult.size() >= 4 * currentPage) {
            String[] temp = SearchWindowController.searchResult.get(4 * currentPage - 1);
            departTime4.setText(temp[2]);
            departStation4.setText(temp[1]);
            arriveTime4.setText(temp[4]);
            arriveStation4.setText(temp[3]);
            time4.setText(temp[5]);
            train4.setText(temp[6]);
            travel_id4 = temp[0];

            hasIt4 = true;
        } else {
            departTime4.setText("");
            departStation4.setText("");
            arriveTime4.setText("");
            arriveStation4.setText("");
            time4.setText("");
            train4.setText("");
            line4.setStartX(0);
            line4.setEndX(0);
            travel_id4 = null;

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

        /////////////////////////////////////////////////////////////////鼠标单击诗词动作
        travel1.setOnMouseClicked(event -> {
            if (hasIt1) {
                TravelDetailWindowController.travel_id = travel_id1;
                TravelDetailWindowController.arrive_station = arriveStation1.getText();
                TravelDetailWindowController.depart_station = departStation1.getText();
                TravelDetailWindowController.depart_time = departTime1.getText();
                TravelDetailWindowController.arrive_time = arriveTime1.getText();
                TravelDetailWindowController.train_id = train1.getText();
                TravelDetailWindowController.depart_date = date.getText();

                TravelDetailWindow travelDetailWindow = new TravelDetailWindow();
                try {
                    travelDetailWindow.showWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //关闭原来的window
                Stage stage = (Stage) travel1.getScene().getWindow();
                stage.close();
            }
        });
        travel2.setOnMouseClicked(event -> {
            if (hasIt2) {
                TravelDetailWindowController.travel_id = travel_id2;
                TravelDetailWindowController.arrive_station = arriveStation2.getText();
                TravelDetailWindowController.depart_station = departStation2.getText();
                TravelDetailWindowController.depart_time = departTime2.getText();
                TravelDetailWindowController.arrive_time = arriveTime2.getText();
                TravelDetailWindowController.train_id = train2.getText();
                TravelDetailWindowController.depart_date = date.getText();

                TravelDetailWindow travelDetailWindow = new TravelDetailWindow();
                try {
                    travelDetailWindow.showWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //关闭原来的window
                Stage stage = (Stage) travel2.getScene().getWindow();
                stage.close();
            }

        });
        travel3.setOnMouseClicked(event -> {
            if (hasIt3) {
                TravelDetailWindowController.travel_id = travel_id3;
                TravelDetailWindowController.arrive_station = arriveStation3.getText();
                TravelDetailWindowController.depart_station = departStation3.getText();
                TravelDetailWindowController.depart_time = departTime3.getText();
                TravelDetailWindowController.arrive_time = arriveTime3.getText();
                TravelDetailWindowController.train_id = train3.getText();
                TravelDetailWindowController.depart_date = date.getText();

                TravelDetailWindow travelDetailWindow = new TravelDetailWindow();
                try {
                    travelDetailWindow.showWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //关闭原来的window
                Stage stage = (Stage) travel3.getScene().getWindow();
                stage.close();
            }

        });
        travel4.setOnMouseClicked(event -> {
            if (hasIt4) {
                TravelDetailWindowController.travel_id = travel_id4;
                TravelDetailWindowController.arrive_station = arriveStation4.getText();
                TravelDetailWindowController.depart_station = departStation4.getText();
                TravelDetailWindowController.depart_time = departTime4.getText();
                TravelDetailWindowController.arrive_time = arriveTime4.getText();
                TravelDetailWindowController.train_id = train4.getText();
                TravelDetailWindowController.depart_date = date.getText();

                TravelDetailWindow travelDetailWindow = new TravelDetailWindow();
                try {
                    travelDetailWindow.showWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //关闭原来的window
                Stage stage = (Stage) travel4.getScene().getWindow();
                stage.close();
            }

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

        if (hasIt4 == true && SearchWindowController.searchResult.size() > 4 * currentPage) {
            currentPage += 1;
            SearchWindow searchWindow = new SearchWindow();
            searchWindow.showWindow();
            Stage stage = (Stage) nextPageButton.getScene().getWindow();
            stage.close();
        }


    }

    public void previousPage() throws Exception {
        if (currentPage != 1) {
            currentPage -= 1;
            SearchWindow searchWindow = new SearchWindow();
            searchWindow.showWindow();
            Stage stage = (Stage) previousPageButton.getScene().getWindow();
            stage.close();
        }

    }




}

