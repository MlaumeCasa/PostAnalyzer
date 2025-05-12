package za.ac.tut.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Tk
 */
@Entity
@Table(name = "PostManager")
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;
    private Long userid;
    private String content;
    private Double predicatedScore;
    private Double actualScore;

    public Post() {
    }

    public Post(Long id, Long userid, String content, Double predicatedScore, Double actualScore) {
        this.id = id;
        this.userid = userid;
        this.content = content;
        this.predicatedScore = predicatedScore;
        this.actualScore = actualScore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getPredicatedScore() {
        return predicatedScore;
    }

    public void setPredicatedScore(Double predicatedScore) {
        this.predicatedScore = predicatedScore;
    }

    public Double getActualScore() {
        return actualScore;
    }

    public void setActualScore(Double actualScore) {
        this.actualScore = actualScore;
    }

 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "za.ac.tut.entities.Post[ id=" + id + " ]";
    }
    
}
