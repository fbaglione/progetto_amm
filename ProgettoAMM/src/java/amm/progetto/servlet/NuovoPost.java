/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.progetto.servlet;

import amm.progetto.Classi.Post;
import amm.progetto.Classi.PostFactory;
import amm.progetto.Classi.User;
import amm.progetto.Classi.UserFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DatrhiilPC
 */
public class NuovoPost extends HttpServlet {

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
            
            // Controllo sicurezza
            // se l'autore del post non è lo stesso di quello loggato attualmente
            if(((User) request.getAttribute("loggedUser")).getId() == Integer.parseInt(request.getParameter("autore"))) {
                
                // Utente autorizzato all'invio
                
                // Popolamento dati del post e dell'autore
                User user = UserFactory.getInstance().getUserById(Integer.parseInt(request.getParameter("userID")));
                
                Post post = new Post();
                post.setContent((String) request.getParameter("allegatoPost"));
                post.setText((String) request.getParameter("testoPost"));
                post.setAutore(UserFactory.getInstance().getUserById(Integer.parseInt(request.getParameter("autore"))));
                post.setPostType(Post.Type.valueOf((String) request.getParameter("tipologiaPost")));

                request.setAttribute("post", post);
                request.setAttribute("user", user);
                
                // Pagina di conferma
                if(request.getParameter("conferma") == null) {
                
                    // redirect pagina di conferma
                    request.getRequestDispatcher("confermaPost.jsp").forward(request, response);
                }
                else {
                    
                    // update effettivo del post
                    PostFactory.getInstance().addPost(post, user);
                    
                    // redirect bacheca
                    request.getRequestDispatcher("Bacheca?user=" + user.getId()).forward(request, response);
                }
            }
            else {
                            
                // Sovrapposizione tra una nuova sessione ed una pagina
                // di una vecchia sessione
                response.getWriter().println("<h1> Accesso Negato! </h1>");
                response.getWriter().println("Si sta tentando di inviare un post preparato da un utente precedentemente loggato.");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        else {
            
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
