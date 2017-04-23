package amm.progetto.Classi;

/**
 *
 * @author DatrhiilPC
 */
public class Post {
   
    /**
     * Tipologia del post
     */
    public enum Type {

        /**
         * solo testo
         */
        TEXT,

        /**
         * con immagine
         */
        IMAGE,
        
        /**
         * con link
         */
        LINK
    };

    private int id;
    private User user;
    private String content;
    private String text;
    private Type postType;
    
    /**
     * @return id del post
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id da settare
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return User autore del post
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user User autore da settare
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return contenuto del post
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content contenuto del post
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return tipologia del post
     */
    public Type getPostType() {
        return postType;
    }

    /**
     * @param postType tipologia del post
     */
    public void setPostType(Type postType) {
        this.postType = postType;
    }

    /**
     * @return testo del post
     */
    public String getText() {
        return text;
    }

    /**
     * @param text testo del post da settare
     */
    public void setText(String text) {
        this.text = text;
    }
}
