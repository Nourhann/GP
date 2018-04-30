/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author norhan
 */
public class Sensor {
 private final StringProperty Subsystem = new SimpleStringProperty();

    private final StringProperty Name = new SimpleStringProperty();
    private int ID ;
    
    
    
     public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSubsystem() {
        return Subsystem.get();
    }

    public String getName() {
        return Name.get();
    }
    public void setName(String name)
    {
        Name.set( name);
    }
    
     public void setSubsystem(String subsystem)
    {
        Subsystem.set(subsystem);
    }
//   private  String Subsystem ;
//   private  String Name;
//
//    public void setSubsystem(String Subsystem) {
//        this.Subsystem = Subsystem;
//    }
//
//    public void setName(String Name) {
//        this.Name = Name;
//    }
//
//    public String getSubsystem() {
//        return Subsystem;
//    }
//
//    public String getName() {
//        return Name;
//    }
//   
   
}
