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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

    public VBox centerVBOX;
    public VBox buttonVBOX;
    public VBox dataVBOX;
    public VBox dataVBOXInner;

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

                        gridpane.setHgap(60);
                        gridpane.setVgap(30);

                        gridpane.setPadding(new Insets(30, 40, 30, 40));

                        gridpane.setId("gridi");

                        Text labels[] = new Text[8];

                        labels[0] = new Text("Attribute");

                        labels[0].setStyle("-fx-font-weight: bold;-fx-font-size: 15;-fx-text-fill: #6da9ff;-fx-font-weight: bold;-fx-text-alignment: center");


                        labels[1] = new Text("Tree size");
                        labels[1].setStyle("-fx-font-weight: bold;");
                        labels[2] = new Text("Drawing size");
                        labels[2].setStyle("-fx-font-weight: bold;");
                        labels[3] = new Text("Proportion");
                        labels[3].setStyle("-fx-font-weight: bold;");
                        labels[4] = new Text("Reference to leafs");
                        labels[4].setStyle("-fx-font-weight: bold;");
                        labels[5] = new Text("Sideways movement");
                        labels[5].setStyle("-fx-font-weight: bold;");
                        labels[6] = new Text("Tree location");
                        labels[6].setStyle("-fx-font-weight: bold;");
                        labels[7] = new Text("Psychological diagnosis");
                        labels[7].setStyle("-fx-font-weight: bold;");

                        Text data[] = new Text[8];

                        data[0] = new Text("Value");

                        data[0].setStyle("-fx-font-weight: bold;-fx-font-size: 15;-fx-text-fill: #6da9ff;-fx-font-weight: bold;-fx-text-alignment: center");


                        data[1] = new Text(Integer.toString(t.getTreeSizePercentage())+ " %");
                        data[2]= new Text(Integer.toString(t.getDrawingSizePercentage())+ " %");
                        data[3]= new Text(Integer.toString(t.getProportionBetweenElements())+ " %");
                        data[4] = new Text(Integer.toString(t.getReferenceToLeafs()));
                        data[5]= new Text(Integer.toString(t.getSidewaysMovement())+ " %");
                        data[6]= new Text(t.getTreeLocation());
                        data[7]= new Text(t.getDiagnosticFreeText());

                        for (int i=0 ; i < 8 ; i++) {

                            gridpane.add(labels[i] , 0 , i );

                            if (i<7)
                                gridpane.add(data[i] , 1 , i );

                        }

                        Label getDiagnosticFreeTextData = new Label(t.getDiagnosticFreeText());

                        getDiagnosticFreeTextData.setStyle("-fx-wrap-text: true");

                        gridpane.add(getDiagnosticFreeTextData , 1 , 7);

                        buttonVBOX.getChildren().remove(ShowButton);

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

                break;

            case "Person":

                for (PersonDrawingTest t : personDrawingTestList)
                {
                    if(u.equals(t.getTestId())){

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

                        buttonVBOX.getChildren().remove(ShowButton);

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

                break;

            case "House":

                for (HouseDrawingTest t : houseDrawingTestList)
                {
                    if(u.equals(t.getTestId())){

                        GridPane gridpane = new GridPane();

                        gridpane.setHgap(60);
                        gridpane.setVgap(30);

                        gridpane.setPadding(new Insets(30, 40, 30, 40));

                        gridpane.setId("gridi");

                        Text labels[] = new Text[8];

                        labels[0] = new Text("Attribute");

                        labels[0].setStyle("-fx-font-weight: bold;-fx-font-size: 15;-fx-text-fill: #6da9ff;-fx-font-weight: bold;-fx-text-alignment: center");


                        labels[1] = new Text("House size");
                        labels[1].setStyle("-fx-font-weight: bold;");
                        labels[2] = new Text("Drawing size");
                        labels[2].setStyle("-fx-font-weight: bold;");
                        labels[3] = new Text("Proportion");
                        labels[3].setStyle("-fx-font-weight: bold;");
                        labels[4] = new Text("Reference To Door and Windows");
                        labels[4].setStyle("-fx-font-weight: bold;");
                        labels[5] = new Text("Reference To Roof");
                        labels[5].setStyle("-fx-font-weight: bold;");
                        labels[6] = new Text("Walls in the painting");
                        labels[6].setStyle("-fx-font-weight: bold;");
                        labels[7] = new Text("Psychological diagnosis");
                        labels[7].setStyle("-fx-font-weight: bold;");

                        Text data[] = new Text[8];

                        data[0] = new Text("Value");
                        data[0].setStyle("-fx-font-weight: bold;-fx-font-size: 15;-fx-text-fill: #6da9ff;-fx-font-weight: bold;-fx-text-alignment: center");

                        data[1] = new Text(Integer.toString(t.getHouseSizePercentageSlider()) + " %");
                        data[2]= new Text(Integer.toString(t.getDrawingSizePercentageSlider())+ " %");
                        data[3]= new Text(Integer.toString(t.getProportionBetweenElementsSlider())+ " %");
                        data[4] = new Text(Integer.toString(t.getReferenceToDoorWindowsSlider())+ " %");
                        data[5]= new Text(Integer.toString(t.getReferenceToRoofSlider())+ " %");
                        data[6]= new Text(t.getYesNoWall());

                        data[7]= new Text(t.getDiagnosticFreeText());
                        data[7].setStyle("-fx-wrap-text: true");

                        for (int i=0 ; i < 8 ; i++) {

                            gridpane.add(labels[i] , 0 , i );

                            if (i<7)
                                gridpane.add(data[i] , 1 , i );

                        }

                        Label getDiagnosticFreeTextData = new Label(t.getDiagnosticFreeText());

                        getDiagnosticFreeTextData.setStyle("-fx-wrap-text: true");

                        gridpane.add(getDiagnosticFreeTextData , 1 , 7);


                        buttonVBOX.getChildren().remove(ShowButton);

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

                break;

        };

    }

    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)ExitButton.getScene().getWindow()).close();

        this.refToParent.show();


    }


}
