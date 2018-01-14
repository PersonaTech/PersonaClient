package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class WatchWorkersDiagnosticsController {

    @FXML
    public BorderPane borderPaneWatch;

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


    }

    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)ExitButton.getScene().getWindow()).close();

        this.refToParent.show();


    }


}
