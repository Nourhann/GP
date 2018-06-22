/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author norhan
 */

public class Model2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
       // stage.setResizable(false);
        //stage.initStyle(StageStyle.values());
        stage.initStyle(StageStyle.UNDECORATED);

        BorderPane borderPane = new BorderPane();
        //borderPane.setStyle("-fx-background-color: green;");

        ToolBar toolBar = new ToolBar();

        int height = 25;
        toolBar.setPrefHeight(height);
        toolBar.setMinHeight(height);
        toolBar.setMaxHeight(height);
        toolBar.setStyle("-fx-background-color: black;");
        WindowButtons button = new WindowButtons();
        button.setAlignment(Pos.CENTER_RIGHT);
        button.setStyle("-fx-background-color: red;");
        
        //button.setAlignment(Pos.valueOf());
        toolBar.getItems().add(button);

        borderPane.setTop(toolBar);
        borderPane.setBottom(root);

        stage.setScene(new Scene(borderPane));
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    class WindowButtons extends HBox {

        public WindowButtons() {
            Button closeBtn = new Button("X");

            closeBtn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });

            this.getChildren().add(closeBtn);
        }
    }
}
