package sample;

import ModulesPackage.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WatchWorkersDiagnosticsController {

    public List<TreeDrawingTest> treeDrawingTestList;
    public List<HouseDrawingTest> houseDrawingTestList;
    public List<PersonDrawingTest> personDrawingTestList;

    public List<Employee> listOfEmployees;

    @FXML
    public BorderPane borderPaneWatch;
    public ComboBox swCB;
    public ComboBox typeCB;
    public ComboBox pCB;
    public ImageView imageToShow;

    //ShowButton.setDisable(true);

    private Stage refToParent;

    @FXML
    public Button ShowButton;
    public Button ExitButton;

    public void initialize()  {

    }

    public void initData(Stage refToParent){

        this.refToParent = refToParent;
        ShowButton.setDisable(true);
        typeCB.setDisable(true);
        pCB.setDisable(true);
        listOfEmployees = new ArrayList<>();

        try {

            PersonaSocket.objectOutputStream.writeObject("get Employees");
            listOfEmployees = (ArrayList<Employee>)PersonaSocket.objectInputStream.readObject();

            if(! listOfEmployees.isEmpty()) {

                List<String> EmployeesNamesList = new ArrayList<>();

                for (Employee c: listOfEmployees) {
                    EmployeesNamesList.add(c.getName());
                }

                swCB.setItems(FXCollections.observableArrayList(EmployeesNamesList));
                swCB.setPromptText("select an employee...");

            }else {
                System.out.println("failed to load social workers list!!!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        swCB.setEditable(true);
        swCB.getEditor().textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                typeCB.setDisable(false);
                typeCB.setPromptText("pick type...");

            }
        });

        typeCB.setEditable(true);
        typeCB.getEditor().textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String workerUid = "";
                String workerName = swCB.getSelectionModel().getSelectedItem().toString();

                for ( Employee employee: listOfEmployees) {

                    if(employee.getName().equals(workerName))
                        workerUid = employee.getuId();

                }

                String type = newValue;

                System.out.println(type);

                System.out.println(workerUid);

                switch (type){

                    case "Tree":

                        try {

                            PersonaSocket.objectOutputStream.writeObject("get Diagnostic");
                            PersonaSocket.objectOutputStream.writeObject("tree");
                            PersonaSocket.objectOutputStream.writeObject(workerUid);

                            System.out.println("ok here...");

                            treeDrawingTestList = (ArrayList<TreeDrawingTest>) PersonaSocket.objectInputStream.readObject();

                            if(treeDrawingTestList!=null && !treeDrawingTestList.isEmpty()) {

                                List<String> drawingTestuid = new ArrayList<>();

                                for (TreeDrawingTest c: treeDrawingTestList) {
                                    drawingTestuid.add(c.getTestId());
                                }
                                pCB.setItems(FXCollections.observableArrayList(drawingTestuid));

                            }else {

                                System.out.println("failed to load tests list!!!");
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }


                        break;


                    case "House":

                        try {

                            PersonaSocket.objectOutputStream.writeObject("get Diagnostic");
                            PersonaSocket.objectOutputStream.writeObject("house");
                            PersonaSocket.objectOutputStream.writeObject(workerUid);

                            houseDrawingTestList =  (ArrayList<HouseDrawingTest>) PersonaSocket.objectInputStream.readObject();

                            if(houseDrawingTestList!=null && !houseDrawingTestList.isEmpty()) {

                                List<String> drawingTestuid = new ArrayList<>();
                                for (HouseDrawingTest c: houseDrawingTestList) {
                                    drawingTestuid.add(c.getTestId());
                                }

                                pCB.setItems(FXCollections.observableArrayList(drawingTestuid));

                            }else {

                                System.out.println("failed to load tests list!!!");
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                        break;

                    case "Person":

                        try {

                            PersonaSocket.objectOutputStream.writeObject("get Diagnostic");
                            PersonaSocket.objectOutputStream.writeObject("person");
                            PersonaSocket.objectOutputStream.writeObject(workerUid);

                            personDrawingTestList =  (ArrayList<PersonDrawingTest>) PersonaSocket.objectInputStream.readObject();

                            if(personDrawingTestList!=null && !personDrawingTestList.isEmpty()) {

                                List<String> drawingTestuid = new ArrayList<>();
                                for (PersonDrawingTest c: personDrawingTestList) {
                                    drawingTestuid.add(c.getTestId());
                                }

                                pCB.setItems(FXCollections.observableArrayList(drawingTestuid));

                            }else {

                                System.out.println("failed to load tests list!!!");
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                        break;

                };

                pCB.setDisable(false);

            }

        });

        pCB.setEditable(true);

        pCB.getEditor().textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String typeOfTest = typeCB.getSelectionModel().getSelectedItem().toString();

                String picId = newValue;

                switch (typeOfTest){

                    case "Tree":

                        for (TreeDrawingTest c: treeDrawingTestList) {

                            if (c.getTestId().equals(picId)) {

                                imageToShow.setImage(new Image(c.getRefToPicture().getPictureUrl()));

                            }

                        }

                        break;

                    case "House":

                        for (HouseDrawingTest c: houseDrawingTestList) {

                            if(c.getTestId().equals(picId)){

                                imageToShow.setImage(new Image(c.getRefToPicture().getPictureUrl()));

                            }
                        }

                        break;

                    case "Person":

                        for (PersonDrawingTest c: personDrawingTestList) {

                            if(c.getTestId().equals(picId)){

                                imageToShow.setImage(new Image(c.getRefToPicture().getPictureUrl()));

                            }
                        }

                        break;

                }

                ShowButton.setDisable(false);

            }

        });





    }

    public void onShowButtonClicked(ActionEvent actionEvent) {

        //get the type of the picture
        String type = typeCB.getSelectionModel().getSelectedItem().toString();

        //get the id of the picture
        String u = pCB.getSelectionModel().getSelectedItem().toString();

        switch (type){

            case "Tree":

                for (TreeDrawingTest t : treeDrawingTestList)
                {
                    if(u.equals(t.getTestId())){

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

                break;

            case "Person":

                for (PersonDrawingTest t : personDrawingTestList)
                {
                    if(u.equals(t.getTestId())){

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

                break;

            case "House":

                for (HouseDrawingTest t : houseDrawingTestList)
                {
                    if(u.equals(t.getTestId())){

                        GridPane gridpane = new GridPane();
                        gridpane.setPadding(new Insets(15, 20, 15, 20));
                        gridpane.setAlignment(Pos.CENTER);
                        gridpane.setHgap(60);
                        gridpane.setVgap(10);
                        gridpane.setId("gridi");

                        Label labels[] = new Label[7];

                        labels[0] = new Label("house size");
                        labels[1] = new Label("Drawing size");
                        labels[2] = new Label("Proportion");
                        labels[3] = new Label("reference To Door and Windows");
                        labels[4] = new Label("reference To Roof");
                        labels[5] = new Label("walls in the painting");
                        labels[6] = new Label("Psychological diagnosis");

                        Label data[] = new Label[7];

                        data[0] = new Label(Integer.toString(t.getHouseSizePercentageSlider()));
                        data[1]= new Label(Integer.toString(t.getDrawingSizePercentageSlider()));
                        data[2]= new Label(Integer.toString(t.getProportionBetweenElementsSlider()));
                        data[3] = new Label(Integer.toString(t.getReferenceToDoorWindowsSlider()));
                        data[4]= new Label(Integer.toString(t.getReferenceToRoofSlider()));
                        data[5]= new Label(t.getYesNoWall());
                        data[6]= new Label(t.getDiagnosticFreeText());

                        for (int i=0 ; i < 7 ; i++) {

                            gridpane.add(labels[i] , 0 , i );
                            gridpane.add(data[i] , 1 , i );

                        }

                        borderPaneWatch.setCenter(gridpane);

                        System.out.println("complete load");
                    }
                }

                break;

        };

    }

    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)ExitButton.getScene().getWindow()).close();

        this.refToParent.show();


    }


}
