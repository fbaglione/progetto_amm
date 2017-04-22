package amm.progetto.Classi;

import java.util.ArrayList;

/**
 * Factory degli user
 * @author DatrhiilPC
 */
public class UserFactory {

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

    private ArrayList<User> listaUser = new ArrayList<User>();

    private UserFactory() {
        
        // Creazione utenti

        User user1 = new User();
        user1.setId(0);
        user1.setNome("Djanni");
        user1.setCognome("Randagio");
        user1.setDataDiNascita("01/01/2011");
        user1.setPassword("sonodjanni");
        user1.setUrlImmagine("img/djanni_randagio.jpg");

        User user2 = new User();
        user2.setId(0);
        user2.setNome("Scateni");
        user2.setCognome("Riccardi");
        user2.setDataDiNascita("06/11/1961");
        user2.setPassword("ric61");
        user2.setUrlImmagine("img/djanni_randagio.jpg");

        User user3 = new User();
        user3.setId(0);
        user3.setNome("Davide");
        user3.setCognome("Spano");
        user3.setDataDiNascita("01/01/2011");
        user3.setPassword("ammprogetto");
        user3.setUrlImmagine("img/davide_spano.jpg");

        listaUser.add(user1);
        listaUser.add(user2);
        listaUser.add(user3);
    }

    /**
     * Permette di ottenere l'utente con id specificato
     * @param id dell'utente richiesto
     * @return User con id richiesto
     */
    public User getUserById(int id) {
        for (User user : this.listaUser) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
