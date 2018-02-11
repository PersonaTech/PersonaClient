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

    List<TreeDrawingTest> treeDrawingTestList;

    List<HouseDrawingTest> houseDrawingTestList;

    List<PersonDrawingTest> personDrawingTestList;


    List<Employee> listOfEmployees;

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

        imageToShow.setImage(new Image("http://goo.gl/kYEQl"));

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

                ShowButton.setDisable(false);

            }

        });





    }

    public void onShowButtonClicked(ActionEvent actionEvent) {

        String type = typeCB.getSelectionModel().getSelectedItem().toString();

        switch (type){

            case "Tree":

                String u = pCB.getSelectionModel().getSelectedItem().toString();

                for (TreeDrawingTest t : treeDrawingTestList)
                {
                    if(u.equals(t.getTestId())){


                        GridPane gridpane = new GridPane();
                        gridpane.setPadding(new Insets(15, 20, 15, 20));
                        gridpane.setAlignment(Pos.CENTER);
                        gridpane.setHgap(60);
                        gridpane.setVgap(10);

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
                        TextArea res7 = new TextArea("here will be the diagnosis");

                        for (int i=0 ; i< 6 ; i++) {

                            gridpane.add(labels[i] , 0 , i );
                            gridpane.add(data[i] , 1 , i );

                        }

                        borderPaneWatch.setCenter(gridpane);

                        System.out.println("complete load");
                    }
                }

                break;

            case "Person":

                /*

                String u = pCB.getSelectionModel().getSelectedItem().toString();

                for (PersonDrawingTest t : PersonDrawingTest)
                {
                    if(u.equals(t.getTestId())){

                        GridPane gridpane = new GridPane();
                        gridpane.setPadding(new Insets(15, 20, 15, 20));
                        gridpane.setAlignment(Pos.CENTER);
                        gridpane.setHgap(60);
                        gridpane.setVgap(10);

                        Label labels[] = new Label[11];

                        labels[0] = new Label("Tree size");
                        labels[1] = new Label("Drawing size");
                        labels[2] = new Label("Proportion");
                        labels[3] = new Label("headIsExist");
                        labels[4] = new Label("LegsIsExist");
                        labels[5] = new Label("ArmsIsExist");
                        labels[6] = new Label("eyesIsExist");
                        labels[6] = new Label("mouthIsExist");
                        labels[6] = new Label("hairIsExist");
                        labels[6] = new Label("numberOfClothing");
                        labels[6] = new Label("Psychological diagnosis");

                        Label data[] = new Label[11];

//                        data[0] = new Label(Integer.toString(t.getPersonSizePercentageSlider()));
//                        data[1]= new Label(Integer.toString(t.getDrawingSizePercentageSlider()));
//                        data[2]= new Label(Integer.toString(t.getProportionBetweenElementsSlider()));
//                        data[3] = new Label(Integer.toString(t.getHeadIsExist()));
//                        data[4]= new Label(Integer.toString(t.getLegsIsExist()));
//                        data[5]= new Label(t.getArmsIsExist());
//                        data[6]= new Label(t.getEyesIsExist());
//                        data[7]= new Label(t.getEyesIsExist());
//                        data[8]= new Label(t.getEyesIsExist());
//                        data[9]= new Label(t.getEyesIsExist());
                        TextArea res7 = new TextArea("here will be the diagnosis");

                        for (int i=0 ; i< 6 ; i++) {

                            gridpane.add(labels[i] , 0 , i );
                            gridpane.add(data[i] , 1 , i );

                        }

                        borderPaneWatch.setCenter(gridpane);

                        System.out.println("complete load");
                    }
                }

*/

                break;

            case "House":
                break;




        };




        /*

        String worker;
        String picType;

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(15, 20, 15, 20));
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setHgap(60);
        gridpane.setVgap(10);


        picType = "Tree";

        // TODO: 14/01/2018 DB QUARY TO RECIEVE PICTURE DATATYPE

        switch (picType){

            case "Tree":

                Label labels[] = new Label[7];

                labels[0] = new Label("Tree size");
                labels[1] = new Label("Drawing size");
                labels[2] = new Label("Proportion");
                labels[3] = new Label("reference to leafs");
                labels[4] = new Label("Sideways movement");
                labels[5] = new Label("Tree location");
                labels[6] = new Label("Psychological diagnosis");

                Label data[] = new Label[7];

                data[0] = new Label("...");
                data[1]= new Label("...");
                data[2]= new Label("...");
                data[3] = new Label("...");
                data[4]= new Label("...");
                data[5]= new Label("...");
                data[6]= new Label("...");
                TextArea res7 = new TextArea("here will be the diagnosis");

                for (int i=0 ; i< 6 ; i++) {

                    gridpane.add(labels[i] , 0 , i );
                    gridpane.add(data[i] , 1 , i );

                }

                borderPaneWatch.setCenter(gridpane);

                System.out.println("complete load");


                break;



            case "Person":





                break;

            case "House":







                break;

            default:

        }


        */














    }

    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)ExitButton.getScene().getWindow()).close();

        this.refToParent.show();


    }


}
