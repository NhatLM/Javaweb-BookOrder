/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.tbl_order.tbl_orderDAO;

/**
 *
 * @author DEll
 */
public class UpdateOrderServlet extends HttpServlet {
    private final String fail = "error.html";
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
        String url = fail;
        try {
           String fromDate = request.getParameter("fromDate");
           String toDate = request.getParameter("toDate");
           String isDeliverStr = request.getParameter("isDeliver");
           boolean isDeliver = false;
           if(isDeliverStr.equalsIgnoreCase("true")){
               isDeliver=true;
           }
           String[] orderIds = request.getParameterValues("chkAction");
            tbl_orderDAO dao = new tbl_orderDAO();
           for(String id: orderIds){
               boolean rs = dao.updateOrder(id, !isDeliver);
           }
           url = "searchOrder?fromDate="+fromDate+"&toDate="+toDate;
        }
        catch (NamingException e){
            log("UpdateOrderServlet - NamingException: "+e.getMessage());
        }
        catch (SQLException e){
            log("UpdateOrderServlet - SQLException: "+e.getMessage());
        }
        finally{
            response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
            response.sendRedirect(url);
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
