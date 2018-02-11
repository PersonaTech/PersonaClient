package sample;

import ModulesPackage.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by itamarfredavrahami on 17/12/2017.
 */
public class AddHouseTestDiagnosticController {

    public Employee refToEmployee;

    public List<Child> childrenList;

    @FXML
    public Button submitAddNewDiagnostic;
    public Button exitButton;
    public ImageView imageToShow;

    private Stage refToParent;

    @FXML
    public Slider houseSizePercentageSlider;
    public Slider drawingSizePercentageSlider;
    public Slider proportionBetweenElementsSlider;
    public Slider referenceToDoorWindowsSlider;
    public Slider referenceToRoofSlider;
    public TextField diagnosticFreeText;

    @FXML
    public ComboBox kindergartenCB;
    public ComboBox pictureIdCB;
    public ComboBox childIdCB;

    @FXML
    ToggleGroup yesNoToggleGroup;




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

                            pictureIdCB.setItems(FXCollections.observableArrayList(child.getPicIdOfChild("house")));

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


        yesNoToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

                if (yesNoToggleGroup.getSelectedToggle() != null) {

                    RadioButton chk = (RadioButton)yesNoToggleGroup.getSelectedToggle();


                }

            }
        });



    }


    public void onSubmitButtonClicked(ActionEvent actionEvent) {

        boolean houseTestFieldsChecker =
                diagnosticFreeText.getText().isEmpty() || diagnosticFreeText.getText().trim().isEmpty();

        boolean houseChoosingChecker =
                kindergartenCB.getSelectionModel().isEmpty() ||
                        childIdCB.getSelectionModel().isEmpty() ||
                        pictureIdCB.getSelectionModel().isEmpty() ;



        if (houseTestFieldsChecker || houseChoosingChecker ) {

            if(houseTestFieldsChecker)
                System.out.println("One of the Tree test fields is empty!");

            if (houseChoosingChecker)
                System.out.println(" You must Choose kindergarten , child and picture!!!");

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



            RadioButton chk = (RadioButton)yesNoToggleGroup.getSelectedToggle();


            HouseDrawingTest houseDrawingTest = new HouseDrawingTest(uuid.toString() , refToEmployee.getuId() , childUid , pictureIdCB.getSelectionModel().getSelectedItem().toString(),p
                    , (int) this.houseSizePercentageSlider.getValue()
                    , (int) this.drawingSizePercentageSlider.getValue()
                    , (int) this.proportionBetweenElementsSlider.getValue()
                    , (int) this.referenceToDoorWindowsSlider.getValue()
                    , (int) this.referenceToRoofSlider.getValue()
                    , chk.getText()
                    , diagnosticFreeText.getText()
            );

            System.out.println(houseDrawingTest.toString());



            try {

                PersonaSocket.objectOutputStream.writeObject("Add Diagnostic");

                PersonaSocket.objectOutputStream.writeObject("house");

                PersonaSocket.objectOutputStream.writeObject(refToEmployee);

                PersonaSocket.objectOutputStream.writeObject(houseDrawingTest);


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

    }
}
