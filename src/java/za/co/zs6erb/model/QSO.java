package za.co.zs6erb.model;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author SeanR
 */
public class QSO {
    private int qso_id;
    private int user_id; 
    private Timestamp startTime;
    private Timestamp endTime;
    private String callsign;
    private int bandId;
    private String freq;
    private int modeId;
    private int powerId;
    private int localRST;
    private int remoteRST;
    private String notes;
    private String name;
    private String location;
    
    public int getID() { return qso_id; }
    public void setID(int qso_id) { this.qso_id = qso_id; }
    
    public int getuserID() { return user_id; }
    public void setuserID(int user_id) { this.user_id = user_id; }
    
    public Timestamp getstartTime() { return startTime; }
    public void setstartTime(Timestamp startTime) { this.startTime = startTime; }
    
    public Timestamp getendTime() { return endTime; }
    public void setendTime(Timestamp endTime) { this.endTime = endTime; }
    
    public String getcallSign() { return callsign; }
    public void setcallSign(String callsign) { this.callsign = callsign; }

    public int getbandId() { return bandId; }
    public void setbandId(int bandId) { this.bandId = bandId; }
    
    public String getfreq() { return freq; }
    public void setfreq(String freq) { this.freq = freq; }
    
    public int getmodeId() { return modeId; }
    public void setmodeId(int modeId) { this.modeId = modeId; }
    
    public int getpowerId() { return powerId; }
    public void setcpowerId(int powerId) { this.powerId = powerId; }
    
    public int getlocalRST() { return localRST; }
    public void setlocalRST(int localRST) { this.localRST = localRST; }
    
    public int getremoteRST() { return remoteRST; }
    public void setremoteRST(int remoteRST) { this.remoteRST = remoteRST; }
    
    public String getnotes() { return notes; }
    public void setnotes(String notes) { this.notes = notes; }
    
    public String getname() { return name; }
    public void setname(String name) { this.name = name; }
    
    public String getlocation() { return location; }
    public void setlocation(String location) { this.location = location; }
    
    public String getstartTimeStr() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dt.format(startTime);
    }
    
    public String getendTimeStr() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dt.format(endTime);
    }
    
    @Override
    public String toString() {
        return "QSO [qso_id=" + qso_id + ", User ID=" + user_id + ", Start Time=" + startTime + ", End Time=" + endTime + ", Callsign=" + callsign + 
                ", BandID=" + bandId + ", ModeID=" + modeId + ", PowerID=" + powerId +", localRST=" + localRST + 
                ", remoteRST=" + remoteRST + ", notes=" + notes + ", Name=" + name + "location=" + location + 
                "]";
    } 
}
