/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_book;

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
public class tbl_bookDAO implements Serializable{
    public String getBookname(String id) throws NamingException,SQLException{
        Connection con=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try{
            con=dtbUtils.makeConnection();
            String bookname;
            if(con!=null){
                String sql="Select * From tbl_book Where bookID = ?";
                stm=con.prepareStatement(sql);
                stm.setString(1, id);
                rs=stm.executeQuery();
                while(rs.next()){
                    bookname=rs.getString("title");
                    return bookname;
                }
            }
        }
        finally{
            if(rs!=null) rs.close();
            if(stm!=null) stm.close();
            if(con!=null) con.close();
        }
        return null;
    }
}
