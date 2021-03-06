/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import za.co.zs6erb.model.QSO;
import za.co.zs6erb.util.DbUtil;

/**
 *
 * @author SeanR
 */
public class QSODao {

    private Connection connection;

    public QSODao() {
        connection = DbUtil.getConnection();
    }

    public void addQSO(QSO qso) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into qsos(user_id, start_time, end_time, callsign, name, location, freq, band_id, mode_id, power_id, local_rst, remote_rst, accOther, contest_id) "
                            + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, qso.getuserID());
            preparedStatement.setTimestamp(2, qso.getstartTime());
            preparedStatement.setTimestamp(3, qso.getendTime());
            preparedStatement.setString(4, qso.getcallSign());
            preparedStatement.setString(5, qso.getname());
            preparedStatement.setString(6, qso.getlocation());
            preparedStatement.setString(7, qso.getfreq());
            preparedStatement.setInt(8, qso.getbandId());
            preparedStatement.setInt(9, qso.getmodeId());
            preparedStatement.setInt(10, qso.getpowerId());
            preparedStatement.setInt(11, qso.getlocalRST());
            preparedStatement.setInt(12, qso.getremoteRST());
            preparedStatement.setString(13, qso.getnotes());
            preparedStatement.setInt(14, qso.getcontestID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteQSO(int qso_id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from qsos where qso_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, qso_id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateQSO(QSO qso) {
//        try {
//            PreparedStatement preparedStatement = connection
//                    .prepareStatement("update band set band=? where band_id=?");
//            // Parameters start with 1
//            preparedStatement.setString(1, band.getband());
//            preparedStatement.setInt(2, band.getID());
//            preparedStatement.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public List<QSO> getAllQSOs() {
        List<QSO> qsoList = new ArrayList<QSO>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from qsos inner join users on qsos.user_id=users.user_id order by start_time desc");
            while (rs.next()) {
                QSO lQSO = new QSO();
                lQSO.setID(rs.getInt("qso_id"));
                lQSO.setuserID(rs.getInt("user_id"));
                lQSO.setstartTime(rs.getTimestamp("start_time"));
                lQSO.setendTime(rs.getTimestamp("end_time"));
                lQSO.setcallSign(rs.getString("callsign"));
                lQSO.setname(rs.getString("name"));
                lQSO.setlocation(rs.getString("location"));
                lQSO.setfreq(rs.getString("freq"));
                lQSO.setbandId(rs.getInt("band_id"));
                lQSO.setmodeId(rs.getInt("mode_id"));
                lQSO.setlocalRST(rs.getInt("local_rst"));
                lQSO.setremoteRST(rs.getInt("remote_rst"));
                lQSO.setnotes(rs.getString("accOther"));
                lQSO.setcontestID(rs.getInt("contest_id"));
                qsoList.add(lQSO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return qsoList;
    }

    public List<String> getAllCallSigns() {
        List<String> callSignList = new ArrayList<String>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select distinct callsign from qsos");
            while (rs.next()) {
                callSignList.add(rs.getString("callsign"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return callSignList;
    }

    //All Callsigns for a specific contest
    public List<String> getAllCallSigns(int contestId) {
        List<String> callSignList = new ArrayList<String>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select distinct callsign from qsos where contest_id=" + contestId);
            while (rs.next()) {
                callSignList.add(rs.getString("callsign"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return callSignList;
    }

    public HashMap<String, ArrayList<Integer>> getAllCS_Modes() {
        HashMap<String, ArrayList<Integer>> cs_mode = new HashMap<String, ArrayList<Integer>>();
        try {
            Statement statement = connection.createStatement();
            ArrayList<String> lCallsigns = getDistinctCallSigns();
            for (String cs : lCallsigns) {
                ArrayList<Integer> alBands = new ArrayList<Integer>();
                ResultSet rs = statement.executeQuery("select distinct mode_id from qsos where callsign = \"" + cs + "\"");
                while (rs.next()) {
                    alBands.add(rs.getInt("mode_id"));
                }
                cs_mode.put(cs, alBands);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cs_mode;
    }

    //All Callsigns for a specific contest
    public HashMap<String, ArrayList<Integer>> getAllCS_Modes(int contestId) {
        HashMap<String, ArrayList<Integer>> cs_mode = new HashMap<String, ArrayList<Integer>>();
        try {
            Statement statement = connection.createStatement();
            ArrayList<String> lCallsigns = getDistinctCallSigns();
            for (String cs : lCallsigns) {
                ArrayList<Integer> alBands = new ArrayList<Integer>();
                ResultSet rs = statement.executeQuery("select distinct mode_id from qsos where callsign = \"" + cs + "\" where contest_id=" + contestId);
                while (rs.next()) {
                    alBands.add(rs.getInt("mode_id"));
                }
                cs_mode.put(cs, alBands);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return cs_mode;
    }

    //Get all bands for callsigns
    public HashMap<String, ArrayList<Integer>> getAllCS_Bands() {
        HashMap<String, ArrayList<Integer>> cs_band = new HashMap<String, ArrayList<Integer>>();

        try {
            Statement statement = connection.createStatement();
            ArrayList<String> lCallsigns = getDistinctCallSigns();
            for (String cs : lCallsigns) {
                ArrayList<Integer> alBands = new ArrayList<Integer>();
                ResultSet rs = statement.executeQuery("select distinct band_id from qsos where callsign = \"" + cs + "\"");
                while (rs.next()) {
                    alBands.add(rs.getInt("band_id"));
                }
                cs_band.put(cs, alBands);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cs_band;
    }

    //All Callsigns for a specific contest
    public HashMap<String, ArrayList<Integer>> getAllCS_Bands(int contestId) {
        HashMap<String, ArrayList<Integer>> cs_band = new HashMap<String, ArrayList<Integer>>();

        try {
            Statement statement = connection.createStatement();
            ArrayList<String> lCallsigns = getDistinctCallSigns();
            for (String cs : lCallsigns) {
                ArrayList<Integer> alBands = new ArrayList<Integer>();
                ResultSet rs = statement.executeQuery("select distinct band_id from qsos where callsign = \"" + cs + "\" where contest_id=" + contestId);
                while (rs.next()) {
                    alBands.add(rs.getInt("band_id"));
                }
                cs_band.put(cs, alBands);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cs_band;
    }
    
    //Convenience method to return an array of all distinct callsigns
    public ArrayList<String> getDistinctCallSigns() {
        ArrayList<String> al_cs = new ArrayList<String>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs_callsign = statement.executeQuery("select distinct callsign from qsos");
            while (rs_callsign.next()) {
                al_cs.add(rs_callsign.getString("callsign"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return al_cs;
    }
}
