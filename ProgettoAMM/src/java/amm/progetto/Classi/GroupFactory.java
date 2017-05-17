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
        
        // Caricamento utente
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
