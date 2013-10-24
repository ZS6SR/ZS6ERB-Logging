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
    private String power;
    private int pMultiplier;
    
    public int getID() { return power_id; }
    public void setID(int power_id) { this.power_id = power_id; }
    
    public String getpower() { return power; }
    public void setpower(String power) { this.power = power; }

    public int getpmultiplier() { return pMultiplier; }
    public void setpmultiplier(int pMultiplier) { this.pMultiplier = pMultiplier; }
    
    @Override
    public String toString() {
        return "Power [power_id=" + power_id + ", Power=" + power + " Multiplier=" + pMultiplier + "]";
    } 
}
