package sample;
import com.sun.javafx.logging.Logger;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;


public class sampleController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private CheckBox rememberMeField;
    @FXML
    private Button loginButton;

    // UI call automatically to the "public void initilize() " function when loaded
    @FXML
    public void initialize(){
        //loginButton.setDisable(true); // at the beginnings disable the login button until filled all required fields
    }

    @FXML
    public void onLoginButtonClicked(ActionEvent event){

        //System.out.println(event.getSource().equals(loginButton));

        System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  ");

        //System.out.println("Event::: " + event.getSource());
        System.out.println("Login details::: Username: " + usernameField.getText() + " | " + "Password: " + passwordField.getText() );
        System.out.println("remember me: " + rememberMeField.isSelected());

        if(usernameField.getText().equals(""))
            System.out.println("Username field is required!");
        else if (passwordField.getText().equals(""))
            System.out.println("Password field is required!");
        else
            System.out.println("Successfully login!");

            // TODO: 09/12/2017 take the username and the password and wrapper them to LoginClass


            System.out.println(" - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -  ");



        try {

            if(usernameField.getText().equals("admin")) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ManagerMenuScreen.fxml"));


                Stage stage = new Stage();
                stage.setTitle("Manager Menu");

                stage.setScene(new Scene(fxmlLoader.load(), 830, 500));

                ManagerMenuScreenController controller = fxmlLoader.<ManagerMenuScreenController>getController();

                controller.initData("hello from login");

                stage.show();

                ((Node) (event.getSource())).getScene().getWindow().hide();

            }else {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("SocialWorkerMenuScreen.fxml"));


                Stage stage = new Stage();
                stage.setTitle("Social Worker Menu");

                stage.setScene(new Scene(fxmlLoader.load(), 830, 500));

                SocialWorkerMenuScreenController controller = fxmlLoader.<SocialWorkerMenuScreenController>getController();

                controller.initData("hello from login");

                stage.show();

                ((Node) (event.getSource())).getScene().getWindow().hide();


            }


        } catch (IOException e) {

            e.printStackTrace();
        }






    }


    @FXML
    public void handleKeyReleased(){

        String usernameText = usernameField.getText();
        String passwordText = passwordField.getText();

        //check for empty strings or string with spaces only!

        boolean disableLoginButton =
                usernameText.isEmpty() || usernameText.trim().isEmpty()
                        || passwordText.isEmpty() || passwordText.trim().isEmpty();

        loginButton.setDisable(disableLoginButton);
    }

    @FXML
    public void handleCheckBox(){

        System.out.println("handleCheckBox say: " + rememberMeField.isSelected());

    }



}
