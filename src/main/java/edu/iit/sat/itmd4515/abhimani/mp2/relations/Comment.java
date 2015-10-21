package edu.iit.sat.itmd4515.abhimani.mp2.relations;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractEntityUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Event;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Student;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
@Entity
@Table(name="comments")
@NamedQueries({
    @NamedQuery(name="Comments.retrieveAll", query="SELECT c FROM Comment AS c"),
    @NamedQuery(name="Comments.findById", query="SELECT c FROM Comment AS c WHERE c.PId=:Id"),
    @NamedQuery(name="Comments.retrieveByEvent", query="SELECT c FROM Comment AS c WHERE c.Evt=:Evt"),
    @NamedQuery(name="Comments.retrieveByStudent", query="SELECT c FROM Comment AS c WHERE c.Stud=:Stud")

})
public class Comment
	extends AbstractEntityUnit
	implements Comparable<Comment>, Serializable{
    //COLUMNS
    @JoinColumn(name="Student_Id", referencedColumnName="PId", nullable=false)
    @ManyToOne(optional=false)
    private Student Stud;

    @JoinColumn(name="Event_Id", referencedColumnName="PId", nullable=false)
    @ManyToOne(optional=false)
    private Event Evt;

    @Column(name="Comment", nullable=false, length=2000)
    private String comment;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CDate", nullable=false, insertable=true, updatable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date CDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MDate", insertable=false, updatable=true, columnDefinition="TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date MDate;

    //CONSTRUCTS
    public Comment(){
	super();
    }

    public Comment(String comment){
	this.comment=comment.trim();
    }

    //PROPERTIES
    public Student getStudent(){
	return Stud;
    }

    public void setStudent(Student Stud){
	this.Stud=Stud;
    }

    public Event getEvent(){
	return Evt;
    }

    public void setEvent(Event Evt){
	this.Evt=Evt;
    }

    public String getComment(){
	return comment;
    }

    public void setComment(String comment){
	this.comment=comment;
    }

    /**
     * This field is inserted only once automatically upon row creation.
    */
    public String getCDate(){
	return CDate.toString();
    }

     /**
     * This field is updated automatically receives last update date.
     */
    public String getMDate(){
	return ((MDate==null) ? "-" : MDate.toString());
    }

    //IMPLEMENTATION
    @Override
    public int compareTo(Comment el){
	return (this.getMDate().compareTo(el.getMDate()));
    }

    //OVERRIDES
    @Override
    public boolean equals(Object el){
	if(!(el instanceof Comment))
	    return false;
	try{
	    Comment tstComment=(Comment)el;
	    if(!(Integer.compare(this.hashCode(), tstComment.hashCode())==0))
		return false;
	}catch(Exception ex){
	    ex.printStackTrace();
	    return false;
	}
	return true;
    }

    @Override
    public String toString(){
	try{
	    return ("/Relations.Comment{Id:"+this.getPid()+", Student_Email_Id:"+this.getStudent().getEmailId()+", Event:\""+this.getEvent().getTitle()+"\", Create_Date:\""+this.getCDate()+"\", Last_Modified:\""+this.getMDate()+"\"}");
	}catch(Exception ex){
	    ex.printStackTrace();
	    return ex.toString();
	}
    }
}
