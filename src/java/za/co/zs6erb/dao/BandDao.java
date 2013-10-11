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
import za.co.zs6erb.model.Band;
import za.co.zs6erb.util.DbUtil;

/**
 *
 * @author SeanR
 */
public class BandDao {

    private Connection connection;

    public BandDao() {
        connection = DbUtil.getConnection();
    }

    public void addBand(Band band) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into band(band, start_range, end_range) values (?,?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, band.getband());
            preparedStatement.setBigDecimal(2, band.getStartBand());
            preparedStatement.setBigDecimal(3, band.getEndBand());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBand(int band_id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from band where band_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, band_id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBand(Band band) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update band set band=?, start_range=?, end_range=? where band_id=?");
            // Parameters start with 1
            preparedStatement.setString(1, band.getband());
            preparedStatement.setBigDecimal(2, band.getStartBand());
            preparedStatement.setBigDecimal(3, band.getEndBand());
            preparedStatement.setInt(4, band.getID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Band> getAllBands() {
        List<Band> bandList = new ArrayList<Band>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from band order by band_id");
            while (rs.next()) {
                Band lBand = new Band();
                lBand.setID(rs.getInt("band_id"));
                lBand.setband(rs.getString("band"));
                lBand.setStartBand(rs.getBigDecimal("start_range"));
                lBand.setEndBand(rs.getBigDecimal("end_range"));
                bandList.add(lBand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bandList;
    }

    public Band getBandById(int band_id) {
        Band lBand = new Band();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from band where band_id=?");
            preparedStatement.setInt(1, band_id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                lBand.setID(rs.getInt("band_id"));
                lBand.setband(rs.getString("band"));
                lBand.setStartBand(rs.getBigDecimal("start_range"));
                lBand.setEndBand(rs.getBigDecimal("end_range"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lBand;
    }    
}
