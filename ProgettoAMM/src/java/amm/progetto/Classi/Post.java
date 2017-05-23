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
    private Type postType;
    private User autore;
    private String content;
    private String text;
    private Group gruppo = null;
    
    /**
     * Controlla l'admin del gruppo del post o il suo autore
     * @param user user da controllare
     * @return boolean se l'user è admin del post
     */
    public boolean isAdministrator(User user) {
        return PostFactory.getInstance().isAdministrator(this.id, user);
    }
    
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
    public User getAutore() {
        return autore;
    }

    /**
     * @param user User autore da settare
     */
    public void setAutore(User user) {
        this.autore = user;
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

    /**
     * @return gruppo in cui il post è stato postato
     */
    public Group getGruppo() {
        return gruppo;
    }

    /**
     * @param gruppo set del gruppo in cui il post è stato postato
     */
    public void setGruppo(Group gruppo) {
        this.gruppo = gruppo;
    }
}
