/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import com.jfoenix.controls.JFXToggleButton;
import static java.awt.SystemColor.menu;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 *
 * @author norhan
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    public AnchorPane root ;
    @FXML
    public JFXToggleButton MOOD;
 
    
    
    @FXML
  
    public void loadInitialization() throws IOException{
        try{AnchorPane pane = FXMLLoader.load(getClass().getResource("initialization_1.fxml"));
       root.getChildren().clear();
       root.getChildren().addAll(pane);
//      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("intialization_1.fxml"));
//      Parent root1 = (Parent) fxmlLoader.load();
//      Stage stage = new Stage();
//      stage.setScene(new Scene(root1)); 
//      stage.show();
        
      //  root
       }
       catch(Exception e){
           
       }
    }
    public void loadDesignedTabs() throws IOException{
       try{AnchorPane pane = FXMLLoader.load(getClass().getResource("ShowDesignedTabs.fxml"));
       root.getChildren().clear();
       root.getChildren().addAll(pane);
//      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("intialization_1.fxml"));
//      Parent root1 = (Parent) fxmlLoader.load();
//      Stage stage = new Stage();
//      stage.setScene(new Scene(root1)); 
//      stage.show();
        
      //  root
       }
       catch(Exception e){
           
       }
    }
    public void LoadUpdateFilesDirectory() throws IOException{
        try{ FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UpdateFilesDistination.fxml"));
         Parent root1 = (Parent) fxmlLoader.load(); 
         Stage stage = new Stage();
         stage.setScene(new Scene(root1));
         stage.show();
        }
        catch(Exception e){
            
        }
     }
    public void loadCharts() throws IOException{
       try{AnchorPane pane = FXMLLoader.load(getClass().getResource("Charts.fxml"));
       root.getChildren().clear();
       root.getChildren().addAll(pane);
       
//      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("intialization_1.fxml"));
//      Parent root1 = (Parent) fxmlLoader.load();
//      Stage stage = new Stage();
//      stage.setScene(new Scene(root1)); 
//      stage.show();
        
      //  root
       }
       catch(Exception e){
           
       }
    }
    public void loadDesignNewTab() throws IOException{
       try{AnchorPane pane = FXMLLoader.load(getClass().getResource("DesignNewTab.fxml"));
       root.getChildren().clear();
       root.getChildren().addAll(pane);
//      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DesignNewTab.fxml"));
//      Parent root1 = (Parent) fxmlLoader.load();
//      Stage stage = new Stage();
//      stage.setScene(new Scene(root1)); 
//      stage.show();
//        
      //  root 
       }
       catch(Exception e){
           
       }
    }
    public void loadBeginUnpackingProcess() throws IOException{
        
     try{
         String a = readIntofile("livefile.txt");
          String b=readIntofile("offlinefile.txt");
            if (a.length()<=0 ||b.length()<=0){
               
             
                    SelectNotification();
                
            }
            else {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("BeginUnpackingProcess.fxml"));
       root.getChildren().clear();
       root.getChildren().addAll(pane);
//      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DesignNewTab.fxml"));
//      Parent root1 = (Parent) fxmlLoader.load();
//      Stage stage = new Stage();
//      stage.setScene(new Scene(root1)); 
//      stage.show();
//        
      //  root
     }
     }
     catch(Exception e){
         
     }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            saveIntofile("mood.txt","off");
            
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    public void ChangeMood () throws IOException{
        if(MOOD.isSelected()){
              saveIntofile("mood.txt","on");
        }
        else {
             saveIntofile("mood.txt","off");
        }
//        String mood =readIntofile("mood.txt");
//        if(mood == "on"){
//            saveIntofile("mood.txt","off");
//        }
//        else if(mood == "off"){
//            saveIntofile("mood.txt","on");
//        }
        
        
    }
    public void saveIntofile(String fileName,String path) throws IOException{
    FileWriter fileWriter = new FileWriter(fileName);
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(path);
            bufferedWriter.close();
    }
    public String readIntofile(String fileName) throws IOException{
    FileReader fileReader = 
                new FileReader(fileName);
    String line , result = "";

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
        

            while((line = bufferedReader.readLine()) != null) {
                result+=line;
            }   

            // Always close files.
            bufferedReader.close();
            return result;
    }
    public void SelectNotification(){
        Notifications notification = Notifications.create()
                .title("Select file first")
                .text("File directory must be selected before begin unpacking process")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.CENTER);
        notification.show();
                
                
        
    }
}
