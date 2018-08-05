/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.customer.customerDAO;
import sample.customer.customerDTO;
import sample.tbl_orderDetail.tbl_orderDetailDAO;
import sample.tbl_orderDetail.tbl_orderDetailDTO;

/**
 *
 * @author DEll
 */
public class OrderDetailServlet extends HttpServlet {
    private final String invalidPage="invalidPage.html";
    private final String validPage="orderDetail.jsp";
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
        String url=invalidPage;
        try {
           String orderId=request.getParameter("orderId");
           String customerId = request.getParameter("customerId");
           String orderDate = request.getParameter("orderDate");
           String fromDate = request.getParameter("fromDate");
           String toDate = request.getParameter("toDate");
           String isDeliver = request.getParameter("isDeliver");
           
           customerDAO custDAO=new customerDAO();
           customerDTO customer=custDAO.findCustomer(customerId);
           
           tbl_orderDetailDAO orderDAO=new tbl_orderDetailDAO();
           orderDAO.searchOrder(orderId);
           List<tbl_orderDetailDTO> orderlist=orderDAO.getOrderlist();
           
           url=validPage;
           request.setAttribute("customer",customer);
           request.setAttribute("orderDetail",orderlist);
           request.setAttribute("orderID",orderId);
           request.setAttribute("orderDate",orderDate);
           request.setAttribute("fromDate",fromDate);
           request.setAttribute("toDate",toDate);
           request.setAttribute("isDeliver",isDeliver);
        }
        catch(NamingException e){
            log("orderDetailServlet - NamingException: "+e.getMessage());
        }
        catch(SQLException e){
            log("orderDetailServlet - SQLException: "+e.getMessage());
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
