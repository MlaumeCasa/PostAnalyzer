/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.ac.tut.bl;

import java.util.List;
import javax.ejb.Local;
import za.ac.tut.entities.PostUser;

/**
 *
 * @author Tk
 */
@Local
public interface PostUserFacadeLocal {

    void create(PostUser postUser);

    void edit(PostUser postUser);

    void remove(PostUser postUser);

    PostUser find(Object id);

    List<PostUser> findAll();

    List<PostUser> findRange(int[] range);

    int count();
    
}
