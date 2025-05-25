package oto.project.frontend;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        SqlEditor sqlEditor = new SqlEditor("Editor", 1080, 720);
        sqlEditor.show();
        
        // String dbUrl = "jdbc:sqlserver://OTARI\\SQLEXPRESS;databaseName=myDB;trustServerCertificate=true;";
        // Database db = new Database(dbUrl, "sa", "");
        // db.startDatabase();
        // db.executeSql("insert");
    }

    public static void main(String[] args) {
        launch(args);
    }
}