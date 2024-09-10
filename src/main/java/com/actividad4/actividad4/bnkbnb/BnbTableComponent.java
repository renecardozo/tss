package com.actividad4.actividad4.bnkbnb;

import com.actividad4.actividad4.ballsurn.SecuencePair;
import com.actividad4.actividad4.ballsurn.UtilsBalls;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BnbTableComponent {
    private TableView<BnbData> tableView;
    private ObservableList<BnbData> data;
    private UtilsBnb utilsBnb;

    public BnbTableComponent(double[] statisticDataList, Integer start) {
        utilsBnb = new UtilsBnb(statisticDataList, start);
        data = utilsBnb.generateDataForCumulativeTable(false);
        tableView = generateTableView();
    }
    public TableView<BnbData> generateTableView() {
        // Create the TableView
        TableView<BnbData> tableView = new TableView<>();
        tableView.setPrefWidth(600);
        // Create the "Random" column
        TableColumn<BnbData, Double> statisticsDataColumn = new TableColumn<>("Probabilidad");
        statisticsDataColumn.setCellValueFactory(new PropertyValueFactory<>("statisticData"));

        TableColumn<BnbData, Double> cumulativeColumn = new TableColumn<>("Distribucion Acumulada");
        cumulativeColumn.setCellValueFactory(new PropertyValueFactory<>("cumulative"));

        TableColumn<BnbData, String> rangeColumn = new TableColumn<>("Range");
        rangeColumn.setCellValueFactory(new PropertyValueFactory<>("range"));

        // Create the "Number" column
        TableColumn<BnbData, Integer> timeColumn = new TableColumn<>("Tiempo Servicio");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));

        // Add columns to the TableView

        tableView.getColumns().add(statisticsDataColumn);
        tableView.getColumns().add(cumulativeColumn);
        tableView.getColumns().add(rangeColumn);
        tableView.getColumns().add(timeColumn);
        // Generate data and set it to the TableView
        tableView.setItems(this.data);

        return tableView;
    }

    private void calculateCumulativeResult() {
        data.clear();
        data = utilsBnb.generateDataForCumulativeTable(true);

        tableView.setItems(data);
        tableView.refresh();
    }

    public Integer getDataInRange (double random) {
        return utilsBnb.getDataInRange(random);
    }
    public TableView<BnbData> getTableView() {
        return tableView;
    }

    public void calculateCumulativeDistribution() {
        data.clear();
        data = utilsBnb.generateDataForCumulativeTable(true);

        tableView.setItems(data);
        tableView.refresh();
    }

    public ObservableList<BnbData> getData() {
        return data;
    }

    public void setTableView(TableView<BnbData> tableView) {
        this.tableView = tableView;
    }

    public void setData(ObservableList<BnbData> data) {
        this.data = data;
    }

}
