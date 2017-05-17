package amm.progetto.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Factory degli user
 * @author DatrhiilPC
 */
public class UserFactory {

    // Stringhe per la connessione
    private String connectionString;
    private String connectionUser;
    private String connectionPassword;
    
    // Pattern Design Singleton
    private static UserFactory singleton;

    /**
     * Metodo per l'accesso alla singleton
     * @return singleton della factory
     */
    public static UserFactory getInstance() {
        if (singleton == null) {
            singleton = new UserFactory();
        }
        return singleton;
    }

    /**
     * Permette di ottenere l'utente con id specificato
     * @param id dell'utente richiesto
     * @return User con id richiesto
     */
    public User getUserById(int id) {
        
        User user = null;
        
        // Caricamento utente
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            stmt.setInt(1, id);
            
            ResultSet set = stmt.executeQuery();            
            
            if(set.next()) {
                
                // Utente trovato
                user = new User();
                user.setId(set.getInt("id"));
                user.setUsername(set.getString("username"));
                user.setPassword(set.getString("password"));
                user.setNome(set.getString("nome"));
                user.setCognome(set.getString("cognome"));
                user.setDataDiNascita(set.getDate("dataDiNascita"));
                user.setFrase(set.getString("frase"));
                user.setUrlImmagine(set.getString("urlImmagine"));
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        return user;
    }

    /**
     * @return lista degli utenti del sistema
     */
    public ArrayList<User> getListaUser() {
        
        ArrayList<User> listaUser = new ArrayList<>();
        
        // Caricamento utenti
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            Statement stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery("SELECT * FROM users");
            
            while(set.next()) {
                
                User user = new User();
                user.setId(set.getInt("id"));
                user.setUsername(set.getString("username"));
                user.setPassword(set.getString("password"));
                user.setNome(set.getString("nome"));
                user.setCognome(set.getString("cognome"));
                user.setDataDiNascita(set.getDate("dataDiNascita"));
                user.setFrase(set.getString("frase"));
                user.setUrlImmagine(set.getString("urlImmagine"));
                
                listaUser.add(user);
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        return listaUser;
    }

    /**
     * Permette di ottenere l'utente con username e password specificati
     *
     * @param username username dell'utente richiesto
     * @param password password dell'utente richiesto
     * @return User dell'utente
     */
    public User login(String username, String password) {
        
        User user = null;
        
        // Caricamento utente
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet set = stmt.executeQuery();           
            
            if(set.next()) {
                
                // Utente trovato
                user = new User();
                user.setId(set.getInt("id"));
                user.setUsername(set.getString("username"));
                user.setPassword(set.getString("password"));
                user.setNome(set.getString("nome"));
                user.setCognome(set.getString("cognome"));
                user.setDataDiNascita(set.getDate("dataDiNascita"));
                user.setFrase(set.getString("frase"));
                user.setUrlImmagine(set.getString("urlImmagine"));
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        return user;
    }
    
     /**
     * Permette di modificare l'user con id specificato
     *
     * @param user id e dati dell'utente da modificare
     */
    public void updateUser(User user) {
        
        // Caricamento utenti
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET " + 
                    "username = ?, password = ?, nome = ?, cognome = ?, dataDiNascita = ?, frase = ?, urlImmagine = ? " +
                    "WHERE id = ?");
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getNome());
            stmt.setString(4, user.getCognome());
            stmt.setDate(5, user.getDataDiNascita());
            stmt.setString(6, user.getFrase());
            stmt.setString(7, user.getUrlImmagine());
            stmt.setInt(8, user.getId());
            
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
    }
    
    /**
     * Permette di eliminare l'utente
     *
     * @param user da eliminare
     */
    public void deleteUser(User user) {
        
        // Caricamento utenti
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
             // Inizio transazione
            conn.setAutoCommit(false);
            
            // Eliminazione post
            
            // Eliminazione amicizie
            // Eliminazione iscrizioni ai gruppi
            // Eliminazione finale utente


            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
    }
    
    /**
     * @return connectionString
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * @param connectionString connectionString da settare
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    
    /**
     * @return connectionUser
     */
    public String getConnectionUser() {
        return connectionUser;
    }

    /**
     * @param connectionUser da settare
     */
    public void setConnectionUser(String connectionUser) {
        this.connectionUser = connectionUser;
    }

    /**
     * @return connectionPassword
     */
    public String getConnectionPassword() {
        return connectionPassword;
    }

    /**
     * @param connectionPassword connectionPassword da settare
     */
    public void setConnectionPassword(String connectionPassword) {
        this.connectionPassword = connectionPassword;
    }
}
