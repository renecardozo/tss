module com.actividad4.actividad4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires jdk.jshell;

    opens com.actividad4.actividad4 to javafx.fxml;
    exports com.actividad4.actividad4;
    exports com.actividad4.actividad4.bnkblood;
    exports com.actividad4.actividad4.common;
    exports com.actividad4.actividad4.bnkblood.tableresult;
    exports com.actividad4.actividad4.ballsurn;
    exports com.actividad4.actividad4.bnkbnb;
    exports com.actividad4.actividad4.university;
    exports com.actividad4.actividad4.ventilationfan;
}