package oto.project.frontend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SqlEditor {

    private Stage stage;
    private TextArea queryArea;
    private TextArea resultArea;
    private Button runBtn;
    private Button clearBtn;
    private Button saveBtn;

    public SqlEditor(String title, int width, int height) {
        this.stage = new Stage();
        this.queryArea = new TextArea();
        this.resultArea = new TextArea();
        this.runBtn = new Button("Run");
        this.clearBtn = new Button("Clear");
        this.saveBtn = new Button("Save");

        this.setupStructure(width, height);
        this.setupStyles();
        this.setupEvents();
    }

    private void setupStructure(int width, int heigth) {
        BorderPane root = new BorderPane();
        HBox statusBar = new HBox();
        statusBar.getChildren().addAll(this.runBtn, this.clearBtn, this.saveBtn);
        root.setTop(statusBar); 

        VBox mainSection = new VBox();
        mainSection.getChildren().addAll(this.queryArea, this.resultArea);
        root.setCenter(mainSection);

        Scene scene = new Scene(root, width, heigth, c);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        this.stage.setScene(scene);
    }

    private void setupStyles() {
        this.runBtn.getStyleClass().add("button");
        this.queryArea.getStyleClass().add("text-area");
    }

    private void setupEvents() {
        this.runBtn.setOnAction(event -> {
            
        });
        this.clearBtn.setOnAction(event -> {
            this.queryArea.clear();
        });
        this.saveBtn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter sqlFilter = new FileChooser.ExtensionFilter("SQL files (*.sql)", "*.sql");
            fileChooser.getExtensionFilters().addAll(sqlFilter);
            File file = fileChooser.showSaveDialog(this.stage);
            String quaryText = this.queryArea.getText();

            if (file != null) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(quaryText);
                } catch (IOException ex) {
                    System.err.println("Error saving SQL file: " + ex.getMessage());
                }
            }
        });
    }

    public String getQuery() {
        return this.queryArea.getText();
    }

    public void setQuery(String query) {
        this.queryArea.setText(query);
    }

    public void getResults(String results) {
        this.resultArea.getText();
    }

    public void setResults(String results) {
        this.resultArea.setText(results);
    }

    public void show() {
        this.stage.show();
    }

    public Stage getStage() {
        return this.stage;
    }
}
