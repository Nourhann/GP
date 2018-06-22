/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author norhan
 */
public class ChartsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public AnchorPane root;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
       public void loadHome() throws IOException{
      // System.out.println("model2.InitializeWindowController.loadHome()");
        try{// System.out.println("model2.InitializeWindowController.loadHome()");
       AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       //FXMLLoader  loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
       //Vbox = (AnchorPane) loader.load();
       root.getChildren().clear();
       root.getChildren().addAll(pane);
       }
       catch(Exception e){
           System.out.println("...........");
       }
   
     } 
     
    
}
