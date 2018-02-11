package sample;

import ModulesPackage.Child;
import ModulesPackage.Employee;
import ModulesPackage.Picture;
import ModulesPackage.TreeDrawingTest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by itamarfredavrahami on 10/12/2017.
 */
public class AddTreeTestDiagnosticController {


    public Employee refToEmployee;

    public ImageView imageToShow;



    private Stage refToParent;



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
    public Button exitButton;

    @FXML
    public BorderPane borderRoot;


    public ComboBox kindergartenCB;
    public ComboBox childIdCB;
    public ComboBox pictureIdCB;


    public List<Child> childrenList;




    public void initData(Stage refToParent , Employee refToEmployee){

        this.refToParent = refToParent;
        this.refToEmployee = refToEmployee;

        System.out.println(refToEmployee.toString());
        System.out.println("uid ::: "  + refToEmployee.getuId());

        try {

            List<String> kindergartenList = new ArrayList<>();

            PersonaSocket.objectOutputStream.writeObject("get Kindergartens");

            kindergartenList = (ArrayList<String>) PersonaSocket.objectInputStream.readObject();

            if(! kindergartenList.isEmpty()) {

                kindergartenCB.setItems(FXCollections.observableArrayList(kindergartenList));

            } else {

                System.out.println("failed to load kindergartens!!!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




        kindergartenCB.setEditable(true);
        kindergartenCB.getEditor().textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


                try {

                    childrenList=null;

                    childrenList = new ArrayList<>();

                    PersonaSocket.objectOutputStream.writeObject("get childrensByKindergarten");

                    System.out.println("garten ::: " + newValue);

                    PersonaSocket.objectOutputStream.writeObject(newValue);

                    childrenList = (ArrayList<Child>) PersonaSocket.objectInputStream.readObject();


                    List<String> childrenNamesList = new ArrayList<>();

                    if(! childrenList.isEmpty()) {

                        for (Child c: childrenList) {
                            childrenNamesList.add(c.getChildName());
                        }

                        childIdCB.setItems(FXCollections.observableArrayList(childrenNamesList));

                    }else {

                        System.out.println("failed to load children list!!!");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

        childIdCB.setEditable(true);

        childIdCB.getEditor().textProperty().addListener(new ChangeListener<String>() {


            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                for (Child child: childrenList) {

                    if(child.getChildName().equals(newValue)){

                       if(child.getPictures() != null) {

                           pictureIdCB.setItems(FXCollections.observableArrayList(child.getPicIdOfChild("tree")));

                       }else {

                           System.out.println("no pictures to show");

                           pictureIdCB.getSelectionModel().clearSelection();

                           pictureIdCB.getItems().clear();

                       }

                    }

                }

            }
        });



        pictureIdCB.setEditable(true);

        pictureIdCB.getEditor().textProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String c =  childIdCB.getSelectionModel().getSelectedItem().toString();

                if(newValue.equals("")){

                    imageToShow.getImage().cancel();
                }else {

                    for (Child child : childrenList) {

                        if (child.getChildName().equals(c)) {

                            imageToShow.setImage(new Image(child.getPicUrlByPicId(newValue)));

                        }
                    }
                }

            }

        });


    }





    public void onSubmitButtonClicked(ActionEvent event) {

        boolean treeTestFieldsChecker =
                diagnosticFreeText.getText().isEmpty() || diagnosticFreeText.getText().trim().isEmpty() ||
                        treeLocationTF.getSelectionModel().isEmpty()
                ;


        if (treeTestFieldsChecker) {

            System.out.println("One of the Tree test fields is empty!");

        }else {

            Picture p=null;

            String childUid = "";

            UUID uuid = UUID.randomUUID();

            String childName = childIdCB.getSelectionModel().getSelectedItem().toString();

            for (Child child: childrenList) {

                if(child.getChildName().equals(childName)) {

                    childUid = child.getChildID();
                    p = child.getPicByPicId(pictureIdCB.getSelectionModel().getSelectedItem().toString());

                }

            }



            TreeDrawingTest treeDrawingTest = new TreeDrawingTest(uuid.toString(), refToEmployee.getuId() ,
                    childUid, pictureIdCB.getSelectionModel().getSelectedItem().toString() ,p
                    , (int) this.treeSizePercentageSlider.getValue()
                    , (int) this.drawingSizePercentageSlider.getValue()
                    , (int) this.proportionBetweenElementsSlider.getValue()
                    , (int) this.referenceToLeafsSlider.getValue()
                    , (int) this.sidewaysMovementSlider.getValue()
                    , this.treeLocationTF.getSelectionModel().getSelectedItem().toString()
                    , diagnosticFreeText.getText()
            );


            System.out.println(treeDrawingTest.toString());



            try {

                PersonaSocket.objectOutputStream.writeObject("Add Diagnostic");

                PersonaSocket.objectOutputStream.writeObject("tree");

                PersonaSocket.objectOutputStream.writeObject(refToEmployee);

                PersonaSocket.objectOutputStream.writeObject(treeDrawingTest);


                String authResponse = (String)PersonaSocket.objectInputStream.readObject();

                if (authResponse.equals(PersonaSocket.SUCCESS)){

                    System.out.println("added diag successfully!!!");

                }else if (authResponse.equals(PersonaSocket.FAIL)) {

                    System.out.println("added diag failed!!!");

                }

            } catch (IOException e) {

                e.printStackTrace();

            } catch (ClassNotFoundException e) {

                e.printStackTrace();

            }




            treeSizePercentageSlider.setValue(50);
            drawingSizePercentageSlider.setValue(50);
            proportionBetweenElementsSlider.setValue(50);
            referenceToLeafsSlider.setValue(50);
            sidewaysMovementSlider.setValue(50);
            diagnosticFreeText.clear();


            treeLocationTF.getSelectionModel().clearSelection();
            treeLocationTF.setValue(null);

            kindergartenCB.setPromptText("select kindergarten");

            pictureIdCB.getSelectionModel().clearSelection();
            pictureIdCB.setValue(null);

            childIdCB.getSelectionModel().clearSelection();
            childIdCB.setValue(null);


        }


    }


    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)exitButton.getScene().getWindow()).close();

        this.refToParent.show();


    }
}
