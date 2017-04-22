package amm.progetto.Classi;

/**
 *
 * @author DatrhiilPC
 */
public class Group {

    private int id;
    private String nome;
    private User admin;
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the admin
     */
    public User getAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(User admin) {
        this.admin = admin;
    }
}
