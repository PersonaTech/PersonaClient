package sample;

import ModulesPackage.TreeDrawingTest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 * Created by itamarfredavrahami on 10/12/2017.
 */
public class AddTreeTestDiagnosticController {

    @FXML
    public Slider treeSizePercentageSlider;
    @FXML
    public Slider drawingSizePercentageSlider;
    @FXML
    public Slider proportionBetweenElementsSlider;
    @FXML
    public Slider referenceToLeafsSlider;
    @FXML
    public Slider sidewaysMovementSlider;
    @FXML
    public TextField diagnosticFreeText;
    @FXML
    public ComboBox treeLocationTF;
    @FXML
    public Button submitAddNewDiagnostic;
    @FXML
    public Button exit;
    public ComboBox childIdCB;
    public ComboBox pictureIdCB;

    public void onSubmitButtonClicked(ActionEvent event) {

        boolean treeTestFieldsChecker =
                diagnosticFreeText.getText().isEmpty() || diagnosticFreeText.getText().trim().isEmpty() ||
                        treeLocationTF.getSelectionModel().isEmpty()
                ;

        if (treeTestFieldsChecker) {
            System.out.println("One of the Tree test fields is empty!");
        }

        /*
              super(testId, socialWorkerId, childId, pictureId);
        this.treeSizePercentage = treeSizePercentage;
        this.drawingSizePercentage = drawingSizePercentage;
        this.proportionBetweenElements = proportionBetweenElements;
        this.referenceToLeafs = referenceToLeafs;
        this.sidewaysMovement = sidewaysMovement;
        this.treeLocation = treeLocation;
        this.diagnosticFreeText = diagnosticFreeText;
         */

        TreeDrawingTest treeDrawingTest = new TreeDrawingTest("test1" , "sw1" , "ch1" ,"p1"
                ,(int) this.treeSizePercentageSlider.getValue()
                ,(int) this.drawingSizePercentageSlider.getValue()
                ,(int) this.proportionBetweenElementsSlider.getValue()
                ,(int) this.referenceToLeafsSlider.getValue()
                ,(int) this.sidewaysMovementSlider.getValue()
                , this.treeLocationTF.getSelectionModel().getSelectedItem().toString()
                ,diagnosticFreeText.getText()

        );





        System.out.println(treeDrawingTest.toString());






    }

    public void onExitButtonClicked(ActionEvent event) {


    }
}
