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
import za.co.zs6erb.model.Contact;
import za.co.zs6erb.util.DbUtil;

/**
 *
 * @author SeanR
 */
public class ContactDao {

    private Connection connection;

    public ContactDao() {
        connection = DbUtil.getConnection();
    }

    public void addContact(Contact contact) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into contacts(user_id, start_time, end_time, callsign, name, location, freq, band_id, mode_id, power_id, local_rst, remote_rst, accOther) " + 
                                        "values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, contact.getuserID());
            preparedStatement.setTimestamp(2, contact.getstartTime());
            preparedStatement.setTimestamp(3, contact.getendTime());
            preparedStatement.setString(4, contact.getcallSign());
            preparedStatement.setString(5, contact.getname());
            preparedStatement.setString(6, contact.getlocation());
            preparedStatement.setString(7, contact.getfreq());
            preparedStatement.setInt(8, contact.getbandId());
            preparedStatement.setInt(9, contact.getmodeId());
            preparedStatement.setInt(10, contact.getpowerId());
            preparedStatement.setInt(11, contact.getlocalRST());
            preparedStatement.setInt(12, contact.getremoteRST());
            preparedStatement.setString(13, contact.getnotes());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteContact(int contact_id) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from contacts where contact_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, contact_id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateContact(Contact contact) {
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

    public List<Contact> getAllContacts() {
        List<Contact> conList = new ArrayList<Contact>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from contacts inner join users on contacts.user_id=users.user_id order by contact_id");
            while (rs.next()) {
                Contact lContact = new Contact();
                lContact.setID(rs.getInt("contact_id"));
                lContact.setuserID(rs.getInt("user_id"));
                lContact.setstartTime(rs.getTimestamp("start_time"));
                lContact.setendTime(rs.getTimestamp("end_time"));
                lContact.setcallSign(rs.getString("callsign"));
                lContact.setname(rs.getString("name"));
                lContact.setlocation(rs.getString("location"));
                lContact.setfreq(rs.getString("freq"));
                lContact.setbandId(rs.getInt("band_id"));
                lContact.setmodeId(rs.getInt("mode_id"));
                lContact.setlocalRST(rs.getInt("local_rst"));
                lContact.setremoteRST(rs.getInt("remote_rst"));
                lContact.setnotes(rs.getString("accOther"));
                conList.add(lContact);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conList;
    }

//    public Band getBandById(int band_id) {
//        Band lBand = new Band();
//        try {
//            PreparedStatement preparedStatement = connection.
//                    prepareStatement("select * from band where band_id=?");
//            preparedStatement.setInt(1, band_id);
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                lBand.setID(rs.getInt("band_id"));
//                lBand.setband(rs.getString("band"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return lBand;
//    }    
}
