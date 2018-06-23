package sample;

import ModulesPackage.Employee;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by itamarfredavrahami on 10/12/2017.
 */
public class SocialWorkerMenuScreenController {

    public Employee refToEmployee;

    private Stage refToParent;

    @FXML
    public Button addTreeTestDiagnosticButton;


    @FXML
    public Button addPersonTestDiagnosticButton;
    @FXML
    public Button addHouseTestDiagnosticButton;
    @FXML
    public Button addGeneralDiagnosticButton;
    @FXML
    public Button watchTreeTestDiagnosticButton;
    @FXML
    public Button watchPersonTestDiagnosticButton;
    @FXML
    public Button watchHouseTestDiagnosticButton;
    @FXML
    public Button watchGeneralDiagnosticButton;
    @FXML
    public Button exitButton;

    @FXML
    public void initialize(){

        System.out.println("ok the social worker menu is open now");
    }

    public void initData(Stage refToParent , Employee refToEmployee){

        this.refToParent = refToParent;
        this.refToEmployee = refToEmployee;

    }

    public void onMenuButtonClicked(ActionEvent event) {

        if (event.getSource().equals(addTreeTestDiagnosticButton)){
            System.out.println("1");
            lunchTreeTestDiagnosticScreen(event);


        }else if(event.getSource().equals(addPersonTestDiagnosticButton)){
            System.out.println("2");
            lunchPersonTestDiagnosticScreen(event);


        }else if(event.getSource().equals(addHouseTestDiagnosticButton)){
            System.out.println("3");
            lunchHouseTestDiagnosticScreen(event);

        }else if(event.getSource().equals(addGeneralDiagnosticButton)){
            System.out.println("4");

        }else if(event.getSource().equals(watchTreeTestDiagnosticButton)){
            System.out.println("5");
            lunchWatchTreeTestDiagnosticScreen(event);


        }else if(event.getSource().equals(watchPersonTestDiagnosticButton)){
            System.out.println("6");
            lunchWatchPersonTestDiagnosticScreen(event);

        }else if(event.getSource().equals(watchHouseTestDiagnosticButton)){
            System.out.println("7");
            lunchWatchHouseTestDiagnosticScreen(event);

        }else if(event.getSource().equals(watchGeneralDiagnosticButton)){
            System.out.println("8");

        }else if(event.getSource().equals(exitButton)){

            System.out.println("9");

            ((Stage)exitButton.getScene().getWindow()).close();
            this.refToParent.show();
        }



    }

    public void lunchTreeTestDiagnosticScreen(Event event){

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddTreeTestDiagnostic.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Tree Test Diagnostic");

            stage.setScene(new Scene(fxmlLoader.load(), 1300, 800));

            AddTreeTestDiagnosticController controller = fxmlLoader.<AddTreeTestDiagnosticController>getController();
            controller.initData((Stage) addTreeTestDiagnosticButton.getScene().getWindow() , this.refToEmployee);

            addTreeTestDiagnosticButton.getScene().getWindow().hide();
            stage.show();


        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void lunchPersonTestDiagnosticScreen(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("addPersonTestDiagnostic.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Person Test Diagnostic");

            stage.setScene(new Scene(fxmlLoader.load(), 1300, 800));

            AddPersonTestDiagnosticController controller = fxmlLoader.<AddPersonTestDiagnosticController>getController();
            controller.initData( (Stage)addPersonTestDiagnosticButton.getScene().getWindow() , this.refToEmployee );

            addPersonTestDiagnosticButton.getScene().getWindow().hide();
            stage.show();


        } catch (IOException e) {

            e.printStackTrace();
        }





    }

    private void lunchHouseTestDiagnosticScreen(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddHouseTestDiagnostic.fxml"));

            Stage stage = new Stage();
            stage.setTitle("House Test Diagnostic");

            stage.setScene(new Scene(fxmlLoader.load(), 1300, 800));

            AddHouseTestDiagnosticController controller = fxmlLoader.<AddHouseTestDiagnosticController>getController();
            controller.initData((Stage) addHouseTestDiagnosticButton.getScene().getWindow() , this.refToEmployee);

            addHouseTestDiagnosticButton.getScene().getWindow().hide();
            stage.show();


        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    private void lunchWatchTreeTestDiagnosticScreen(ActionEvent event) {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("WatchTreeTestDiagnosticScreen.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Tree Test Watch");

            stage.setScene(new Scene(fxmlLoader.load(), 1300, 800));

            WatchTreeTestDiagnosticScreenController controller = fxmlLoader.<WatchTreeTestDiagnosticScreenController>getController();
            controller.initData((Stage) watchTreeTestDiagnosticButton.getScene().getWindow() , this.refToEmployee);

            watchTreeTestDiagnosticButton.getScene().getWindow().hide();
            stage.show();


        } catch (IOException e) {

            e.printStackTrace();
        }


    }



    private void lunchWatchPersonTestDiagnosticScreen(ActionEvent event) {


        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("WatchPersonTestDiagnosticScreen.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Person Test Watch");

            stage.setScene(new Scene(fxmlLoader.load(), 1300, 800));

            WatchPersonTestDiagnosticScreenController controller = fxmlLoader.<WatchPersonTestDiagnosticScreenController>getController();
            controller.initData((Stage) watchPersonTestDiagnosticButton.getScene().getWindow() , this.refToEmployee);

            watchPersonTestDiagnosticButton.getScene().getWindow().hide();
            stage.show();


        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    private void lunchWatchHouseTestDiagnosticScreen(ActionEvent event) {


        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("WatchHouseTestDiagnosticScreen.fxml"));

            Stage stage = new Stage();
            stage.setTitle("House Test Watch");

            stage.setScene(new Scene(fxmlLoader.load(), 1300, 800));

            WatchHouseTestDiagnosticScreenController controller = fxmlLoader.<WatchHouseTestDiagnosticScreenController>getController();
            controller.initData((Stage) watchHouseTestDiagnosticButton.getScene().getWindow() , this.refToEmployee);

            watchHouseTestDiagnosticButton.getScene().getWindow().hide();
            stage.show();


        } catch (IOException e) {

            e.printStackTrace();
        }

    }



}
