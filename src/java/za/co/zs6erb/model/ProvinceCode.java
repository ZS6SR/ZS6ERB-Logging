/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.model;

/**
 *
 * @author SeanR
 */
public class ProvinceCode {
    private int provinceCode_id;
    private String provinceCode;
    private String provinceName;
    
    public int getID() { return provinceCode_id; }
    public void setID(int provinceCode_id) { this.provinceCode_id = provinceCode_id; }
    
    public String getprovinceCode() { return provinceCode; }
    public void setprovinceCode(String provinceCode) { this.provinceCode = provinceCode; }
    
    public String getprovinceName() { return provinceName; }
    public void setprovinceName(String provinceName) { this.provinceName = provinceName; }

    @Override
    public String toString() {
        return "StationClass [provinceCode_id=" + provinceCode_id + ", provinceCode=" + provinceCode + ", provinceName=" + provinceName + "]";
    } 
}
