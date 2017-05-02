/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.progetto.servlet;

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
public class Login extends HttpServlet {

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
            
            if (((User) request.getAttribute("loggedUser")).profiloCompleto()) {
                
                // redirect sulla bacheca
                request.getRequestDispatcher("Bacheca").forward(request, response);
            }
            else {
                
                // redirect sulla pagina profilo
                request.getRequestDispatcher("Profilo").forward(request, response);
            }
        } else {

            // Utente non autenticato 
            
            // recupero dei dati di login dal form
            String username = request.getParameter("username");
            String password = request.getParameter("pswd");

            if (username == null && password == null) {
                
                // form di login
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            else {

                // tentativo di login
                User userLogged = this.login(username, password);

                if (userLogged != null) {

                    // login utente
                    session = request.getSession();
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("userID", userLogged.getId());
                    request.getRequestDispatcher("Login").forward(request, response);
                }
                else {

                    // dati errati
                    request.setAttribute("datiErrati", true);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
        }
    }
    
    /**
     * Permette di ottenere l'utente con username e password specificati
     * @param username username dell'utente richiesto
     * @param password password dell'utente richiesto
     * @return User dell'utente
     */
    private User login(String username, String password) {

        for (User user : UserFactory.getInstance().getListaUser()) {

            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
                return null;
            }
        }
        return null;
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
