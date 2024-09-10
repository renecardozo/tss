package com.actividad4.actividad4;

import com.actividad4.actividad4.ballsurn.BallsCumulative;
import com.actividad4.actividad4.bnkblood.BnkBloodApplication;
import com.actividad4.actividad4.bnkbnb.BnbTableCumulative;
import com.actividad4.actividad4.university.TableProgramsUniv;
import com.actividad4.actividad4.ventilationfan.TableVentilation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApplication extends Application {
    private final BnkBloodApplication bnkBloodApplication = new BnkBloodApplication();
    private final BallsCumulative ballsCumulative = new BallsCumulative();
    private final BnbTableCumulative bnbTableCumulative = new BnbTableCumulative();
    private final TableProgramsUniv tableProgramsUniv = new TableProgramsUniv();
    private final TableVentilation tableVentilation = new TableVentilation();
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Tabbed window with statistics Tables");
        TabPane tabPane = new TabPane();
        Tab tab = new Tab(bnkBloodApplication.getName());
        tab.setContent(bnkBloodApplication.createTabContent());

        Tab tabColor = new Tab(ballsCumulative.getName());
        tabColor.setContent(ballsCumulative.createTabContent());

        Tab tabBnb = new Tab(bnbTableCumulative.getName());
        tabBnb.setContent(bnbTableCumulative.createTabContent());

        Tab tabUniv = new Tab(tableProgramsUniv.getName());
        tabUniv.setContent(tableProgramsUniv.createTabContent());

        Tab tabVentilation = new Tab(tableVentilation.getName());
        tabVentilation.setContent(tableVentilation.createTabContent());

        tabPane.getTabs().add(tab);
        tabPane.getTabs().add(tabColor);
        tabPane.getTabs().add(tabBnb);
        tabPane.getTabs().add(tabUniv);
        tabPane.getTabs().add(tabVentilation);

        VBox vBox = new VBox(tabPane);
        Scene scene = new Scene(vBox, 800, 400);
        stage.setScene(scene);
        stage.show();
    }

}
