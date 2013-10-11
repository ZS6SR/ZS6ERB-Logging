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
import za.co.zs6erb.model.Power;
import za.co.zs6erb.util.DbUtil;

/**
 *
 * @author SeanR
 */
public class PowerDao {

    private Connection connection;

    public PowerDao() {
        connection = DbUtil.getConnection();
    }

    public void addPower(Power power) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into power(power) values (?)");
            // Parameters start with 1
            preparedStatement.setString(1, power.getpower());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePower(int power_id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from power where power_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, power_id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePower(Power power) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update power set power=? where power_id=?");
            // Parameters start with 1
            preparedStatement.setString(1, power.getpower());
            preparedStatement.setInt(2, power.getID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Power> getAllPower() {
        List<Power> usrList = new ArrayList<Power>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from power order by power");
            while (rs.next()) {
                Power lPower = new Power();
                lPower.setID(rs.getInt("power_id"));
                lPower.setpower(rs.getString("power"));
                usrList.add(lPower);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usrList;
    }

    public Power getPowerById(int power_id) {
        Power lPower = new Power();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from power where power_id=?");
            preparedStatement.setInt(1, power_id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                lPower.setID(rs.getInt("power_id"));
                lPower.setpower(rs.getString("power"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lPower;
    }    
}
