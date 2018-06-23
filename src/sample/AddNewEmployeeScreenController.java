package sample;

import ModulesPackage.Employee;
import ModulesPackage.LoginClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by itamarfredavrahami on 09/12/2017.
 */
public class AddNewEmployeeScreenController {

    private Stage refToParent;

    @FXML
    public Button exitButton;
    @FXML
    private TextField usernameTF;
    @FXML
    private TextField passwordTF;


    @FXML
    private TextField fNameTF;
    @FXML
    private TextField lNameTF;
    @FXML
    private TextField idTF;
    @FXML
    private TextField ageTF;

    @FXML
    private TextField addressTF;
    @FXML
    private TextField phoneNumberTF;
    @FXML
    private ComboBox<String> countyTF;

    @FXML
    private Button submitAddNewEmployee;

    public void initData(Stage refToParent){

        this.refToParent = refToParent;

    }


    public void onSubmitButtonClicked(ActionEvent event) {

        System.out.println("ok");

        /*
        Phase 1 : check loginClass fields (username , password )
         */
        boolean loginFieldsChecker =
                usernameTF.getText().isEmpty() || usernameTF.getText().trim().isEmpty()
                        || passwordTF.getText().isEmpty() || passwordTF.getText().trim().isEmpty();

        if (loginFieldsChecker) {
            System.out.println("Username or password is empty!");

        }

        /*
        Phase 2 : check Employee fields (first name , last name , age  )
         */

        boolean employeeFieldsChecker =
                fNameTF.getText().isEmpty() || fNameTF.getText().trim().isEmpty() ||
                        lNameTF.getText().isEmpty() || lNameTF.getText().trim().isEmpty() ||
                        idTF.getText().isEmpty() || idTF.getText().trim().isEmpty() ||
                        ageTF.getText().isEmpty() || ageTF.getText().trim().isEmpty() ||
                        ErrorHandlerForUserInterface.tryParse(ageTF.getText()) == 0 ||
                        countyTF.getSelectionModel().isEmpty() ||
                        phoneNumberTF.getText().isEmpty() || phoneNumberTF.getText().trim().isEmpty() ||
                        addressTF.getText().isEmpty() || addressTF.getText().trim().isEmpty()
                ;

        if (employeeFieldsChecker) {
            System.out.println("One of the employee fields is empty!");

            sampleController.showStage("One of the employee fields is empty!");
        }


        if(!loginFieldsChecker && !employeeFieldsChecker) {

            LoginClass loginClass = new LoginClass(usernameTF.getText(), passwordTF.getText());

            Employee employee = new Employee(
                    idTF.getText(), "SocialWorker",
                    Integer.parseInt(ageTF.getText()),
                    fNameTF.getText() + " " + lNameTF.getText(),
                    addressTF.getText(),
                    phoneNumberTF.getText(),
                    countyTF.getValue()
            );


            System.out.println(loginClass.toString());
            System.out.println(employee.toString());


            try {

                PersonaSocket.objectOutputStream.writeObject("Add employee");

                PersonaSocket.objectOutputStream.writeObject(loginClass);

                PersonaSocket.objectOutputStream.writeObject(employee);

                String authResponse = (String)PersonaSocket.objectInputStream.readObject();

                if (authResponse.equals(PersonaSocket.SUCCESS)){

                    System.out.println("added emp successfully!!!");

                    sampleController.showStage("added new employee successfully!");

                }else if (authResponse.equals(PersonaSocket.FAIL)) {

                    System.out.println("added emp failed!!!");

                    sampleController.showStage("added new employee failed!");

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            usernameTF.clear();
            passwordTF.clear();
            fNameTF.clear();
            lNameTF.clear();
            idTF.clear();
            ageTF.clear();
            addressTF.clear();
            phoneNumberTF.clear();
            countyTF.getSelectionModel().clearSelection();
            countyTF.setValue(null);


        }

    }



    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)exitButton.getScene().getWindow()).close();

        this.refToParent.show();

    }
}
