/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author SeanR
 */
public class User {
    private int user_id;
    private String callsign;
    private String firstname;
    private String lastname;
    private String pwd;
    private int role;
    private Date userSince;
    
    public int getID() { return user_id; }
    public void setID(int user_id) { this.user_id = user_id; }
    
    public String getcallSign() { return callsign; }
    public void setcallSign(String callsign) { this.callsign = callsign; }

    public String getfirstName() { return firstname; }
    public void setfirstName(String firstname) { this.firstname = firstname; }

    public String getlastName() { return lastname; }
    public void setlastName(String lastname) { this.lastname = lastname; }
    
    public String getpwd() { return pwd; }
    public void setpwd(String pwd) { this.pwd = pwd; }
    
    public int getrole() { return role; }
    public void setrole(int role) { this.role = role; }
    
    public Date getuserSince() { return userSince; }
    public void setuserSince(Date userSince) { this.userSince = userSince; }
    
    public String getusString() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return dt.format(userSince);
    }
    
    @Override
    public String toString() {
        return "Users [user_id=" + user_id + ", Callsign=" + callsign + ", First Name=" + firstname + ", Last Name=" + lastname + 
                " pwd=" + pwd + " role=" + role + " userSince=" + userSince + "]";
    } 
}
