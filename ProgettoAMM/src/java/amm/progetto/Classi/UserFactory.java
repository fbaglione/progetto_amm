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
        user1.setUsername("djanni");
        user1.setPassword("sonodjanni");
        user1.setNome("Djanni");
        user1.setCognome("Randagio");
        user1.setDataDiNascita("2011-01-01");
        user1.setFrase("Datemi cibo! Miao...");        
        user1.setUrlImmagine("http://scontent.cdninstagram.com/t51.2885-15/s750x750/sh0.08/e35/18160279_1496958173662222_5345517778365317120_n.jpg?ig_cache_key=MTUwMjg0NzcwNzQ1ODY5NzI4Nw%3D%3D.2");
        
        User user2 = new User();
        user2.setId(1);
        user2.setUsername("riccardo");
        user2.setPassword("ric");
        user2.setNome("Riccardo");
        user2.setCognome("Scateni");
        user2.setDataDiNascita("1961-11-06");
        user2.setFrase("W Oculus Rift!"); 
        user2.setUrlImmagine("https://media.licdn.com/mpr/mpr/shrinknp_200_200/p/2/000/10c/3d8/1556a8a.jpg");

        User user3 = new User();
        user3.setId(2);
        user3.setUsername("davide");
        user3.setPassword("ammprogetto");
        user3.setNome("Davide");
        user3.setCognome("Spano");
        user3.setDataDiNascita("2011-01-11");
        user3.setFrase("Do or do not. There is no try.");
        user3.setUrlImmagine("https://i1.rgstatic.net/ii/profile.image/AS%3A278717961130010%401443462948368_l/Lucio_Spano.png");

        User user4 = new User();
        user4.setId(3);
        user4.setUsername("incompleto");
        user4.setNome("incompleto");
        user4.setPassword("incompleto");
        user4.setUrlImmagine("https://openclipart.org/image/2400px/svg_to_png/177570/dwcheckno.png");
        
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
