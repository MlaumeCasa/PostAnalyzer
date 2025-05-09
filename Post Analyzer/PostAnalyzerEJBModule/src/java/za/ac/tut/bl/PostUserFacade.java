package za.ac.tut.bl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import za.ac.tut.entities.PostUser;

/**
 *
 * @author Tk
 */
@Stateless
public class PostUserFacade extends AbstractFacade<PostUser> implements PostUserFacadeLocal {

    @PersistenceContext(unitName = "PostAnalyzerEJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostUserFacade() {
        super(PostUser.class);
    }
    
}
