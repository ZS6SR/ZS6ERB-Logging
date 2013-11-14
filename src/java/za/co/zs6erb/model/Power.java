/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.model;

/**
 *
 * @author SeanR
 */
public class Power {
    private int power_id;
    private String powerName;
    private int pMultiplier;
    
    public int getID() { return power_id; }
    public void setID(int power_id) { this.power_id = power_id; }
    
    public String getpowerName() { return powerName; }
    public void setpowerName(String powerName) { this.powerName = powerName; }

    public int getpmultiplier() { return pMultiplier; }
    public void setpmultiplier(int pMultiplier) { this.pMultiplier = pMultiplier; }
    
    @Override
    public String toString() {
        return "Power [power_id=" + power_id + ", PowerName=" + powerName + " Multiplier=" + pMultiplier + "]";
    } 
}
