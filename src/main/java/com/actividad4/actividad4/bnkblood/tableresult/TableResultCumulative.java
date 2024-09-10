package com.actividad4.actividad4.bnkblood.tableresult;

import com.actividad4.actividad4.bnkblood.Utils;
import com.actividad4.actividad4.bnkblood.delivery.DeliveryCumulative;
import com.actividad4.actividad4.bnkblood.demand.DemandCumulative;
import com.actividad4.actividad4.bnkblood.patients.PatientsCumulative;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class TableResultCumulative {
    private DeliveryCumulative deliveryCumulative;
    private DemandCumulative demandCumulative;
    private PatientsCumulative patientsCumulative;
    protected ObservableList<DataResult> data;
    protected TableView<DataResult> tableViewResultCumulative;

    public TableResultCumulative(
            DeliveryCumulative deliveryCumulative,
            DemandCumulative demandCumulative,
            PatientsCumulative patientsCumulative
    ) {
        this.deliveryCumulative = deliveryCumulative;
        this.demandCumulative = demandCumulative;
        this.patientsCumulative = patientsCumulative;
        Utils.buildStackRandom(Utils.getDefaultRandomNumbers());
    }
    public TableView<DataResult> generateTable() {
        // Create the TableView
        TableView<DataResult> tableView = new TableView<>();
        tableView.setPrefWidth(1000);

        TableColumn<DataResult, Integer> week = new TableColumn<>("# Semana");
        week.setCellValueFactory(new PropertyValueFactory<>("week"));

        TableColumn<DataResult, Integer> inventory = new TableColumn<>("Inventario Inicial");
        inventory.setCellValueFactory(new PropertyValueFactory<>("inventory"));

        TableColumn<DataResult, Double> bloodRandom = new TableColumn<>("#Aleatorio");
        bloodRandom.setCellValueFactory(new PropertyValueFactory<>("bloodRandom"));

        TableColumn<DataResult, Integer> pints = new TableColumn<>("Pintas");
        pints.setCellValueFactory(new PropertyValueFactory<>("pints"));

        TableColumn<DataResult, Integer> totalAvailability = new TableColumn<>("Sangre Disponible");
        totalAvailability.setCellValueFactory(new PropertyValueFactory<>("totalAvailability"));

        TableColumn<DataResult, Double> needPatientRandom = new TableColumn<>("#Aleatorio");
        needPatientRandom.setCellValueFactory(new PropertyValueFactory<>("needPatientRandom"));

        TableColumn<DataResult, Integer> patients = new TableColumn<>("#Pacientes");
        patients.setCellValueFactory(new PropertyValueFactory<>("patients"));

        TableColumn<DataResult, ArrayList<Integer>> patientsToAttend = new TableColumn<>("#Pacientes");
        patientsToAttend.setCellValueFactory(new PropertyValueFactory<>("patientsToAttend"));

        TableColumn<DataResult, ArrayList<Double>> patientDemandRandom = new TableColumn<>("#Aleatorio");
        patientDemandRandom.setCellValueFactory(new PropertyValueFactory<>("patientDemandRandom"));

        TableColumn<DataResult, ArrayList<Integer>> patientDemandBlood = new TableColumn<>("#Pintas");
        patientDemandBlood.setCellValueFactory(new PropertyValueFactory<>("patientDemandBlood"));

        TableColumn<DataResult, ArrayList<Integer>> pintsRest = new TableColumn<>("#Pintas Restantes");
        pintsRest.setCellValueFactory(new PropertyValueFactory<>("pintsRest"));

        // Add columns to the TableView
        tableView.getColumns().add(week);
        tableView.getColumns().add(inventory);
        tableView.getColumns().add(bloodRandom);
        tableView.getColumns().add(pints);
        tableView.getColumns().add(totalAvailability);
        tableView.getColumns().add(needPatientRandom);
        tableView.getColumns().add(patients);
        tableView.getColumns().add(patientsToAttend);
        tableView.getColumns().add(patientDemandRandom);
        tableView.getColumns().add(patientDemandBlood);
        tableView.getColumns().add(pintsRest);
        tableViewResultCumulative = tableView;
        return tableView;
    }

    public ObservableList<DataResult> fillResultsData() {
        ObservableList<DataResult> data = FXCollections.observableArrayList();
        int initialInventory = 0;
        for (int week = 1; week <= 6; week++) {
            Integer inventory = initialInventory;
            Double bloodRandom = Utils.getRandomStack().pop();
            int result = this.deliveryCumulative.getDataInRange(bloodRandom);
            Integer pints = result;
            initialInventory = initialInventory + result;
            Integer totalAvailability = initialInventory;
            Double needPatientRandom = Utils.getRandomStack().pop();
            int patients = this.patientsCumulative.getDataInRange(needPatientRandom);
            ArrayList<Integer> patientsToAttend = new ArrayList<>();
            ArrayList<Double> patientDemandRandom = new ArrayList<>();
            ArrayList<Integer> patientDemandBlood = new ArrayList<>();
            ArrayList<Integer> pintsRest = new ArrayList<>();
            for (int index = 1; index <= patients; index++) {
                patientsToAttend.add(index);
                Double random = Utils.getRandomStack().pop();
                patientDemandRandom.add(random);
                Integer patientDemandedBlood = this.demandCumulative.getDataInRange(random);
                patientDemandBlood.add(patientDemandedBlood);
                initialInventory = initialInventory - patientDemandedBlood;
                pintsRest.add(initialInventory);
            }
            DataResult dataResult = new DataResult(
                    week,
                    inventory,
                    bloodRandom,
                    pints,
                    totalAvailability,
                    needPatientRandom,
                    patients,
                    patientsToAttend,
                    patientDemandRandom,
                    patientDemandBlood,
                    pintsRest);
            data.add(dataResult);
        }
        return data;
    }

    public void calculateCumulativeDistribution() {
        if (this.data != null ) {
            this.data.clear();
        }
        this.data = fillResultsData();
        this.tableViewResultCumulative.setItems(this.data);
        this.tableViewResultCumulative.refresh();
    }

    public void clean() {
        this.tableViewResultCumulative.getItems().clear();
    }

    public ObservableList<DataResult> getData() {
        return data;
    }

    public TableView<DataResult> getTableViewResultCumulative() {
        return tableViewResultCumulative;
    }
}
