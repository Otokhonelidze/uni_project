package oto.project.frontend;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class Dialog implements Structure, Style {

    private Scene scene;
    final Stage stage;
    final HBox hbox;
    final VBox vbox;
    final Button okBtn;
    final Button cancelBtn;
    final Label textLabel;
    private Choice userChoice = Choice.CANCEL;

    public enum Choice {
        OK,
        CANCEL,
    }

    public Dialog(String text) {
        this.stage = new Stage();
        this.hbox = new HBox(50);
        this.vbox = new VBox();
        this.okBtn = new Button("Ok");
        this.cancelBtn = new Button("Cancel");
        this.textLabel = new Label(text);

        this.setupStructure();
        this.setupStyles();
    }

    @Override
    public void setupStructure() {
        VBox rootLayout = new VBox(50);
        rootLayout.setPrefWidth(400);

        this.textLabel.setWrapText(true); 
        this.vbox.getChildren().addAll(this.textLabel);
        this.vbox.setAlignment(Pos.CENTER);
        this.hbox.getChildren().addAll(this.okBtn, this.cancelBtn);
        this.hbox.setAlignment(Pos.CENTER);

        rootLayout.getChildren().addAll(this.vbox, this.hbox);
        this.scene = new Scene(rootLayout);
        this.stage.setScene(scene);
    }

    @Override
    public void setupStyles() {
        this.scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        this.okBtn.getStyleClass().add("button");
    }

    public void setupStyle() {
    }

    public Choice eventResult() {
        this.okBtn.setOnAction(event -> {
            this.userChoice = Choice.OK;
            this.stage.close();
        });

        this.cancelBtn.setOnAction(event -> {
            this.userChoice = Choice.CANCEL;
            this.stage.close();
        });

        this.stage.showAndWait();
        return this.getUserChoice();
    }

    public Choice getUserChoice() {
        return this.userChoice;
    }

    public void show() {
        this.stage.show();
    }
}
