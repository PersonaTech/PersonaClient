package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerMenuScreenController {

    private Stage refToParent;
    @FXML
    private Button addSwButton;
    @FXML
    private Button addChildButton;
    @FXML
    private Button watchPerformanceButton;
    @FXML
    private Button watchDiagnosticsButton;

    //private Button watchAutoDiagnosticsButton;
    @FXML
    private Button exitButton;

    @FXML
    public void initialize(){

        System.out.println("ok the manager menu is open now");
    }

    public void initData(Stage refToParent){

        this.refToParent = refToParent;

    }

    public void onMenuButtonClicked(Event event){

        if (event.getSource().equals(addSwButton)){

            System.out.println("1");
            lunchSwScreen(event);

        }else if(event.getSource().equals(addChildButton)){

            System.out.println("2");
            lunchChildScreen(event);

        }else if(event.getSource().equals(watchPerformanceButton)){

            System.out.println("3");
            lunchWatchPerformanceScreen(event);

        }else if(event.getSource().equals(watchDiagnosticsButton)){

            System.out.println("4");
            lunchWorkersDiagnosticsScreen(event);

        }else if(event.getSource().equals(exitButton)){

            System.out.println("6");
            ((Stage)exitButton.getScene().getWindow()).close();
            this.refToParent.show();

        }

    }



    public void lunchSwScreen(Event event){

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddNewEmployeeScreen.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Add new Employee");

            stage.setScene(new Scene(fxmlLoader.load(), 1300, 800));

            AddNewEmployeeScreenController controller = fxmlLoader.<AddNewEmployeeScreenController>getController();
            controller.initData((Stage) addSwButton.getScene().getWindow());

            addSwButton.getScene().getWindow().hide();
            stage.show();


        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    public void lunchChildScreen(Event event){

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("AddNewChildScreen.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Add new Child");

            stage.setScene(new Scene(fxmlLoader.load(), 1300, 800));

            AddNewChildScreenController controller = fxmlLoader.<AddNewChildScreenController>getController();
            controller.initData((Stage) addChildButton.getScene().getWindow());

            addChildButton.getScene().getWindow().hide();
            stage.show();


        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void lunchWatchPerformanceScreen(Event event){

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("WatchWorkersPerformance.fxml"));

            Stage stage = new Stage();
            stage.setTitle("watch performance ");

            stage.setScene(new Scene(fxmlLoader.load(), 1300, 800));

            WatchWorkersPerformanceController controller = fxmlLoader.<WatchWorkersPerformanceController>getController();
            controller.initData((Stage) watchPerformanceButton.getScene().getWindow());

            watchPerformanceButton.getScene().getWindow().hide();
            stage.show();



        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    private void lunchWorkersDiagnosticsScreen(Event event) {


        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("WatchWorkersDiagnostics.fxml"));

            Stage stage = new Stage();
            stage.setTitle("watch diagnostics ");

            stage.setScene(new Scene(fxmlLoader.load(), 1300, 800));

            WatchWorkersDiagnosticsController controller = fxmlLoader.<WatchWorkersDiagnosticsController>getController();
            controller.initData((Stage) watchDiagnosticsButton.getScene().getWindow());

            watchDiagnosticsButton.getScene().getWindow().hide();
            stage.show();



        } catch (IOException e) {

            e.printStackTrace();
        }


    }











}
