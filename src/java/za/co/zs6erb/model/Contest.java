package za.co.zs6erb.model;

import java.sql.Timestamp;

/**
 *
 * @author SeanR
 */
public class Contest {
    private int contest_id;
    private String contestName;
    private Timestamp startDate;
    private Timestamp endDate;
    
    public int getID() { return contest_id; }
    public void setID(int contest_id) { this.contest_id = contest_id; }
    
    public String getcontestName() { return contestName; }
    public void setcontestName(String contestName) { this.contestName = contestName; }
    
    public Timestamp getstartDate() { return startDate; }
    public void setstartDate(Timestamp startDate) { this.startDate = startDate; }
    
    public Timestamp getendDate() { return endDate; }
    public void setendDate(Timestamp endDate) { this.endDate = endDate; }

    @Override
    public String toString() {
        return "Contest [contest_id=" + contest_id + ", Contest Name=" + contestName + ", Start Date=" + startDate + 
                ", End Date=" + endDate + "]";
    } 
}
