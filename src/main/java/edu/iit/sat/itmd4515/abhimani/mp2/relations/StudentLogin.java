package edu.iit.sat.itmd4515.abhimani.mp2.relations;

import edu.iit.sat.itmd4515.abhimani.mp2.EncryptText;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Student;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
@Entity
@Table(name="students_login")
@NamedQueries({
    @NamedQuery(name="StudentLogin.authenticate", query="SELECT l FROM StudentLogin AS l WHERE l.Username=:uname AND l.Password IS NOT NULL")
})
public class StudentLogin
	implements Serializable{
    //COLUMNS
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    protected int LoginId;

    @Column(length=63, unique=true, updatable=false)
    private String Username;

    @Column(name="enc_pwd")
    private String Password;

    @OneToOne(mappedBy = "auth")
    private Student stud;
    //CONSTRUCT
    public StudentLogin(){
	super();
    }

    public StudentLogin(String Username){
	this.Username=Username.trim();
    }

    //PROPERTIES
    public Student getStudentDetails(){
	return this.stud;
    }

    public String getUsername(){
	return this.Username;
    }

    public String getPasswordKey(){
	return Password;
    }

    public void setPassword(String Password){
	this.Password=EncryptText.base64encode(Password.trim());
    }

    public boolean matchLogin(String Password){
	return this.getPasswordKey().equals(EncryptText.base64encode(Password));
    }

    //OVERRIDES
    @Override
    public int hashCode(){
	return 0;
    }

    @Override
    public boolean equals(Object el){
	if(!(el instanceof StudentLogin))
	    return false;
	try{
	    StudentLogin tstLogin=(StudentLogin)el;
	    if(!(Integer.compare(this.hashCode(), tstLogin.hashCode())==0))
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
	    return ("NOT_AUTHORIZED");
	}catch(Exception ex){
	    ex.printStackTrace();
	    return ex.toString();
	}
    }
}
