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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

    public VBox buttonVBOX;
    public VBox dataVBOX;
    public VBox dataVBOXInner;


    private Stage refToParent;
    private List<PersonDrawingTest> personDrawingTestList;

    public void initData(Stage refToParent , Employee refToEmployee) {

        this.refToParent = refToParent;
        this.refToEmployee = refToEmployee;

        submitAddNewDiagnostic.setDisable(true);

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

                gridpane.setHgap(60);
                gridpane.setVgap(15);

                gridpane.setPadding(new Insets(30, 40, 30, 40));

                gridpane.setId("gridi");

                Text labels[] = new Text[12];


                labels[0] = new Text("Attribute");

                labels[0].setStyle("-fx-font-weight: bold;-fx-font-size: 15;-fx-text-fill: #6da9ff;-fx-font-weight: bold;-fx-text-alignment: center");

                labels[1] = new Text("person size");
                labels[2] = new Text("Drawing size");
                labels[3] = new Text("Proportion");
                labels[4] = new Text("headIsExist");
                labels[5] = new Text("LegsIsExist");
                labels[6] = new Text("ArmsIsExist");
                labels[7] = new Text("eyesIsExist");
                labels[8] = new Text("mouthIsExist");
                labels[9] = new Text("hairIsExist");
                labels[10] = new Text("numberOfClothing");
                labels[11] = new Text("Psychological diagnosis");

                Text data[] = new Text[12];


                data[0] = new Text("Value");

                data[0].setStyle("-fx-font-weight: bold;-fx-font-size: 15;-fx-text-fill: #6da9ff;-fx-font-weight: bold;-fx-text-alignment: center");

                data[1] = new Text(Integer.toString(t.getPersonSizePercentageSlider()));
                data[2]= new Text(Integer.toString(t.getDrawingSizePercentageSlider()));
                data[3]= new Text(Integer.toString(t.getProportionBetweenElementsSlider()));
                data[4] = new Text(t.getHeadIsExist().toString());
                data[5]= new Text(t.getLegsIsExist().toString());
                data[6]= new Text(t.getArmsIsExist().toString());
                data[7]= new Text(t.getEyesIsExist().toString());
                data[8]= new Text(t.getEyesIsExist().toString());
                data[9]= new Text(t.getEyesIsExist().toString());
                data[10]= new Text(t.getEyesIsExist().toString());
                data[11]= new Text(t.getDiagnosticFreeText());

                for (int i=0 ; i < 12 ; i++) {

                    gridpane.add(labels[i] , 0 , i );

                    if (i<11)
                        gridpane.add(data[i] , 1 , i );


                }

                Label getDiagnosticFreeTextData = new Label(t.getDiagnosticFreeText());

                getDiagnosticFreeTextData.setStyle("-fx-wrap-text: true");

                gridpane.add(getDiagnosticFreeTextData , 1 , 11);

                buttonVBOX.getChildren().remove(submitAddNewDiagnostic);

                VBox stam = new VBox();

                stam.setAlignment(Pos.CENTER);
                stam.setPadding(new Insets(30, 0, 30, 0));

                Text checlll = new Text("Diagnostic's Details Results");

                checlll.setStyle("-fx-font-weight: bold;-fx-font-size: 20;-fx-text-fill: #07348b;");

                stam.getChildren().add(checlll);

                dataVBOXInner.getChildren().add(stam);
                dataVBOXInner.getChildren().add(gridpane);

                System.out.println("complete load");
            }
        }



    }

    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)exitButton.getScene().getWindow()).close();

        this.refToParent.show();

    }

}
