/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    @FXML
    public RadioButton positiveFormat;

    @FXML
    public RadioButton NegativeFormat;
    @FXML
    public TextField APID;
    @FXML
    public TextField Data;
    private Map<String , String > StatusData= new HashMap<String , String > ();
    private Map<String , String > EQUStatusData= new HashMap<String , String > ();
   
    @FXML
    public TableView<?> TableView;
   @FXML
    public ComboBox<?> Subsystem;
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
    public Label ON;

    @FXML
    public Label OFF;

    public Label Statuss;

    
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
    public Label Rangee;
    @FXML
    public Button ADD2;
     @FXML
    public Label MAXRange;

    @FXML
    public Label MINRange;
    @FXML
    private TableColumn<?, ?> SubsystemCol;

    @FXML
    private TableColumn<?, ?> SensorCol;

    @FXML
    private TableColumn<?, ?> UnitCol;

    @FXML
    private TableColumn<?, ?> MinCol;

    @FXML
    private TableColumn<?, ?> MaxCol;

    @FXML
    private TableColumn<?, ?> ONCol;

    @FXML
    private TableColumn<?, ?> OFFCol;

    @FXML
    private TableColumn<?, ?> StatusNumCol;

    @FXML
    private TableColumn<?, ?> StatusValueCol;

    @FXML
    private TableColumn<?, ?> EquationCol;

    @FXML
    private TableColumn<?, ?> RangeNum;

    @FXML
    private TableColumn<?, ?> RangeValue;

    @FXML
    public TextField RangeFiled;
    public AnchorPane Vbox ;
   
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
        ON.setDisable(false);
        OFF.setDisable(false);
        ONField.setDisable(false);
        OFFfiled.setDisable(false);
        if(!Statuss.isDisabled()){
        Statuss.setDisable(true);
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
    public void enableStatus(){
        Statuss.setDisable(false);
        StatusField.setDisable(false);
        StatusField1.setDisable(false);
        ADD1.setDisable(false);
        if(!ON.isDisabled()){
             ON.setDisable(true);
            OFF.setDisable(true);
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
        if(!ON.isDisabled()){
             ON.setDisable(true);
            OFF.setDisable(true);
            ONField.setDisable(true);
            OFFfiled.setDisable(true);
        }
         if(!Statuss.isDisabled()){
            Statuss.setDisable(true);
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
         if (Rangee.isDisabled()){
            Rangee.setDisable(false);
            RangeMax.setDisable(false);
            RangeMin.setDisable(false); 
            MINRange.setDisable(false); 
            MAXRange.setDisable(false); 
         }
         else {
             Rangee.setDisable(true);
            RangeMax.setDisable(true);
            RangeMin.setDisable(true); 
            MINRange.setDisable(true); 
            MAXRange.setDisable(true); 
         }
       

     }
     
     
     public void PutIntoTable(){
        
         String Sensor_Name = SensorName.getText();
         String Sensor_Unit = SensorUnit.getText();
         String Sensor_description = Description.getText();
         String Sensor_Byte = SensorByte.getText();
         String Sensor_Bit = SensorBits.getText();
         String Sensor_Order = SensorOrder.getText();
         if (!Rangee.isDisabled()){
             String MaxRange = RangeMax.getText();
             String MinRange = RangeMin.getText();
         }
           if (!OnOff.isDisabled()){
             String ONfield = ONField.getText();
             String OFfiled = OFFfiled.getText();
         }
           
         //SubsystemCol.s  
           
     }
     public void SaveForSubsystem(){
         String APId = APID.getText();
         String data =  Data.getText();
         String subsystemName = Subsystem.getSelectionModel().getSelectedItem().toString(); 
     }
     
     public void ADDstatus(){
        String num = StatusField.getText();
        String value = StatusField1.getText();
        StatusData.put(num, value);
         System.out.println(StatusData.size());
         StatusField.clear();
         StatusField1.clear();
     }
       public void ADDEQUstatus(){
        String num = EQuStatusField1.getText();
        String value = EQuStatusField2.getText();
        EQUStatusData.put(num, value);
         System.out.println(EQUStatusData.size());
         EQuStatusField1.clear();
         EQuStatusField2.clear();
       }
    
}
