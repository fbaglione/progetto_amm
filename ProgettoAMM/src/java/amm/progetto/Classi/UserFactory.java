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
     * @return lista degli utenti del sistema eccetto l'amministratore
     */
    public ArrayList<User> getListaUser() {
        
        ArrayList<User> listaUser = new ArrayList<>();
        
        // Caricamento utenti
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            Statement stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery("SELECT * FROM users WHERE id != 0");
            
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
     * Permette di sapere se un user è amico di un'altro
     * Restituisce vero anche se i due utenti coincidono
     * @param follower primo user
     * @param followed user con cui si ha una amicizia
     * @return boolean se il follower ha stretto amicizia con il followed
     */
    public boolean areFriends(User follower, User followed) {
        
        // restituisce true se i due user sono lo stesso
        if(follower.getId() == followed.getId())
            return true;
        
        boolean areFriends = false;
        
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM friendship WHERE follower = ? AND followed = ?");
            stmt.setInt(1, follower.getId());
            stmt.setInt(2, followed.getId());
            
            ResultSet set = stmt.executeQuery();           
            
            if(set.next()) {
                
                // amicizia trovata
                areFriends = true;
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        return areFriends;
    }
    
    /**
     * Permette ad un utente di seguirne un altro
     * @param follower primo user
     * @param followed user con cui si vuole avere una amicizia
     */
    public void addFriendship(User follower, User followed) {
    
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO friendship "
                    + "(follower, followed) VALUES "
                    + "(?, ?),(?, ?)");
            stmt.setInt(1, follower.getId());
            stmt.setInt(2, followed.getId());
            stmt.setInt(3, followed.getId());
            stmt.setInt(4, follower.getId());
            
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
    }
    
    /**
     * Permette di modificare l'user con id specificato
     * @param user id e dati dell'utente da modificare
     */
    public void updateUser(User user) {
        
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            PreparedStatement stmt = conn.prepareStatement("UPDATE users SET " + 
                    "password = ?, nome = ?, cognome = ?, dataDiNascita = ?, frase = ?, urlImmagine = ? " +
                    "WHERE id = ?");
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getNome());
            stmt.setString(3, user.getCognome());
            stmt.setDate(4, user.getDataDiNascita());
            stmt.setString(5, user.getFrase());
            stmt.setString(6, user.getUrlImmagine());
            stmt.setInt(7, user.getId());
            
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
        
        PreparedStatement stmt_posts = null;
        PreparedStatement stmt_friendship = null;
        PreparedStatement stmt_membri_gruppo = null;
        PreparedStatement stmt_admin_gruppo = null;
        PreparedStatement stmt_users = null;
        
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
        
            // Caricamento utenti
            try {
                 // Inizio transazione
                conn.setAutoCommit(false);

                // Eliminazione posts di cui l'user è autore
                stmt_posts = conn.prepareStatement("DELETE FROM posts WHERE autore = ?");
                stmt_posts.setInt(1, user.getId());
                stmt_posts.executeUpdate();

                // Eliminazione posts sulla bacheca dell'user
                stmt_posts = conn.prepareStatement("DELETE FROM posts WHERE bacheca_user = ?");
                stmt_posts.setInt(1, user.getId());
                stmt_posts.executeUpdate();
                
                // Eliminazione amicizie
                stmt_friendship = conn.prepareStatement("DELETE FROM friendship WHERE follower = ? OR followed = ?");
                stmt_friendship.setInt(1, user.getId());
                stmt_friendship.setInt(2, user.getId());
                stmt_friendship.executeUpdate();

                // Eliminazione iscrizioni ai gruppi
                stmt_membri_gruppo = conn.prepareStatement("DELETE FROM membri_gruppo WHERE membro = ?");
                stmt_membri_gruppo.setInt(1, user.getId());
                stmt_membri_gruppo.executeUpdate();

                // Sostituzione dell'amministratore del sito come admin dei gruppi
                // di cui l'utente era amministratore
                stmt_admin_gruppo = conn.prepareStatement("UPDATE gruppi SET administrator = 0 WHERE administrator = ?");
                stmt_admin_gruppo.setInt(1, user.getId());
                stmt_admin_gruppo.executeUpdate();
                
                // Eliminazione finale utente
                stmt_users = conn.prepareStatement("DELETE FROM users WHERE id = ?");
                stmt_users.setInt(1, user.getId());
                stmt_users.executeUpdate();

                conn.commit();

            } catch (SQLException ex) {

                // Errore SQL, rollback
                if(conn != null)
                    conn.rollback();

                ex.printStackTrace();
            } finally {

                // Chiusura statements
                if(stmt_posts != null)
                    stmt_posts.close();
                if(stmt_friendship != null)
                    stmt_friendship.close();
                if(stmt_membri_gruppo != null)
                    stmt_membri_gruppo.close();
                if(stmt_admin_gruppo != null)
                    stmt_admin_gruppo.close();
                if(stmt_users != null)
                    stmt_users.close();

                // Chiusura connessione
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch(SQLException ex) {
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
