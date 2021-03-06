/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import za.co.zs6erb.util.DbUtil;

/**
 *
 * @author SeanR
 */
public class SummaryDao {

    private Connection connection;

    public SummaryDao() {
        connection = DbUtil.getConnection();
    }

    public int getTotalQSO() {
        int tQSO = 0;
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(qso_id) from qsos");
            while (rs.next()) {
                tQSO = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tQSO;
    }
    
    public String getTopUser() {
        String topUser = "";
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select users.callsign, count(*) as count from qsos " +
                                                    "inner join users on qsos.user_id=users.user_id " +
                                                    "group by users.callsign order by count desc");
            if (rs.first()) {
                topUser = rs.getString("callsign");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return topUser;
    }
    
    public int getTopUserQSO() {
        int tQSO = 0;
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select users.callsign, count(*) as count from qsos " +
                                                    "inner join users on qsos.user_id=users.user_id " +
                                                    "group by users.callsign order by count desc");
            if (rs.first()) {
                tQSO = rs.getInt(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tQSO;
    }
    
    public int getNumQSOinLastHour() {
        int numQsos = 0;
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar c = Calendar.getInstance();
        //This is for 1 (One) hour ago but the QSOs are logged according to GMT time (we are +2hrs)
        c.add(Calendar.HOUR_OF_DAY, -3);
        java.util.Date date = c.getTime();
        String query = "select count(*) as count from qsos where start_time > '" + formatter.format(date) + "'";
        //System.out.println("Query = " + query);
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            if (rs.first()) {
                numQsos = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return numQsos;
    }
}
