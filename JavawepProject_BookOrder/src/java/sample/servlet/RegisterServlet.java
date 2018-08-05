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
import sample.Utils.ErrorCheck;
import sample.customer.customerDAO;

/**
 *
 * @author DEll
 */
public class RegisterServlet extends HttpServlet {
    private final String validPage="login.html";
    private final String invalidPage = "register.jsp";
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
        Boolean error=false;
        ErrorCheck errorCheck = new ErrorCheck();
        try {
           String userId=request.getParameter("username");
           String password = request.getParameter("password");
           String name = request.getParameter("custName");
           String middleName = request.getParameter("middlename");
           String lastName = request.getParameter("lastname");
           String address = request.getParameter("address");
           String phone = request.getParameter("phonenum");
           
           if(userId.trim().length()<1 || userId.length()>10){
               error=true;
               errorCheck.setUserIdLength("Your user name must have 1 - 10 chars");
           }
           if(password.trim().length()<1 || password.length()>30){
               error=true;
               errorCheck.setPasswordLength("Your password must have 1 - 30 chars");
           }
           if(name.trim().length()<1 || name.length()>15 || 
                   errorCheck.checkContainNum(name)){
               error=true;
               errorCheck.setNameLength("Your input must have 1 - 15 chars"
                       + " and no digits");
           }
           if(middleName.trim().length()<1 || middleName.length()>15 ||
                   errorCheck.checkContainNum(middleName)){
               error=true;
               errorCheck.setMiddleNameLength("Your input must have 1 - 15 chars"
                       + " and no digits");
           }
           if(lastName.trim().length()<1 || lastName.length()>15 ||
                   errorCheck.checkContainNum(lastName)){
               error=true;
               errorCheck.setLastNameLength("Your input must have 1 - 15 chars"
                       + " and no digits");
           }
           if(address.trim().length()<1 || address.length()>250){
               error=true;
               errorCheck.setAddressLength("Your address must have 1 - 250 chars");
           }
           if(phone.trim().length()<8 || phone.length()>11 || 
                   !errorCheck.checkValidPhone(phone)){
               error=true;
               errorCheck.setPhoneNumber("Your phone number must have 8 - 11 numbers");
           }
           
           if(error == false){
               customerDAO dao = new customerDAO();
               boolean result = dao.insertNewCustomer(userId, password, name, middleName, lastName, address, phone);
               if(result) {
                   url=validPage;
               }
           }
           else{
               request.setAttribute("error",errorCheck);
           }
               
        }
        catch(NamingException e){
            log("RegisterServlet - NamingException: "+e.getMessage());
        }
        catch(SQLException e){
            log("RegisterServlet - SQLException: "+e.getMessage());
            if(e.getMessage().contains("duplicate")){
                errorCheck.setDuplicateId("Duplicate Id");
                request.setAttribute("error",errorCheck);
            }
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
