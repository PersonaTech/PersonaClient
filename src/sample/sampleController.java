package sample;
import ModulesPackage.Employee;
import ModulesPackage.LoginClass;
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



    @FXML
    public void initialize() {


        //loginButton.setDisable(true);
        // at the beginnings disable the login button until filled all required fields


        try {
            PersonaSocket personaSocket = new PersonaSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }



        usernameField.setText("itamar@gmail.com");

        passwordField.setText("99887766");



        // OR

        //usernameField.setText("per@gmail.com");

        //passwordField.setText("123456");


    }

    @FXML
    public void onLoginButtonClicked(ActionEvent event) {

        System.out.println("=================================================================");

        boolean loginFieldsChecker =
                  usernameField.getText().isEmpty() || usernameField.getText().trim().isEmpty() ||
                          passwordField.getText().isEmpty() || passwordField.getText().trim().isEmpty();

        if (loginFieldsChecker){
            System.out.println("username and password is required");
        }

        System.out.println("remember me: " + rememberMeField.isSelected());

        LoginClass user = new LoginClass(usernameField.getText() , passwordField.getText());

        System.out.println(user.toString());

        System.out.println("=================================================================");



        try {

            PersonaSocket.objectOutputStream.writeObject("login");
            PersonaSocket.objectOutputStream.writeObject(user);

            String authResponse = (String) PersonaSocket.objectInputStream.readObject();


            if (authResponse.equals(PersonaSocket.SUCCESS)){

                System.out.println("Login Successfully !!!");

                Employee currentEmployee = (Employee) PersonaSocket.objectInputStream.readObject();
                System.out.println("Current Employee : " + currentEmployee.toString());

                if (currentEmployee.getUserType().equals("Manager")) {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ManagerMenuScreen.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("Manager Menu");


                    stage.setScene(new Scene(fxmlLoader.load(), 830, 500));


                    ManagerMenuScreenController controller = fxmlLoader.<ManagerMenuScreenController>getController();
                    controller.initData((Stage) loginButton.getScene().getWindow());

                    loginButton.getScene().getWindow().hide();
                    stage.show();

                }else if (currentEmployee.getUserType().equals("SocialWorker")){

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("SocialWorkerMenuScreen.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("Social Worker Menu");

                    stage.setScene(new Scene(fxmlLoader.load(), 830, 500));

                    SocialWorkerMenuScreenController controller = fxmlLoader.<SocialWorkerMenuScreenController>getController();
                    controller.initData("hello from login");

                    loginButton.getScene().getWindow().hide();
                    stage.show();


                }else {

                    System.out.println("undefined employee type!");

                }

            }else if (authResponse.equals(PersonaSocket.FAIL)){

                System.out.println("wrong username or password !");

                // TODO: 17/12/2017 add screen for alert!!!

            }else {

                System.out.println("some error occurred !");
            }



        } catch (IOException e) {

            e.printStackTrace();

        } catch (ClassNotFoundException e) {

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
