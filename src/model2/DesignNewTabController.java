/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.IndexedCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author norhan
 */
public class DesignNewTabController implements Initializable {

   @FXML
    private ListView<String> listView;
    public static DataFormat dataFormat = new DataFormat("mydata");
    @FXML
    public TableColumn<Sensor, String> nameCol;

    @FXML
    public TableColumn<Sensor, String> IDCol;

    @FXML
    public TableColumn<Sensor, String> snCol;

    @FXML
    public TableView<Sensor> tableView;
    @FXML
    public TextField TabName;
    public AnchorPane root;
    DB db = new DB();
    String[] Sensors;
    ObservableList<Integer> selectedIndexes = FXCollections.observableArrayList();
    ObservableList<String> selectedSensors = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert nameCol != null ;
        assert IDCol != null ;
        assert snCol != null ;
        assert tableView != null;

        // changed to multiple selection mode
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // set cell value factories
        setCellValueFactories();

        //set Dummy Data for the TableView
        tableView.setItems(getData());

        //ListView items bound with selection index property of tableview
        listView.setItems(selectedSensors);

        //change listview observable list
        tableView.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Integer> change) {
                selectedIndexes.setAll(change.getList());
                selectedSensors.clear();
                for (int i=0;i<selectedIndexes.size();i++){
                    selectedSensors.add(Sensors[selectedIndexes.get(i)]);
                }
                
            }
        });
      
      

        //set the Row Factory of the table
        setRowFactory();

        //Set row selection as default
        setRowSelection();
    }    
    public void loadHome() throws IOException{
      // System.out.println("model2.InitializeWindowController.loadHome()");
       try{AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       //FXMLLoader  loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
       //Vbox = (AnchorPane) loader.load();
       root.getChildren().clear();
       root.getChildren().addAll(pane);
       }
       catch(Exception e){
           
       }
     }
    @FXML
    private void Store(ActionEvent event) throws SQLException {
      // final ObservableList<Sensor> table = tableView.getSelectionModel().getSelectedItems(); 
       String result="";
       for (int i =0 ;i<selectedSensors.size();i++){
           result+=selectedSensors.get(i)+'|';
           System.out.println(result);
       }
      // System.out.println(TabName.getText());
       db.Insertusertab(result,TabName.getText(),db.getConnection());
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
    public void initializeTable() throws SQLException {
      System.out.println("henea");
    
      String result=db.PACKETSENSORS("Power",db.connectDB());
      System.out.println(result);
      Sensors = result.split(",");
         
    }
    public ObservableList<Sensor> getData() {
        try {
           
            initializeTable();
        } catch (SQLException ex) {
            Logger.getLogger(DesignNewTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Sensor> sensors = FXCollections.observableArrayList();
        for (int i = 0; i < Sensors.length; i++) {
            Sensor p = new Sensor();
            
            p.setName(Sensors[i]);
          
            p.setSubsystem("Power");
            p.setID(i);
            sensors.add(p);
        }
        return sensors;
    }
   
}

