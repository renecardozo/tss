package com.actividad4.actividad4.bnkblood;
import com.actividad4.actividad4.bnkblood.tableresult.DataResult;
import com.actividad4.actividad4.common.RandomTable;
import com.actividad4.actividad4.bnkblood.delivery.DeliveryCumulative;
import com.actividad4.actividad4.bnkblood.demand.DemandCumulative;
import com.actividad4.actividad4.bnkblood.patients.PatientsCumulative;
import com.actividad4.actividad4.bnkblood.tableresult.TableResultCumulative;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BnkBloodApplication {

    private DeliveryCumulative deliveryCumulative;
    private RandomTable randomTable;
    private PatientsCumulative patientsCumulative;
    private DemandCumulative demandCumulative;
    private TableResultCumulative tableResultCumulative;
    private String name;
    Label titleLabel= new Label("Conclusion:\n");
    Label asnwerLabel = new Label("");

    public BnkBloodApplication() {
        name = new String("Bank Blood");
        deliveryCumulative = new DeliveryCumulative();
        randomTable = new RandomTable();
        patientsCumulative = new PatientsCumulative();
        demandCumulative = new DemandCumulative();
        tableResultCumulative = new TableResultCumulative(deliveryCumulative, demandCumulative, patientsCumulative);
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
        Button btnUseDefaultRandom = new Button("Calculate Result");

        // Event Handlers for buttons
        btnGenerateRandom.setOnAction(e -> generateRandomNumbers());
        btnCalculateCumulative.setOnAction(e -> calculateCumulativeDistribution());
        btnCleanResult.setOnAction(e -> cleanResults());
        btnUseDefaultRandom.setOnAction(e -> useDefaultRandomNumbers());

        // Layout for buttons
        HBox buttonBox = new HBox(10, btnGenerateRandom, btnCalculateCumulative, btnCleanResult, btnUseDefaultRandom);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));


        // Layout containers
        HBox tablesBox = new HBox(20,
                randomTable.getTableViewRandomNumbersTable(),
                deliveryCumulative.getTableViewAccumulativeDistributionTable(),
                patientsCumulative.getTableViewAccumulativeDistributionTable(),
                demandCumulative.getTableViewAccumulativeDistributionTable());
        HBox result = new HBox(20, tableResultCumulative.generateTable(), titleLabel, asnwerLabel);
        tablesBox.setPadding(new Insets(10));

        VBox content = new VBox(10, title, buttonBox, tablesBox, result);
        content.setPadding(new Insets(20));
        return content;
    }

    // Method to generate random numbers and populate the table
    private void generateRandomNumbers() {
        randomTable.generateRandomNumbers();
    }

    // Method to calculate and display cumulative distribution in a new table
    private void calculateCumulativeDistribution() {
        deliveryCumulative.calculateCumulativeDistribution();
        patientsCumulative.calculateCumulativeDistribution();
        demandCumulative.calculateCumulativeDistribution();
    }

    // Method to clean results from the cumulative distribution table
    private void cleanResults() {
        deliveryCumulative.clean();
        patientsCumulative.clean();
        demandCumulative.clean();
        tableResultCumulative.clean();
    }

    // Method to use default random numbers and populate the table
    private void useDefaultRandomNumbers() {
        tableResultCumulative.calculateCumulativeDistribution();
        ObservableList<DataResult> dataResult = tableResultCumulative.getData();
        DataResult lastItem = dataResult.get(dataResult.size() - 1);
        int pintRestLast  = lastItem.getPintsRest().get(lastItem.getPintsRest().size() - 1 );
        asnwerLabel.setText("Al final de las 6 semanas , hay " + pintRestLast + " pintas restantes");
    }
}
