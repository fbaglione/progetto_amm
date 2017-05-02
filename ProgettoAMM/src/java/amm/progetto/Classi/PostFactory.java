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
        post2.setContent("http://scontent.cdninstagram.com/t51.2885-15/s750x750/sh0.08/e35/18014025_292883181123886_5187515530797383680_n.jpg?ig_cache_key=MTUwMjE2Njg3MzE1MjAyNjU5Mg%3D%3D.2");

        Post post3 = new Post();
        post3.setId(2);
        post3.setUser(userFactory.getUserById(2));
        post3.setPostType(Post.Type.LINK);
        post3.setText("Ecco un ottimo sito per generare 'dummy text'.");
        post3.setContent("http://www.lipsum.com/");

        Post post4 = new Post();
        post4.setId(3);
        post4.setUser(userFactory.getUserById(0));
        post4.setPostType(Post.Type.IMAGE);
        post4.setText("W i gatti.");
        post4.setContent("https://d4n5pyzr6ibrc.cloudfront.net/media/27FB7F0C-9885-42A6-9E0C19C35242B5AC/4785B1C2-8734-405D-96DC23A6A32F256B/thul-90efb785-97af-5e51-94cf-503fc81b6940.jpg?response-content-disposition=inline");
        
        listaPost.add(post1);
        listaPost.add(post2);
        listaPost.add(post3);
        listaPost.add(post4);
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
