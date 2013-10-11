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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import za.co.zs6erb.model.Contest;
import za.co.zs6erb.util.DbUtil;

/**
 *
 * @author SeanR
 */
public class ContestDao {

    private Connection connection;

    public ContestDao() {
        connection = DbUtil.getConnection();
    }

    public void addContest(Contest cont) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into contests(contest_name, contest_startdate, contest_enddate) values (?,?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, cont.getcontestName());
            preparedStatement.setTimestamp(2, cont.getstartDate());
            preparedStatement.setTimestamp(3, cont.getendDate());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContest(int contest_id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from contests where contest_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, contest_id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateContest(Contest contest) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update contests set contestName=?, contest_startdate=?, contest_enddate=? where contest_id=?");
            // Parameters start with 1
            preparedStatement.setString(1, contest.getcontestName());
            preparedStatement.setTimestamp(2, contest.getstartDate());
            preparedStatement.setTimestamp(3, contest.getendDate());
            preparedStatement.setInt(4, contest.getID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contest> getAllContests() {
        List<Contest> contList = new ArrayList<Contest>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from contests order by contest_id");
            while (rs.next()) {
                Contest lCont = new Contest();
                lCont.setID(rs.getInt("contest_id"));
                lCont.setcontestName(rs.getString("contest_name"));
                lCont.setstartDate(rs.getTimestamp("contest_startdate"));
                lCont.setendDate(rs.getTimestamp("contest_enddate"));
                contList.add(lCont);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contList;
    }

    public Contest getContestById(int contest_id) {
        Contest lCont = new Contest();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from contests where contest_id=?");
            preparedStatement.setInt(1, contest_id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                lCont.setID(rs.getInt("contest_id"));
                lCont.setcontestName(rs.getString("contestName"));
                lCont.setstartDate(rs.getTimestamp("contest_startdate"));
                lCont.setendDate(rs.getTimestamp("contest_enddate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lCont;
    }
    
    public int getcurrentContestID() {
        
        int ContestID = 0;
        
        
        java.util.Date date= new java.util.Date();
        Timestamp tNow = new Timestamp(date.getTime());
        
        
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select contest_id from contests where contest_startDate < ? and contest_enddate > ?");
            preparedStatement.setTimestamp(1, tNow);
            preparedStatement.setTimestamp(2, tNow);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                ContestID = rs.getInt("contest_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ContestID;
    }
}
