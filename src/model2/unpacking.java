/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mostafa
 */
public class unpacking {

    private int Start_APID;
    private int End_APID;
    private int Start_Data;
    private int End_Data;
    private int Start_Time;
    private int End_Time;
    private String Type;
    private int SessionID;
    private int SessionNo;
    private Conversions convert=new Conversions();
    private DB db=new DB();
    private Vector<Packet> packets=new Vector<>();
    private Map<String, List<String>> sensors = new HashMap<String, List<String>>();
    public String Sensor="";
    public String PacketRecieve="";
    private Map<Integer, List<String>> TablePackets = new HashMap<Integer, List<String>>();

    public Map<Integer, List<String>> getTablePackets() {
        return TablePackets;
    }

    public void setTablePackets(Map<Integer, List<String>> TablePackets) {
        this.TablePackets = TablePackets;
    }
        
    public Map<String, List<String>> getSensors() {
        return sensors;
    }

    public void setSensors(Map<String, List<String>> sensors) {
        this.sensors = sensors;
    }
    public int getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(int Start_Time) {
        this.Start_Time = Start_Time;
    }

    public int getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(int End_Time) {
        this.End_Time = End_Time;
    }
    public int getSessionNo() {
        return SessionNo;
    }

    public void setSessionNo(int SessionNo) {
        this.SessionNo = SessionNo;
    }
    public int getSessionID() {
        return SessionID;
    }

    public void setSessionID(int SessionID) {
        this.SessionID = SessionID;
    }
    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    public int getStart_APID() {
        return Start_APID;
    }

    public void setStart_APID(int Start_APID) {
        this.Start_APID = Start_APID;
    }

    public int getEnd_APID() {
        return End_APID;
    }

    public void setEnd_APID(int End_APID) {
        this.End_APID = End_APID;
    }

    public int getStart_Data() {
        return Start_Data;
    }

    public void setStart_Data(int Start_Data) {
        this.Start_Data = Start_Data;
    }

    public int getEnd_Data() {
        return End_Data;
    }

    public void setEnd_Data(int End_Data) {
        this.End_Data = End_Data;
    }

    public Conversions getConvert() {
        return convert;
    }

    public void setConvert(Conversions convert) {
        this.convert = convert;
    }

    public DB getDb() {
        return db;
    }

    public void setDb(DB db) {
        this.db = db;
    }

    public Vector<Packet> getPackets() {
        return packets;
    }

    public void setPackets(Vector<Packet> packets) {
        this.packets = packets;
    }

    public unpacking() {
        this.convert=new Conversions();
        this.db=new DB();
        this.packets=new Vector<>();
        this.Start_APID =0;
        this.End_APID =0;
        this.Start_Data =0;
        this.End_Data =0;
        this.Type="";
        this.SessionID=0;
        this.SessionNo=0;
        this.Start_Time=0;
        this.End_Time=0;
    }
    public unpacking(int Start_APID, int End_APID, int Start_Data, int End_Data,String Type,int SessionID,int SessionNo,int Start_Time,int End_Time) {
        this.Start_APID = Start_APID;
        this.End_APID = End_APID;
        this.Start_Data = Start_Data;
        this.End_Data = End_Data;
        this.Type=Type;
        this.SessionID=SessionID;
        this.SessionNo=SessionNo;
        this.End_Time=End_Time;
        this.Start_Time=Start_Time;
    }
    public void Map(unpacking obj) throws SQLException
    {
        ResultSet Table=obj.getDb().SelectAllSensors(obj.getDb().getConnection());
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        while(Table.next())
        {
            List<String> val = new ArrayList<String>();
                val.add(Table.getString("SENSORNO"));
                val.add(Table.getString("TYPEID"));
                val.add(Table.getString("SENSORUNIT"));
                val.add(Table.getString("FORMAT"));
                val.add(Table.getString("DSISPLAYFORMAT"));
                val.add(Table.getString("MIN_VALUE"));
                val.add(Table.getString("MAX_VALUE"));
                val.add(Table.getString("SENSORDESC"));
                map.put(Table.getString("SENSORNAME"),val);
        }
        obj.setSensors(map);
    }
    public void MapPackets(unpacking obj) throws SQLException
    {
        ResultSet Table=obj.getDb().SelectAllPacket(obj.getDb().getConnection());
        Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
        while(Table.next())
        {
            List<String> val = new ArrayList<String>();
                val.add(Table.getString("PacketID"));
                val.add(Table.getString("PACKETSENSORS"));
                map.put(Integer.parseInt(Table.getString("PACKETADDRESS")), val);
        }
        obj.setTablePackets(map);
    }
    public void Standard(unpacking obj) throws SQLException
    {
        ResultSet Table=obj.getDb().SelectStandard(obj.getDb().getConnection());
        Table.next();
        obj.setStart_APID(Integer.parseInt(Table.getString("APID")));
        obj.setStart_Data(Integer.parseInt(Table.getString("DATA")));
        obj.setStart_Time(Integer.parseInt(Table.getString("TIME")));
        Table.next();
        obj.setEnd_APID(Integer.parseInt(Table.getString("APID")));
        obj.setEnd_Data(Integer.parseInt(Table.getString("DATA")));
        obj.setEnd_Time(Integer.parseInt(Table.getString("TIME")));
        obj.setType(Table.getString("TYPE"));
    }
    public int CreateSession(unpacking obj) throws SQLException
    {
        int count=0;
        ResultSet Table=obj.getDb().SelectMaxSession(obj.getDb().getConnection());
        try
        {
            Table.next();
            int ID=Integer.parseInt(Table.getString("largest"));
            int num=Integer.parseInt(Table.getString("SESSIONNO"));
            ID++;num++;
            obj.setSessionID(ID);
            obj.setSessionNo(num);
            obj.getDb().InsertSession(ID, num, obj.getDb().getConnection());
            count=obj.getDb().CountRows("`packetrecive`", obj.getDb().getConnection());
        }
        catch(Exception e)
        {
            int ID=0;
            int num=0;
            ID++;num++;
            obj.setSessionID(ID);
            obj.setSessionNo(num);
            obj.getDb().InsertSession(ID, num, obj.getDb().getConnection());
        }
       /* if(Table.next())
        {
            int ID=Integer.parseInt(Table.getString("largest"));
            System.out.println(ID);
            int num=Integer.parseInt(Table.getString("SESSIONNO"));
            ID++;num++;
            obj.setSessionID(ID);
            obj.setSessionNo(num);
            obj.getDb().InsertSession(ID, num, obj.getDb().getConnection());
            count=obj.getDb().CountRows("`packetrecive`", obj.getDb().getConnection());
        }
        else
        {
            int ID=0;
            int num=0;
            ID++;num++;
            obj.setSessionID(ID);
            obj.setSessionNo(num);
            obj.getDb().InsertSession(ID, num, obj.getDb().getConnection());
        }*/
        return count;
    }
    public String PacketInformation(unpacking obj) throws SQLException, ScriptException
    {
        ResultSet Table;
        int SENSORNO=0,TYPEID=0,MIN_VALUE=0,MAX_VALUE=0,ByteNo=0,Bits=0;
        String SENSORUNIT="",DSISPLAYFORMAT="",FORMAT="",SENSORNAME="",SENSORDESC="",result="",Byte="",Time="";
        for (int i=0;i<obj.getPackets().size();i++)
        {
            if(obj.getPackets().get(i).getSensors().equals("No Sensors"))
            {
                Time=obj.getPackets().get(i).Timecalculation(obj.getPackets().get(i), obj.getConvert());
                continue;
            }
            if(!obj.getPackets().get(i).getTime().equals(""))
            {
                Time=obj.getPackets().get(i).Timecalculation(obj.getPackets().get(i), obj.getConvert());
            }
            String Sensors[]=obj.getPackets().get(i).getSensors().split(",");
            String Data[]=obj.getPackets().get(i).getData().split(" ");
            PacketRecieve="("+obj.getPackets().get(i).getPacketSequence()+","+obj.getSessionID()+","+obj.getPackets().get(i).getPacketID()+",'"+Time+"')";
            obj.getDb().InsertPacketRecieve(PacketRecieve, obj.getDb().getConnection());
            for(int j=0;j<Sensors.length;j++)
            {
                //Table=obj.getDb().SelectSensor(Sensors[j], obj.getDb().getConnection());
                //Table.next();
                
                List<String> val = new ArrayList<String>();
                val=obj.getSensors().get(Sensors[j]);
                for (int s = 0; s < val.size(); s++) 
                {
                    if (s==0)
                    {
                        SENSORNO=Integer.parseInt(val.get(s));
                        SENSORNAME=Sensors[j];
                    }
                    else if (s==1)
                    {
                        TYPEID=Integer.parseInt(val.get(s));
                    }
                    else if (s==2)
                    {
                        SENSORUNIT=val.get(s);
                    }
                    else if (s==3)
                    {
                        FORMAT=val.get(s);
                    }
		}
                
                //SENSORNO=Integer.parseInt(Table.getString("SENSORNO"));
                //TYPEID=Integer.parseInt(Table.getString("TYPEID"));
                //SENSORUNIT=Table.getString("SENSORUNIT");
                //SENSORNAME=Table.getString("SENSORNAME");
                //FORMAT=Table.getString("FORMAT");
                Table=obj.getDb().SelectAddress(SENSORNO, obj.getDb().getConnection());
                while(Table.next())
                {
                    Bits=Integer.parseInt(Table.getString("BITS"));
                    ByteNo=Integer.parseInt(Table.getString("BYTENO"));
                    Byte=Data[ByteNo-1];
                    Byte=obj.getConvert().HexToBinary(Byte, obj.getConvert());
                    result+=obj.getConvert().SelectSpecificBits(Byte, obj.getConvert().intToBinary(Bits)); 
                }
                int read=0;
                if (FORMAT.equals("+"))
                {
                    read=obj.getConvert().ConvertBinaryToDecimal(result);
                }
                else if (FORMAT.equals("-"))
                {
                    result=obj.getConvert().TwoSComplement(result);
                    read=obj.getConvert().ConvertBinaryToDecimal(result);
                    read*=-1;
                }
                //System.out.println(read);
                String value=Calibration(TYPEID,SENSORNO,read,obj);
                System.out.println(value);
                Sensor="("+obj.getSessionID()+","+obj.getPackets().get(i).getPacketSequence()+","+SENSORNO+","+read+",'"+value+"','"+Time+"')";
                obj.getDb().InsertData(Sensor, obj.getDb().getConnection());
                //Sensor+="&";
                result="";
            }
            //Sensor+="#";
            //PacketRecieve+="#";
            
        }
        return "";
    }
    public String Calibration(int TYPEID,int SENSORNO,int read,unpacking obj) throws SQLException, ScriptException
    {
        ResultSet Table;
        Table = obj.getDb().SelectType(TYPEID, SENSORNO, obj.getDb().getConnection());
        Table.next();
        String calibration = Table.getString("MESSAGEDESC");
        Vector<String> reads = new Vector<>();
        String Result = "";
        if (TYPEID == 1 && calibration.equals("No change")) {
            Result = "" + read;
        } else if (TYPEID == 1) {
            if (read==255)
            {
                read=1;
            }
            if (!Table.next())
            {
                Result=calibration;
            }
            else
            {
                reads.add(calibration);
                calibration = Table.getString("MESSAGEDESC");
                reads.add(calibration);
                Result = "" + reads.get(read);
            }
        } else if (TYPEID == 2) {
            reads.add(calibration);
            while (Table.next()) {
                calibration = Table.getString("MESSAGEDESC");
                reads.add(calibration);
            }
            Result = "" + reads.get(read);
        } else if (TYPEID == 3) {
            calibration = calibration.replace("code", Integer.toString(read));
            calibration = calibration.replace("[", "(");
            calibration = calibration.replace("]",")");
            calibration = calibration.replace("10^-5","1/100000");

            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            Double rDouble = 0.0;
            int rInt = 0;
            Object object = engine.eval(calibration);
            if (object instanceof Integer) {
                rInt = (int) engine.eval(calibration);
                Result = Integer.toString(rInt);
            }
            if (object instanceof Double) {
                rDouble = (Double) engine.eval(calibration);
                Result = Double.toString(rDouble);
            }
        }
        return Result;        
    }
}
