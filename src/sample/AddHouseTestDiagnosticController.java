package sample;

import ModulesPackage.Employee;
import javafx.stage.Stage;

/**
 * Created by itamarfredavrahami on 17/12/2017.
 */
public class AddHouseTestDiagnosticController {

    public Employee refToEmployee;

    private Stage refToParent;

    public void initData(Stage refToParent , Employee refToEmployee){

        this.refToParent = refToParent;
        this.refToEmployee = refToEmployee;

        System.out.println(refToEmployee.toString());
        System.out.println("uid ::: "  + refToEmployee.getuId());

    }
}
