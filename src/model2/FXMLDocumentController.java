/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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
        AnchorPane pane = FXMLLoader.load(getClass().getResource("InitializeWindow.fxml"));
        //System.out.println("model2.FXMLDocumentController.loadInitialization()");
        root.getChildren().addAll(pane);
        
      //  root 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
