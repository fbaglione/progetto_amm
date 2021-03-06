package amm.progetto.Classi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 * Factory dei post
 *
 * @author DatrhiilPC
 */
public class PostFactory {

    // Stringhe per la connessione
    private String connectionString;
    private String connectionUser;
    private String connectionPassword;

    // Pattern Design Singleton
    private static PostFactory singleton;

    /**
     * Metodo per l'accesso alla singleton
     *
     * @return singleton della factory
     */
    public static PostFactory getInstance() {
        if (singleton == null) {
            singleton = new PostFactory();
        }
        return singleton;
    }

    /**
     * Permette di ottenere il post con id specificato
     *
     * @param id id del post richiesto
     * @return Post con id richiesto
     */
    public Post getPostById(int id) {

        Post post = null;

        // Caricamento post
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM posts WHERE id = ?");
            stmt.setInt(1, id);

            ResultSet set = stmt.executeQuery();

            if (set.next()) {

                // Post trovato
                post = new Post();
                post.setId(set.getInt("id"));
                post.setAutore(UserFactory.getInstance().getUserById(set.getInt("autore")));
                post.setPostType(postTypeFromInt(set.getInt("postType")));
                post.setText(set.getString("text"));
                post.setContent(set.getString("content"));
                post.setGruppo(GroupFactory.getInstance().getGroupById(set.getInt("bacheca_gruppo")));
            }

            stmt.close();
            conn.close();

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return post;
    }

    /**
     * Controlla l'admin del gruppo del post o il suo autore
     * @param user user
     * @return boolean se l'user è admin del post
     */
    public boolean isAdministrator(int postID, User user) {
        
        return this.isAdministrator(postID, user.getId());
    }
    
    /**
     * Controlla l'admin del gruppo del post o il suo autore
     * @param userID id dell'user
     * @return boolean se l'user associato all'userID è admin del post
     */
    public boolean isAdministrator(int postID, int userID) {

        Integer autore = null;
        Integer adminGruppo = null;

        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

            // Seleziona sia l'autore che l'admin del gruppo del post
            PreparedStatement stmt = conn.prepareStatement("SELECT posts.autore, gruppi.administrator FROM posts"
                    + " LEFT JOIN gruppi ON posts.bacheca_gruppo = gruppi.id"
                    + " WHERE posts.id = ?");
            stmt.setInt(1, postID);

            ResultSet set = stmt.executeQuery();

            if (set.next()) {

                autore = set.getInt("autore");
                adminGruppo = set.getInt("administrator");
            }

            stmt.close();
            conn.close();

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return (autore == userID || (adminGruppo != null && adminGruppo == userID));
    }

    /**
     * Restituisce una lista con i post di cui l'user specificato è autore
     *
     * @param user user richiesto
     * @return lista con i post dell'user
     */
    public List getPostBacheca(User user) {

        List<Post> listaPost = new ArrayList<>();

        // Caricamento post
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

            /* Seleziona tutti i post sulla bacheca dell'utente o di uno dei gruppi
             * a cui è iscritto */
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM posts"
                    + " LEFT JOIN membri_gruppo ON posts.bacheca_gruppo = membri_gruppo.gruppo"
                    + " WHERE posts.bacheca_user = ? OR membri_gruppo.membro = ?"
                    + " ORDER BY posts.id DESC");
            stmt.setInt(1, user.getId());
            stmt.setInt(2, user.getId());

            ResultSet set = stmt.executeQuery();

            while (set.next()) {

                Post post = new Post();
                post.setId(set.getInt("id"));
                post.setAutore(UserFactory.getInstance().getUserById(set.getInt("autore")));
                post.setPostType(postTypeFromInt(set.getInt("postType")));
                post.setText(set.getString("text"));
                post.setContent(set.getString("content"));
                post.setGruppo(GroupFactory.getInstance().getGroupById(set.getInt("bacheca_gruppo")));
                
                listaPost.add(post);
            }

            stmt.close();
            conn.close();

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return listaPost;
    }

    /**
     * Restituisce una lista con i post fatti sul gruppo dagli utenti iscritti
     *
     * @param group gruppo richiesto
     * @return lista con i post del gruppo
     */
    public List getPostBacheca(Group group) {

        List<Post> listaPost = new ArrayList<>();

        // Caricamento post
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

            /* Seleziona tutti i post sulla bacheca dell'utente o di uno dei gruppi
             * a cui è iscritto */
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM posts WHERE bacheca_gruppo = ? ORDER BY id DESC");
            stmt.setInt(1, group.getId());

            ResultSet set = stmt.executeQuery();

            while (set.next()) {

                Post post = new Post();
                post.setId(set.getInt("id"));
                post.setAutore(UserFactory.getInstance().getUserById(set.getInt("autore")));
                post.setPostType(postTypeFromInt(set.getInt("postType")));
                post.setText(set.getString("text"));
                post.setContent(set.getString("content"));
                post.setGruppo(GroupFactory.getInstance().getGroupById(set.getInt("bacheca_gruppo")));
                
                listaPost.add(post);
            }

            stmt.close();
            conn.close();

        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return listaPost;
    }

    /**
     * Inserimento di un post nella bacheca di un user
     *
     * @param post post da inserire nella bacheca di userDestinatario
     * @param userDestinatario user al quale pubblicare il post in bacheca
     */
    public void addPost(Post post, User userDestinatario) {

        // Inserimento post
        try {

            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO posts "
                    + "(id, postType, autore, text, content, bacheca_user, bacheca_gruppo) VALUES "
                    + "(default, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, postTypeFromEnum(post.getPostType()));
            stmt.setInt(2, post.getAutore().getId());
            stmt.setString(3, post.getText());
            stmt.setString(4, post.getContent());
            stmt.setInt(5, userDestinatario.getId());
            stmt.setNull(6, java.sql.Types.INTEGER);

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }

    /**
     * Inserimento di un post nella bacheca di un user
     *
     * @param post post da inserire nella bacheca di groupDestinatario
     * @param groupDestinatario group al quale pubblicare il post in bacheca
     */
    public void addPost(Post post, Group groupDestinatario) {

        // Inserimento post
        try {

            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO posts "
                    + "(id, postType, autore, text, content, bacheca_user, bacheca_gruppo) VALUES "
                    + "(default, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, postTypeFromEnum(post.getPostType()));
            stmt.setInt(2, post.getAutore().getId());
            stmt.setString(3, post.getText());
            stmt.setString(4, post.getContent());
            stmt.setNull(5, java.sql.Types.INTEGER);
            stmt.setInt(6, groupDestinatario.getId());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
    }

    /**
     * Elimina il post con id specificato
     * @param postID id del post da eliminare
     */
    public void deletePost(int postID) {
    
        try {
            Connection conn = DriverManager.getConnection(connectionString, connectionUser, connectionPassword);
            
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM posts "
                    + "WHERE id = ? ");
            stmt.setInt(1, postID);
            
            stmt.executeUpdate();

            stmt.close();
            conn.close();
            
        } catch (SQLException ex) {
            
            ex.printStackTrace();
        }
    }
    
    /**
     * @param type stringa con il nome del tipo
     * @return Post.Type tipo del post
     */
    private Post.Type postTypeFromString(String type) {

        if (type.equals("IMAGE")) {
            return Post.Type.IMAGE;
        } else if (type.equals("LINK")) {
            return Post.Type.LINK;
        } else if (type.equals("TEXT")) {
            return Post.Type.TEXT;
        }

        throw new RuntimeException("Tipo non supportato");
    }

    /**
     * @param type intero rappresentante il tipo
     * @return Post.Type tipo del post
     */
    private Post.Type postTypeFromInt(int type) {

        if (type == 1) {
            return Post.Type.TEXT;
        } else if (type == 2) {
            return Post.Type.IMAGE;
        } else if (type == 3) {
            return Post.Type.LINK;
        }

        throw new RuntimeException("Tipo non supportato");
    }

    /**
     * @param type tipo del post
     * @return int intero del tipo del post
     */
    private int postTypeFromEnum(Post.Type type) {

        if (type == Post.Type.TEXT) {
            return 1;
        } else if (type == Post.Type.IMAGE) {
            return 2;
        } else if (type == Post.Type.LINK) {
            return 3;
        }

        throw new RuntimeException("Tipo non supportato");
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
