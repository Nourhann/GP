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
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javax.script.ScriptException;

/**
 * FXML Controller class
 *
 * @author norhan
 */
public class BeginUnpackingProcessController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public AnchorPane main;
    private int NumberOFPowersensors ;
    private String PowerSensors[];
    public TilePane loadPane1;
    public TilePane OBCTile;
    public GridPane gride ;
    private Label loadingPower[] ;
    private int counter = 0;
    Subsystem subsystem = new Subsystem();
    ResultSet Powerresult;
    private  Map<String, List<String>> PowerLimits ; 
    unpacking obj ;

              
              
              
              
              
              
     public void loadHome() throws IOException{
      // System.out.println("model2.InitializeWindowController.loadHome()");
       AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       //FXMLLoader  loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
       //Vbox = (AnchorPane) loader.load();
       main.getChildren().clear();
       main.getChildren().addAll(pane);
   
     }
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            initializePowerTables();
            convert(" ");
            ViewPower();
            System.out.println("NNNN");
        } catch (SQLException ex) {
            Logger.getLogger(BeginUnpackingProcessController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ScriptException ex) {
            Logger.getLogger(BeginUnpackingProcessController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BeginUnpackingProcessController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     public void convert(String file) throws SQLException, ScriptException{   
     obj=new unpacking();
     Packet p=new Packet();
     obj.getDb().setConnection(obj.getDb().connectDB());
     obj.getConvert().setConvertTable(obj.getConvert().Initialization(obj.getConvert().getConvertTable()));
     obj.Standard(obj);
     obj.Map(obj);
     obj.MapPackets(obj);
     int count=obj.CreateSession(obj);
     p.SplitData(count, obj,"test.txt");
     obj.PacketInformation(obj);
     p=null;
     //-------------------------------------------------------------------------------------------------
        
    }
    public void ViewPower() throws Exception {
    

            ReadPowerData();
            createLoadPane();
            Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                    System.out.println("this is called every 5 seconds on UI thread");
                    counter=0;
                    loadPowerTab();


            }
                }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
   
      }
    private void loadPowerTab() {
  
    
    final Service<String> SensorBuilder = new Service<String>() {
          @Override protected Task<String> createTask() {
              return new Task<String>() {
                  @Override protected String call() throws InterruptedException, SQLException {
                      
             
                      updateProgress(0, 10);
                     for (int i = 0; i < 10; i++) {
                        Thread.sleep(10);
                      }
                   
                     
                      return PowerSensors[counter]+"  "+Powerresult.getString(1)+"\n";
                      
                  }
              };
          }
    };
    

    SensorBuilder.stateProperty().addListener(new ChangeListener<Worker.State>() {
        @Override public void changed(ObservableValue<? extends Worker.State> observableValue,
                        Worker.State oldState, Worker.State newState) {
            switch (newState) {
            case SCHEDULED:
                break;
            case READY:
            case RUNNING:
                break;
            case SUCCEEDED:
            {
                try {
                    Powerresult.next();
                   // System.out.println(counter +" "+Powerresult.getString(1));
                     System.out.println("henA");
                Label rec1 = new Label();
                String split []= SensorBuilder.valueProperty().getValue().split("  ");
              
                rec1.setText(SensorBuilder.valueProperty().getValue());
                loadingPower[counter].textProperty().unbind();
                List list = PowerLimits.get(PowerSensors[counter]);
                double min =Double.parseDouble((String) list.get(5));
                //System.out.println(min);
                double max = Double.parseDouble((String) list.get(6));
                if((min !=0 || max !=0)){
                   // System.out.println("value "+split[1]);
                double value = Double.parseDouble(split[1]);
                  if((value <min || value>max)){
                       
                 rec1.setTextFill(Color.web("#FF0000"));
                  }
                  else {
                      rec1.setTextFill(Color.web("#008000")); 
                  }
                }
                else {
                rec1.setTextFill(Color.web("#008000"));
                }
                rec1.setFont(Font.font ("Verdana", 15));
                loadPane1.getChildren().set(counter, rec1);
                if(counter<NumberOFPowersensors-1){
                    counter++;
                    nextPowerPane(SensorBuilder);
                   
               
                }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
           
            }
         
            
            }
       }
    });
     
    
    nextPowerPane(SensorBuilder);
    }
    
    public void initializePowerTables() throws SQLException {
  
    
      PowerSensors = subsystem.setSensors("power");
     
      
              //db.PACKETSENSORS("power",db.connectDB());
     

         
}
    private void nextPowerPane(Service<String> recBuilder) {
    
    loadingPower[counter].textProperty().bind(recBuilder.messageProperty());
    recBuilder.restart();
    }
    public void ReadPowerData() throws SQLException {
        int x=obj.getSessionID();
         PowerLimits = obj.getSensors();
        Powerresult = subsystem.ReadData(x, "Power SubSystem");
        
        Powerresult.next();
    }
    
    private Node createLoadPane() throws SQLException {
   
    NumberOFPowersensors = subsystem.getNumberOfSensors("power subsystem");
    //loadPane1 = new TilePane();
    loadingPower = new Label[NumberOFPowersensors+1];
    for(int i=0;i<NumberOFPowersensors;i++){
            StackPane waitingPane1 = new StackPane();
            waitingPane1.setMinSize(10, 20);
            waitingPane1.setMaxSize(100, 100);
            Label background = new Label();
            background.setText(i+"");
            loadingPower[i] = new Label();
            waitingPane1.getChildren().addAll(background, loadingPower[i]);
            loadPane1.getChildren().add(waitingPane1);
    }
   

    return loadPane1;
}
    
}
