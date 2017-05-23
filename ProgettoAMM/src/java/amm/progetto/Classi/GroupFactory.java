package amm.progetto.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory dei gruppi
 * @author DatrhiilPC
 */
public class GroupFactory {

    // Stringhe per la connessione
    private String connectionString;
    private String connectionUser;
    private String connectionPassword;
    
    // Pattern Design Singleton
    private static GroupFactory singleton;

    /**
     * Metodo per l'accesso alla singleton
     * @return singleton della factory
     */
    public static GroupFactory getInstance() {
        if (singleton == null) {
            singleton = new GroupFactory();
        }
        return singleton;
    }

    /**
     * Permette di ottenere il gruppo con id specificato
     * @param id id del gruppo richiesto
     * @return Group con id richiesto
     */
    public Group getGroupById(int id) {
        
        Group group = null;
        
        // Caricamento group
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM gruppi WHERE id = ?");
            stmt.setInt(1, id);
            
            ResultSet set = stmt.executeQuery();            
            
            if(set.next()) {
                
                // Group trovato
                group = new Group();
                group.setId(set.getInt("id"));
                group.setNome(set.getString("nome"));
                group.setUrlImmagine(set.getString("urlImmagine"));
                group.setAdmin(UserFactory.getInstance().getUserById(set.getInt("administrator")));
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        return group;
    }

    /**
     * @return lista dei gruppi nel sistema
     */
    public ArrayList<Group> getListaGroup() {
                
        ArrayList<Group> listaGroup = new ArrayList<>();
        
        // Caricamento utenti
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            Statement stmt = conn.createStatement();
            ResultSet set = stmt.executeQuery("SELECT * FROM gruppi");
            
            while(set.next()) {
                
                Group group = new Group();
                group.setId(set.getInt("id"));
                group.setNome(set.getString("nome"));
                group.setUrlImmagine(set.getString("urlImmagine"));
                group.setAdmin(UserFactory.getInstance().getUserById(set.getInt("administrator")));
                
                listaGroup.add(group);
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        return listaGroup;
    }
    
    /**
     * Permette di verificare che un user appartenga ad un gruppo
     * @param user utente
     * @param group gruppo di appartenenza
     * @return boolean appartenenza di user al group
     */
    public boolean belongsToGroup(User user, Group group) {
                
        boolean belongsToGroup = false;
        
        // Caricamento utenti
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM membri_gruppo WHERE gruppo = ? AND membro = ?");
            stmt.setInt(1, group.getId());
            stmt.setInt(2, user.getId());
            
            ResultSet set = stmt.executeQuery();           
            
            if(set.next()) {

                belongsToGroup = true;
            }
            
            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
        
        return belongsToGroup;
    }

    /**
     * Aggiunge un nuovo gruppo
     * @param group gruppo da creare
     * @param admin user che sarà amministratore del gruppo
     */
    public void addGroup(Group group, User admin) {
    
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO gruppi "
                    + "(id, nome, urlImmagine, administrator) VALUES "
                    + "(default, ?, ?, ?)");
            stmt.setString(1, group.getNome());
            stmt.setString(2, group.getUrlImmagine());
            stmt.setInt(3, admin.getId());
            
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
    }
    
    /**
     * Permette ad un utente di entrare in un gruppo
     * @param user user che si vuole iscrivere
     * @param group gruppo al quale si vuole iscrivere
     */
    public void addSubscription(User follower, Group gruppo) {
    
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO membri_gruppo "
                    + "(gruppo, membro) VALUES "
                    + "(?, ?)");
            stmt.setInt(1, gruppo.getId());
            stmt.setInt(2, follower.getId());

            stmt.executeUpdate();

            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
    }
    
    /**
     * Permette di eliminare il gruppo
     *
     * @param group da eliminare
     */
    public void deleteGroup(Group group) {
        
        PreparedStatement stmt_posts = null;
        PreparedStatement stmt_subscriptions = null;
        PreparedStatement stmt_group = null;
        
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
        
            // Caricamento utenti
            try {
                 // Inizio transazione
                conn.setAutoCommit(false);

                // Eliminazione posts del gruppo
                stmt_posts = conn.prepareStatement("DELETE FROM posts WHERE bacheca_gruppo = ?");
                stmt_posts.setInt(1, group.getId());
                stmt_posts.executeUpdate();
                
                // Eliminazione iscrizioni
                stmt_subscriptions = conn.prepareStatement("DELETE FROM membri_gruppo WHERE gruppo = ?");
                stmt_subscriptions.setInt(1, group.getId());
                stmt_subscriptions.executeUpdate();

                // Eliminazione finale gruppo
                stmt_group = conn.prepareStatement("DELETE FROM gruppi WHERE id = ?");
                stmt_group.setInt(1, group.getId());
                stmt_group.executeUpdate();

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
                if(stmt_subscriptions != null)
                    stmt_subscriptions.close();
                if(stmt_group != null)
                    stmt_group.close();

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
