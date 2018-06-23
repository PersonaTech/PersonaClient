package sample;
import ModulesPackage.Employee;
import ModulesPackage.LoginClass;
import com.sun.javafx.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.PopupBuilder;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;


public class sampleController {

    public ImageView loginPicImageView;
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

//        usernameField.setText("per@gmail.com");
//        passwordField.setText("123456");

        // OR

        usernameField.setText("itamar@gmail.com");
        passwordField.setText("99887766");



    }

    public static void showStage( String msg){

        Stage newStage = new Stage();
        Group g = new Group();
        VBox msgWrapper = new VBox();
        msgWrapper.setAlignment(Pos.CENTER);
        msgWrapper.setPrefHeight(100);
        msgWrapper.setPrefWidth(400);
        Label msgLabel = new Label(msg);
        msgLabel.setStyle("-fx-text-fill: darkblue");
        msgWrapper.getChildren().add(msgLabel);
        HBox comp = new HBox();
        comp.setAlignment(Pos.CENTER);
        comp.setPrefHeight(200);
        comp.setPrefWidth(400);
        Button okButton = new Button("OK");
        okButton.setPrefWidth(100);
        Button CloseButton = new Button("Close");
        CloseButton.setPrefWidth(100);
        comp.getChildren().add(CloseButton);
        comp.getChildren().add(okButton);

        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                newStage.close();
            }
        });

        CloseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                newStage.close();
            }
        });

        g.getChildren().addAll(msgWrapper , comp);
        Scene stageScene = new Scene(g, 400, 150);
        newStage.setScene(stageScene);
        newStage.setTitle("Pop up from Persona!");
        newStage.show();
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

                    stage.setScene(new Scene(fxmlLoader.load(), 1300, 800));

                    ManagerMenuScreenController controller = fxmlLoader.<ManagerMenuScreenController>getController();
                    controller.initData((Stage) loginButton.getScene().getWindow());

                    loginButton.getScene().getWindow().hide();
                    stage.show();

                }else if (currentEmployee.getUserType().equals("SocialWorker")){

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("SocialWorkerMenuScreen.fxml"));

                    Stage stage = new Stage();
                    stage.setTitle("Social Worker Menu");

                    stage.setScene(new Scene(fxmlLoader.load(), 1300, 800));

                    SocialWorkerMenuScreenController controller = fxmlLoader.<SocialWorkerMenuScreenController>getController();
                    controller.initData((Stage) loginButton.getScene().getWindow() , currentEmployee);

                    loginButton.getScene().getWindow().hide();
                    stage.show();


                }else {

                    System.out.println("undefined employee type!");

                }

            }else if (authResponse.equals(PersonaSocket.FAIL)){

                System.out.println("wrong username or password !");

                showStage("Wrong username or password!!!");


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
