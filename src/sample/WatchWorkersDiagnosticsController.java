package sample;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class WatchWorkersDiagnosticsController {

    @FXML
    public BorderPane borderPaneWatch;
    public ComboBox swCB;
    public ComboBox pCB;

    private Stage refToParent;

    @FXML
    public Button ShowButton;
    public Button ExitButton;

    public void initialize()  {

    }

    public void initData(Stage refToParent){

        this.refToParent = refToParent;

    }

    public void onShowButtonClicked(ActionEvent actionEvent) {

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














    }

    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)ExitButton.getScene().getWindow()).close();

        this.refToParent.show();


    }


}
