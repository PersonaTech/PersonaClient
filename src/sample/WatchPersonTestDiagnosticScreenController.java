package sample;

import ModulesPackage.Employee;
import ModulesPackage.HouseDrawingTest;
import ModulesPackage.PersonDrawingTest;
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
public class WatchPersonTestDiagnosticScreenController {

    public Employee refToEmployee;
    @FXML
    public Button submitAddNewDiagnostic;
    public Button exitButton;
    public ComboBox diagIdCB;
    public BorderPane borderPaneWatch;
    public ImageView imageToShow;

    private Stage refToParent;
    private List<PersonDrawingTest> personDrawingTestList;

    public void initData(Stage refToParent , Employee refToEmployee) {

        this.refToParent = refToParent;
        this.refToEmployee = refToEmployee;

        System.out.println(refToEmployee.toString());
        System.out.println("uid ::: " + refToEmployee.getuId());

        try {
            PersonaSocket.objectOutputStream.writeObject("get Diagnostic");
            PersonaSocket.objectOutputStream.writeObject("person");
            PersonaSocket.objectOutputStream.writeObject(refToEmployee.getuId());

            personDrawingTestList = (ArrayList<PersonDrawingTest>) PersonaSocket.objectInputStream.readObject();

            if(personDrawingTestList!=null && !personDrawingTestList.isEmpty()) {

                List<String> drawingTestuid = new ArrayList<>();

                for (PersonDrawingTest c: personDrawingTestList) {
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

                    for (PersonDrawingTest p: personDrawingTestList) {

                        if(p.getTestId().equals(newValue)) {

                            imageToShow.setImage(new Image(p.getRefToPicture().getPictureUrl()));

                        }
                    }

                    submitAddNewDiagnostic.setDisable(false);

                }

            }
        });


    }


    public void onSubmitButtonClicked(ActionEvent actionEvent) {

        String testID = this.diagIdCB.getSelectionModel().getSelectedItem().toString();

        for (PersonDrawingTest t : personDrawingTestList)
        {
            if(testID.equals(t.getTestId())){

                GridPane gridpane = new GridPane();
                gridpane.setPadding(new Insets(15, 20, 15, 20));
                gridpane.setAlignment(Pos.CENTER);
                gridpane.setHgap(60);
                gridpane.setVgap(10);
                gridpane.setId("gridi");

                Label labels[] = new Label[11];

                labels[0] = new Label("person size");
                labels[1] = new Label("Drawing size");
                labels[2] = new Label("Proportion");
                labels[3] = new Label("headIsExist");
                labels[4] = new Label("LegsIsExist");
                labels[5] = new Label("ArmsIsExist");
                labels[6] = new Label("eyesIsExist");
                labels[7] = new Label("mouthIsExist");
                labels[8] = new Label("hairIsExist");
                labels[9] = new Label("numberOfClothing");
                labels[10] = new Label("Psychological diagnosis");

                Label data[] = new Label[11];

                data[0] = new Label(Integer.toString(t.getPersonSizePercentageSlider()));
                data[1]= new Label(Integer.toString(t.getDrawingSizePercentageSlider()));
                data[2]= new Label(Integer.toString(t.getProportionBetweenElementsSlider()));
                data[3] = new Label(t.getHeadIsExist().toString());
                data[4]= new Label(t.getLegsIsExist().toString());
                data[5]= new Label(t.getArmsIsExist().toString());
                data[6]= new Label(t.getEyesIsExist().toString());
                data[7]= new Label(t.getEyesIsExist().toString());
                data[8]= new Label(t.getEyesIsExist().toString());
                data[9]= new Label(t.getEyesIsExist().toString());
                data[10]= new Label(t.getDiagnosticFreeText());

                for (int i=0 ; i < 11 ; i++) {

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
