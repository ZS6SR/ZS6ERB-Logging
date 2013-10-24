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
import za.co.zs6erb.model.ProvinceCode;
import za.co.zs6erb.util.DbUtil;

/**
 *
 * @author SeanR
 */
public class ProvinceCodeDao {

    private Connection connection;

    public ProvinceCodeDao() {
        connection = DbUtil.getConnection();
    }

    public void addProvinceCode(ProvinceCode pc) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into province_codes(province_name, province_code) values (?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, pc.getprovinceName());
            preparedStatement.setString(2, pc.getprovinceCode());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProvinceCode(int pc) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from province_codes where province_code_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, pc);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProvinceCode(ProvinceCode pc) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update province_codes set province_code=?, province_name=? where province_code_id=?");
            // Parameters start with 1
            preparedStatement.setString(1, pc.getprovinceCode());
            preparedStatement.setString(2, pc.getprovinceName());
            preparedStatement.setInt(3, pc.getID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProvinceCode> getAllProvinceCode() {
        List<ProvinceCode> pcList = new ArrayList<ProvinceCode>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from province_codes order by province_code_id");
            while (rs.next()) {
                ProvinceCode lPc = new ProvinceCode();
                lPc.setID(rs.getInt("province_code_id"));
                lPc.setprovinceName(rs.getString("province_name"));
                lPc.setprovinceCode(rs.getString("province_code"));
                pcList.add(lPc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pcList;
    }

    public ProvinceCode getPCById(int pc_id) {
        ProvinceCode lPc = new ProvinceCode();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from province_codes where province_code_id=?");
            preparedStatement.setInt(1, pc_id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                lPc.setID(rs.getInt("province_code_id"));
                lPc.setprovinceName(rs.getString("province_name"));
                lPc.setprovinceCode(rs.getString("province_code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lPc;
    }    
}
