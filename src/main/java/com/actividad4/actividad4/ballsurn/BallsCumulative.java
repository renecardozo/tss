package com.actividad4.actividad4.ballsurn;

import com.actividad4.actividad4.common.NumberRandomPair;
import com.actividad4.actividad4.common.NumberRandomPairCumulative;
import com.actividad4.actividad4.common.TableComponent;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BallsCumulative {

    private TableView<SecuencePair> tableView;
    private TableView<NumberRandomPair> randomTableView;
    private TableView<SecuencePair> tableViewResult;
    private ObservableList<SecuencePair> data;
    private ObservableList<NumberRandomPair> dataRandom;
    private ObservableList<SecuencePair> dataResult;
    private UtilsBalls utilsBalls = new UtilsBalls();
    private String name;
    public BallsCumulative() {
        data = utilsBalls.generateDataForCumulativeTable(false);
        tableView = generateTableView();

        dataRandom = utilsBalls.fillRandomData();
        randomTableView = generateRandomTableView();

        dataResult = utilsBalls.generateCumulativeFullData(false);
        tableViewResult = generateCumulativeResult();

        name = "URNA DE PELOTAS";

    }

    public String getName() {
        return name;
    }

    public VBox createTabContent() {
        // Title
        Label title = new Label("Table Result Two");

        // Buttons
        Button btnGenerateRandom = new Button("Generate Random");
        Button btnCalculateCumulative = new Button("Calculate Cumulative Distribution");
        Button btnCleanResult = new Button("Clean Result");
        Button btnCalculateResult = new Button("Calculate Result");
//        Button btnUseDefaultRandom = new Button("Calculate Result");

        // Event Handlers for buttons
        btnGenerateRandom.setOnAction(e -> generateRandomNumbers());
        btnCalculateCumulative.setOnAction(e -> calculateCumulativeDistribution());
        btnCleanResult.setOnAction(e -> cleanResults());
        btnCalculateResult.setOnAction(e -> calculateCumulativeResult());

        HBox buttonBox = new HBox(10, btnGenerateRandom, btnCalculateCumulative, btnCleanResult, btnCalculateResult);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        HBox tablesBox = new HBox(20, randomTableView, tableView);
        HBox result = new HBox(20, tableViewResult);
        tablesBox.setPadding(new Insets(10));
        VBox content = new VBox(10, title, buttonBox, tablesBox, result);
        content.setPadding(new Insets(20));
        return content;
    }

    private void calculateCumulativeResult() {
        dataResult.clear();
        dataResult = utilsBalls.generateCumulativeFullData(true);

        tableViewResult.setItems(dataResult);
        tableViewResult.refresh();
    }

    private void generateRandomNumbers() {
        dataRandom.clear();
        dataRandom = utilsBalls.generateRandomData();

        randomTableView.setItems(dataRandom);
        randomTableView.refresh();
    }

    public TableView<SecuencePair> generateTableView() {
        // Create the TableView
        TableView<SecuencePair> tableView = new TableView<>();
        tableView.setPrefWidth(600);
        // Create the "Random" column
        TableColumn<SecuencePair, Double> statisticsDataColumn = new TableColumn<>("Distribuciones de Probabilidad");
        statisticsDataColumn.setCellValueFactory(new PropertyValueFactory<>("statisticsData"));

        TableColumn<SecuencePair, Double> cumulativeColumn = new TableColumn<>("Distribucion Acumulada");
        cumulativeColumn.setCellValueFactory(new PropertyValueFactory<>("cumulative"));

        TableColumn<SecuencePair, String> rangeColumn = new TableColumn<>("Range");
        rangeColumn.setCellValueFactory(new PropertyValueFactory<>("range"));

        // Create the "Number" column
        TableColumn<SecuencePair, Integer> colorColumn = new TableColumn<>("Color");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

        // Add columns to the TableView

        tableView.getColumns().add(statisticsDataColumn);
        tableView.getColumns().add(cumulativeColumn);
        tableView.getColumns().add(rangeColumn);
        tableView.getColumns().add(colorColumn);
        // Generate data and set it to the TableView
        tableView.setItems(this.data);

        return tableView;
    }

    public TableView<NumberRandomPair> generateRandomTableView() {
        // Create the TableView
        TableView<NumberRandomPair> tableView = new TableView<>();
        tableView.setPrefWidth(200);
        // Create the "Random" column
        TableColumn<NumberRandomPair, Double> randomDataColumn = new TableColumn<>("#");
        randomDataColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<NumberRandomPair, Double> randomColumn = new TableColumn<>("Numero Aleatorio");
        randomColumn.setCellValueFactory(new PropertyValueFactory<>("random"));

        tableView.getColumns().add(randomDataColumn);
        tableView.getColumns().add(randomColumn);
        tableView.setItems(this.dataRandom);
        return tableView;
    }

    public TableView<SecuencePair> generateCumulativeResult() {
        // Create the TableView
        TableView<SecuencePair> tableView = new TableView<>();
        tableView.setPrefWidth(800);
        // Create the "Random" column
        TableColumn<SecuencePair, Double> statisticsDataColumn = new TableColumn<>("# de pelota ");
        statisticsDataColumn.setCellValueFactory(new PropertyValueFactory<>("range"));

        TableColumn<SecuencePair, Double> randomColumn = new TableColumn<>("Numero Aleatorio");
        randomColumn.setCellValueFactory(new PropertyValueFactory<>("statisticsData"));

        TableColumn<SecuencePair, Double> colorColumn = new TableColumn<>("Color");
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));

        tableView.getColumns().add(statisticsDataColumn);
        tableView.getColumns().add(randomColumn);
        tableView.getColumns().add(colorColumn);
        tableView.setItems(dataResult);
        return tableView;
    }
    public void cleanResults() {
        tableView.getItems().clear();
        randomTableView.getItems().clear();
    }

    public void calculateCumulativeDistribution() {
        data.clear();
        data = utilsBalls.generateDataForCumulativeTable(true);
        tableView.setItems(data);
        tableView.refresh();
    }
}
