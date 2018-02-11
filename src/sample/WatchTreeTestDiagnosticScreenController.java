package sample;

import ModulesPackage.Employee;
import ModulesPackage.TreeDrawingTest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itamarfredavrahami on 11/02/2018.
 */
public class WatchTreeTestDiagnosticScreenController {


    public Employee refToEmployee;
    @FXML
    public Button submitAddNewDiagnostic;
    public Button exitButton;
    public ComboBox diagIdCB;
    public BorderPane borderPaneWatch;
    public ImageView imageToShow;

    private Stage refToParent;
    private List<TreeDrawingTest> treeDrawingTestList;

    public void initData(Stage refToParent , Employee refToEmployee) {

        submitAddNewDiagnostic.setDisable(true);

        this.refToParent = refToParent;
        this.refToEmployee = refToEmployee;

        System.out.println(refToEmployee.toString());
        System.out.println("uid ::: " + refToEmployee.getuId());

        try {
            PersonaSocket.objectOutputStream.writeObject("get Diagnostic");
            PersonaSocket.objectOutputStream.writeObject("tree");
            PersonaSocket.objectOutputStream.writeObject(refToEmployee.getuId());

            treeDrawingTestList = (ArrayList<TreeDrawingTest>) PersonaSocket.objectInputStream.readObject();

            if(treeDrawingTestList!=null && !treeDrawingTestList.isEmpty()) {

                List<String> drawingTestuid = new ArrayList<>();

                for (TreeDrawingTest c: treeDrawingTestList) {
                    drawingTestuid.add(c.getTestId());
                }
                this.diagIdCB.setItems(FXCollections.observableArrayList(drawingTestuid));



            }else {
                System.out.println("failed to load tests list!!!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        diagIdCB.setEditable(true);

        diagIdCB.getEditor().textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(newValue.equals("")){

                    System.out.println("you must pick valid index!");

                }else {

                    //imageToShow.setImage(new Image(c.getRefToPicture().getPictureUrl()));

                    for (TreeDrawingTest t: treeDrawingTestList) {

                        if(t.getTestId().equals(newValue)) {

                            imageToShow.setImage(new Image(t.getRefToPicture().getPictureUrl()));

                        }
                    }



                    submitAddNewDiagnostic.setDisable(false);

                }

            }
        });











    }


    public void onSubmitButtonClicked(ActionEvent actionEvent) {

        String testID = this.diagIdCB.getSelectionModel().getSelectedItem().toString();

        for (TreeDrawingTest t: treeDrawingTestList) {

            if(t.getTestId().equals(testID))
            {

                GridPane gridpane = new GridPane();
                gridpane.setPadding(new Insets(15, 20, 15, 20));
                gridpane.setAlignment(Pos.CENTER);
                gridpane.setHgap(60);
                gridpane.setVgap(10);
                gridpane.setId("gridi");

                Label labels[] = new Label[7];

                labels[0] = new Label("Tree size");
                labels[1] = new Label("Drawing size");
                labels[2] = new Label("Proportion");
                labels[3] = new Label("reference to leafs");
                labels[4] = new Label("Sideways movement");
                labels[5] = new Label("Tree location");
                labels[6] = new Label("Psychological diagnosis");

                Label data[] = new Label[7];

                data[0] = new Label(Integer.toString(t.getTreeSizePercentage()));
                data[1]= new Label(Integer.toString(t.getDrawingSizePercentage()));
                data[2]= new Label(Integer.toString(t.getProportionBetweenElements()));
                data[3] = new Label(Integer.toString(t.getReferenceToLeafs()));
                data[4]= new Label(Integer.toString(t.getSidewaysMovement()));
                data[5]= new Label(t.getTreeLocation());
                data[6]= new Label(t.getDiagnosticFreeText());

                for (int i=0 ; i < 7 ; i++) {

                    gridpane.add(labels[i] , 0 , i );
                    gridpane.add(data[i] , 1 , i );

                }

                borderPaneWatch.setCenter(gridpane);

                System.out.println("complete load");


            }
        }


    }

    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)exitButton.getScene().getWindow()).close();

        this.refToParent.show();

    }
}
