package edu.iit.sat.itmd4515.abhimani.mp2.Relations;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractEntityUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.EncryptText;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
@Embeddable
public class StudentLogin
	extends AbstractEntityUnit{
    @Column(name="pwd")
    private String Password;

    //CONSTRUCT
    public StudentLogin(){
    }

    //PROPERTIES
    public String getPasswordKey(){
	return EncryptText.base64decode(Password);
    }

    public void setPassword(String Password){
	this.Password=EncryptText.base64encode(Password.trim());
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
