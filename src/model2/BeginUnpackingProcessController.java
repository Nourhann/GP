/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import java.io.BufferedReader;
import java.io.FileReader;
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
import javafx.geometry.Insets;
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
    private int NumberOFOBCsensors ;
    private String PowerSensors[];
    private String OBCSensors[];
    public TilePane loadPane1;
    public TilePane OBCTile;
    public GridPane gride ;
    private Label loadingPower[] ;
    private Label loadingOBC[] ;
    private int Powercounter = 0;
    private int OBCcounter = 0;
    Subsystem subsystem = new Subsystem();
    ResultSet Powerresult;
    ResultSet obcresult;
    private  Map<String, List<String>> Limits ; 
    private  Map<String, List<String>>  OBCLimits;
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
            initializeOBCTables();
            convert();
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
   /* public void convert2() throws SQLException, IOException, ScriptException{
     unpacking1 obj=new unpacking1();
     Packet_1 p=new Packet_1();
     obj.getDb().setConnection(obj.getDb().connectDB());
     obj.getConvert().setConvertTable(obj.getConvert().Initialization(obj.getConvert().getConvertTable()));
     obj.Standard(obj);
     obj.Map(obj);
     obj.MapPackets(obj);
     obj.setModeOfProgram(false);
        if (obj.isModeOfProgram()) 
        {
            int count = obj.CreateSession(obj);
            //PrintWriter writer = new PrintWriter(count+".txt", "UTF-8");
            //writer.close();
            String SessionFile = count + ".txt";
            long length = 0;
            while (true) 
            {
                while (length == 0) 
                {
                    length = p.FileLength("ahmed.txt");
                }
                String Data = p.readfileOnline("ahmed.txt");
                if (length > 0) 
                {
                    length = 0;
                    //writer.print(Data);
                    p.writefile(Data, SessionFile);
                    p.SplitData(count, obj, " " + Data);
                    obj.PacketInformation(obj);
                }
                System.out.println("0");
            }
        }
        else 
        {
            String Data = p.readfileOffline("test.txt");
            p.SplitData(0, obj, " " + Data);
            obj.PacketInformation(obj);
        }
    
    }*/
    public void convert() throws SQLException, ScriptException, IOException{   
     String file="";
     String mood = readFromfile("mood.txt");
     if(mood.equals("on")){
         file=readFromfile("livefile.txt");
     }
     else if(mood.equals("off")){
         file= readFromfile("offlinefile.txt");
     }
        System.out.println("hena "+mood);
     obj=new unpacking();
     Packet p=new Packet();
     obj.getDb().setConnection(obj.getDb().connectDB());
     obj.getConvert().setConvertTable(obj.getConvert().Initialization(obj.getConvert().getConvertTable()));
     obj.Standard(obj);
     obj.Map(obj);
     obj.MapPackets(obj);
     int count=obj.CreateSession(obj);
     p.SplitData(count, obj,file);
     obj.PacketInformation(obj);
     p=null;
     //-------------------------------------------------------------------------------------------------
        
    }
    public void ViewPower() throws Exception {
    

            ReadPowerData();
            //ReadOBCData();
            createLoadPane();
            //createObcPane();
            Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                    System.out.println("this is called every 5 seconds on UI thread");
                    Powercounter=0;
                    OBCcounter =0;
                    loadPowerTab();
                  //  loadOBCTab();


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
                   
                     
                      return PowerSensors[Powercounter]+" ="+Powerresult.getString(1)+"\n\n";
                      
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
                String split []= SensorBuilder.valueProperty().getValue().split(" =");
              
                rec1.setText(SensorBuilder.valueProperty().getValue());
                loadingPower[Powercounter].textProperty().unbind();
                List list = Limits.get(PowerSensors[Powercounter]);
                double min =Double.parseDouble((String) list.get(5));
                //System.out.println(min);
                double max = Double.parseDouble((String) list.get(6));
                if((min !=0 || max !=0)){
                 System.out.println("value "+split[1]);
                try{
                    double value = Double.parseDouble(split[1]);
                     if((value <min || value>max)){
                       
                 rec1.setTextFill(Color.web("#FF0000"));
                  }
                  else {
                      rec1.setTextFill(Color.web("#010b17")); 
                  }
                }
                 catch(Exception e)
                    {
                        System.out.println("Cant check!");
                    }
                }
                else {
                rec1.setTextFill(Color.web("#010b17"));
                }
                rec1.setFont(Font.font ("Verdana", 14));
                loadPane1.getChildren().set(Powercounter, rec1);
                if(Powercounter<NumberOFPowersensors-1){
                    Powercounter++;
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
    private void loadOBCTab() {
  
    
    final Service<String> SensorBuilder = new Service<String>() {
          @Override protected Task<String> createTask() {
              return new Task<String>() {
                  @Override protected String call() throws InterruptedException, SQLException {
                      
             
                      updateProgress(0, 10);
                     for (int i = 0; i < 10; i++) {
                        Thread.sleep(10);
                      }
                   
                     
                      return OBCSensors[OBCcounter]+"  "+obcresult.getString(1)+"\n";
                      
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
                loadingOBC[OBCcounter].textProperty().unbind();
                List list = Limits.get(OBCSensors[OBCcounter]);
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
                OBCTile.getChildren().set(OBCcounter, rec1);
                if(Powercounter<NumberOFPowersensors-1){
                    OBCcounter++;
                    nextPowerPane(SensorBuilder);
                   
               
                }
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
           
            }
         
            
            }
       }
    });
     
    
    nextOBCPane(SensorBuilder);
    }
    public void initializePowerTables() throws SQLException {
  
    
      PowerSensors = subsystem.setSensors("power");
     
      
              //db.PACKETSENSORS("power",db.connectDB());
     

         
}
    public void initializeOBCTables() throws SQLException {
  
    
      OBCSensors = subsystem.setSensors("OBC");
     
      
              //db.PACKETSENSORS("power",db.connectDB());
     

         
}
    private void nextPowerPane(Service<String> recBuilder) {
    
    loadingPower[Powercounter].textProperty().bind(recBuilder.messageProperty());
    recBuilder.restart();
    }
    private void nextOBCPane(Service<String> recBuilder) {
    
    loadingOBC[OBCcounter].textProperty().bind(recBuilder.messageProperty());
    recBuilder.restart();
    }
    public void ReadPowerData() throws SQLException {
        int x=obj.getSessionID();
        Limits = obj.getSensors();
        Powerresult = subsystem.ReadData(x, "Power SubSystem");
        
        Powerresult.next();
    }
    public void ReadOBCData() throws SQLException {
        int x=obj.getSessionID();
        OBCLimits = obj.getSensors();
        obcresult = subsystem.ReadData(x, "OBC SubSystem");
        obcresult.next();
    } 
    private Node createLoadPane() throws SQLException {
   
    NumberOFPowersensors =16;
            //subsystem.getNumberOfSensors("power subsystem");
    //loadPane1 = new TilePane();
    loadingPower = new Label[NumberOFPowersensors+1];
    for(int i=0;i<NumberOFPowersensors;i++){
            StackPane waitingPane1 = new StackPane();
           // waitingPane1.setMinSize(10, 20);
           // waitingPane1.setMaxSize(100, 100);
            Label background = new Label();
            //background.setText(i+"");
            Label NewLabel = new Label();
            //NewLabel.setMaxSize(50,50);
           
            NewLabel.setPadding(new Insets(0, 0,25, 0));
            
            loadingPower[i] =NewLabel;
            loadingPower[i].setPadding(new Insets(0, 0,10, 0));
            waitingPane1.getChildren().addAll(loadingPower[i]);
            loadPane1.getChildren().add(waitingPane1);
    }
   

    return loadPane1;
}
    private Node createObcPane() throws SQLException {
   
    NumberOFOBCsensors =16;
            //subsystem.getNumberOfSensors("power subsystem");
    //loadPane1 = new TilePane();
    loadingOBC = new Label[NumberOFPowersensors+1];
    for(int i=0;i<NumberOFPowersensors;i++){
            StackPane waitingPane1 = new StackPane();
            waitingPane1.setMinSize(10, 20);
            waitingPane1.setMaxSize(100, 100);
            Label background = new Label();
            background.setText(i+"");
            loadingOBC[i] = new Label();
            waitingPane1.getChildren().addAll(background, loadingOBC[i]);
            OBCTile.getChildren().add(waitingPane1);
    }
   

    return OBCTile;
}  
    public String readFromfile(String fileName) throws IOException{
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
    
}
