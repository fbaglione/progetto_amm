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
        user1.setDataDiNascita("2011-01-01");
        user1.setPassword("sonodjanni");
        user1.setFrase("Datemi cibo! Miao...");        
        user1.setUrlImmagine("img/djanni_randagio.jpg");
        //user1.setUrlImmagine("http://scontent-sea1-1.cdninstagram.com/t51.2885-15/s480x480/e35/17818566_1876586082610013_8501213461156462592_n.jpg?ig_cache_key=MTQ5MDUyNzkyOTk4NTY0MDk5OA%3D%3D.2");
        
        User user2 = new User();
        user2.setId(1);
        user2.setNome("Riccardo");
        user2.setCognome("Scateni");
        user2.setDataDiNascita("1961-11-06");
        user2.setPassword("ric61");
        user2.setFrase("W Oculus Rift!"); 
        user2.setUrlImmagine("img/scateni.jpg");

        User user3 = new User();
        user3.setId(2);
        user3.setNome("Davide");
        user3.setCognome("Spano");
        user3.setDataDiNascita("2011-01-11");
        user3.setPassword("ammprogetto");
        user3.setFrase("Do or do not. There is no try.");
        user3.setUrlImmagine("img/spano.jpg");

        User user4 = new User();
        user4.setId(3);
        user4.setNome("incompleto");
        user4.setPassword("incompleto");
        
        listaUser.add(user1);
        listaUser.add(user2);
        listaUser.add(user3);
        listaUser.add(user4);
    }


    
    /**
     * Permette di ottenere l'utente con id specificato
     * @param id dell'utente richiesto
     * @return User con id richiesto
     */
    public User getUserById(int id) {
        for (User user : this.getListaUser()) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    /**
     * @return lista degli utenti del sistema
     */
    public ArrayList<User> getListaUser() {
        return listaUser;
    }
}
