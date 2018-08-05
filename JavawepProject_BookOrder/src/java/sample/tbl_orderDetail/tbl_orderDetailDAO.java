/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import sample.Utils.dtbUtils;
import sample.tbl_book.tbl_bookDAO;

/**
 *
 * @author DEll
 */
public class tbl_orderDetailDAO implements Serializable{
    List<tbl_orderDetailDTO> orderlist;

    public List<tbl_orderDetailDTO> getOrderlist() {
        return orderlist;
    }
    
    public void searchOrder(String id) throws NamingException,SQLException{
        Connection con=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try{
            con=dtbUtils.makeConnection();
            if(con!=null){
                String sql="Select * From tbl_orderDetail Where orderID = ?";
                stm=con.prepareStatement(sql);
                stm.setString(1, id);
                rs=stm.executeQuery();
                while(rs.next()){
                    //get item name
                    String productId = rs.getString("productID");
                    tbl_bookDAO dao = new tbl_bookDAO();
                    String productName = dao.getBookname(productId);
                    
                    int quantity = rs.getInt("quantity");
                    float unitPrice = rs.getFloat("unitPrice");
                    float totalPrice = rs.getFloat("total");
                    String orderId = rs.getString("orderID");
                    
                    tbl_orderDetailDTO dto=new tbl_orderDetailDTO(productName, quantity, unitPrice, totalPrice, orderId);
                    
                    if(this.orderlist == null){
                        this.orderlist = new ArrayList<>();
                    }
                    orderlist.add(dto);
                }
            }
        }
        finally{
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
            if(con!=null) con.close();
        }
    }
}
