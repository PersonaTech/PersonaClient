package sample;

import ModulesPackage.Child;
import ModulesPackage.Employee;
import ModulesPackage.TreeDrawingTest;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itamarfredavrahami on 10/12/2017.
 */
public class AddTreeTestDiagnosticController {


    public Employee refToEmployee;
    public HBox paneToShowPic;


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


    public ImageView myimage;






    public ComboBox kindergartenCB;
    public ComboBox childIdCB;
    public ComboBox pictureIdCB;




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

                    List<Child> childrenList = new ArrayList<>();

                    PersonaSocket.objectOutputStream.writeObject("get childrensByKindergarten");

                    System.out.println("garten ::: " + newValue);

                    PersonaSocket.objectOutputStream.writeObject(newValue);

                    childrenList = (ArrayList<Child>) PersonaSocket.objectInputStream.readObject();

                    if(! childrenList.isEmpty()) {

                        childIdCB.setItems(FXCollections.observableArrayList(childrenList));

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

        pictureIdCB.setEditable(true);
        pictureIdCB.getEditor().textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


                //// List<String> picturesList = new ArrayList<>();


                System.out.println("val ::: " + newValue);

                if (newValue.equals("example")){

//
//                    ImageView imageView = new ImageView();
//                    imageView.fitHeightProperty().setValue(200);
//                    imageView.fitWidthProperty().setValue(200);
//
//                    Image image = new Image("http://goo.gl/kYEQl");
//
//                    imageView.setImage(image);

//                    borderRoot.setLeft(imageView);

//                    paneToShowPic.getChildren().



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

            TreeDrawingTest treeDrawingTest = new TreeDrawingTest("default", refToEmployee.getuId() , "ch1", "pic1"
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

        }






    }


    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)exitButton.getScene().getWindow()).close();

        this.refToParent.show();





//
//        borderRoot.setLeft(imageView);






//        StackPane root = new StackPane();
//        root.getChildren().add(imageView);
//        Scene scene = new Scene(root, 300, 300);

//        Stage stage = new Stage();
//
//        stage.setTitle("java-buddy.blogspot.com");
//        stage.setScene(scene);
//        stage.show();



    }
}
