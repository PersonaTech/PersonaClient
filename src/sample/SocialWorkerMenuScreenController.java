package sample;

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
        System.out.println("ok the menu is open now");
    }

    public void initData(String message ){
        //System.out.println(message);
    }

    public void onMenuButtonClicked(ActionEvent event) {

        if (event.getSource().equals(addTreeTestDiagnosticButton)){
            System.out.println("1");
            lunchTreeTestDiagnosticScreen(event);


        }else if(event.getSource().equals(addPersonTestDiagnosticButton)){
            System.out.println("2");


        }else if(event.getSource().equals(addHouseTestDiagnosticButton)){
            System.out.println("3");

        }else if(event.getSource().equals(addGeneralDiagnosticButton)){
            System.out.println("4");

        }else if(event.getSource().equals(watchTreeTestDiagnosticButton)){
            System.out.println("5");

        }else if(event.getSource().equals(watchPersonTestDiagnosticButton)){
            System.out.println("6");

        }else if(event.getSource().equals(watchHouseTestDiagnosticButton)){
            System.out.println("7");

        }else if(event.getSource().equals(watchGeneralDiagnosticButton)){
            System.out.println("8");

        }else if(event.getSource().equals(exitButton)){
            System.out.println("9");

        }





    }


    public void lunchTreeTestDiagnosticScreen(Event event){

        try {

            FXMLLoader fxmlLoader = new FXMLLoader();

            fxmlLoader.setLocation(getClass().getResource("AddTreeTestDiagnostic.fxml"));


            Stage stage = new Stage();
            stage.setTitle("Tree Test Diagnostic");

            stage.setScene(new Scene(fxmlLoader.load(), 830, 600));

            AddTreeTestDiagnosticController controller = fxmlLoader.<AddTreeTestDiagnosticController>getController();

            stage.show();

            ((Node)(event.getSource())).getScene().getWindow().hide();


        } catch (IOException e) {

            e.printStackTrace();
        }
    }


}
