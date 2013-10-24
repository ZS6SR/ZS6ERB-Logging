/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.model;

/**
 *
 * @author SeanR
 */
public class StationClass {
    private int stationClass_id;
    private String classLetter;
    private String className;
    
    public int getID() { return stationClass_id; }
    public void setID(int stationClass_id) { this.stationClass_id = stationClass_id; }
    
    public String getclassLetter() { return classLetter; }
    public void setclassLetter(String classLetter) { this.classLetter = classLetter; }
    
    public String getclassName() { return className; }
    public void setclassName(String className) { this.className = className; }

    @Override
    public String toString() {
        return "StationClass [stationClass_id=" + stationClass_id + ", classLetter=" + classLetter + ", className=" + className + "]";
    } 
}
