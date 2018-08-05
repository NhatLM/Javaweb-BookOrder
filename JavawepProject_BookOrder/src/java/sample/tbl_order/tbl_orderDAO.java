/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_order;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.Utils.dtbUtils;
import java.util.Date;

/**
 *
 * @author DEll
 */
public class tbl_orderDAO implements Serializable{
    List<tbl_orderDTO> list;

    public List<tbl_orderDTO> getList() {
        return list;
    }
    
    public void searchOrder(boolean isDelivered, Date fromDate,Date toDate)
    throws NamingException,SQLException,ParseException{
        Connection con=null;
        PreparedStatement stm = null;
        ResultSet rs=null;
        try{
            con=dtbUtils.makeConnection();
            if(con!=null){
                //convert string to sql date
                SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
                java.sql.Date sqlDateFrom=new java.sql.Date(fromDate.getTime());
                java.sql.Date sqlDateTo=new java.sql.Date(toDate.getTime());
                
                //query part
                String sql="Select * From tbl_order Where isDeliver = ? "
                        + "And orderDate Between ? And ?";
                stm=con.prepareStatement(sql);
                stm.setBoolean(1, isDelivered);
                stm.setDate(2, sqlDateFrom);
                stm.setDate(3,sqlDateTo);
                rs=stm.executeQuery();
                while(rs.next()){
                    String orderId=rs.getString("orderID");
                    String orderDate = format.format(rs.getDate("orderDate"));
                    String customerID = rs.getString("custID");
                    float totalPrice =rs.getFloat("total");
                    boolean isDelivere = rs.getBoolean("isDeliver");
                    String reason = rs.getString("reason");
                    tbl_orderDTO dto = new tbl_orderDTO(orderId, orderDate, customerID, totalPrice, isDelivered,reason);
                    
                    if(this.list == null){
                        list = new ArrayList<>();
                    }
                    list.add(dto);
                }
            }
        }
        finally{
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
            if(con!=null) con.close();
        }
    }
    
    public boolean updateOrder(String id, boolean isDeliver)
            throws NamingException,SQLException{
        Connection con=null;
        PreparedStatement stm = null;
        
        try{
            con=dtbUtils.makeConnection();
            if(con!=null){
                String sql="Update tbl_order Set isDeliver = ?"
                        + " Where orderID = ? ";
                stm=con.prepareStatement(sql);
                stm.setBoolean(1, isDeliver);
                stm.setString(2, id);
                int rs = stm.executeUpdate();
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
