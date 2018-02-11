package sample;

import ModulesPackage.Employee;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by itamarfredavrahami on 11/02/2018.
 */
public class WatchHouseTestDiagnosticScreenController {

    public Employee refToEmployee;
    public Button submitAddNewDiagnostic;
    public Button exitButton;

    private Stage refToParent;

    public void initData(Stage refToParent , Employee refToEmployee) {

        this.refToParent = refToParent;
        this.refToEmployee = refToEmployee;

        System.out.println(refToEmployee.toString());
        System.out.println("uid ::: " + refToEmployee.getuId());


    }


    public void onSubmitButtonClicked(ActionEvent actionEvent) {
    }

    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)exitButton.getScene().getWindow()).close();

        this.refToParent.show();

    }
}
