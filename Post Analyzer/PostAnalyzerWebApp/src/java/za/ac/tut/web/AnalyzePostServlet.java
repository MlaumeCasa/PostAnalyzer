package za.ac.tut.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.ac.tut.bl.PostFacadeLocal;
import za.ac.tut.bl.PostUserFacadeLocal;
import za.ac.tut.entities.Post;

/**
 *
 * @author Tk
 */
public class AnalyzePostServlet extends HttpServlet {

    @EJB
    PostUserFacadeLocal pufl;
    
    @EJB
    PostFacadeLocal pfl;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String userid = req.getParameter("userid");
        String content = req.getParameter("content");
        
        Post post = createPost(id,userid,content);
        pfl.create(post);
        
        req.getSession().setAttribute("post", post);
        
        RequestDispatcher rd = req.getRequestDispatcher("analyze_post_outcome.jsp");
        rd.forward(req, resp);
        
    }

    private Post createPost(Long id, String userid, String content) {
        Post post = new Post();
        
        post.setId(id);
        post.setContent(content);
        post.setUserid(id);
        
        return post;
    }
    
    
    
}
