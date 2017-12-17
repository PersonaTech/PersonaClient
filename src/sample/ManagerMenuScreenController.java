package sample;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ManagerMenuScreenController {

    @FXML
    private Button addSwButton;
    @FXML
    private Button addChildButton;
    @FXML
    private Button watchPerformanceButton;
    @FXML
    private Button watchDiagnosticsButton;
    @FXML
    private Button watchAutoDiagnosticsButton;
    @FXML
    private Button exitButton;


    @FXML
    public void initialize(){
        System.out.println("ok the menu is open now");
    }

    public void initData(String message ){
        //System.out.println(message);
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

        }else if(event.getSource().equals(watchDiagnosticsButton)){
            System.out.println("4");

        }else if(event.getSource().equals(watchAutoDiagnosticsButton)){
            System.out.println("5");

        }else if(event.getSource().equals(exitButton)){
            System.out.println("6");

        }

    }

    public void lunchSwScreen(Event event){

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("AddNewEmployeeScreen.fxml"));


            Stage stage = new Stage();

            stage.setTitle("Add new Employee");


            stage.setScene(new Scene(fxmlLoader.load(), 830, 500));

            AddNewEmployeeScreenController controller = fxmlLoader.<AddNewEmployeeScreenController>getController();

            //controller.initData("hello from login");

            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();


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


            stage.setScene(new Scene(fxmlLoader.load(), 830, 500));



            AddNewChildScreenController controller = fxmlLoader.<AddNewChildScreenController>getController();

            //controller.initData("hello from login");

            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();


        } catch (IOException e) {

            e.printStackTrace();
        }
    }







}
