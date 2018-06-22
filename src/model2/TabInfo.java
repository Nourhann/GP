/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model2;

/**
 *
 * @author norhan
 */
public class TabInfo {
    String Name;
    String Sensors;

    public TabInfo(String Name, String Sensors) {
        this.Name = Name;
        this.Sensors = Sensors;
    }

    public TabInfo() {
    }

    public String getName() {
        return Name;
    }

    public String getSensors() {
        return Sensors;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setSensors(String Sensors) {
        this.Sensors = Sensors;
    }
    
}
