package edu.iit.sat.itmd4515.abhimani.mp2.Entities;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractEntityUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.Relations.StudentLogin;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Students.retrieveAll", query="SELECT s FROM Students AS s"),
    @NamedQuery(name="Students.findByNumber", query="SELECT s FROM Students s WHERE s.Student_Number=:Number"),
    @NamedQuery(name="Students.findByEmailId", query="SELECT s FROM Students s WHERE s.EmailId=:EmailId")
})
public class Students
	extends AbstractEntityUnit
	implements Comparable<Students>{
    //COLUMNS
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Student_Id", nullable=false)
    private int PId;

    @Column(name="Student_Number", nullable=false, length=36, unique=true, updatable=false)
    private String Student_Number;

    @Column(name="FName", nullable=false, length=255)
    private String FName;

    @Column(name="LName", nullable=false, length=255)
    private String LName;

    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."+"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"+"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid Email Id.")
    @Column(name="EmailId", nullable=false, length=255, unique=true)
    private String EmailId;

    @Column(name="Gender", nullable=false)
    private char Gender='M'; //M, F

    @Column(name="Phone", nullable=false, length=10)
    @Min(1000000000)
    private long Phone=0;

    @Column(name="NotifyEvents", nullable=false)
    private boolean NotifyEvents;

    @Column(name="Special", length=2000)
    private String Special;

    @Embedded
    private StudentLogin auth;

    @ManyToMany(cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    @JoinTable(
	    name="event_student_attends",
	    joinColumns={
		@JoinColumn(name="Student_Id", referencedColumnName="Student_Id")},
	    inverseJoinColumns={
		@JoinColumn(name="Event_Id", referencedColumnName="Event_Id")})
    private List<Events> lstEvents;

    //CONSTRUCTS
    public Students(){
	this.PId=0;
	this.Student_Number=UUID.randomUUID().toString();
    }

    public Students(String FName, String LName, char Gender, long Phone, String EmailId, boolean NotifyEvents, String Special){
	this.Student_Number=UUID.randomUUID().toString();
	this.FName=FName.trim();
	this.LName=LName.trim();
	this.Gender=Gender;
	this.Phone=Phone;
	this.EmailId=EmailId.trim();
	this.NotifyEvents=NotifyEvents;
	this.Special=Special.trim();
    }

    public Students(String FName, String LName, char Gender, long Phone, String EmailId, boolean NotifyEvents){
	this.Student_Number=UUID.randomUUID().toString();
	this.FName=FName.trim();
	this.LName=LName.trim();
	this.Gender=Gender;
	this.Phone=Phone;
	this.EmailId=EmailId.trim();
	this.NotifyEvents=NotifyEvents;
    }

    //PROPERTIES
    public int getPid(){
	return this.PId;
    }

    public StudentLogin getAuth(){
	return this.auth;
    }

    public void setAuth(StudentLogin auth){
	this.auth=auth;
    }

    public String getStudent_Number(){
	return Student_Number;
    }

    public String getFName(){
	return FName;
    }

    public void setFName(String FName){
	this.FName=FName.trim();
    }

    public String getLName(){
	return LName;
    }

    public void setLName(String LName){
	this.LName=LName.trim();
    }

    public char getGender(){
	return Gender;
    }

    public void setGender(char Gender){
	this.Gender=Gender;
    }

    public long getPhone(){
	return Phone;
    }

    public void setPhone(long Phone){
	this.Phone=Phone;
    }

    public String getEmailId(){
	return EmailId;
    }

    public void setEmailId(String EmailId){
	this.EmailId=EmailId.trim();
    }

    public boolean isNotifyEvents(){
	return NotifyEvents;
    }

    public void setNotifyEvents(boolean NotifyEvents){
	this.NotifyEvents=NotifyEvents;
    }

    public String getSpecial(){
	return Special;
    }

    public void setSpecial(String Special){
	this.Special=Special.trim();
    }

    //RELATIONS
    public List<Events> getEventList(){
	return lstEvents;
    }

    public void attendEvent(Events event){
	lstEvents.add(event);
    }

    public void unAttendEvent(Events event){
	lstEvents.remove(event);
    }

    //IMPLEMENTATION
    @Override
    public int compareTo(Students el){
	return (this.getFName().compareTo(el.getFName()));
    }

    //OVERRIDES
    @Override
    public int hashCode(){
	return ((this.getPid()>0) ? Integer.hashCode(this.getPid()) : 0);
    }

    @Override
    public boolean equals(Object el){
	if(!(el instanceof Students))
	    return false;
	try{
	    Students tstStudent=(Students)el;
	    if(!(Integer.compare(this.hashCode(), tstStudent.hashCode())==0))
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
	    return ("/Entities.Students{Id:"+this.getPid()+", Number:"+this.getStudent_Number()+", Name:{First:\""+this.getFName()+"\", Last:\""+this.getLName()+"\"}, Contact:{Phone:"+this.getPhone()+", EmailId:"+this.getEmailId()+"}, NotifyEvents:"+Boolean.toString(this.isNotifyEvents())+", Special:{Length:"+((this.getSpecial()==null) ? 0 : this.getSpecial().length())+"}}");
	}catch(Exception ex){
	    ex.printStackTrace();
	    return ex.toString();
	}
    }
}
