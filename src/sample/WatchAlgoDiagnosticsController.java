package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Created by itamarfredavrahami on 14/01/2018.
 */
public class WatchAlgoDiagnosticsController {

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
