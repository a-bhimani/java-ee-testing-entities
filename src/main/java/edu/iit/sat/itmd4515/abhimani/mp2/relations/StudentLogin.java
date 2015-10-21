package edu.iit.sat.itmd4515.abhimani.mp2.relations;

import edu.iit.sat.itmd4515.abhimani.mp2.EncryptText;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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

    //CONSTRUCT
    private StudentLogin(){
	super();
    }

    /**
     * You must not initialize and use an instance of this class with the
     * default constructor, instead use the Student.getAuth() and
     * Student.setAuth(StudentLogin auth) methods to access or update the
     * credentials; Use this constructor only to use the matchLogin(String
     * Password) and authenticate the user.
     */
    public StudentLogin(String Username){
	this.Username=Username.trim();
    }

    //PROPERTIES
    public String getUsername(){
	return this.Username;
    }

    public String getPasswordKey(){
	return Password;
    }

    /**
     * Password is base64encoded and saved.
     */
    public void setPassword(String Password){
	this.Password=EncryptText.base64encode(Password.trim());
    }

    /**
     * Can be used to authenticate the user.
     */
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
