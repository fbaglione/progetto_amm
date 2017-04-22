package amm.progetto.Classi;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DatrhiilPC
 */
public class GroupFactory {
    
    // Pattern Design Singleton
    private static GroupFactory singleton;

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
        group1.setAdmin(userFactory.getUserById(0));
        
        Group group2 = new Group();
        group2.setId(1);
        group2.setNome("Professori");
        group2.setAdmin(userFactory.getUserById(2));
        
        Group group3 = new Group();
        group3.setId(0);
        group3.setNome("Animali");
        group3.setAdmin(userFactory.getUserById(0));
        
        listaGroup.add(group1);
        listaGroup.add(group2);
        listaGroup.add(group3);
    }

    public Group getGroupById(int id) {
        for (Group group : this.listaGroup) {
            if (group.getId() == id) {
                return group;
            }
        }
        return null;
    }

    public List getGroupList(User user) {

        List<Group> listaGroup = new ArrayList<Group>();

        for (Group group : this.listaGroup) {
            if (group.getAdmin().equals(user)) {
                listaGroup.add(group);
            }
        }
        return listaGroup;
    }
}
