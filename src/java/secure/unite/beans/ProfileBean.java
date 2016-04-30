/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secure.unite.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Heather
 */

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import unite.login.beans.DataConnect;
import java.util.*;
import secure.unite.entities.User;

@ManagedBean(name = "profilebean")
@ViewScoped
public class ProfileBean implements Serializable{
    private List<User> ulist;
    private User user;
    
    @PostConstruct
    public void init(){      
      ulist = new ArrayList<User>();
    }
    public User getUserInfo(){
                
        String email = SessionState.getUserName();
        PreparedStatement ps;
        Connection con = null;
        ResultSet rs = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DataConnect.getConnection();
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/record", "root", "root");
            ps = con.prepareStatement("Select * from UserInformationTable where U_Email = ?");
            ps.setString(1, email);
            rs= ps.executeQuery(); 
            while (rs.next()){
                user = new User();  
                user.setuId(rs.getInt("U_ID"));
                user.setuEmail(rs.getString("U_Email"));
                user.setuFirst(rs.getString("U_FName"));
                user.setuLast(rs.getString("U_LName"));
                user.setuPass(rs.getString("U_Password"));
                user.setuPhone(rs.getString("U_Phone"));
                user.setuAddress(rs.getString("U_Address"));
                user.setuState(rs.getString("U_State"));
                user.setuZip(rs.getInt("U_Zip"));
                user.setuAdmin(rs.getInt("U_GO_Admin"));
                user.setuGname(rs.getString("U_GO_Group_Name"));
                user.setuCdate(rs.getTimestamp("U_Create_Date"));
                user.setuRpoints(rs.getInt("U_Reward_Points"));
                user.setuRlevel(rs.getInt("U_Reward_Level"));
                user.setuDisabled(rs.getInt("U_Disabled"));
                user.setuGid(rs.getInt("U_V_Group_ID"));
                user.setuFirstTime(rs.getInt("U_First_Time"));
               
            } 
        }
        catch(Exception e){
             //e.printStackTrace();
             System.out.println("Get list error--> " + e.getMessage());
        }
        finally{
            try{
                DataConnect.close(con);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
      return user;          
        
    }  
        
}