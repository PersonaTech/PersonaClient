package sample;

import ModulesPackage.Child;
import ModulesPackage.Picture;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itamarfredavrahami on 08/12/2017.
 */

public class AddNewChildScreenController {

    private Stage refToParent;

    @FXML
    public Button exitButton;

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



        try {

            List<String> kindergartenList = new ArrayList<>();

            PersonaSocket.objectOutputStream.writeObject("get Kindergartens");

            kindergartenList = (ArrayList<String>) PersonaSocket.objectInputStream.readObject();

            if(! kindergartenList.isEmpty()) {

                kNameFT.setItems(FXCollections.observableArrayList(kindergartenList));

            }else {

                System.out.println("failed to load kindergartens");
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void initData(Stage refToParent){

        this.refToParent = refToParent;

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

            //Picture p1 = new Picture("9988773" , "https://firebasestorage.googleapis.com/v0/b/persona-tech.appspot.com/o/Photos%2Fhouse3.jpg?alt=media&token=03642060-e620-4201-b771-e68df4368b2c" , false , "tree");


            //List<Picture> pArr = new ArrayList<Picture>();

            //pArr.add(p1);

            //child.setPictures(pArr);

            System.out.println(child.toString());


            try {
                PersonaSocket.objectOutputStream.writeObject("Add child");
                PersonaSocket.objectOutputStream.writeObject(child);

                String authResponse = (String)PersonaSocket.objectInputStream.readObject();

                if (authResponse.equals(PersonaSocket.SUCCESS)){

                    System.out.println("added child successfully!!!");

                }else if (authResponse.equals(PersonaSocket.FAIL)) {

                    System.out.println("added child failed!!!");

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            kNameFT.getSelectionModel().clearSelection();
            kNameFT.setValue(null);

            fNameTF.clear();
            lNameTF.clear();
            idTF.clear();
            ageTF.clear();

            countyTF.getSelectionModel().clearSelection();
            countyTF.setValue(null);

        }

    }


    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)exitButton.getScene().getWindow()).close();

        this.refToParent.show();


    }
}
