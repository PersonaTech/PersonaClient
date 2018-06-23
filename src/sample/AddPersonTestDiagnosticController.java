package sample;

import ModulesPackage.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by itamarfredavrahami on 17/12/2017.
 */
public class AddPersonTestDiagnosticController {

    public Employee refToEmployee;
    private Stage refToParent;

    /* Json Handler and Json to Table variables */

    public VBox centerWrapper;
    public VBox showButtonOfAutoEngineVbox;

     /* ------------------------------------------ */

    private JSONObject jsonObjectHolder = null;

//    public TableView tViewForAutoEngineView;
//    private final ObservableList<EngineJsonToClass> dataToTable = FXCollections.observableArrayList();
//
//    public TableView tViewForAutoColorsView;
//    private final ObservableList<EngineColorsJsonToClass> dataColorsToTable = FXCollections.observableArrayList();

     /* ------------------------------------------ */


    public List<Child> childrenList;


    public ImageView imageToShow;
    public Button submitAddNewDiagnostic;
    public Button exitButton;
    public Button processByAutoEngine;


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


    public void initData(Stage refToParent , Employee refToEmployee){

        this.refToParent = refToParent;
        this.refToEmployee = refToEmployee;

        System.out.println(refToEmployee.toString());
        System.out.println("uid ::: "  + refToEmployee.getuId());

        processByAutoEngine.setDisable(true);

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

                            processByAutoEngine.setDisable(false);

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
                //System.out.println("One of the Tree test fields is empty!");
                sampleController.showStage("One of the Person test fields is empty!");


            else if (personChoosingChecker)
                //System.out.println(" You must Choose kindergarten , child and picture!!!");
                sampleController.showStage("You must Choose kindergarten , child and picture!");

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

            if (jsonObjectHolder == null)
                System.out.println("Json still Null");
            else {
                System.out.println("Json Updated");
                personDrawingTest.updateTest(jsonObjectHolder);
            }

            System.out.println(personDrawingTest.toString());

            try {

                PersonaSocket.objectOutputStream.writeObject("Add Diagnostic");
                PersonaSocket.objectOutputStream.writeObject("person");
                PersonaSocket.objectOutputStream.writeObject(refToEmployee);
                PersonaSocket.objectOutputStream.writeObject(personDrawingTest);

                String authResponse = (String)PersonaSocket.objectInputStream.readObject();

                if (authResponse.equals(PersonaSocket.SUCCESS)){

                    System.out.println("added diag successfully!!!");
                    sampleController.showStage("added person diagnosis successfully!");

                }else if (authResponse.equals(PersonaSocket.FAIL)) {

                    System.out.println("added diag failed!!!");
                    sampleController.showStage("added person diagnosis failed!");

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

            this.centerWrapper.getChildren().clear();

        }


    }

    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)exitButton.getScene().getWindow()).close();

        this.refToParent.show();
    }

    private class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {

        private VBox centerWrapperRef;

        public TableView tViewForAutoEngineView;
        private ObservableList<EngineJsonToClass> dataToTable = FXCollections.observableArrayList();

        public TableView tViewForAutoColorsView;
        private ObservableList<EngineColorsJsonToClass> dataColorsToTable = FXCollections.observableArrayList();

        private ImageView loadingImageView= new ImageView();


        public MyAsyncTask(VBox centerWrapperRef){

            this.centerWrapperRef = centerWrapperRef;
        }

        @Override
        public void onPreExecute() {

            System.out.println("Background Thread will start");

            Image image = new Image(getClass().getResourceAsStream("processing_gif.gif"));

            Circle circle = new Circle(100, 100, 100);

            this.loadingImageView.setImage(image);
            this.loadingImageView.setClip(circle);
            this.centerWrapperRef.getChildren().add(loadingImageView);

            System.out.println("done preExecute");

        }

        @Override
        public Boolean doInBackground(String... params) {


            System.out.println("Background Thread is running");

            try {

                System.out.println("Going to talk with engine....");
                PersonaSocket.objectOutputStream.writeObject("run Engine");

                String child = childIdCB.getSelectionModel().getSelectedItem().toString();
                String picId = pictureIdCB.getSelectionModel().getSelectedItem().toString();


                for (Child c : childrenList) {

                    if (c.getChildName().equals(child)) {

                        System.out.println("request for analyze url ---> " + c.getPicUrlByPicId(picId));

                        PersonaSocket.objectOutputStream.writeObject(c.getPicUrlByPicId(picId));

                        break;

                    }
                }

                String authResponse = (String) PersonaSocket.objectInputStream.readObject();

                System.out.println("here -----> " + authResponse.toString());

                if (authResponse.equals(PersonaSocket.SUCCESS)) {

                    System.out.println("state ---> PersonaSocket.SUCCESS");
                    JSONObject jsonObject = (JSONObject) PersonaSocket.objectInputStream.readObject();

                    // ------------ create the table to all data from json except colors ------- //

                    this.tViewForAutoEngineView = new TableView();
                    this.tViewForAutoEngineView.setEditable(true);
                    this.tViewForAutoEngineView.setMaxHeight(200);

                    TableColumn col1_head = new TableColumn("Segment name");
                    col1_head.setMinWidth(120);
                    col1_head.setCellValueFactory(new PropertyValueFactory<>("segmentName"));

                    TableColumn col2_head = new TableColumn("Classifier");
                    col2_head.setMinWidth(70);
                    col2_head.setCellValueFactory(new PropertyValueFactory<>("label"));

                    TableColumn col3_head = new TableColumn("Percentages");
                    col3_head.setMinWidth(90);
                    col3_head.setCellValueFactory(new PropertyValueFactory<>("percent"));

                    TableColumn col4_head = new TableColumn("Width");
                    col4_head.setMinWidth(70);
                    col4_head.setCellValueFactory(new PropertyValueFactory<>("width"));

                    TableColumn col5_head = new TableColumn("Height");
                    col5_head.setMinWidth(70);
                    col5_head.setCellValueFactory(new PropertyValueFactory<>("height"));

                    TableColumn col6_head = new TableColumn("Info");
                    col6_head.setMinWidth(150);
                    col6_head.setCellValueFactory(new PropertyValueFactory<>("informational"));

                    this.tViewForAutoEngineView.setItems(this.dataToTable);
                    this.tViewForAutoEngineView.setStyle("-fx-background-radius: 5px;-fx-padding: 5px");
                    this.tViewForAutoEngineView.getColumns().addAll(col1_head, col2_head, col3_head, col4_head, col5_head, col6_head);


                    // ------------ create the table to all colors from the json  ------- //

                    this.dataColorsToTable = FXCollections.observableArrayList();

                    this.tViewForAutoColorsView = new TableView();
                    this.tViewForAutoColorsView.setEditable(true);
                    this.tViewForAutoColorsView.setMaxHeight(200);

                    TableColumn col1_head_colors = new TableColumn("Label");
                    col1_head_colors.setMinWidth(150);
                    col1_head_colors.setCellValueFactory(new PropertyValueFactory<>("label"));

                    TableColumn col2_head_colors = new TableColumn("Color");
                    col2_head_colors.setMinWidth(150);
                    col2_head_colors.setCellValueFactory(new PropertyValueFactory<>("color"));

                    TableColumn col3_head_colors = new TableColumn("Percentages");
                    col3_head_colors.setMinWidth(150);
                    col3_head_colors.setCellValueFactory(new PropertyValueFactory<>("percent"));

                    this.tViewForAutoColorsView.setItems(this.dataColorsToTable);
                    this.tViewForAutoColorsView.setStyle("-fx-background-radius: 5px;-fx-padding: 5px");
                    this.tViewForAutoColorsView.getColumns().addAll(col1_head_colors, col2_head_colors, col3_head_colors);


                    jsonObjectHolder = jsonObject;
                    System.out.println("-------------- \n");
                    System.out.println("Json is working fine\n ");
                    System.out.println(jsonObject);
                    System.out.println("-------------- \n");

                    JSONArray arrayOfSegments = (JSONArray) jsonObject.get("segments");

                    for (int k = 0; k < arrayOfSegments.size(); k++) {

                        if (arrayOfSegments.get(k) instanceof JSONObject) {

                            JSONObject segmentAsJson = (JSONObject) arrayOfSegments.get(k);

                            System.out.println(segmentAsJson.get("segment_name").toString());
                            System.out.println(segmentAsJson.get("label").toString());
                            System.out.println(segmentAsJson.get("percentage").toString());
                            System.out.println(segmentAsJson.get("width").toString());
                            System.out.println(segmentAsJson.get("height").toString());
                            System.out.println(segmentAsJson.get("info").toString());

                            String field_1 = new String(segmentAsJson.get("segment_name").toString());
                            String field_2 = new String(segmentAsJson.get("label").toString());
                            String field_3 = new String(segmentAsJson.get("percentage").toString() + " %");
                            String field_4 = new String(segmentAsJson.get("width").toString());
                            String field_5 = new String(segmentAsJson.get("height").toString());
                            String field_6 = new String(segmentAsJson.get("info").toString());


                            this.dataToTable.add(new EngineJsonToClass(field_1, field_2, field_3, field_4, field_5, field_6));
                            System.out.println("done.....");

                            JSONObject dictionaryOfColors = (JSONObject) segmentAsJson.get("colors");
                            System.out.println(dictionaryOfColors);

                            for (Iterator iterator = dictionaryOfColors.keySet().iterator(); iterator.hasNext(); ) {

                                String key = (String) iterator.next();
                                System.out.println(key + " ||| " + dictionaryOfColors.get(key).toString());
                                String field_1_col = new String(field_2);
                                String field_2_col = new String(key);
                                String field_3_col = new String(dictionaryOfColors.get(key).toString() + " %");

                                Color c = ColorHandler.getColorByName(key);

                                if (c != null)
                                    System.out.println("defined color");
                                else
                                    System.out.println("undefined color");

                                this.dataColorsToTable.add(new EngineColorsJsonToClass(field_1_col, field_2_col, field_3_col));
                            }


                        } else {
                            System.out.println("Not found ! ");
                        }

                    }


                } else {
                    System.out.println("state ---> PersonaSocket.FAILED");

                }

            } catch (IOException e) {
                e.printStackTrace();

                System.out.println("Error 1: Something wrong with reading the Json file !!!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();

                System.out.println("Error 2: Something wrong with reading the Json file !!!");

            }


            return true;



        }

        @Override
        public void onPostExecute(Boolean params) {

            System.out.println("Background Thread has stopped");

            this.centerWrapperRef.getChildren().remove(this.loadingImageView);

            this.centerWrapperRef.getChildren().addAll(this.tViewForAutoEngineView);
            this.centerWrapperRef.getChildren().addAll(this.tViewForAutoColorsView);

            HandleImagesGroup();

            if (params) {
                System.out.println("Done with success");
            } else {
                System.out.println("Done with error");
            }

        }

        @Override
        public void progressCallback(Integer... params) {

            System.out.println("Progress " + params[0]);

        }

        public void HandleImagesGroup() {

            System.out.println("HandleImagesGroup enter ....");

            try {

                int size = PersonaSocket.objectInputStream.readInt();

                System.out.println("size is -------> " + size);

                List<BufferedImage> bufferedImages = new ArrayList<>(size);

                int lenOfHboxes = 0;

                if (size%8==0)
                    lenOfHboxes= (int)size/8;
                else
                    lenOfHboxes= ((int)size/8) + 1;

                System.out.println("lenOfHboxes is -------> " + lenOfHboxes);


                for (int b = 0; b < size; b++) {

                    byte[] bytes = (byte[]) PersonaSocket.objectInputStream.readObject();
                    BufferedImage bi = ImageIO.read(new ByteArrayInputStream(bytes));
                    bufferedImages.add(bi);
                }

                List<HBox> hBoxList = new ArrayList<>();

                int index = 0;

                for (BufferedImage bimage : bufferedImages) {

                    if(index%8==0) {

                        HBox hBoxForImagesRoller = new HBox();
                        hBoxForImagesRoller.setSpacing(5);
                        hBoxForImagesRoller.setAlignment(Pos.CENTER);
                        hBoxList.add(hBoxForImagesRoller);
                    }

                    Image image = SwingFXUtils.toFXImage(bimage, null);
                    ImageView imageView = new ImageView();


                    imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");


                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);

                    imageView.setImage(image);
                    hBoxList.get(index/8).getChildren().add(imageView);

                    index++;


                }

                VBox vBox = new VBox();
                vBox.setAlignment(Pos.CENTER);
                vBox.setSpacing(5);

                for(HBox hBox: hBoxList)
                    vBox.getChildren().add(hBox);

                this.centerWrapperRef.getChildren().add(vBox);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            System.out.println("done test.....");

        }
    }






    public void onProcessByEngineClicked(ActionEvent actionEvent) {

        this.showButtonOfAutoEngineVbox.getChildren().remove(processByAutoEngine);

        MyAsyncTask myAsyncTask = new MyAsyncTask(this.centerWrapper);
        myAsyncTask.setDaemon(false);
        myAsyncTask.execute();

    }




}
