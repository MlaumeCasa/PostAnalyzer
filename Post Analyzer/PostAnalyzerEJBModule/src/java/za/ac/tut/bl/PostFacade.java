/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.bl;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import za.ac.tut.entities.Post;
import org.ac.tut.ai.AI;

/**
 *
 * @author Tk
 */
@Stateless
public class PostFacade extends AbstractFacade<Post> implements PostFacadeLocal {

    @PersistenceContext(unitName = "PostAnalyzerEJBModulePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostFacade() {
        super(Post.class);
    }

    @Override
    public void analyze(Post post) {
        Query query = em.createQuery("SELECT p FROM Post p WHERE p.userid = :userID");
        query.setParameter("userID", post.getId());
        
        StringBuilder sb = new StringBuilder();
        sb.append("<prompt>\n");
        query
            .getResultList()
            .forEach(item -> {
                Post top = (Post) item;
                sb
                    .append("<post>".indent(4))
                    .append(String.format("<text>%s</text>", top.getContent()).indent(8))
                    .append(String.format("<predicted>%s</predicted>", top.getPredicatedScore()).indent(8))
                    .append(String.format("<actual>%s</acual>", top.getActualScore()).indent(8))
                    .append("</post>".indent(4));
            });
        
        sb
          .append("<target-post>".indent(4))
          .append(String.format("<text>%s</text>", post.getContent()).indent(8))
          .append(String.format("<predicted>%s</predicted>", post.getPredicatedScore()).indent(8))
          .append(String.format("<actual>%s</acual>", post.getActualScore()).indent(8))
          .append("</target-post>".indent(4));
        
        String sys = "Given list of post above and the target post, analyze the target post and give the predicated score for the target post.";
        sys += "The ranking criterial is the tonality of previsous posts, their actual engagement scores. The result should be formatted like ";
        sys += "this: <ranking>score</ranking> e.g: <ranking>70%</ranking>";
        sb.append("<system-prompt>").append(sys).append("<system-prompt>");
        sb.append("</prompt>\n");
        
        String prompt = sb.toString();
        String result = AI.chat(prompt);
        System.out.println(result);
    }
    
}
