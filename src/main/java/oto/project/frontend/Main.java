package oto.project.frontend;

import javafx.application.Application;
import javafx.stage.Stage;
import oto.project.backend.Database;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Database db = new Database("OTARI\\SQLEXPRESS", "myDB", "sa", "database890");
        db.startConnection();

        SqlEditor sqlEditor = new SqlEditor("Editor", 1080, 720, db);
        sqlEditor.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}