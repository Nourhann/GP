/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mostafa
 */
public class DB {
    private Connection connection;

    public DB() {
        this.connection = null;
    }
    public DB(Connection connection) {
        this.connection = connection;
    }
    public Connection getConnection() {
        return connection;
    }
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public Connection connectDB()
    {
        Connection connection=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String dbURL="jdbc:mysql://localhost:3306/cubesat";
            String Username="root";
            String password="";
             connection=DriverManager.getConnection(dbURL,Username,password);
            System.out.println("Success");
        }
        catch(Exception e)
        {
            System.out.println("connection failure");
        }
        return connection;
    }
    void InsertSession(int SessionID,int SessionNo,Connection connection) throws SQLException
    {
        try
        {
            String query="INSERT INTO `session`(`SESSIONID`, `SESSIONNO`) VALUES ("+SessionID+","+SessionNo+")";
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in Session statement");
        }
    }
    void InsertPacketRecieve(String PacketAddress,Connection connection) throws SQLException
    {
        try
        {
            String query="INSERT INTO `packetrecive`(`PACKETSEQUENCE`, `SESSIONID`, `PACKETID`, `TIME`) VALUES"+PacketAddress;
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in packetrecive statement");
        }
    }
    void InsertData(String Data,Connection connection) throws SQLException
    {
        try
        {
            String query="INSERT INTO `storage`(`sessionID`, `PACKETSEQUENCE`, `SENSORNO`, `CODE`, `VALUE`, `Time`) VALUES "+Data;
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in InsertReads statement");
        }
    }
    void InsertSystem(String SubSystemID,String Description,Connection connection) throws SQLException
    {
        try
        {
            String query="INSERT INTO `system`(`SYSTEMID`, `SYSTEMDESCRIPTION`) VALUES ('"+SubSystemID+"','"+Description+"')";
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in Insert SubSystem statement");
        }
    }    
    void InsertStandard(int APID,int Data,Connection connection) throws SQLException
    {
        try
        {
            String query="INSERT INTO `standard`(`APID`, `DATA`) VALUES ("+APID+","+Data+")";
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in Insert SubSystem statement");
        }
    }
    void InsertPacket(int PacketID,int SystemID,int PacketAddress,String PacketSensors,Connection connection) throws SQLException
    {
        try
        {
            String query="INSERT INTO `packet`(`PACKETID`, `SYSTEMID`, `PACKETADDRESS`, `PACKETSENSORS`) VALUES ("+PacketID+","+SystemID+","+PacketAddress+",'"+PacketSensors+"')";
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in Insert Packet Table statement");
        }
    }
    void InsertSenssorsAddress(int ByteNumber,int PacketID,int Sensorno,int Bits,int Order,Connection connection) throws SQLException
    {
        try
        {
            String query="INSERT INTO `address`(`BYTENO`, `PACKETID`, `SENSORNO`, `BITS`, `BYTEORDER`) VALUES ("+ByteNumber+","+PacketID+","+Sensorno+","+Bits+","+Order+")";
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in Insert Address Table statement");
        }
    }
    void InsertSenssor(int SensorNo,int TypeID,String SensorUnit,String DisplayFormat,String Format,int max,int min,String SensorName,String SensorDescription,Connection connection) throws SQLException
    {
        try
        {
            String query="INSERT INTO `sensors`(`SENSORNO`, `TYPEID`, `SENSORUNIT`, `DSISPLAYFORMAT`, `FORMAT`, `MIN_VALUE`, `MAX_VALUE`, `SENSORNAME`, `SENSORDESC`) VALUES ("+SensorNo+","+TypeID+",'"+SensorUnit+"','"+DisplayFormat+"','"+Format+"',"+max+","+min+",'"+SensorName+"','"+SensorDescription+"')";
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in Insert Sensors Table statement");
        }
    }
    void InsertType(int TypeID,String Description,Connection connection) throws SQLException
    {
        try
        {
            String query="INSERT INTO `type`(`TYPEID`, `DESCRIPTION`) VALUES ("+TypeID+",'"+Description+"')";
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in Type statement");
        }
    }
    void InsertTypes(int TypeID,String MessageDescOrEquation,String MessageCode,int MessageIndex,Connection connection) throws SQLException
    {
        try
        {
            String query="INSERT INTO `type1`(`TYPEID`, `MESSAGEDESC`, `MESSAGECODE`, `MessageIndex`) VALUES ("+TypeID+",'"+MessageDescOrEquation+"','"+MessageCode+"',"+MessageIndex+")";
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in Types statement");
        }
    }
    void InsertTypeInterval(int x,int y,int MessageIndex,Connection connection) throws SQLException
    {
        try
        {
            String query="INSERT INTO `type3_1`(`X`, `Y`, `messageIndex`) VALUES ("+x+","+y+","+MessageIndex+")";
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in TypeInterval statement");
        }
    }
    
    void Updata(String NameUpdatedVariable,String NewValue,String TableName,Connection connection) throws SQLException
    {
        try
        {
            String query="UPDATE "+TableName+" SET "+NameUpdatedVariable+" = "+NewValue;
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in updata statement");
        }
    }
    void Delete(String NameDeletedVariable,String DeletedValue,String TableName,Connection connection) throws SQLException
    {
        String query="DELETE FROM "+TableName+" WHERE "+NameDeletedVariable+ " = "+DeletedValue;
        Statement statement=connection.createStatement();
        int flag=statement.executeUpdate(query);
    }
    void DeleteTable(String TableName,Connection connection) throws SQLException
    {
        String query="DELETE FROM "+TableName;
        Statement statement=connection.createStatement();
        int flag=statement.executeUpdate(query);
    } 
    
    ResultSet SelectAllTable(String TableName,Connection connection) throws SQLException
    {
        String query="SELECT * FROM "+TableName;
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;
    }
    ResultSet SelectMaxSession(Connection connection) throws SQLException
    {
        String query="SELECT `SESSIONNO`,MAX(`SESSIONID`) AS `largest` FROM SESSION";
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;
    }
    ResultSet SelectStandard(Connection connection) throws SQLException
    {
        String query="SELECT * FROM `standard`";
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;
    }
    ResultSet SelectPacketID(int APID,Connection connection) throws SQLException
    {
        String query="SELECT * FROM `packet` WHERE `PACKETADDRESS`="+APID;
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;
    }
    ResultSet SelectAllPacket(Connection connection) throws SQLException
    {
        String query="SELECT * FROM `packet`";
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;
    }
    ResultSet SelectSensor(String SensorName,Connection connection) throws SQLException
    {
        String query="SELECT * FROM `sensors` WHERE `SENSORNAME` = '"+SensorName+"'";
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;
    }
    ResultSet SelectAllSensors(Connection connection) throws SQLException
    {
        String query="SELECT * FROM `sensors`";
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;
    }
    ResultSet SelectType(int Type,int SensorID ,Connection connection) throws SQLException
    {
        String query="SELECT * FROM `type"+Type+"` WHERE `MessageIndex` = "+SensorID+"";
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;
    }
    ResultSet SelectAddress(int SensorID ,Connection connection) throws SQLException
    {
        String query="SELECT * FROM `address` WHERE `SENSORNO` = "+SensorID+" ORDER BY BYTEORDER ASC";
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;
    }
  ResultSet SelectReadData(int sessionID,String SubSystemName ,Connection connection) throws SQLException
    {
        String query="SELECT `VALUE` FROM `storage`,`packet`,`system`\n" +
            "WHERE storage.sessionID="+sessionID+" AND packet.SYSTEMID=(select system.SYSTEMID as ID from system where system.SYSTEMDESCRIPTION='"+SubSystemName
                +"') AND packet.PACKETID=(select packet.PACKETID as ID from packet where packet.SYSTEMID=(select system.SYSTEMID as ID from system where system.SYSTEMDESCRIPTION='"+SubSystemName+"'))";
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;
    }
    int CountRows(String TableName,Connection connection) throws SQLException
    {
        String query="SELECT COUNT(*) AS Count FROM "+TableName;
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        Table.next();
        int Count=Integer.parseInt( Table.getString("Count"));
        return Count;
    }
        ResultSet Selectbysesnorreadbytimerange(String Systemname,String sensorname,String firsttime,String secondtime,Connection connection) throws SQLException
    {
        String tablename=Systemname+"SubSystem";
        String query="SELECT VALUE,Time FROM `storage` a,`packetrecive` p, `sensors` s,`packet`,`system`WHERE s.SENSORNO=a.SENSORNO and s.PACKETSEQUENCE=p.PACKETSEQUENCE and \n" +
"p.PACKETID=`packet`.`PACKETID` and `packet`.`SYSTEMID`=`system`.`SYSTEMID`\n" +
" `storage`.SENSORNAME="+sensorname+" and `storage`.`Time` between "+"\""+firsttime+"\""+  "and" +"\""+secondtime+"\"`system`.`SYSTEMDESCRIPTION`="+tablename;
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;// lsa el min w el max
        
    }
//    String PACKETSENSORS(String subsystemname,Connection connection) throws SQLException
//    {
//        
//        String query="SELECT `PACKETSENSORS` FROM `system` JOIN `packet` WHERE packet.SYSTEMID=system.SYSTEMID and system.SYSTEMDESCRIPTION='"
//                +subsystemname+" SubSystem'";
//        
//        Statement statement=connection.createStatement();
//        ResultSet Table=statement.executeQuery(query);
//        Table.next();
//        String PACKETSENSORS=Table.getString("PACKETSENSORS");
//        return PACKETSENSORS;
//    }
        ResultSet SelectAllsensorspositions(String Subsystemname ,Connection connection) throws SQLException
    {
        String query="SELECT BYTENO,SENSORNO,BITS,BYTEORDER FROM `address` a,`packet` p, `system` s where a.PACKETID=p.PACKETID and p.SYSTEMID=s.SYSTEMID and s.SYSTEMDESCRIPTION="+Subsystemname+" SubSystem";
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;
    }
        int NumOfSensorsForSubSystem(String SubSystem ,Connection connection) throws SQLException
    {
        String query="SELECT PACKETSENSORS FROM system JOIN packet WHERE system.SYSTEMID=packet.SYSTEMID AND system.SYSTEMDESCRIPTION='"+SubSystem+"'";
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        Table.next();
        String sensors[]=Table.getString("PACKETSENSORS").split(",");
        return sensors.length;
    }
         String PACKETSENSORS(String subsystemname,Connection connection) throws SQLException
    {

           String query="SELECT PACKETSENSORS FROM packet x,system y WHERE x.SYSTEMID=y.SYSTEMID and y.SYSTEMDESCRIPTION='"+subsystemname+" SubSystem' ";
        
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        Table.next();
        String PACKETSENSORS=Table.getString("PACKETSENSORS");
        return PACKETSENSORS;
    }
         void Insertusertab (String sensors , String tabname,Connection connection) throws SQLException
    {
        try
        {
            String query="INSERT INTO `usertab`(`tabname`, `sensors`) VALUES ('"+tabname+"','"+sensors+"')";
            Statement statement=connection.createStatement();
            int flag=statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println("Error in Usertab statement");
        }
}
         ResultSet ShowUserTabs (Connection connection) throws SQLException
    {   
        
            String query="SELECT * FROM `usertab`";
            Statement statement=connection.createStatement();
            ResultSet Table=statement.executeQuery(query);
            return Table;
}
         int NumOfSensors(String TableName ,Connection connection) throws SQLException
    {
        String query="SELECT COUNT(*) FROM "+TableName;
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        Table.next();
        int num=Table.getInt(1);
        return num;
    }
          
        String Selectsensorsoftab(String tabname,Connection connection) throws SQLException
    {
        String query="SELECT `sensors` FROM `usertab` WHERE `tabname` = '"+tabname+"'";
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        Table.next();
        String sensors = Table.getString("sensors");
        return sensors;
    }
        ResultSet Selectreadsoftab( String sensorname , String sessionid ,Connection connection) throws SQLException
    {

//        String query = "SELECT  `CODE`  FROM `storage`, `sensors`"
//                + " WHERE storage.SENSORNO=sensors.SENSORNO and"
//                + " sessionID="+sessionid+" and sensors.SENSORNAME= "+sensorname;
        String query = "SELECT  `CODE`  FROM `storage`, `sensors`\n" +
                       "WHERE storage.SENSORNO = sensors.SENSORNO and\n" +
                       "sessionID='"+sessionid+"' and sensors.SENSORNAME= '"+sensorname+"'";
        Statement statement=connection.createStatement();
        ResultSet Table=statement.executeQuery(query);
        return Table;
    }
    
}
