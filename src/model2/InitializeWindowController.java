/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author norhan
 */
public class InitializeWindowController implements Initializable {

    /**
     * Initializes the controller class.
     */
     ToggleGroup groupType = new ToggleGroup();
     ToggleGroup groupEquation = new ToggleGroup();
     ToggleGroup groupFormat = new ToggleGroup();
     String SubSystem;
     String APiD;
    @FXML
    public RadioButton positiveFormat;

    @FXML
    public RadioButton NegativeFormat;
    @FXML
    public TextField APID;
    @FXML
    public TextField Data;
   // private Map<String , String > StatusData= new HashMap<String , String > ();
    //private Map<String , String > EQUStatusData= new HashMap<String , String > ();
      private ArrayList<String> EquationStatusValue = new ArrayList <String> ();
      private ArrayList<String> EquationStatusNum = new ArrayList <String> ();
      private ArrayList<String> StatusNum = new ArrayList <String> ();
      private ArrayList<String> StatusValue = new ArrayList <String> ();
   
    @FXML
    public TableView<SensorInfo> TableView;
   @FXML
    public ComboBox<String> Subsystem;
    @FXML
    public TextField SensorByte;

    @FXML
    public TextField SensorBits;

    @FXML
    public TextField SensorOrder;

    @FXML
    public CheckBox Range;

    @FXML
    public TextField SensorName;

    @FXML
    public TextField SensorUnit;
    @FXML
    private TableColumn<?, ?> FormatCol;

    @FXML
    private TableColumn<?, ?> BitCol;

    @FXML
    private TableColumn<?, ?> ByteCol;

    @FXML
    private TableColumn<?, ?> OrderCol;

    @FXML
    public TextArea Description;

    @FXML
    public TextField ONField;

    @FXML
    public TextField OFFfiled;

    @FXML
    public TextField StatusField;

    @FXML
    public TextField EquationField;

    @FXML
    public RadioButton OnOff;

    @FXML
    public RadioButton Equation;

    @FXML
    public RadioButton Status;

    @FXML
    public TextField RangeMin;

    @FXML
    public TextField RangeMax;

 
    

    
    @FXML
    public RadioButton EQuNormal;

    @FXML
    public RadioButton EQuStatus;

    @FXML
    public TextField EQuStatusField1;

    @FXML
    public TextField EQuStatusField2;
   
    @FXML
    public TextField StatusField1;
    @FXML
    public Button ADD1;
   
    @FXML
    public Button ADD2;
   

   
    @FXML
    private TableColumn<SensorInfo, String> SubsystemCol  = new TableColumn<SensorInfo, String>("Subsystem");;

    @FXML
    private TableColumn<SensorInfo, String> SensorCol;

    @FXML
    private TableColumn<SensorInfo, String> UnitCol;

    @FXML
    private TableColumn<SensorInfo, String> MinCol;

    @FXML
    private TableColumn<SensorInfo, String> MaxCol;

    @FXML
    private TableColumn<SensorInfo, String> ONCol;

    @FXML
    private TableColumn<SensorInfo, String> OFFCol;

    @FXML
    private TableColumn<SensorInfo, String> StatusNumCol;

    @FXML
    private TableColumn<SensorInfo, String> StatusValueCol;

    @FXML
    private TableColumn<SensorInfo, String> EquationCol;

    @FXML
    private TableColumn<SensorInfo, String> RangeNum;

    @FXML
    private TableColumn<SensorInfo, String> RangeValue;

    @FXML
    private TableColumn<SensorInfo, String> DescriptionCol;
    @FXML
    public TextField RangeFiled;
    public AnchorPane Vbox ;
    public ObservableList<String> SubsystemsComboBox=FXCollections.observableArrayList("Power","ADCS");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        OnOff.setToggleGroup(groupType);
        //OnOff.setSelected(true);
        Status.setToggleGroup(groupType);
        Equation.setToggleGroup(groupType);
         EQuNormal.setToggleGroup(groupEquation);
         EQuStatus.setToggleGroup(groupEquation);
         
         positiveFormat.setToggleGroup(groupFormat);
         NegativeFormat.setToggleGroup(groupFormat);
         
         SubsystemCol.setCellValueFactory(new PropertyValueFactory("SubsystemName"));
         SensorCol.setCellValueFactory(new PropertyValueFactory("Name"));
         UnitCol.setCellValueFactory(new PropertyValueFactory("Unit"));
         MinCol.setCellValueFactory(new PropertyValueFactory("MinRange"));
         MaxCol.setCellValueFactory(new PropertyValueFactory("MaxRange"));
         ONCol.setCellValueFactory(new PropertyValueFactory("ValueON"));
         OFFCol.setCellValueFactory(new PropertyValueFactory("ValueOFF"));
         StatusNumCol.setCellValueFactory(new PropertyValueFactory("StatusNum2"));
         StatusValueCol.setCellValueFactory(new PropertyValueFactory("StatusValue2"));
         EquationCol.setCellValueFactory(new PropertyValueFactory("NormalEquation"));
         RangeNum.setCellValueFactory(new PropertyValueFactory("EquationStatusNum2"));
         RangeValue.setCellValueFactory(new PropertyValueFactory("EquationStatusValue2"));
         DescriptionCol.setCellValueFactory(new PropertyValueFactory("description"));
         FormatCol.setCellValueFactory(new PropertyValueFactory("Format"));
         BitCol.setCellValueFactory(new PropertyValueFactory("Bit"));
         OrderCol.setCellValueFactory(new PropertyValueFactory("Order"));
         ByteCol.setCellValueFactory(new PropertyValueFactory("Byte"));


         
         TableView.setItems(getData());
         
         
         Subsystem.setItems(SubsystemsComboBox);
         
         TableView.setRowFactory(new Callback<TableView<SensorInfo>, TableRow<SensorInfo>>() {  
        @Override  
        public TableRow<SensorInfo> call(TableView<SensorInfo> tableView) {  
            final TableRow<SensorInfo> row = new TableRow<>();  
            final ContextMenu contextMenu = new ContextMenu();  
            final MenuItem removeMenuItem = new MenuItem("Remove");  
            removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {  
                @Override  
                public void handle(ActionEvent event) {  
                    TableView.getItems().remove(row.getItem());  
                }  
            });  
            contextMenu.getItems().add(removeMenuItem);  
           // Set context menu on row, but use a binding to make it only show for non-empty rows:  
            row.contextMenuProperty().bind(  
                    Bindings.when(row.emptyProperty())  
                    .then((ContextMenu)null)  
                    .otherwise(contextMenu)  
            );  
            return row ;  
        }  
    });  
        
        
    } 
     public void loadHome() throws IOException{
      // System.out.println("model2.InitializeWindowController.loadHome()");
       AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       //FXMLLoader  loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
       //Vbox = (AnchorPane) loader.load();
       Vbox.getChildren().clear();
       Vbox.getChildren().addAll(pane);
   
     }
    public void enableONOFF(){
       // ON.setDisable(false);
        //OFF.setDisable(false);
        ONField.setDisable(false);
        OFFfiled.setDisable(false);
        if(!StatusField.isDisabled()){
        //Statuss.setDisable(true);
        StatusField.setDisable(true);
        StatusField1.setDisable(true);
        ADD1.setDisable(true);
        }
         if(!EQuNormal.isDisabled()){
            EQuStatus.setDisable(true);
            EQuNormal.setDisable(true);
             EquationField.setDisable(true);
            EQuStatusField1.setDisable(true);
            EQuStatusField2.setDisable(true);
            ADD2.setDisable(true);
        }

        
    }
    public void SaveSubsystemButtonAction(){
        String subsystem = Subsystem.getSelectionModel().getSelectedItem().toString();
        SubSystem=subsystem;
        String apid = APID.getText();
        APiD=apid;
    }
    public void enableStatus(){
       // Statuss.setDisable(false);
        StatusField.setDisable(false);
        StatusField1.setDisable(false);
        ADD1.setDisable(false);
        if(!ONField.isDisabled()){
             //ON.setDisable(true);
            //OFF.setDisable(true);
            ONField.setDisable(true);
            OFFfiled.setDisable(true);
        }
         if(!EQuNormal.isDisabled()){
            EQuNormal.setDisable(true);
            EQuStatus.setDisable(true);
            EquationField.setDisable(true);
            EQuStatusField1.setDisable(true);
            EQuStatusField2.setDisable(true);
            ADD2.setDisable(true);
        }

        
    }
     public void enableEquation(){
       EQuNormal.setDisable(false);
       EQuStatus.setDisable(false);
        if(!ONField.isDisabled()){
            // ON.setDisable(true);
            //OFF.setDisable(true);
            ONField.setDisable(true);
            OFFfiled.setDisable(true);
        }
         if(!StatusField.isDisabled()){
           // Statuss.setDisable(true);
        StatusField.setDisable(true);
        StatusField1.setDisable(true);
        ADD1.setDisable(true);
        }

        
    }
      public void enableEquationNormal(){
       EquationField.setDisable(false);
       
        if(!EQuStatus.isDisabled()){
             EQuStatusField1.setDisable(true);
            EQuStatusField2.setDisable(true);
            ADD2.setDisable(true);
           
        }
       

        
    }
       public void enableEquationStatus(){
            EQuStatusField1.setDisable(false);
            EQuStatusField2.setDisable(false);
            ADD2.setDisable(false);
       
        if(!EQuStatus.isDisabled()){
             
            EquationField.setDisable(true);
           
        }
       

        
    }
     public void enableRange(){
         if (RangeMax.isDisabled()){
           
            RangeMax.setDisable(false);
            RangeMin.setDisable(false); 
           // MINRange.setDisable(false); 
            //MAXRange.setDisable(false); 
         }
         else {
           
            RangeMax.setDisable(true);
            RangeMin.setDisable(true); 
          //  MINRange.setDisable(true); 
            //MAXRange.setDisable(true); 
         }
       

     }
     
     
     public void PutIntoTable(){
         SensorInfo Sensor = new SensorInfo();
         Sensor.setSubsystemName(SubSystem);
         Sensor.setAPID(APID.getText());
         Sensor.setName(SensorName.getText());
         if(positiveFormat.isSelected()){
            Sensor.setFormat("+"); 
         }
         else if(NegativeFormat.isSelected()){
              Sensor.setFormat("-"); 
         }
         Sensor.setUnit(SensorUnit.getText());
         Sensor.setDescription(Description.getText());
         Sensor.setBit(SensorBits.getText());
         Sensor.setByte(SensorByte.getText());
         Sensor.setOrder(SensorOrder.getText());
         
         if (Range.isSelected()){
             Sensor.setMaxRange( RangeMax.getText());
             Sensor.setMinRange(RangeMin.getText());
             
         }
         else {
             Sensor.setMaxRange( "");
             Sensor.setMinRange("");
         }
           if (OnOff.isSelected()){
             Sensor.setValueON( ONField.getText());
             Sensor.setValueOFF( OFFfiled.getText());
         }
           else {
                Sensor.setValueON( "");
             Sensor.setValueOFF( "");
           }
           if(Status.isSelected()){
               Sensor.setStatusValue(StatusValue);
               Sensor.setStatusNum(StatusNum);
           }
           else {
             Sensor.setStatusValue(null);
             Sensor.setStatusNum(null);
           }
           if(Equation.isSelected()){
               if(EQuNormal.isSelected()){
                   Sensor.setNormalEquation(EquationField.getText());
               }
               else if(EQuStatus.isSelected()){
                   Sensor.setEquationStatusNum(EquationStatusNum);
                   Sensor.setEquationStatusValue(EquationStatusValue);
               }
           }
          //  ObservableList<SensorInfo> sensors = FXCollections.observableArrayList();
            //sensors.add(Sensor);
       // TableView.setItems(sensors);
        TableView.getItems().add(Sensor);
        SensorName.clear();
        SensorUnit.clear();
        positiveFormat.setSelected(false);
        NegativeFormat.setSelected(false);
        Range.setSelected(false);
        RangeMax.clear();
        RangeMin.clear();
        Description.clear();
        SensorByte.clear();
        SensorBits.clear();
        SensorOrder.clear();
        OnOff.setSelected(false);
        Status.setSelected(false);
        Equation.setSelected(false);
        EQuNormal.setSelected(false);
        EQuStatus.setSelected(false);
        ONField.clear();
        OFFfiled.clear();
        StatusField.clear();
        StatusField1.clear();
        EquationField.clear();
        EQuStatusField1.clear();
        EQuStatusField2.clear();

        
         
           
     }
     public ObservableList<SensorInfo> getData() {
      
        ObservableList<SensorInfo> sensors = FXCollections.observableArrayList();
       // SensorInfo Sensor = new SensorInfo("hi", "hi", "hi", "hi", "hi", "hi", "hi", "hi", "hi", "hi", "hi", "hi", "hi", "hi");
        //sensors.add(Sensor);
        return sensors;
    }
   
     
     public void ADDstatus(){
        String num = StatusField.getText();
        String value = StatusField1.getText();
        StatusValue.add(value);
        StatusNum.add(num);
        
         StatusField.clear();
         StatusField1.clear();
     }
       public void ADDEQUstatus(){
        String num = EQuStatusField1.getText();
        String value = EQuStatusField2.getText();
        EquationStatusValue.add(value);
        EquationStatusNum.add(num);
         EQuStatusField1.clear();
         EQuStatusField2.clear();
       }
       
       public void CreateTable(){
           
       }
    
}
