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
        post1.setContent("Ciao, miei schiavi. Datemi cibo! Adesso! Miaomiaomiaomiaomiao!");
        post1.setId(0);
        post1.setUser(userFactory.getUserById(0));

        Post post2 = new Post();
        post2.setContent("img/djanni1.jpg");
        post2.setId(1);
        post2.setUser(userFactory.getUserById(0));
        post2.setPostType(Post.Type.IMAGE);

        Post post3 = new Post();
        post3.setContent("img/djanni2.jpg");
        post3.setId(2);
        post3.setUser(userFactory.getUserById(0));
        post3.setPostType(Post.Type.IMAGE);

        Post post4 = new Post();
        post4.setContent("I need ansioliticy");
        post4.setId(3);
        post4.setUser(userFactory.getUserById(1));

        Post post5 = new Post();
        post5.setContent("https://68.media.tumblr.com/51942e1f788f7209ee0f6db7cfc5e0fb/tumblr_n37ycpbMZf1rkxod7o1_500.jpg");
        post5.setId(4);
        post5.setUser(userFactory.getUserById(1));
        post5.setPostType(Post.Type.IMAGE);

        listaPost.add(post1);
        listaPost.add(post2);
        listaPost.add(post3);
        listaPost.add(post4);
        listaPost.add(post5);
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
