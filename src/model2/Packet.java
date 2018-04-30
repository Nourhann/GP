/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mostafa
 */
public class Packet {

    private String Data;
    private int PacketID;
    private String Sensors;
    private int PacketSequence;
    private String Time;
    
    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public int getPacketSequence() {
        return PacketSequence;
    }

    public void setPacketSequence(int PacketSequence) {
        this.PacketSequence = PacketSequence;
    }

    public String getSensors() {
        return Sensors;
    }

    public void setSensors(String Sensors) {
        this.Sensors = Sensors;
    }
    public int getPacketID() {
        return PacketID;
    }

    public void setPacketID(int PacketID) {
        this.PacketID = PacketID;
    }
    public String getData() {
        return Data;
    }

    public void setData(String Data) {
        this.Data = Data;
    }
    public Packet(String Data,int PackrtID,String Sensors,int PacketSequence,String Time) {
        this.Data = Data;
        this.PacketID=PackrtID;
        this.Sensors=Sensors;
        this.PacketSequence=PacketSequence;
        this.Time=Time;
    }
    public Packet() {
        this.Data ="";
        this.PacketID=0;
        this.Sensors="";
        PacketSequence=0;
        Time="";
    }
    
    private String readfile(String fileName)
    {
        FileReader fileReader;
        String Data="";
        try {
            fileReader = new FileReader(fileName);
            BufferedReader in = new BufferedReader(fileReader);
            Data=in.readLine();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(unpacking.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(unpacking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return " "+Data;
    }
    public unpacking SplitData(int Sequence, unpacking obj,String fileName) throws SQLException
    {
        int Start_APID=obj.getStart_APID();
        int End_APID=obj.getEnd_APID();
        int Start_Time=obj.getStart_Time();
        int End_Time=obj.getEnd_Time();
        int Start_Data=obj.getStart_Data();
        int End_Data=obj.getEnd_Data();
        String type=obj.getType();
        String Data=readfile( fileName);
        int startAPID=0,endAPID=0,startData=0,endData=0,startTime=0,endTime=0;
        int LengthAPID=0,LengthData=0,LengthTime=0;
        String data="";
        Packet temp;
        if (type.equals("bits"))
        {
            startAPID=Start_APID/8;
            endAPID=End_APID/8;
            startData=Start_Data/8;
            endData=End_Data/8;
            startTime=Start_Time/8;
            endTime=End_Time/8;
            int temp1=startAPID,temp3=startData,temp4=startTime;
            if (Start_APID%8==0){startAPID--;}
            if (Start_Data%8==0){startData--;}
            if (Start_Time%8==0){startTime--;}
            if (End_APID%8!=0){endAPID++;}
            if (End_Data%8!=0){endData++;}
            if (End_Time%8!=0){endTime++;}
            LengthAPID=(Start_APID-(temp1*8));
            LengthData=(Start_Data-(temp3*8));
            LengthTime=(Start_Time-(temp4*8));
        }
        else if (type.equals("bytes"))
        {
            startAPID=Start_APID-1;
            endAPID=End_APID;
            startData=Start_Data-1;
            endData=End_Data;
            startTime=Start_Time-1;
            endTime=End_Time;
        }
        while (Data.length()>0)
        {
             Data=Data.substring(1,Data.length());
             temp=new Packet();
             String APID=Data.substring(((startAPID*2)+startAPID), ((endAPID*2)+endAPID-1));
             int apid=determine(APID,LengthAPID,obj);
             //ResultSet Table=obj.getDb().SelectPacketID(apid, obj.getDb().getConnection());
             String DataLength=Data.substring(((startData*2)+startData), ((endData*2)+endData-1));
             int dataLength=determine(DataLength,LengthData,obj)+1;
             if (obj.getEnd_Time()!=0 && obj.getStart_Time()!=0)
             {
                 temp.setTime(Data.substring(((startTime*2)+startTime), ((endTime*2)+endTime-1)));
             }
             data=Data.substring(((endData*2)+endData),(((dataLength*2)+(dataLength-1))+((endData*2)+endData)));
             Data=Data.substring((((dataLength*2)+(dataLength-1))+((endData*2)+endData)),Data.length());
             if(obj.getTablePackets().containsKey(apid))
             {
                 Sequence++;
                 List<String> val = new ArrayList<String>();
                 val=obj.getTablePackets().get(apid);
                 temp.setPacketID(Integer.parseInt(val.get(0)));
                 temp.setSensors(val.get(1));
                 temp.setData(data);
                 temp.setPacketSequence(Sequence);
                 obj.getPackets().add(temp);
             }
        }
        return obj;
    }
    public int determine(String Data,int length,unpacking obj)
    {
        String arr[]=Data.split(" ");
        String data="";
        for (int i=0;i<arr.length;i++)
        {
            data+=obj.getConvert().HexToBinary(arr[i], obj.getConvert());
        }
        if (length!=0)
        {
            data=data.substring(length,data.length());
        }
        int value=obj.getConvert().ConvertBinaryToDecimal(data);
        return value;
    }
    public String Timecalculation(Packet obj,Conversions convert){
        String declength="";
        String hexatime="";
        String hexadata=obj.getData();
        String bytes[]=hexadata.split(" ");
        for(int i=0;i<bytes.length;i++){
            declength+=convert.HexToBinary(""+bytes[i].charAt(0),convert);
            declength+=convert.HexToBinary(""+bytes[i].charAt(1),convert);
        }
        int length = convert.ConvertBinaryToDecimal(declength);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.YEAR, 1958);
        cal.add(Calendar.SECOND, length);
        Format formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String s = formatter.format(cal.getTime());
        return s;
    }
}
