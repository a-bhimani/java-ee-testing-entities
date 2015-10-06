package edu.iit.sat.itmd4515.abhimani.mp2.Entities;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractEntityUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.EventState;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name="event_store")
@NamedQueries({
    @NamedQuery(name="Events.retrieveAll", query="SELECT e FROM Events AS e"),
    @NamedQuery(name="Events.retrieveByDepartment", query="SELECT e FROM Events AS e WHERE e.Dept=:Dept"),
    @NamedQuery(name="Events.retrieveByVenue", query="SELECT e FROM Events AS e WHERE e.Ven=:Ven"),
    @NamedQuery(name="Events.findById", query="SELECT e FROM Events AS e WHERE e.PId=:EventId")
})
public class Events
	extends AbstractEntityUnit
	implements Comparable<Events>{
    //COLUMNS
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Event_Id", nullable=false)
    protected int PId;

    @Column(name="Title", nullable=false, length=255, unique=true)
    private String Title;

    @Column(name="Description", length=2000)
    private String Description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="EBegin", nullable=false)
    private Date EBegin;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="EEnd", nullable=false)
    private Date EEnd;

    @Column(name="AState", nullable=false)
    private EventState AState=EventState.Active;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CDate", nullable=false, insertable=true, updatable=false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date CDate=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="MDate", insertable=false, updatable=true, columnDefinition="TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date MDate;

    @JoinColumn(name="Event_Dept_Id", referencedColumnName="Dept_Id", nullable=false)
    @ManyToOne(optional=false)
    private Departments Dept;

    @JoinColumn(name="Event_Venue_Id", referencedColumnName="Venue_Id", nullable=false)
    @ManyToOne(optional=false)
    private Venues Ven;

    @ManyToMany(cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    @JoinTable(
	    name="event_student_attends",
	    joinColumns={
		@JoinColumn(name="Event_Id", referencedColumnName="Event_Id")},
	    inverseJoinColumns={
		@JoinColumn(name="Student_Id", referencedColumnName="Student_Id")})
    private List<Students> lstStudents;

    //CONSTRUCTS
    public Events(){
	this.PId=0;
    }

    public Events(Departments Dept, Venues Ven, String Title, String Description, Date EBegin, Date EEnd, EventState AState){
	this.Dept=Dept;
	this.Ven=Ven;
	this.Title=Title.trim();
	this.Description=Description.trim();
	this.EBegin=EBegin;
	this.EEnd=EEnd;
	this.AState=AState;
    }

    public Events(Departments Dept, Venues Ven, String Title, String Description, Date EBegin, Date EEnd){
	this.Dept=Dept;
	this.Ven=Ven;
	this.Title=Title.trim();
	this.Description=Description.trim();
	this.EBegin=EBegin;
	this.EEnd=EEnd;
    }

    public Events(String Title, String Description, Date EBegin, Date EEnd, EventState AState){
	this.Title=Title.trim();
	this.Description=Description.trim();
	this.EBegin=EBegin;
	this.EEnd=EEnd;
	this.AState=AState;
    }

    public Events(String Title, String Description, Date EBegin, Date EEnd){
	this.Title=Title.trim();
	this.Description=Description.trim();
	this.EBegin=EBegin;
	this.EEnd=EEnd;
    }

    //PROPERTIES
    public int getPid(){
	return this.PId;
    }

    public String getTitle(){
	return Title;
    }

    public void setTitle(String Title){
	this.Title=Title.trim();
    }

    public String getDescription(){
	return Description;
    }

    public void setDescription(String Description){
	this.Description=Description.trim();
    }

    public String getEBegin(){
	return EBegin.toString();
    }

    public void setEBegin(Date EBegin){
	this.EBegin=EBegin;
    }

    public String getEEnd(){
	return EEnd.toString();
    }

    public void setEEnd(Date EEnd){
	this.EEnd=EEnd;
    }

    public String getAState(){
	return AState.toString();
    }

    public void setAState(EventState AState){
	this.AState=AState;
    }

    public String getCDate(){
	return CDate.toString();
    }

    public String getMDate(){
	return ((MDate==null) ? "-" : MDate.toString());
    }

    //RELATIONS
    public Departments getDepartment(){
	return Dept;
    }

    public void setDepartment(Departments Dept){
	this.Dept=Dept;
    }

    public Venues getVenue(){
	return Ven;
    }

    public void setVenue(Venues Ven){
	this.Ven=Ven;
    }

    public List<Students> getStudentList(){
	return lstStudents;
    }

    public void addStudent(Students Stud){
	this.lstStudents.add(Stud);
    }

    //IMPLEMENTATION
    @Override
    public int compareTo(Events el){
	return (this.getTitle().compareTo(el.getTitle()));
    }

    //OVERRIDES
    @Override
    public int hashCode(){
	return ((this.getPid()>0) ? Integer.hashCode(this.getPid()) : 0);
    }

    @Override
    public boolean equals(Object el){
	if(!(el instanceof Events))
	    return false;
	try{
	    Events tstEvt=(Events)el;
	    if(!(Integer.compare(this.hashCode(), tstEvt.hashCode())==0))
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
	    return ("/Entities.Events{Id:"+this.getPid()+", Dept_Id:\""+this.getDepartment().getPid()+"\", Department:\""+this.getDepartment().getName()+"\", Venue_Id:\""+this.getVenue().getPid()+"\", Venue:\""+this.getVenue().getTitle()+"\", Title:\""+this.getTitle()+"\", Description:{Length:"+((this.getDescription()==null) ? 0 : this.getDescription().length())+"}, EBegin:\""+this.getEBegin()+"\", EEnd:\""+this.getEEnd()+"\", State:"+this.getAState().toUpperCase()+", Create_Date:\""+this.getCDate()+"\", Last_Modified:\""+this.getMDate()+"\"}");
	}catch(Exception ex){
	    ex.printStackTrace();
	    return ex.toString();
	}
    }
}
