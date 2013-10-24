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
import java.util.List;
import za.co.zs6erb.model.StationClass;
import za.co.zs6erb.util.DbUtil;

/**
 *
 * @author SeanR
 */
public class StationClassDao {

    private Connection connection;

    public StationClassDao() {
        connection = DbUtil.getConnection();
    }

    public void addStationClass(StationClass pc) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into stationClasses(className, classLetter) values (?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, pc.getclassName());
            preparedStatement.setString(2, pc.getclassLetter());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStationClass(int pc) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from stationClasses where stationClass_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, pc);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStationClass(StationClass pc) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update stationClasses set classLetter=?, className=? where stationClass_id=?");
            // Parameters start with 1
            preparedStatement.setString(1, pc.getclassLetter());
            preparedStatement.setString(2, pc.getclassName());
            preparedStatement.setInt(3, pc.getID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<StationClass> getAllStationClasses() {
        List<StationClass> pcList = new ArrayList<StationClass>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from stationClasses order by stationClass_id");
            while (rs.next()) {
                StationClass lSc = new StationClass();
                lSc.setID(rs.getInt("stationClass_id"));
                lSc.setclassName(rs.getString("className"));
                lSc.setclassLetter(rs.getString("classLetter"));
                pcList.add(lSc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pcList;
    }

    public StationClass getSCById(int sc_id) {
        StationClass lSc = new StationClass();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from stationClasses where stationClass_id=?");
            preparedStatement.setInt(1, sc_id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                lSc.setID(rs.getInt("stationClasses_id"));
                lSc.setclassName(rs.getString("className"));
                lSc.setclassLetter(rs.getString("classLetter"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lSc;
    }    
}
