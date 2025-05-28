package oto.project.frontend;

import javafx.application.Application;
import javafx.stage.Stage;
import oto.project.backend.Database;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        String dbUrl = "jdbc:sqlserver://OTARI\\SQLEXPRESS;databaseName=myDB;trustServerCertificate=true;";
        Database db = new Database(dbUrl, "sa", "");
        db.startConnection();

        SqlEditor sqlEditor = new SqlEditor("Editor", 1080, 720, db);
        sqlEditor.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}