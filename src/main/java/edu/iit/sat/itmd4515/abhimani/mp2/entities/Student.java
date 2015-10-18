package edu.iit.sat.itmd4515.abhimani.mp2.entities;

import edu.iit.sat.itmd4515.abhimani.mp2.SuperEntityUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.relations.StudentLogin;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
@Entity
@Table(name="students")
@NamedQueries({
    @NamedQuery(name="Students.retrieveAll", query="SELECT s FROM Student AS s"),
    @NamedQuery(name="Students.findByNumber", query="SELECT s FROM Student AS s WHERE s.Student_Number=:Number"),
    @NamedQuery(name="Students.findByEmailId", query="SELECT s FROM Student AS s WHERE s.EmailId=:EmailId")
})
public class Student
	extends SuperEntityUnit
	implements Comparable<Student>, Serializable{
    //COLUMNS
    @Column(name="Student_Number", nullable=false, length=36, unique=true, updatable=false)
    private final String Student_Number;

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

    @OneToOne(cascade=CascadeType.PERSIST, optional=false, fetch=FetchType.LAZY, orphanRemoval=false)
    @JoinColumn(name="LoginId", unique=true, nullable=false, updatable=false)
    private StudentLogin auth;

    @ManyToMany(cascade=CascadeType.REMOVE, fetch=FetchType.EAGER)
    @JoinTable(
	    name="event_student_attends",
	    joinColumns={
		@JoinColumn(name="Student_Id", referencedColumnName="PId")},
	    inverseJoinColumns={
		@JoinColumn(name="Event_Id", referencedColumnName="PId")})
    private List<Event> lstEvents;

    //CONSTRUCTS
    public Student(){
	super();
	this.Student_Number=UUID.randomUUID().toString();
    }

    public Student(String FName, String LName, char Gender, long Phone, String EmailId, boolean NotifyEvents, String Special){
	this.Student_Number=UUID.randomUUID().toString();
	this.FName=FName.trim();
	this.LName=LName.trim();
	this.Gender=Gender;
	this.Phone=Phone;
	this.EmailId=EmailId.trim();
	this.NotifyEvents=NotifyEvents;
	this.Special=Special.trim();
    }

    public Student(String FName, String LName, char Gender, long Phone, String EmailId, boolean NotifyEvents){
	this.Student_Number=UUID.randomUUID().toString();
	this.FName=FName.trim();
	this.LName=LName.trim();
	this.Gender=Gender;
	this.Phone=Phone;
	this.EmailId=EmailId.trim();
	this.NotifyEvents=NotifyEvents;
    }

    //PROPERTIES
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
    public List<Event> getEventList(){
	return lstEvents;
    }

    public void attendEvent(Event event){
	lstEvents.add(event);
    }

    public void unAttendEvent(Event event){
	lstEvents.remove(event);
    }

    //IMPLEMENTATION
    @Override
    public int compareTo(Student el){
	return (this.getEmailId().compareTo(el.getEmailId()));
    }

    //OVERRIDES
    @Override
    public boolean equals(Object el){
	if(!(el instanceof Student))
	    return false;
	try{
	    Student tstStudent=(Student)el;
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
