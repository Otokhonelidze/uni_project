package oto.project.frontend;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import oto.project.backend.Database;
import oto.project.frontend.Dialog.Choice;

public class SqlEditor implements Structure, Style, Event {

    private Scene scene;
    final Stage stage;
    final TextArea queryArea;
    final TextArea resultArea;
    final Button runBtn;
    final Button clearBtn;
    final Button saveBtn;
    final int width;
    final int height;
    final Database db;

    public SqlEditor(String title, int width, int height, Database db) {
        this.stage = new Stage();
        this.stage.setTitle(title);
        this.queryArea = new TextArea();
        this.resultArea = new TextArea();
        this.resultArea.setEditable(false);
        this.runBtn = new Button("Run");
        this.clearBtn = new Button("Clear");
        this.saveBtn = new Button("Save");
        this.width = width;
        this.height = height;
        this.db = db;

        this.setupStructure();
        this.setupStyles();
        this.setupEvents();
    }

    @Override
    public final void setupStructure() {
        BorderPane root = new BorderPane();
        HBox statusBar = new HBox();
        statusBar.getChildren().addAll(this.runBtn, this.clearBtn, this.saveBtn);
        root.setTop(statusBar);

        VBox mainSection = new VBox(10);
        mainSection.getChildren().addAll(this.queryArea, this.resultArea);
        VBox.setVgrow(this.queryArea, Priority.ALWAYS);
        root.setCenter(mainSection);

        this.scene = new Scene(root, this.width, this.height);
        this.stage.setScene(scene);
    }

    @Override
    public final void setupStyles() {
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        this.runBtn.getStyleClass().add("runBtn");
        this.queryArea.getStyleClass().add("queryArea");
        this.resultArea.getStyleClass().add("resultArea");

        InputStream iconStream = getClass().getResourceAsStream("/icons/database_32px.png");
        Image icon = new Image(iconStream);
        this.stage.getIcons().add(icon);
    }

    @Override
    public final void setupEvents() {
        this.runBtn.setOnAction(event -> {
            String sqlCode = queryArea.getText();
            runSQL(sqlCode);
        });
        this.clearBtn.setOnAction(event -> {
            Dialog dialog = new Dialog("Clear the code? ");
            if (dialog.eventResult() == Choice.OK) {
                this.queryArea.clear();
            }
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

    public void runSQL(String sqlCode) {
        try {
            Statement stmt = this.db.getCon().createStatement();
            boolean isResultSet = stmt.execute(sqlCode);

            if (isResultSet) {
                try (ResultSet rs = stmt.getResultSet()) {
                    int columnCount = rs.getMetaData().getColumnCount();

                    StringBuilder sb = new StringBuilder();

                    for (int i = 1; i <= columnCount; i++) {
                        sb.append(rs.getMetaData().getColumnLabel(i)).append("\t");
                    }
                    sb.append("\n");

                    while (rs.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            sb.append(rs.getString(i)).append("\t");
                        }
                        sb.append("\n");
                    }

                    this.resultArea.setText(sb.toString());
                }
            } else {
                int updateCount = stmt.getUpdateCount();
                this.resultArea.setText(updateCount + " rows affected.");
            }
        } catch (SQLException e) {
            this.resultArea.setText("SQL Error: " + e.getMessage());
        }
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
