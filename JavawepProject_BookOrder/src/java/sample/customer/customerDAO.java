/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.customer;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import sample.Utils.dtbUtils;

/**
 *
 * @author DEll
 */
public class customerDAO implements Serializable{
    public boolean checkLogin(String username, String password)
        throws NamingException,SQLException{
        Connection con=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try{
            con=dtbUtils.makeConnection();
            if(con!=null){
                String sql="Select * From customer Where custID = ? And password = ?";
                stm=con.prepareStatement(sql);
                stm.setString(1,username);
                stm.setString(2,password);
                rs=stm.executeQuery();
                if(rs.next()) return true;
            }
        }
        finally{
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
            if(con!=null) con.close();
        }
        return false;
    }
    
    public customerDTO findCustomer(String id) throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        customerDTO customer=null;
        try{
            con=dtbUtils.makeConnection();
            if(con!=null){
                String sql="Select * From customer Where custID = ?";
                stm=con.prepareStatement(sql);
                stm.setString(1,id);
                rs=stm.executeQuery();
                while(rs.next()){
                    String lastname = rs.getString("lastName");
                    String middlename = rs.getString("middleName");
                    String name = rs.getString("custName");
                    String fullname=lastname+" "+middlename+" "+name;
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    customer=new customerDTO(fullname, phone, address);
                }
            }
        }
        finally{
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
            if(con!=null) con.close();
        }
        return customer;
    }
    
    public boolean insertNewCustomer(String id, String password, String name, 
            String middleName, String lastName, String address, String phone) 
            throws NamingException, SQLException{
        Connection con=null;
        PreparedStatement stm=null;
        try{
            con=dtbUtils.makeConnection();
            if(con!=null){
                String sql="Insert Into customer Values(?,?,?,?,?,?,?,?)";
                stm=con.prepareStatement(sql);
                stm.setString(1, id);
                stm.setString(2,password);
                stm.setString(3,name);
                stm.setString(4,middleName);
                stm.setString(5,lastName);
                stm.setString(6,address);
                stm.setString(7,phone);
                stm.setInt(8, 0);
                int rs=stm.executeUpdate();
                if(rs>0) return true;
            }
        }
        finally{
            if(stm!=null) stm.close();
            if(con!=null) con.close();
        }
        return false;
    }
}
