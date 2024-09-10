package com.actividad4.actividad4.university;

import com.actividad4.actividad4.bnkbnb.BnbData;
import com.actividad4.actividad4.bnkbnb.UtilsBnb;
import com.actividad4.actividad4.common.NumberRandomPair;
import com.actividad4.actividad4.common.NumberRandomPairCumulative;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;

import java.util.Arrays;

public class TableProgramsUniv {
    private UtilsRandom utilsRandom;
    private ObservableList<NumberRandomPair> dataRandom;
    private ObservableList<BnbData> dataCumulative;
    private ObservableList<DataResultUniv> dataResult;

    private TableView<NumberRandomPair> tableRandom;
    private TableView<BnbData> tableCumulative;
    private TableView<DataResultUniv> tableResult;

    private String name = "University";
    private Integer programsToCalculate = 0;
    private double averageProfit = 0.0;
    private Label labelResult = new Label("Average Profit:");

    public TableProgramsUniv() {
        utilsRandom = new UtilsRandom();
        dataRandom = utilsRandom.fillRandomData();
        dataCumulative = utilsRandom.generateDataForCumulativeTable(false);
        dataResult = utilsRandom.calculateDataResultUni(programsToCalculate);

        tableRandom = generateRandomTable();
        tableCumulative = generateTableCumulative();
        tableResult = generateTableResult();
    }

    public String getName() {
        return name;
    }

    public VBox createTabContent() {
        // Create a Label
        Label label = new Label("Enter an integer:");

        // Create a TextField for integer input
        TextField integerTextField = new TextField();
        integerTextField.setPromptText("Enter integer");

        // Create a Button to read and process the value
        Button readButton = new Button("Read Value");

        // Create a Button to clear the TextField
        Button clearButton = new Button("Clear");

        // Create a Label to display the read value
        Label outputLabel = new Label();

        // Set action for the read button
        readButton.setOnAction(e -> {
            try {
                // Read the value from the TextField
                programsToCalculate = Integer.parseInt(integerTextField.getText());
                outputLabel.setText("You entered: " + programsToCalculate);
            } catch (NumberFormatException ex) {
                // Handle invalid integer input
                outputLabel.setText("Please enter a valid integer.");
            }
        });

        // Set action for the clear button
        clearButton.setOnAction(e -> {
            // Clear the TextField
            integerTextField.clear();
            outputLabel.setText("");
        });


        // Buttons
        Button btnGenerateRandom = new Button("Generate Random");
        Button btnCalculateCumulative = new Button("Calculate Cumulative Distribution");
        Button btnCleanResult = new Button("Clean Result");
        Button btnCalculateResult = new Button("Calculate Result");

        btnGenerateRandom.setOnAction(e -> generateNewRandomNumber());
        btnCalculateCumulative.setOnAction(e -> calculateCumulativeDistribution());
        btnCleanResult.setOnAction(e -> cleanResults());
        btnCalculateResult.setOnAction(e -> calculatedDataResult(programsToCalculate));

        HBox textFieldBox = new HBox(10, label, integerTextField, readButton, clearButton, outputLabel);
        textFieldBox.setAlignment(Pos.CENTER);
        textFieldBox.setPadding(new Insets(10));

        HBox buttonBox = new HBox(10, btnGenerateRandom, btnCalculateCumulative, btnCleanResult, btnCalculateResult);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        HBox tablesBox = new HBox(20, tableRandom, tableCumulative);
        labelResult = new Label("Average Profit:");
        HBox result = new HBox(20, tableResult, labelResult);
        tablesBox.setPadding(new Insets(10));
        VBox content = new VBox(10, textFieldBox, buttonBox, tablesBox, result);
        content.setPadding(new Insets(20));
        return content;
    }

    public TableView<NumberRandomPair> getTableRandom() {
        return tableRandom;
    }

    public TableView<BnbData> getTableCumulative() {
        return tableCumulative;
    }

    public TableView<DataResultUniv> getTableResult() {
        return tableResult;
    }

    private TableView<NumberRandomPair> generateRandomTable() {
        // Create the TableView
        TableView<NumberRandomPair> tableView = new TableView<>();
        tableView.setPrefWidth(200);
        // Create the "Random" column
        TableColumn<NumberRandomPair, Double> randomDataColumn = new TableColumn<>("#");
        randomDataColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<NumberRandomPair, Double> randomColumn = new TableColumn<>("Numero Aleatorio");
        randomColumn.setCellValueFactory(cellData -> cellData.getValue().randomProperty().asObject());
        randomColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        randomColumn.setOnEditCommit(event -> {
            NumberRandomPair rowData = event.getRowValue();
            rowData.setRandom(event.getNewValue());
        });
        tableView.setEditable(true);
        tableView.getColumns().add(randomDataColumn);
        tableView.getColumns().add(randomColumn);
        tableView.setItems(this.dataRandom);
        return tableView;
    }

    private TableView<BnbData> generateTableCumulative() {
        // Create the TableView
        TableView<BnbData> tableView = new TableView<>();
        tableView.setPrefWidth(500);

        // Create the "Random" column
        TableColumn<BnbData, Double> statisticDataColumn = new TableColumn<>("Statistics");
        statisticDataColumn.setCellValueFactory(new PropertyValueFactory<>("statisticData"));

        TableColumn<BnbData, Double> cumulativeColumn = new TableColumn<>("Cumulative");
        cumulativeColumn.setCellValueFactory(new PropertyValueFactory<>("cumulative"));

        TableColumn<BnbData, String> rangeColumn = new TableColumn<>("Range");
        rangeColumn.setCellValueFactory(new PropertyValueFactory<>("range"));

        // Create the "Number" column
        TableColumn<BnbData, Integer> programsColumn = new TableColumn<>("Programas");
        programsColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        // Add columns to the TableView
        tableView.getColumns().add(statisticDataColumn);
        tableView.getColumns().add(cumulativeColumn);
        tableView.getColumns().add(rangeColumn);
        tableView.getColumns().add(programsColumn);

        // Generate data and set it to the TableView
        tableView.setItems(dataCumulative);
        return tableView;
    }

    private TableView<DataResultUniv> generateTableResult() {
        // Create the TableView
        TableView<DataResultUniv> tableView = new TableView<>();
        tableView.setPrefWidth(500);

        // Create the "Random" column
        TableColumn<DataResultUniv, Double> dayColumn = new TableColumn<>("Dia");
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("day"));

        TableColumn<DataResultUniv, Double> randomColumn = new TableColumn<>("# Aleatorio");
        randomColumn.setCellValueFactory(new PropertyValueFactory<>("random"));

        // Create the "Number" column
        TableColumn<DataResultUniv, Integer> demandColumn = new TableColumn<>("Demanda");
        demandColumn.setCellValueFactory(new PropertyValueFactory<>("demand"));

        TableColumn<DataResultUniv, String> profitColumn = new TableColumn<>("Ganancia");
        profitColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));

        // Add columns to the TableView
        tableView.getColumns().add(dayColumn);
        tableView.getColumns().add(randomColumn);
        tableView.getColumns().add(demandColumn);
        tableView.getColumns().add(profitColumn);

        // Generate data and set it to the TableView
        dataResult = utilsRandom.calculateDataResultUni(0);
        tableView.setItems(dataResult);
        return tableView;
    }

    public void calculateCumulativeDistribution() {
        dataCumulative.clear();
        dataCumulative = utilsRandom.generateDataForCumulativeTable(true);

        tableCumulative.setItems(dataCumulative);
        tableCumulative.refresh();
    }

    public void cleanResults() {
        tableCumulative.getItems().clear();
        tableResult.getItems().clear();
    }

    public void generateNewRandomNumber() {
        dataRandom.clear();
        dataRandom = utilsRandom.generateRandomData();

        tableRandom.setItems(dataRandom);
        tableRandom.refresh();
    }

    public void calculatedDataResult(Integer programs) {
        dataResult.clear();
        dataResult = utilsRandom.calculateDataResultUni(programs);
        double totalProfit = 0.0;
        tableResult.setItems(dataResult);
        tableResult.refresh();
        for (DataResultUniv result : dataResult) {
            totalProfit += result.getProfit();  // Assuming getProfit() returns the profit as a double
        }

        labelResult.setText("Average Profit: " + (totalProfit / dataResult.size()));
    }

}
