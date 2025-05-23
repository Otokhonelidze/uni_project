package oto.project.frontend;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Window win = new Window("Hello", 800, 600);

        Stage stage = win.getStage();
        win.addComponent(new Label("Enter your code"));

        TextField queryField = win.addComponentReturn(new TextField());
        queryField.setTranslateY(-50);

        Button runButton = win.addComponentReturn(new Button("Run"));
        runButton.setTranslateY(0);

        runButton.setOnAction(e -> {
            String query = queryField.getText();
            win.addComponentReturn(new Label(query)).setTranslateY(50);
        });

        stage.show();
        
        // String dbUrl = "jdbc:sqlserver://OTARI\\SQLEXPRESS;databaseName=myDB;trustServerCertificate=true;";
        // Database db = new Database(dbUrl, "sa", "");
        // db.startDatabase();
    }

    public static void main(String[] args) {
        launch(args);
    }
}