package sample;

import ModulesPackage.EmployeePerformance;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WatchWorkersPerformanceController {

    @FXML
    public BorderPane borderPaneWatch;

    private Stage refToParent;

    @FXML
    public Button ShowButton;
    public Button ExitButton;

    @FXML
    public void initialize()  {

    }

    public void initData(Stage refToParent){

        this.refToParent = refToParent;

    }

    public void onShowButtonClicked(ActionEvent actionEvent) throws ClassNotFoundException {


        try {



            PersonaSocket.objectOutputStream.writeObject("get EmployeeStatistics");

            List<EmployeePerformance> empPerList = new ArrayList<>();

            empPerList = (ArrayList<EmployeePerformance>)PersonaSocket.objectInputStream.readObject();

            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();

            final BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);

            bc.setTitle("Employees performance chart");

            xAxis.setLabel("Employees");
            yAxis.setLabel("Amounts");

            /*

            XYChart.Series series1 = new XYChart.Series();
            series1.setName("2003");
            series1.getData().add(new XYChart.Data("austria", 25601.34));
            series1.getData().add(new XYChart.Data("brazil", 20148.82));
            series1.getData().add(new XYChart.Data("france", 10000));
            series1.getData().add(new XYChart.Data("italy", 35407.15));
            series1.getData().add(new XYChart.Data("usa", 12000));

            XYChart.Series series2 = new XYChart.Series();
            series2.setName("2004");
            series2.getData().add(new XYChart.Data("austria", 57401.85));
            series2.getData().add(new XYChart.Data("brazil", 41941.19));
            series2.getData().add(new XYChart.Data("france", 45263.37));
            series2.getData().add(new XYChart.Data("italy", 117320.16));
            series2.getData().add(new XYChart.Data("usa", 14845.27));

            XYChart.Series series3 = new XYChart.Series();
            series3.setName("2005");
            series3.getData().add(new XYChart.Data("austria", 45000.65));
            series3.getData().add(new XYChart.Data("brazil", 44835.76));
            series3.getData().add(new XYChart.Data("france", 18722.18));
            series3.getData().add(new XYChart.Data("italy", 17557.31));
            series3.getData().add(new XYChart.Data("usa", 92633.68));

            bc.getData().addAll(series1, series2, series3);

            borderPaneWatch.setTop(bc);

            */


            XYChart.Series seriesTree = new XYChart.Series();
            XYChart.Series seriesPerson = new XYChart.Series();
            XYChart.Series seriesHouse = new XYChart.Series();

            seriesTree.setName("Tree");
            seriesPerson.setName("Person");
            seriesHouse.setName("House");


            for (EmployeePerformance emp: empPerList) {

                seriesTree.getData().add(new XYChart.Data( emp.getEmpName() , emp.getTreeDiagCounter() ));


                seriesPerson.getData().add(new XYChart.Data(emp.getEmpName()  , emp.getPersonDiagCounter() ));


                seriesHouse.getData().add(new XYChart.Data( emp.getEmpName() , emp.getHouseDiagCounter() ));

            }

            bc.getData().addAll(seriesTree, seriesPerson, seriesHouse);

            bc.setPadding(new Insets(10, 10, 10, 10));

            borderPaneWatch.setCenter(bc);




















        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void onExitButtonClicked(ActionEvent actionEvent) {

        ((Stage)ExitButton.getScene().getWindow()).close();

        this.refToParent.show();


    }
}
