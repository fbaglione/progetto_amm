package amm.progetto.Classi;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory dei gruppi
 * @author DatrhiilPC
 */
public class GroupFactory {

    // Stringa per la connessione
    private String connectionString;
    
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

    private ArrayList<Group> listaGroup = new ArrayList<Group>();

    private GroupFactory() {
        
        UserFactory userFactory = UserFactory.getInstance();

        // Creazione Group
        
        Group group1 = new Group();
        group1.setId(0);
        group1.setNome("Mongolfieristi");
        group1.setUrlImmagine("img/mongolfieristi.png");
        group1.setAdmin(userFactory.getUserById(0));
        
        Group group2 = new Group();
        group2.setId(1);
        group2.setNome("Professori");
        group2.setUrlImmagine("img/lucio.bmp");
        group2.setAdmin(userFactory.getUserById(2));
        
        Group group3 = new Group();
        group3.setId(2);
        group3.setNome("Animali");
        group3.setUrlImmagine("img/gattodjanni.jpg");
        group3.setAdmin(userFactory.getUserById(0));
        
        listaGroup.add(group1);
        listaGroup.add(group2);
        listaGroup.add(group3);
    }

    /**
     * Permette di ottenere il gruppo con id specificato
     * @param id id del gruppo richiesto
     * @return Group con id richiesto
     */
    public Group getGroupById(int id) {
        for (Group group : this.getListaGroup()) {
            if (group.getId() == id) {
                return group;
            }
        }
        return null;
    }

    /**
     * Restituisce una lista con i gruppi a cui partecipa
     * l'user specificato
     * @param user user richiesto
     * @return lista con i gruppi dell'user
     */
    public List getGroupList(User user) {

        List<Group> listaGroup = new ArrayList<Group>();

        for (Group group : this.getListaGroup()) {
            if (group.getAdmin().equals(user)) {
                listaGroup.add(group);
            }
        }
        return listaGroup;
    }

    /**
     * @return lista dei gruppi nel sistema
     */
    public ArrayList<Group> getListaGroup() {
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
}
