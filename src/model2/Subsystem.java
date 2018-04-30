/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author norhan
 */
public class Subsystem {
    private int NumberOfSensors;
    private String [] Sensors;
    DB db = new DB();

    public int getNumberOfSensors(String SystemName ) throws SQLException {
       return db.NumOfSensorsForSubSystem(SystemName,db.connectDB());
         
    }

    public String[] getSensors() {
        return Sensors;
    }

    public String[] setSensors(String SystemName) throws SQLException {
       String result = db.PACKETSENSORS(SystemName,db.connectDB());
     
      //System.out.println("test "+NumberOFPowersensors);
      String [] Sensors =result.split(",");
      return Sensors;
    }
    
   public ResultSet ReadData(int SessionID , String SystemName) throws SQLException{
        ResultSet result = db.SelectReadData(SessionID,SystemName,db.connectDB());
       // System.out.println(result.getArray(counter));
        
          //result.next();
          return result;
   }
}
