/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.model;

import java.math.BigDecimal;

/**
 *
 * @author SeanR
 */
public class Band {
    private int band_id;
    private String band;
    private BigDecimal startBand;
    private BigDecimal endBand;
    
    public int getID() { return band_id; }
    public void setID(int band_id) { this.band_id = band_id; }
    
    public String getband() { return band; }
    public void setband(String band) { this.band = band; }
    
    public BigDecimal getStartBand() { return startBand; }
    public void setStartBand(BigDecimal startBand) { this.startBand = startBand; }
    
    public BigDecimal getEndBand() { return endBand; }
    public void setEndBand(BigDecimal endBand) { this.endBand = endBand; }

    @Override
    public String toString() {
        return "Bands [band_id=" + band_id + ", Band=" + band + ", Start Band=" + startBand + 
                ", End Band=" + endBand + "]";
    } 
}
