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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.script.ScriptException;

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
    private int NumberOFPowersensors ;
    private String PowerSensors[];
    @FXML
    public TilePane loadPane1;
    public GridPane gride ;
    private Label loadingPower[] ;
    private int count = 0;
    Subsystem subsystem = new Subsystem();
    unpacking obj ;
    ResultSet Powerresult;
    private  Map<String, List<String>> Limits ;
    private ArrayList<ResultSet> values = new ArrayList<ResultSet>();
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
    public ObservableList<TabInfo> initializeTable() throws SQLException {
      ObservableList<TabInfo> tabs = FXCollections.observableArrayList();
      System.out.println("henea");
      ResultSet result=db.ShowUserTabs(db.connectDB());
//      System.out.println(result.getString(0));
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
       try
       {
          // System.out.println("eh b2a");
           AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
          root.getChildren().clear();
          root.getChildren().addAll(pane);
        
       }
       catch(Exception e){
           System.out.println("model2.ShowDesignedTabsController.loadHome()");
       }
     }
    public void Show() throws SQLException, ScriptException, IOException{
            convert();
            ReadPowerData();
            //ReadOBCData();
            createLoadPane();
            //createObcPane();
            Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                    System.out.println("this is called every 5 seconds on UI thread");
                    count=0;
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
                   
                      values.get(count).next();
                      return PowerSensors[count]+" = "+values.get(count).getString(1)+"        \n \n";
                      
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
                // Powerresult.next();
                // System.out.println(counter +" "+Powerresult.getString(1));
                //System.out.println("henA");
                Label rec1 = new Label();
                //String split []= SensorBuilder.valueProperty().getValue().split(" =");
                rec1.setText(SensorBuilder.valueProperty().getValue());
                loadingPower[count].textProperty().unbind();
                //                List list = Limits.get(PowerSensors[count]);
//                double min =Double.parseDouble((String) list.get(5));
//                //System.out.println(min);
//                double max = Double.parseDouble((String) list.get(6));
//                if((min !=0 || max !=0)){
//                 System.out.println("value "+split[1]);
//                try{
//                    double value = Double.parseDouble(split[1]);
//                     if((value <min || value>max)){
//                       
//                 rec1.setTextFill(Color.web("#FF0000"));
//                  }
//                  else {
//                      rec1.setTextFill(Color.web("#010b17")); 
//                  }
//                }
//                 catch(Exception e)
//                    {
//                        System.out.println("Cant check!");
//                    }
//                }
//                else {
//                rec1.setTextFill(Color.web("#010b17"));
//                }
rec1.setFont(Font.font ("Verdana", 14));
loadPane1.getChildren().set(count, rec1);
if(count<NumberOFPowersensors-1){
    count++;
    nextPowerPane(SensorBuilder);
    
    
}
            
           
            }
         
            
            }
       }
    });
     
    
    nextPowerPane(SensorBuilder);
    } 
    

    private void nextPowerPane(Service<String> recBuilder) {
    
    loadingPower[count].textProperty().bind(recBuilder.messageProperty());
    recBuilder.restart();
    }

    public void ReadPowerData() throws SQLException {
        int x=obj.getSessionID();
        Limits = obj.getSensors();
        String tabname = tableView.getSelectionModel().getSelectedItem().getName();
        System.out.println("offff"+tabname+";;;;");
        String sensors = db.Selectsensorsoftab(tabname,db.connectDB());
        PowerSensors=sensors.split(",");
        for (int i =0 ;i<PowerSensors.length;i++){
            Powerresult =db.Selectreadsoftab( PowerSensors[i] , x+" " ,db.connectDB());
            //Powerresult.next();
            values.add(Powerresult);
        }
        
        //subsystem.ReadData(x, "Power SubSystem");
        
        
    }
   
    private Node createLoadPane() throws SQLException {
   
    NumberOFPowersensors =PowerSensors.length;
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
    
    

