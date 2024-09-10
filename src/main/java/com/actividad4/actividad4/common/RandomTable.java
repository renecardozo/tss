package com.actividad4.actividad4.common;

import com.actividad4.actividad4.bnkblood.Utils;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RandomTable {

    private TableView<NumberRandomPair> tableViewRandomNumbersTable;
    private ObservableList<NumberRandomPair> data;

    public RandomTable() {
        this.data = Utils.fillDefaultRandomData();
        this.tableViewRandomNumbersTable = generateDefaultRandomTable();
    }


    public TableView<NumberRandomPair> getTableViewRandomNumbersTable() {
        return tableViewRandomNumbersTable;
    }

    private TableView<NumberRandomPair> generateDefaultRandomTable() {
        // Create the TableView
        TableView<NumberRandomPair> tableView = new TableView<>();
        tableView.setPrefWidth(200);

        // Create the "Number" column
        TableColumn<NumberRandomPair, Integer> numberColumn = new TableColumn<>("Number");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        // Create the "Random" column
        TableColumn<NumberRandomPair, Double> randomColumn = new TableColumn<>("Random");
        randomColumn.setCellValueFactory(new PropertyValueFactory<>("random"));

        // Add columns to the TableView
        tableView.getColumns().addAll(numberColumn, randomColumn);

        // Generate data and set it to the TableView
        tableView.setItems(this.data);
        return tableView;
    }

    public void generateRandomNumbers() {
        this.data.clear();
        this.data = Utils.generateData();
        this.tableViewRandomNumbersTable.setItems(this.data);
        this.tableViewRandomNumbersTable.refresh();
    }
    public void clean() {
        this.tableViewRandomNumbersTable.getItems().clear();
    }
}
