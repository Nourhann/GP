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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author norhan
 */
public class DesignNewTabController implements Initializable {

    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> snCol;
    @FXML
    private TableColumn<?, ?> nameCol;
    @FXML
    private TableColumn<?, ?> IDCol;
    @FXML
    private ListView<?> listView;
    @FXML
    private ComboBox<?> colSelect;
    
    @FXML 
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void loadHome() throws IOException{
      // System.out.println("model2.InitializeWindowController.loadHome()");
       AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       //FXMLLoader  loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
       //Vbox = (AnchorPane) loader.load();
       root.getChildren().clear();
       root.getChildren().addAll(pane);
   
     }
    @FXML
    private void Store(ActionEvent event) {
    }
    
}
