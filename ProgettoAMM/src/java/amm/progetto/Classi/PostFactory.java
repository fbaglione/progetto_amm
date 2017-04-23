package amm.progetto.Classi;

import java.util.List;
import java.util.ArrayList;

/**
 * Factory dei post
 * @author DatrhiilPC
 */
public class PostFactory {

    // Pattern Design Singleton
    private static PostFactory singleton;

    /**
     * Metodo per l'accesso alla singleton
     * @return singleton della factory
     */
    public static PostFactory getInstance() {
        if (singleton == null) {
            singleton = new PostFactory();
        }
        return singleton;
    }

    private ArrayList<Post> listaPost = new ArrayList<Post>();

    private PostFactory() {
        
        UserFactory userFactory = UserFactory.getInstance();

        // Creazione Post
        
        Post post1 = new Post();
        post1.setId(0);
        post1.setUser(userFactory.getUserById(0));
        post1.setPostType(Post.Type.TEXT);
        post1.setText("Ciao, miei schiavi. Datemi cibo! Adesso!");

        Post post2 = new Post();
        post2.setId(1);
        post2.setUser(userFactory.getUserById(1));
        post2.setPostType(Post.Type.IMAGE);
        post2.setText("Il miglior gatto al mondo.");
        post2.setContent("img/gattodjanni.jpg");

        Post post3 = new Post();
        post3.setId(2);
        post3.setUser(userFactory.getUserById(2));
        post3.setPostType(Post.Type.LINK);
        post3.setText("Ecco un ottimo sito per generare 'dummy text'.");
        post3.setContent("http://www.lipsum.com/");

        listaPost.add(post1);
        listaPost.add(post2);
        listaPost.add(post3);
    }

    /**
     * Permette di ottenere il post con id specificato
     * @param id id del post richiesto
     * @return Post con id richiesto
     */
    public Post getPostById(int id) {
        for (Post post : this.listaPost) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    /**
     * Restituisce una lista con i post di cui l'user
     * specificato è autore
     * @param user user richiesto
     * @return lista con i post dell'user
     */
    public List getPostList(User user) {

        List<Post> listaPost = new ArrayList<Post>();

        for (Post post : this.listaPost) {
            if (post.getUser().equals(user)) {
                listaPost.add(post);
            }
        }
        return listaPost;
    }
}
