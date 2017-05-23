/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.progetto.servlet;

import amm.progetto.Classi.Group;
import amm.progetto.Classi.GroupFactory;
import amm.progetto.Classi.Post;
import amm.progetto.Classi.PostFactory;
import amm.progetto.Classi.User;
import amm.progetto.Classi.UserFactory;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Dathriil
 */
public class Gruppo extends HttpServlet {

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
        
        // Variabili menu
        request.setAttribute("users", UserFactory.getInstance().getListaUser());
        request.setAttribute("groups", GroupFactory.getInstance().getListaGroup());
        
        // Recupero della sessione
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("loggedIn") != null && session.getAttribute("loggedIn").equals(true)) {
        
            // Utente autenticato
            request.setAttribute("loggedUser", (User) UserFactory.getInstance().getUserById((int) session.getAttribute("userID")));
            // privilegi admin
            request.setAttribute("adminPowers", ((User)request.getAttribute("loggedUser")).getId() == 0);
            
            // caricamento del gruppo richiesto nella get
            String groupParam = request.getParameter("group");
            int groupID;

            if(groupParam != null){
                
                // Gruppo richiesto
                groupID = Integer.parseInt(groupParam);
                
                // dati gruppo bacheca
                Group group = (Group) GroupFactory.getInstance().getGroupById(groupID);
                request.setAttribute("group", group);

                // appartenenza utente loggato al gruppo
                request.setAttribute("following", GroupFactory.getInstance().belongsToGroup((User)request.getAttribute("loggedUser"), group));

                // utente attuale è admin del gruppo
                request.setAttribute("groupAdmin", group.getAdmin().equals((User)request.getAttribute("loggedUser")));
                
                // lista posts
                List<Post> posts = PostFactory.getInstance().getPostBacheca(group);
                request.setAttribute("posts", posts);

                // redirect bacheca
                request.getRequestDispatcher("group.jsp").forward(request, response);
            }
            else {
                
                //Gruppo richiesto inesistente
                response.getWriter().println("<h1> La risorsa richiesta non è disponibile! </h1>");
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
        else {
            
            // utente non autenticato
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
