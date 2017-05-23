package amm.progetto.Classi;
import java.sql.Date;

/**
 * Informazioni di un utente
 * @author DatrhiilPC
 */
public class User {

    private int id;
    private String username;
    private String nome;
    private String cognome;
    private String urlImmagine;
    private Date dataDiNascita;
    private String frase;
    private String password;

    /**
     * @return se l'utente ha tutti i dati del profilo
     */
    public boolean profiloCompleto() {
        
        if (this.nome != null
                && this.cognome != null
                && this.urlImmagine != null
                && this.dataDiNascita != null
                && this.frase != null
                && this.password != null) {
            return true;
        }

        return false;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof User)) return false;
        if(obj == this) return true;
        return this.id == ((User) obj).id;
    }
    
    /**
     * @return id dell'utente
     */
    public int getId() {
        return id;
    }

    /**
     * @param id id dell'utente da settare
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return nome dell'utente
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome nome dell'utente da settare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return cognome dell'utente
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * @param cognome cognome dell'utente da settare
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * @return url dell'immagine profilo
     */
    public String getUrlImmagine() {
        return urlImmagine;
    }

    /**
     * @param urlImmagine url dell'immagine profilo da settare
     */
    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    /**
     * @return data di nascita dell'utente
     */
    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * @param dataDiNascita data di nascita dell'utente da settare
     */
    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }
    
    /**
     * @param dataDiNascita data di nascita dell'utente da settare
     */
    public void setDataDiNascita(String dataDiNascita) {
        if(!dataDiNascita.equals(""))
            this.dataDiNascita = Date.valueOf(dataDiNascita);
        else
            this.dataDiNascita = null;
    }
    
    /**
     * @return frase di presentazione dell'utente
     */
    public String getFrase() {
        return frase;
    }

    /**
     * @param frase frase di presentazione da usare
     */
    public void setFrase(String frase) {
        this.frase = frase;
    }
    
    /**
     * @return password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password password dell'utente da settare
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return username dell'utente
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username da usare
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
