package sample;

import ModulesPackage.Employee;
import ModulesPackage.LoginClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


/**
 * Created by itamarfredavrahami on 09/12/2017.
 */
public class AddNewEmployeeScreenController {


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


        }

    }
}
