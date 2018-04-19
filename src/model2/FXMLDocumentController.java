/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import static java.awt.SystemColor.menu;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author norhan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    public AnchorPane root ;
 
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    public void loadInitialization() throws IOException{
       AnchorPane pane = FXMLLoader.load(getClass().getResource("intialization_1.fxml"));
       root.getChildren().clear();
       root.getChildren().addAll(pane);
//      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("intialization_1.fxml"));
//      Parent root1 = (Parent) fxmlLoader.load();
//      Stage stage = new Stage();
//      stage.setScene(new Scene(root1)); 
//      stage.show();
        
      //  root 
    }
      public void loadCharts() throws IOException{
       AnchorPane pane = FXMLLoader.load(getClass().getResource("Charts.fxml"));
       root.getChildren().clear();
       root.getChildren().addAll(pane);
       
//      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("intialization_1.fxml"));
//      Parent root1 = (Parent) fxmlLoader.load();
//      Stage stage = new Stage();
//      stage.setScene(new Scene(root1)); 
//      stage.show();
        
      //  root 
    }
     public void loadDesignNewTab() throws IOException{
       AnchorPane pane = FXMLLoader.load(getClass().getResource("DesignNewTab.fxml"));
       root.getChildren().clear();
       root.getChildren().addAll(pane);
//      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DesignNewTab.fxml"));
//      Parent root1 = (Parent) fxmlLoader.load();
//      Stage stage = new Stage();
//      stage.setScene(new Scene(root1)); 
//      stage.show();
//        
      //  root 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
