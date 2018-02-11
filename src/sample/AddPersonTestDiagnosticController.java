package sample;

import ModulesPackage.Child;
import ModulesPackage.Employee;
import ModulesPackage.PersonDrawingTest;
import ModulesPackage.Picture;
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
public class AddPersonTestDiagnosticController {

    public Employee refToEmployee;
    public ImageView imageToShow;

    private Stage refToParent;

    public List<Child> childrenList;


    @FXML
    public Slider personSizePercentageSlider;
    public Slider drawingSizePercentageSlider;
    public Slider proportionBetweenElementsSlider;

    public CheckBox headIsExist;
    public CheckBox LegsIsExist;
    public CheckBox ArmsIsExist;

    public CheckBox eyesIsExist;
    public CheckBox mouthIsExist;
    public CheckBox hairIsExist;

    public ComboBox numOfClothingCB;
    public TextField diagnosticFreeText;

    public ComboBox kindergartenCB;
    public ComboBox childIdCB;
    public ComboBox pictureIdCB;


    public Button submitAddNewDiagnostic;
    public Button exitButton;



    public void initData(Stage refToParent , Employee refToEmployee){

        this.refToParent = refToParent;
        this.refToEmployee = refToEmployee;

        System.out.println(refToEmployee.toString());
        System.out.println("uid ::: "  + refToEmployee.getuId());

        numOfClothingCB.getSelectionModel().select(0);

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

                    for (Child c: childrenList) {

                        childrenNamesList.add(c.getChildName());

                    }

                    if(! childrenList.isEmpty()) {


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

                            pictureIdCB.setItems(FXCollections.observableArrayList(child.getPicIdOfChild("person")));

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

    public void onSubmitButtonClicked(ActionEvent actionEvent) {

        boolean personTestFieldsChecker =
                diagnosticFreeText.getText().isEmpty() || diagnosticFreeText.getText().trim().isEmpty();

        boolean personChoosingChecker =
                kindergartenCB.getSelectionModel().isEmpty() ||
                        childIdCB.getSelectionModel().isEmpty() ||
                        pictureIdCB.getSelectionModel().isEmpty() ;

        if (personTestFieldsChecker || personChoosingChecker ) {

            if(personTestFieldsChecker)
                System.out.println("One of the Tree test fields is empty!");

            if (personChoosingChecker)
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

            PersonDrawingTest personDrawingTest = new PersonDrawingTest(
                    uuid.toString() , refToEmployee.getuId() , childUid ,
                    pictureIdCB.getSelectionModel().getSelectedItem().toString() ,p
                    , (int) personSizePercentageSlider.getValue()
                    , (int) drawingSizePercentageSlider.getValue()
                    , (int) proportionBetweenElementsSlider.getValue()
                    , headIsExist.isSelected()
                    , LegsIsExist.isSelected()
                    ,ArmsIsExist.isSelected()
                    ,eyesIsExist.isSelected()
                    ,mouthIsExist.isSelected()
                    ,hairIsExist.isSelected()
                    ,Integer.parseInt( numOfClothingCB.getSelectionModel().getSelectedItem().toString() )
                    ,diagnosticFreeText.getText()
            );


            System.out.println(personDrawingTest.toString());




            try {

                PersonaSocket.objectOutputStream.writeObject("Add Diagnostic");

                PersonaSocket.objectOutputStream.writeObject("person");

                PersonaSocket.objectOutputStream.writeObject(refToEmployee);

                PersonaSocket.objectOutputStream.writeObject(personDrawingTest);

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





            personSizePercentageSlider.setValue(50);
            drawingSizePercentageSlider.setValue(50);
            proportionBetweenElementsSlider.setValue(50);

            LegsIsExist.setSelected(false);
            ArmsIsExist.setSelected(false);
            headIsExist.setSelected(false);
            eyesIsExist.setSelected(false);
            hairIsExist.setSelected(false);
            mouthIsExist.setSelected(false);

            numOfClothingCB.getSelectionModel().clearSelection();
            numOfClothingCB.setValue(null);

            diagnosticFreeText.clear();

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
