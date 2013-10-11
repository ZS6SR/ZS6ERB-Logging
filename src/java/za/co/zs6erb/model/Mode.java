/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.model;

/**
 *
 * @author SeanR
 */
public class Mode {
    private int mode_id;
    private String mode;
    
    public int getID() { return mode_id; }
    public void setID(int mode_id) { this.mode_id = mode_id; }
    
    public String getmode() { return mode; }
    public void setmode(String mode) { this.mode = mode; }

    @Override
    public String toString() {
        return "Modes [mode_id=" + mode_id + ", Mode=" + mode + "]";
    } 
}
