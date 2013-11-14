package za.co.zs6erb.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import za.co.zs6erb.dao.PowerDao;
import za.co.zs6erb.dao.ProvinceCodeDao;
import za.co.zs6erb.dao.StationClassDao;

/**
 *
 * @author SeanR
 */
public class Contest {
    private int contest_id;
    private String contestName;
    private Date startDate;
    private Date endDate;
    private int stationClass;
    private int provinceCode;
    private int power;
    
    public int getID() { return contest_id; }
    public void setID(int contest_id) { this.contest_id = contest_id; }
    
    public String getcontestName() { return contestName; }
    public void setcontestName(String contestName) { this.contestName = contestName; }
    
    public Date getstartDate() { return startDate; }
    public void setstartDate(Date startDate) { this.startDate = startDate; }
    
    public Date getendDate() { return endDate; }
    public void setendDate(Date endDate) { this.endDate = endDate; }

    public String getstartDateStr() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dt.format(startDate);
    }
    
    public String getendDateStr() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dt.format(endDate);
    }
    
    public String getEndYear() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy");
        return dt.format(endDate);
    }
    
    public String getEndMonth() {
        SimpleDateFormat dt = new SimpleDateFormat("MM");
        return dt.format(endDate);
    }
    
    public String getEndDay() {
        SimpleDateFormat dt = new SimpleDateFormat("dd");
        return dt.format(endDate);
    }
    
    public String getEndHour() {
        SimpleDateFormat dt = new SimpleDateFormat("HH");
        return dt.format(endDate);
    }
    
    public String getEndMin() {
        SimpleDateFormat dt = new SimpleDateFormat("mm");
        return dt.format(endDate);
    }
    
    public int getstationClass() { return stationClass; }
    public void setstationClass(int stationClass) { this.stationClass = stationClass; }
    public String getstationClassName() {
        StationClassDao sdao = new StationClassDao();
        StationClass sType = sdao.getSCById(stationClass);
        String statClass = "(" + sType.getclassLetter() + ") " + sType.getclassName();
        return statClass;
    }
    
    public int getprovinceCode() { return provinceCode; }
    public void setprovinceCode(int provinceCode) { this.provinceCode = provinceCode; }
    public String getprovinceCodeName() {
        ProvinceCodeDao pdao = new ProvinceCodeDao();
        ProvinceCode pc = pdao.getPCById(provinceCode);
        String provName = "(" + pc.getprovinceCode() + ") " + pc.getprovinceName();
        return provName;
    }
    
    public int getpower() { return power; }
    public void setpower(int power) { this.power = power; }
    public String getpowerName() {
        PowerDao dao = new PowerDao();
        Power pwr = dao.getPowerById(power);
        System.out.println("Power Object = " + pwr.toString());
        String pwrName = pwr.getpowerName();
        System.out.println("pwrName = >" + pwrName + "<");
        return pwrName;
    }
    
    @Override
    public String toString() {
        return "Contest [contest_id=" + contest_id + ", Contest Name=" + contestName + ", Start Date=" + startDate + 
                ", End Date=" + endDate + ", Province Code=" + provinceCode + ", StationClass=" + stationClass + ", Power=" + power + "]";
    } 
}
