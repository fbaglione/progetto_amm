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
public class Profilo extends HttpServlet {

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

        // Variabili della pagina
        request.setAttribute("page", "profilo");
        request.setAttribute("users", UserFactory.getInstance().getListaUser());
        request.setAttribute("groups", GroupFactory.getInstance().getListaGroup());

        // Recupero della sessione
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("loggedIn") != null && session.getAttribute("loggedIn").equals(true)) {

            // Utente autenticato
            request.setAttribute("loggedUser", (User) UserFactory.getInstance().getUserById((int) session.getAttribute("userID")));

            // utente del profilo
            User user = new User();

            // Submission del form
            if (request.getParameter("nome") != null) {

                // Controllo sicurezza sull'id del richiedente
                // check se l'utente che tenta di modificare il profilo è quello loggato
                if(((User) request.getAttribute("loggedUser")).getId() == (Integer.parseInt(request.getParameter("userID")))) {
                
                    // Submission del form
                    user.setNome((String) request.getParameter("nome"));
                    user.setCognome((String) request.getParameter("cognome"));
                    user.setDataDiNascita((String) request.getParameter("dataDiNascita"));
                    user.setFrase((String) request.getParameter("frase"));
                    user.setPassword((String) request.getParameter("pswd"));
                    user.setUrlImmagine((String) request.getParameter("urlImmagine"));
                    
                    // redirect con dati aggiornati
                    request.setAttribute("datiAggiornati", true);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("profilo.jsp").forward(request, response);
                }
                else {
                    
                    // Tentativo di modifica di un profilo non proprio
                    response.getWriter().println("<h1> Accesso Negato! </h1>");
                    response.getWriter().println("<p>Tentativo di modifica di un profilo non proprio</p>");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            }
            else {

                // Visita profilo utente loggato
                user = (User) request.getAttribute("loggedUser");
                request.setAttribute("user", user);
                request.getRequestDispatcher("profilo.jsp").forward(request, response);
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
