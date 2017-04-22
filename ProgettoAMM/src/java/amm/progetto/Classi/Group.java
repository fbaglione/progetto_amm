package amm.progetto.Classi;

/**
 * Informazioni di un gruppo
 * @author DatrhiilPC
 */
public class Group {

    private int id;
    private String nome;
    private User admin;
    
    /**
     * @return id del gruppo
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
     * @return nome del gruppo
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome nome da settare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return admin del gruppo
     */
    public User getAdmin() {
        return admin;
    }

    /**
     * @param admin admin da settare
     */
    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
