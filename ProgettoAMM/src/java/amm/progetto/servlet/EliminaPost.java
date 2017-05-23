/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.progetto.servlet;

import amm.progetto.Classi.GroupFactory;
import amm.progetto.Classi.Post;
import amm.progetto.Classi.PostFactory;
import amm.progetto.Classi.User;
import amm.progetto.Classi.UserFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DatrhiilPC
 */
public class EliminaPost extends HttpServlet {

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
        
        // Recupero della sessione
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("loggedIn") != null && session.getAttribute("loggedIn").equals(true)) {

            // Utente autenticato
            request.setAttribute("loggedUser", (User) UserFactory.getInstance().getUserById((int) session.getAttribute("userID")));
            // privilegi admin
            request.setAttribute("adminPowers", ((User)request.getAttribute("loggedUser")).getId() == 0);
            
            // Recupero informazioni
            if (request.getParameter("post") != null) {
                
                Post post = PostFactory.getInstance().getPostById(Integer.parseInt(request.getParameter("post")));
                
                // Controllo privilegi
                // accesso consentito all'amministratore del sito o del gruppo
                if(PostFactory.getInstance().isAdministrator(post.getId(), (User) request.getAttribute("loggedUser")) || (boolean)request.getAttribute("adminPowers")) {
                    
                    // Eliminazione post
                    PostFactory.getInstance().deletePost(Integer.parseInt(request.getParameter("post")));
                }
                else {
                    
                    // Tentativo di accesso senza privilegi
                    response.getWriter().println("<h1> Accesso Negato! </h1>");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            }

            // redirect bacheca
            request.getRequestDispatcher("Bacheca").forward(request, response);
        } else {

            // Utente non autenticato
            response.getWriter().println("<h1> Accesso Negato! </h1>");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
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
