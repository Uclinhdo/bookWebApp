/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.bookwebapp.controller;

import edu.wctc.bookwebapp.model.Author;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.wctc.bookwebapp.model.AuthorService;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author linhdo
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {
    private static final String ALIAS_PAGE = "/listAuthors.jsp"; 
    private static final String ERROR = "Error: Data could be found!";
    private static final String ACTION_LIST = "list";
    private static final String ACTION_PAR = "action";
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
        String action = request.getParameter(ACTION_PAR);
        String destination = ALIAS_PAGE;
        try {
            AuthorService au = new AuthorService();
            if(action.equals(ACTION_LIST)){
                List <Author> authors = au.getAuthorList();
                request.setAttribute("authors", authors);
                destination = ALIAS_PAGE;
            }else{
                request.setAttribute("errMsg", ERROR);
            }
        }catch(Exception ex){
            request.setAttribute("errMsg", ex.getCause().getMessage());
        }
        RequestDispatcher view = getServletContext().getRequestDispatcher(destination);
              view.forward(request, response);
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
