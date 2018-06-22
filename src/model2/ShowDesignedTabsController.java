/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private AnchorPane root;
    @FXML
    public TableView<TabInfo> tableView;
    @FXML
    public TableColumn<TabInfo, String> nameCol;
    DB db = new DB();
    ArrayList<TabInfo> Tabs = new ArrayList<TabInfo>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       assert nameCol != null ;
       assert tableView != null;
       tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        // set cell value factories
       nameCol.setCellValueFactory(new PropertyValueFactory("Name"));

//        try {
//            
//            tableView.setItems(initializeTable());
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ShowDesignedTabsController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }  
    public ObservableList<TabInfo> initializeTable() throws SQLException {
      ObservableList<TabInfo> tabs = FXCollections.observableArrayList();
      System.out.println("henea");
      ResultSet result=db.ShowUserTabs(db.connectDB());
      System.out.println(result.getString(0));
      //int count =0;
      while (result.next()){
          TabInfo t = new TabInfo();
          t.setName(result.getString(1));
          t.setSensors(result.getString(2));
          tabs.add(t);
          System.out.println("aho "+tabs.get(0));
         // count++;
          
      }
      
      return tabs;
         
}    
    public void loadHome() throws IOException{
       try{
           System.out.println("eh ya wasa5");
           AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
           Scene scene = new Scene(root);
           Stage stage = new Stage();
           stage.setScene(scene);
           stage.show();
           //root.getChildren().clear();
           //root.getChildren().addAll(pane);
       }
       catch(Exception e){
           System.out.println("model2.ShowDesignedTabsController.loadHome()");
       }
     }
    
    
}
