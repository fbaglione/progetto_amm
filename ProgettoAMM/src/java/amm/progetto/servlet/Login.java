/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.progetto.servlet;

import amm.progetto.Classi.PostFactory;
import amm.progetto.Classi.User;
import amm.progetto.Classi.UserFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DatrhiilPC
 */
@WebServlet(loadOnStartup = 0)
public class Login extends HttpServlet {

    // Variabili DB
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";
    private static final String DB_USER = "administrator";
    private static final String DB_PASS = "pass";
    
    @Override
    public void init() {

        String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_CLEAN_PATH + ";upgrade=true";

        // Controllo presenza libreria per JDBC
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Inizilizzazione factory
        UserFactory.getInstance().setConnectionString(dbConnection);
        UserFactory.getInstance().setConnectionUser(DB_USER);
        UserFactory.getInstance().setConnectionPassword(DB_PASS);
        
        PostFactory.getInstance().setConnectionString(dbConnection);
        PostFactory.getInstance().setConnectionUser(DB_USER);
        PostFactory.getInstance().setConnectionPassword(DB_PASS);
    }

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
            } else {

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
            } else {

                // tentativo di login
                User userLogged = UserFactory.getInstance().login(username, password);

                if (userLogged != null) {

                    // login utente
                    session = request.getSession();
                    session.setAttribute("loggedIn", true);
                    session.setAttribute("userID", userLogged.getId());
                    request.getRequestDispatcher("Login").forward(request, response);
                } else {

                    // dati errati
                    request.setAttribute("datiErrati", true);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
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
