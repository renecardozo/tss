package com.actividad4.actividad4.ventilationfan;

import com.actividad4.actividad4.common.NumberRandomPair;
import com.actividad4.actividad4.university.DataResultUniv;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;

public class TableVentilation {
    private UtilsVentilation utilsVentilation;
    private ObservableList<NumberRandomPair> dataRandom;
    private ObservableList<DataFan> dataFan;
    private ObservableList<DataCumulative> dataCumulative;
    private ObservableList<DataResult> dataResult;

    private TableView<NumberRandomPair> tableViewRandom;
    private TableView<DataFan> tableViewFan;
    private TableView<DataCumulative> tableViewCumulative;
    private TableView<DataResult> tableViewResult;

    private String name = "Ventilators";

    // Create a Label
    private Label labelA = new Label("a)");
    // Create a Label
    private Label labelB = new Label("b)");
    // Create a Label
    private Label labelC = new Label("c)");

    public TableVentilation() {
        utilsVentilation = new UtilsVentilation();
        dataRandom = utilsVentilation.fillRandomData();
        dataFan = utilsVentilation.generateDataForStatistics(false);
        dataCumulative = utilsVentilation.generateDataForCumulativeTable(false);
        dataResult = utilsVentilation.generateDataResult(false);

        tableViewRandom = generateTableDataRandom();
        tableViewFan = generateTableDataFan();
        tableViewCumulative = generateTableDataCumulative();
        tableViewResult = generateTableDataResult();

    }

    public String getName() {
        return name;
    }

    public VBox createTabContent() {

        Button btnGenerateRandom = new Button("Generate Random");
        Button btnCalculateCumulative = new Button("Calculate Cumulative Distribution");
        Button btnCleanResult = new Button("Clean Result");
        Button btnCalculateResult = new Button("Calculate Result");

        Button btnCalculateAnswer = new Button("Calculate Answers");

        btnGenerateRandom.setOnAction(e -> generateNewRandomNumber());
        btnCalculateCumulative.setOnAction(e -> calculateCumulativeDistribution());
        btnCleanResult.setOnAction(e -> cleanResults());
        btnCalculateResult.setOnAction(e -> calculatedDataResult());
        btnCalculateAnswer.setOnAction(e -> calculateAnswers());

        HBox buttonBox = new HBox(10, btnGenerateRandom, btnCalculateCumulative, btnCleanResult, btnCalculateResult, btnCalculateAnswer);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        HBox tablesBox = new HBox(20, tableViewRandom, tableViewCumulative, tableViewFan);
        HBox result = new HBox(20, tableViewResult, labelA, labelB, labelC);
        tablesBox.setPadding(new Insets(10));
        VBox content = new VBox(10, buttonBox, tablesBox, result);
        content.setPadding(new Insets(20));
        return content;
    }
    public void calculateAnswers() {
        int answerA = 0;
        int totalSales = 0;
        for (DataResult result : dataResult) {
            if (result.getSalesFan() > 8) {
                answerA++;
            }
            totalSales = totalSales + result.getSalesFan();
        }
        double sales = 0.0;
        for(DataFan result : dataFan) {
            sales = sales + (result.getWeek() * result.getStatisticsData());
        }
        double salesAverage = (double) totalSales / dataResult.size();
        labelA.setText(labelA.getText() + "\n: " + answerA + " veces que el inventario excedio su capacidad\n" );
        labelB.setText(labelB.getText() + "\n: " + salesAverage + "fueron las ventas promedio despues de la simulacion\n");
        labelC.setText(labelC.getText() + "\n: " + sales + " calentadores/semana \n");
    }

    public void calculatedDataResult() {
        dataResult.clear();
        dataResult = utilsVentilation.generateDataResult(true);

        tableViewResult.setItems(dataResult);
        tableViewResult.refresh();
    }
    public void cleanResults() {
        tableViewCumulative.getItems().clear();
        tableViewResult.getItems().clear();
    }
    public void calculateCumulativeDistribution() {
        dataFan.clear();
        dataFan = utilsVentilation.generateDataForStatistics(true);

        tableViewFan.setItems(dataFan);
        tableViewFan.refresh();

        dataCumulative.clear();
        dataCumulative = utilsVentilation.generateDataForCumulativeTable(true);

        tableViewCumulative.setItems(dataCumulative);
        tableViewCumulative.refresh();
    }

    public TableView<NumberRandomPair> generateTableDataRandom() {
        TableView<NumberRandomPair> tableView = new TableView<>();
        tableView.setPrefWidth(200);
        TableColumn<NumberRandomPair, Integer> numberColumn = new TableColumn<>("# de semana");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<NumberRandomPair, Double> randomColumn = new TableColumn<>("Numeros Aleatorios");
        randomColumn.setCellValueFactory(cellData -> cellData.getValue().randomProperty().asObject());
        randomColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        randomColumn.setOnEditCommit(event -> {
            NumberRandomPair rowData = event.getRowValue();
            rowData.setRandom(event.getNewValue());
        });
        tableView.setEditable(true);

        tableView.getColumns().add(numberColumn);
        tableView.getColumns().add(randomColumn);
        tableView.setItems(dataRandom);
        return tableView;
    }
    public void generateNewRandomNumber() {
        dataRandom.clear();
        dataRandom = utilsVentilation.generateRandomData();

        tableViewRandom.setItems(dataRandom);
        tableViewRandom.refresh();
    }
    private TableView<DataResult> generateTableDataResult() {
        TableView<DataResult> tableView = new TableView<>();
        tableView.setPrefWidth(600);
        TableColumn<DataResult, Double> weekColumn = new TableColumn<>("# de semana");
        weekColumn.setCellValueFactory(new PropertyValueFactory<>("week"));

        TableColumn<DataResult, Double> randomColumn = new TableColumn<>("Numeros Aleatorios");
        randomColumn.setCellValueFactory(new PropertyValueFactory<>("random"));

        TableColumn<DataResult, Integer> salesFanColumn = new TableColumn<>("Ventas de Calentadores");
        salesFanColumn.setCellValueFactory(new PropertyValueFactory<>("salesFan"));


        tableView.getColumns().add(weekColumn);
        tableView.getColumns().add(randomColumn);
        tableView.getColumns().add(salesFanColumn);
        tableView.setItems(dataResult);
        return tableView;

    }
    private TableView<DataCumulative> generateTableDataCumulative() {
        TableView<DataCumulative> tableView = new TableView<>();
        tableView.setPrefWidth(800);
        TableColumn<DataCumulative, Double> statisticsDataColumn = new TableColumn<>("Ventas por semana");
        statisticsDataColumn.setCellValueFactory(new PropertyValueFactory<>("statisticsData"));

        TableColumn<DataCumulative, Double> cumulativeColumn = new TableColumn<>("# de semana que se vendio esta cantidad");
        cumulativeColumn.setCellValueFactory(new PropertyValueFactory<>("cumulative"));

        TableColumn<DataCumulative, String> rangeColumn = new TableColumn<>("Probabilidad");
        rangeColumn.setCellValueFactory(new PropertyValueFactory<>("range"));

        TableColumn<DataCumulative, Integer> fanSalesColumn = new TableColumn<>("Probabilidad");
        fanSalesColumn.setCellValueFactory(new PropertyValueFactory<>("fanSales"));

        tableView.getColumns().add(statisticsDataColumn);
        tableView.getColumns().add(cumulativeColumn);
        tableView.getColumns().add(rangeColumn);
        tableView.getColumns().add(fanSalesColumn);
        tableView.setItems(dataCumulative);
        return tableView;

    }
    private TableView<DataFan> generateTableDataFan() {
        // Create the TableView
        TableView<DataFan> tableView = new TableView<>();
        tableView.setPrefWidth(600);
        // Create the "Random" column
        TableColumn<DataFan, Integer> weekColumn = new TableColumn<>("Ventas por semana");
        weekColumn.setCellValueFactory(new PropertyValueFactory<>("week"));

        TableColumn<DataFan, Integer> weekSalesColumn = new TableColumn<>("# de semana que se vendio esta cantidad");
        weekSalesColumn.setCellValueFactory(new PropertyValueFactory<>("weekSales"));

        TableColumn<DataFan, Double> statisticsDataColumn = new TableColumn<>("Probabilidad");
        statisticsDataColumn.setCellValueFactory(new PropertyValueFactory<>("statisticsData"));

        tableView.getColumns().add(weekColumn);
        tableView.getColumns().add(weekSalesColumn);
        tableView.getColumns().add(statisticsDataColumn);
        tableView.setItems(dataFan);
        return tableView;

    }
}
