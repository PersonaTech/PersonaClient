package sample;

import ModulesPackage.Child;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;


/**
 * Created by itamarfredavrahami on 08/12/2017.
 */
public class AddNewChildScreenController {


    @FXML
    private ComboBox<String> kNameFT;
    @FXML
    private TextField fNameTF;
    @FXML
    private TextField lNameTF;
    @FXML
    private TextField idTF;
    @FXML
    private TextField ageTF;
    @FXML
    private ComboBox<String> countyTF;
    @FXML
    private Button submitAddNewChild;



    @FXML
    public void initialize(){


        System.out.println("ok the add new child is open now" );

        String[] Kindergarten = {"A" , "B" , "C" };

        try {
            PersonaSocket.objectOutputStream.writeObject("Add child");
        } catch (IOException e) {
            e.printStackTrace();
        }


        kNameFT.setItems(FXCollections.observableArrayList(Kindergarten));





    }

    public void onSubmitButtonClicked(ActionEvent event) {

        System.out.println("ok");

        boolean childFieldsChecker =
                    kNameFT.getSelectionModel().isEmpty() ||
                    fNameTF.getText().isEmpty() || fNameTF.getText().trim().isEmpty() ||
                    lNameTF.getText().isEmpty() || lNameTF.getText().trim().isEmpty() ||
                    idTF.getText().isEmpty() || idTF.getText().trim().isEmpty() ||
                    ageTF.getText().isEmpty() || ageTF.getText().trim().isEmpty() ||
                            ErrorHandlerForUserInterface.tryParse(ageTF.getText()) == 0 ||
                    countyTF.getSelectionModel().isEmpty()
                ;



        if(childFieldsChecker){

            System.out.println("One of the child fields is empty!");

        } else {

            Child child = new Child(kNameFT.getSelectionModel().selectedItemProperty().getValue(),
            fNameTF.getText().toString()+" " +lNameTF.getText().toString() ,
                    Integer.parseInt(ageTF.getText().toString()) ,
                    idTF.getText().toString() ,
                    countyTF.getValue()
            );

            System.out.println(child.toString());


        }









    }













}
