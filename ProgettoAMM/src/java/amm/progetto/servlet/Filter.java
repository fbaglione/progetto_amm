/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.progetto.servlet;

import amm.progetto.Classi.User;
import amm.progetto.Classi.UserFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DatrhiilPC
 */
public class Filter extends HttpServlet {

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

        String command = request.getParameter("cmd");
        if (command != null) 
        {
            // Ricava l'operazione da eseguire
            if (command.equals("search")) 
            {
                // Esegue la ricerca
                List<User> listaUtenti = UserFactory.getInstance().getListaUser(request.getParameter("q"));
                //(request.getParameter("nomeGattoCercato"));
                
                // Caricamento lista
                request.setAttribute("listaUtenti", listaUtenti);
                
                // Impostazioni dell'header per la restituzione di json
                response.setContentType("application/json");
                response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
                response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
                
                // Caricamento jsp per la formattazione json
                request.getRequestDispatcher("filter.jsp").forward(request, response);
            }
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
