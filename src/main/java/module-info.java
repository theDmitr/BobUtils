module dmitr.bobutils.bobutils {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.h2database;
    requires ormlite.jdbc;
    requires java.sql;

    opens dmitr.bobutils to javafx.fxml;
    opens dmitr.bobutils.controller to javafx.fxml;
    opens dmitr.bobutils.model to ormlite.jdbc;

    exports dmitr.bobutils;
    exports dmitr.bobutils.controller;
    exports dmitr.bobutils.scene;

    opens dmitr.bobutils.scene to javafx.fxml;
}