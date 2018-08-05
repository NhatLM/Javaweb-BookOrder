/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.Utils.ErrorCheck;
import sample.tbl_order.tbl_orderDAO;
import sample.tbl_order.tbl_orderDTO;

/**
 *
 * @author DEll
 */
public class SearchOrderServlet extends HttpServlet {
    private final String invalidPage="searchOrder.jsp";
    private final String validPage = "orderList.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = invalidPage;
        try {
            String fromDate = request.getParameter("fromDate");
            String toDate = request.getParameter("toDate");
            boolean isDeliver=true;
            String chkDeliver = request.getParameter("isDelivered");
            if(chkDeliver == null) isDeliver=false;
            
            if(fromDate.trim().length()>0 && toDate.trim().length()>0){
                //compare 2 days
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date datefrom=format.parse(fromDate);
                Date dateto=format.parse(toDate);
                //if the days are valid
                if(dateto.after(datefrom)){
                    tbl_orderDAO dao = new tbl_orderDAO();
                    dao.searchOrder(isDeliver,datefrom,dateto);
                    List<tbl_orderDTO> list=dao.getList();
                    request.setAttribute("searchResult",list);
                    request.setAttribute("toDate",toDate);
                    request.setAttribute("fromDate",fromDate);
                    request.setAttribute("Delivered",isDeliver);
                    url = validPage;
                }
            }
        }
        catch(ParseException e){
            log("SearchOrderServlet - ParseException: "+e.getMessage());
            ErrorCheck error = new ErrorCheck();
            error.setDateError("Your input must follow pattern: dd/mm/yyyy");
            url=invalidPage;
            request.setAttribute("error",error);
        }
        catch(NamingException e){
            log("SearchOrderServlet - NamingException: "+e.getMessage());
        }
        catch(SQLException e){
            log("SearchOrderServlet - SQLException: "+e.getMessage());
        }
        finally{
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
