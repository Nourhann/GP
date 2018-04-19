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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
     ToggleGroup group = new ToggleGroup();
  

    @FXML
    private Label ON;

    @FXML
    private Label OFF;

    @FXML
    private TextField ONField;

    @FXML
    private TextField OFFfiled;

    @FXML
    public RadioButton OnOff;

    @FXML
    public RadioButton Status;

    @FXML
    public RadioButton Equation;
     @FXML
    private Label Statuss;

    @FXML
    private Label Equationn;


    @FXML
    private TextField StatusField;

    @FXML
    private TextField EquationField;

    
    @FXML
    private Label Rangee;

    @FXML
    private CheckBox Range;
    @FXML
    private TextField RangeFiled;
    public AnchorPane Vbox ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        OnOff.setToggleGroup(group);
        //OnOff.setSelected(true);
        Status.setToggleGroup(group);
        Equation.setToggleGroup(group);
        
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
        }
         if(!Equationn.isDisabled()){
            Equationn.setDisable(true);
            EquationField.setDisable(true);
        }

        
    }
    public void enableStatus(){
        Statuss.setDisable(false);
        StatusField.setDisable(false);
        if(!ON.isDisabled()){
             ON.setDisable(true);
            OFF.setDisable(true);
            ONField.setDisable(true);
            OFFfiled.setDisable(true);
        }
         if(!Equationn.isDisabled()){
            Equationn.setDisable(true);
            EquationField.setDisable(true);
        }

        
    }
     public void enableEquation(){
       Equationn.setDisable(false);
       EquationField.setDisable(false);
        if(!ON.isDisabled()){
             ON.setDisable(true);
            OFF.setDisable(true);
            ONField.setDisable(true);
            OFFfiled.setDisable(true);
        }
         if(!Statuss.isDisabled()){
            Statuss.setDisable(true);
            StatusField.setDisable(true);
        }

        
    }
     public void enableRange(){
       Rangee.setDisable(false);
       RangeFiled.setDisable(false);
     }
    
}
