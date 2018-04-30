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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.IndexedCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author norhan
 */
public class DesignNewTabController implements Initializable {

   @FXML
    private ListView<Integer> listView;

 
     public static DataFormat dataFormat = new DataFormat("mydata");
    @FXML
    public TableColumn<Sensor, String> nameCol;

      @FXML
    public TableColumn<Sensor, String> IDCol;

    @FXML
    public TableColumn<Sensor, String> snCol;

    @FXML
    public TableView<Sensor> tableView;
    public AnchorPane root;

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
    
    public void setRowSelection() {
        tableView.getSelectionModel().clearSelection();
        tableView.getSelectionModel().setCellSelectionEnabled(false);
    }
    private void setSelection(IndexedCell cell) {
        
            if (cell.isSelected()) {
                System.out.println("False");
                tableView.getSelectionModel().clearSelection(cell.getIndex());
            } else {
                System.out.println("true");
                tableView.getSelectionModel().select(cell.getIndex());
                //System.out.println(colSelect.toString());
            }
        

    }
     private void setCellValueFactories() {
        snCol.setCellValueFactory(new PropertyValueFactory("Subsystem"));
        nameCol.setCellValueFactory(new PropertyValueFactory("Name"));
       
        IDCol.setCellValueFactory(new PropertyValueFactory("ID"));
       
    }
    public void setRowFactory() {
        tableView.setRowFactory(new Callback<TableView<Sensor>, TableRow<Sensor>>() {
            @Override
            public TableRow<Sensor> call(TableView<Sensor> p) {
                final TableRow<Sensor> row = new TableRow<Sensor>();
                row.setOnDragEntered(new EventHandler<DragEvent>() {
                    @Override
                    public void handle(DragEvent t) {
                        setSelection(row);
                    }
                });

                row.setOnDragDetected(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        
                            Dragboard db = row.getTableView().startDragAndDrop(TransferMode.COPY);
                            ClipboardContent content = new ClipboardContent();
                            content.put(dataFormat, "XData");
                            db.setContent(content);
                            setSelection(row);
                            t.consume();
                        
                    }
                });
                return row;
            }
        });
    }
    
}
