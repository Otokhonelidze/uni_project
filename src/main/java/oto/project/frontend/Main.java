package oto.project;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import oto.project.backend.Database;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello");

        Label label = new Label("hello world");
        StackPane root = new StackPane();
        root.getChildren().add(label);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();

        // String dbUrl = "jdbc:sqlserver://OTARI\\SQLEXPRESS;databaseName=myDB;trustServerCertificate=true;";
        // Database db = new Database(dbUrl, "sa", "");
        // db.startDatabase();
    }

    public static void main(String[] args) {
        launch(args);
    }
}