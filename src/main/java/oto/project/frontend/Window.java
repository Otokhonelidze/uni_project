package oto.project.frontend;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Window {

    final Stage stage;
    final Pane rootLayout;

    public Window(String title, int width, int height) {
        this.stage = new Stage();
        this.stage.setTitle(title);
        this.rootLayout = new StackPane();
        Scene scene = new Scene(rootLayout, width, height, Color.BLACK);
        this.stage.setScene(scene);
    }

    public Window(String title) {
        this.stage = new Stage();
        this.stage.setTitle(title);
        this.rootLayout = new StackPane();
        Scene scene = new Scene(rootLayout, Color.BLACK);
        this.stage.setScene(scene);
        this.stage.setFullScreen(true);
    }

    public Window(String title, int width, int height, PaneType paneType) {
        this.stage = new Stage();
        this.stage.setTitle(title);

        switch (paneType) {
            case VBOX:
                this.rootLayout = new VBox();
                break;
            case HBOX:
                this.rootLayout = new HBox();
                break;
            case BORDER_PANE:
                this.rootLayout = new BorderPane();
                break;
            case GRID_PANE:
                this.rootLayout = new GridPane();
                break;
            case ANCHOR_PANE:
                this.rootLayout = new AnchorPane();
                break;
            case STACK_PANE:
                this.rootLayout = new StackPane();
                break;
            case PANE:
            default:
                this.rootLayout = new Pane();
                break;
        }
        Scene scene = new Scene(rootLayout, width, height, Color.BLACK);
        this.stage.setScene(scene);
    }

    public void addComponent(Node node) {
        this.rootLayout.getChildren().add(node);
    }

    public <T extends Node> T addComponentReturn(T node) {
        this.rootLayout.getChildren().add(node);
        return node;
    }

    public Stage getStage() {
        return stage;
    }

    public void show() {
        this.stage.show();
    }

    public Pane getRootLayout() {
        return rootLayout;
    }
}
