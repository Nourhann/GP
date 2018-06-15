/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author norhan
 */
public class ShowDesignedTabsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public TableView<String> tableView;
    @FXML
    public TableColumn<String, String> nameCol;
    DB db = new DB();
    ArrayList<String> Tabs = new ArrayList<String>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       assert nameCol != null ;
       assert tableView != null;
       tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        // set cell value factories
       nameCol.setCellValueFactory(new PropertyValueFactory("Name"));

        try {
            
            tableView.setItems(initializeTable());
            
        } catch (SQLException ex) {
            Logger.getLogger(ShowDesignedTabsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     public ObservableList initializeTable() throws SQLException {
      ObservableList<String> tabs = FXCollections.observableArrayList();
      System.out.println("henea");
      ResultSet result=db.ShowUserTabs(db.connectDB());
      System.out.println(result);
      
      while (result.next()){
          tabs.add(result.getString(1));
          System.out.println("aho "+tabs.get(0));
          
      }
      
      return tabs;
         
}    
    
}
