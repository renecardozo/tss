package com.actividad4.actividad4.bnkbnb;

import com.actividad4.actividad4.common.NumberRandomPair;
import javafx.collections.FXCollections;
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

public class BnbTableCumulative {
    private TableTimeService tableTimeService = new TableTimeService();
    private TableTimeArrived tableTimeArrived = new TableTimeArrived();
    private TableView<BnbDataResult> bnbDataResultTableView;
    private TableView<NumberRandomPair> tableRandom;
    private ObservableList<NumberRandomPair> dataRandom;
    private ObservableList<BnbDataResult> dataBnbResult;
    private String name;

    public BnbTableCumulative() {
        dataRandom = UtilsBnb.fillRandomData();
        tableRandom = generateRandomTableView();
        dataBnbResult = calculateCumulativeResult(false);
        bnbDataResultTableView = generateTableResult();
        name = "Bank BNB";
    }

    public String getName() {
        return name;
    }
    public VBox createTabContent() {
        // Title
        Label title = new Label("");
        // Buttons
        Button btnGenerateRandom = new Button("Generate Random");
        Button btnCalculateCumulative = new Button("Calculate Cumulative Distribution");
        Button btnCleanResult = new Button("Clean Result");
        Button btnCalculateResult = new Button("Calculate Result");

        // Event Handlers for buttons
        btnGenerateRandom.setOnAction(e -> generateRandomNumbers());
        btnCalculateCumulative.setOnAction(e -> calculateCumulativeDistribution());
        btnCleanResult.setOnAction(e -> cleanResults());
        btnCalculateResult.setOnAction(e -> generateDataResult());

        HBox buttonBox = new HBox(10, btnGenerateRandom, btnCalculateCumulative, btnCleanResult, btnCalculateResult);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));

        HBox tablesBox = new HBox(20, tableRandom, tableTimeService.getTableView(), tableTimeArrived.getTableView());
        HBox result = new HBox(20, bnbDataResultTableView);
        tablesBox.setPadding(new Insets(10));
        VBox content = new VBox(10, title, buttonBox, tablesBox, result);
//        VBox content = new VBox(10, title, buttonBox, tablesBox);
        content.setPadding(new Insets(20));
        return content;
    }
    public TableView<NumberRandomPair> generateRandomTableView() {
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
    public void cleanResults() {
        tableTimeArrived.getTableView().getItems().clear();
        tableTimeService.getTableView().getItems().clear();
    }

    private void generateRandomNumbers() {
        dataRandom.clear();
        dataRandom = UtilsBnb.generateRandomData();

        tableRandom.setItems(dataRandom);
        tableRandom.refresh();
    }

    private void generateDataResult() {
        dataBnbResult.clear();
        dataBnbResult = calculateCumulativeResult(true);

        bnbDataResultTableView.setItems(dataBnbResult);
        bnbDataResultTableView.refresh();
    }
    public void calculateCumulativeDistribution() {
        tableTimeService.calculateCumulativeDistribution();

        tableTimeArrived.calculateCumulativeDistribution();
    }

    public ObservableList<BnbDataResult> calculateCumulativeResult(boolean calculateData) {
        ObservableList<BnbDataResult> data = FXCollections.observableArrayList();
        Integer arrivedTimePrev = 0;
        Integer startService = 0;
        Integer endService = 0;
        Integer endServicePrev = 0;
        for (int i = 1; i <= 8; i++) {
            Integer client = i;
            if (calculateData) {
                Double randomClient = UtilsBnb.randomStackNumbers.pop();
                Integer intervalTimeArrived = tableTimeArrived.getDataInRange(randomClient);
                Integer arrivedTime = intervalTimeArrived + arrivedTimePrev;
                Double randomService = UtilsBnb.randomStackNumbers.pop();;
                Integer timeService = tableTimeService.getDataInRange(randomService);
                if (client == 1) {
                    startService = arrivedTime;
                    endServicePrev = arrivedTime;
                }
                endService = startService + timeService;
                Integer waitTime = startService - arrivedTime;
                Integer wasteTime = startService - endServicePrev;
                data.add(new BnbDataResult(
                        client,
                        randomClient,
                        intervalTimeArrived,
                        arrivedTime,
                        randomService,
                        timeService,
                        startService,
                        endService,
                        waitTime,
                        wasteTime));
                arrivedTimePrev = arrivedTime;
                startService = endService;
                endServicePrev = endService;
            } else {
                Double randomClient = 0.0;
                Integer intervalTimeArrived = 0;
                Integer arrivedTime = 0;
                Double randomService = 0.0;
                Integer timeService = 0;
                Integer waitTime = 0;
                Integer wasteTime = 0;
                data.add(new BnbDataResult(
                        client,
                        randomClient,
                        intervalTimeArrived,
                        arrivedTime,
                        randomService,
                        timeService,
                        startService,
                        endService,
                        waitTime,
                        wasteTime));
            }
        }
        return data;
    }

    public TableView<BnbDataResult> generateTableResult() {
        // Create the TableView
        TableView<BnbDataResult> tableView = new TableView<>();
        tableView.setPrefWidth(1000);
        // Create the "Random" column
        TableColumn<BnbDataResult, Integer> clientColumn = new TableColumn<>("# cliente");
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        tableView.getColumns().add(clientColumn);

        TableColumn<BnbDataResult, Double> randomClientColumn = new TableColumn<>("# aleatorio 1");
        randomClientColumn.setCellValueFactory(new PropertyValueFactory<>("randomClient"));
        tableView.getColumns().add(randomClientColumn);

        TableColumn<BnbDataResult, Integer> intervalTimeArrivedColumn = new TableColumn<>("Intervalo entre llegadas");
        intervalTimeArrivedColumn.setCellValueFactory(new PropertyValueFactory<>("intervalTimeArrived"));
        tableView.getColumns().add(intervalTimeArrivedColumn);

        TableColumn<BnbDataResult, String> arrivedTimeFormattedColumn = new TableColumn<>("Intervalo entre llegadas");
        arrivedTimeFormattedColumn.setCellValueFactory(new PropertyValueFactory<>("arrivedTimeFormatted"));
        tableView.getColumns().add(arrivedTimeFormattedColumn);

        TableColumn<BnbDataResult, Double> randomServiceColumn = new TableColumn<>("#aleatorio 2");
        randomServiceColumn.setCellValueFactory(new PropertyValueFactory<>("randomService"));
        tableView.getColumns().add(randomServiceColumn);

        TableColumn<BnbDataResult, Integer> timeServiceColumn = new TableColumn<>("t servicio");
        timeServiceColumn.setCellValueFactory(new PropertyValueFactory<>("timeService"));
        tableView.getColumns().add(timeServiceColumn);

        TableColumn<BnbDataResult, String> startServiceFormattedColumn = new TableColumn<>("Inicio de Servicio");
        startServiceFormattedColumn.setCellValueFactory(new PropertyValueFactory<>("startServiceFormatted"));
        tableView.getColumns().add(startServiceFormattedColumn);

        TableColumn<BnbDataResult, String> endServiceFormattedColumn = new TableColumn<>("Final de Servicio");
        endServiceFormattedColumn.setCellValueFactory(new PropertyValueFactory<>("endServiceFormatted"));
        tableView.getColumns().add(endServiceFormattedColumn);

        TableColumn<BnbDataResult, Integer> waitTimeColumn = new TableColumn<>("t espera");
        waitTimeColumn.setCellValueFactory(new PropertyValueFactory<>("waitTime"));
        tableView.getColumns().add(waitTimeColumn);

        TableColumn<BnbDataResult, Integer> wasteTimeColumn = new TableColumn<>("t ocioso");
        wasteTimeColumn.setCellValueFactory(new PropertyValueFactory<>("wasteTime"));
        tableView.getColumns().add(wasteTimeColumn);


        tableView.setItems(dataBnbResult);
        return tableView;

    }
}
