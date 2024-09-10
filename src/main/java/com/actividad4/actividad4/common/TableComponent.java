package com.actividad4.actividad4.common;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.HashMap;
import java.util.Map;

public abstract class TableComponent {
    protected TableView<NumberRandomPairCumulative> tableViewAccumulativeDistributionTable;
    protected ObservableList<NumberRandomPairCumulative> data;
    protected double[] statisticsData;
    private String lastNameColumn;
    protected int start;
    private int prevStart;

    protected HashMap<Integer, double[]> map;

    public int getDataInRange(Double value) {
        int dataItem = 0;
        for (Map.Entry<Integer, double[]> entry : map.entrySet()) {
            int key = entry.getKey();
            double[] values = entry.getValue();
            if (value >= values[0] && value < values[1]) {
                dataItem = key;
                break;
            }
        }
        if (dataItem == 0) {
            System.out.println("Number not found in the limits");
        }
        return dataItem;
    }

    public TableComponent(String lastColumnName, double[] statisticsData, int start) {
        lastNameColumn = lastColumnName;
        this.statisticsData = statisticsData;
        this.start = start;
        this.prevStart = start;
        data = fillStatisticData(false);
        tableViewAccumulativeDistributionTable = generateTable();
        map = new HashMap<>();
    }

    public TableView<NumberRandomPairCumulative> getTableViewAccumulativeDistributionTable() {
        return tableViewAccumulativeDistributionTable;
    }
    protected TableView<NumberRandomPairCumulative> generateTable() {
        // Create the TableView
        TableView<NumberRandomPairCumulative> tableView = new TableView<>();
        tableView.setPrefWidth(400);

        // Create the "Random" column
        TableColumn<NumberRandomPairCumulative, Double> randomColumn = new TableColumn<>("Statistics");
        randomColumn.setCellValueFactory(new PropertyValueFactory<>("random"));

        TableColumn<NumberRandomPairCumulative, Double> cumulativeColumn = new TableColumn<>("Cumulative");
        cumulativeColumn.setCellValueFactory(new PropertyValueFactory<>("cumulative"));

        TableColumn<NumberRandomPairCumulative, String> rangeColumn = new TableColumn<>("Range");
        rangeColumn.setCellValueFactory(new PropertyValueFactory<>("range"));

        // Create the "Number" column
        TableColumn<NumberRandomPairCumulative, Integer> numberColumn = new TableColumn<>(lastNameColumn);
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        // Add columns to the TableView
        tableView.getColumns().addAll(randomColumn, cumulativeColumn, rangeColumn, numberColumn);

        // Generate data and set it to the TableView
        tableView.setItems(this.data);
//        if (this.refreshTable) {
//            tableView.refresh();
//        }
        return tableView;
    }

    public void clean() {
        this.tableViewAccumulativeDistributionTable.getItems().clear();
    }

    public void calculateCumulativeDistribution() {
        this.start = this.prevStart;
        this.data.clear();
        this.data = fillStatisticData(true);
        this.tableViewAccumulativeDistributionTable.setItems(this.data);
        this.tableViewAccumulativeDistributionTable.refresh();
    }

    public ObservableList<NumberRandomPairCumulative> fillStatisticData(boolean refreshTable) {
        ObservableList<NumberRandomPairCumulative> data = FXCollections.observableArrayList();
        double cumulative = 0.0;
        double preCumulative = cumulative;
        for (int i = 0; i < statisticsData.length; i++) {
            if (refreshTable) {
                if (i == 0) {
                    cumulative = statisticsData[i];
                } else {
                    preCumulative = cumulative;
                    cumulative = statisticsData[i] + cumulative;
                }
                double leftLimit = i == 0 ? 0 : preCumulative;
                double rightLimit = cumulative;
                String range = "[ " + leftLimit + ", " + rightLimit + "]";
                data.add(new NumberRandomPairCumulative(
                        this.start,
                        statisticsData[i],
                        range,
                        cumulative,
                        leftLimit,
                        rightLimit));
                map.put(this.start, new double[]{leftLimit, rightLimit});
            } else {
                data.add(new NumberRandomPairCumulative(
                        this.start,
                        statisticsData[i],
                        "[,]",
                        0.0,
                        0.0,
                        0.0));
            }
            this.start++;
        }
        return data;
    }


}
