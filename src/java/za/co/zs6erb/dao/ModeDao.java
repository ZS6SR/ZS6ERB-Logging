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
import za.co.zs6erb.model.Mode;
import za.co.zs6erb.util.DbUtil;

/**
 *
 * @author SeanR
 */
public class ModeDao {

    private Connection connection;

    public ModeDao() {
        connection = DbUtil.getConnection();
    }

    public void addMode(Mode m) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into mode(mode) values (?)");
            // Parameters start with 1
            preparedStatement.setString(1, m.getmode());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMode(int mId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from mode where mode_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, mId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMode(Mode m) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update mode set mode=? where mode_id=?");
            // Parameters start with 1
            preparedStatement.setString(1, m.getmode());
            preparedStatement.setInt(2, m.getID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Mode> getAllModes() {
        List<Mode> modeList = new ArrayList<Mode>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from mode order by mode_id");
            while (rs.next()) {
                Mode m = new Mode();
                m.setID(rs.getInt("mode_id"));
                m.setmode(rs.getString("mode"));
                modeList.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modeList;
    }

    public Mode getModeById(int mid) {
        Mode m = new Mode();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from mode where mode_id=?");
            preparedStatement.setInt(1, mid);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                m.setID(rs.getInt("mode_id"));
                m.setmode(rs.getString("mode"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return m;
    }    
}
