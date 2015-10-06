package edu.iit.sat.itmd4515.abhimani.mp2.TestSubRelations;

import edu.iit.sat.itmd4515.abhimani.mp2.EncryptText;
import edu.iit.sat.itmd4515.abhimani.mp2.AbstractTestJUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.Entities.Students;
import edu.iit.sat.itmd4515.abhimani.mp2.Relations.StudentLogin;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
public class Test5_StudentLogin
	extends AbstractTestJUnit{
    //CRUD - calls
    private static final String TEST_PWD="hello_world";
    private static final String NEW_PWD="dummy";

    @Override
    protected void create()
	    throws Exception{
	Students s=em.createNamedQuery("Students.findByEmailId", Students.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
	StudentLogin sl=new StudentLogin();
	sl.setPassword(TEST_PWD);
	s.setAuth(sl);
	em.persist(s);
	em.flush();
	System.out.println("Login created successfully : /Relations.StudentLogin{EmailId:\"abhimani@hawk.iit.edu\", password:\""+TEST_PWD+"\"}\n");
    }

    @Override
    protected void retrieve()
	    throws Exception{
	Students s=em.createNamedQuery("Students.findByEmailId", Students.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
	if(s.getAuth().getPasswordKey().equals(EncryptText.base64encode("dummy")))
	    System.out.println("Login not authenticated with credentials : /Relations.StudentLogin{EmailId:\"abhimani@hawk.iit.edu\", password:\"dummy\"}\n");
	if(s.getAuth().getPasswordKey().equals(EncryptText.base64encode(TEST_PWD)))
	    System.out.println("Login authenticated with credentials : /Relations.StudentLogin{EmailId:\"abhimani@hawk.iit.edu\", password:\""+TEST_PWD+"\"}\n");
    }

    @Override
    protected void update()
	    throws Exception{
	Students s=em.createNamedQuery("Students.findByEmailId", Students.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
	StudentLogin sl=new StudentLogin();
	sl.setPassword(NEW_PWD);
	s.setAuth(sl);
	em.persist(s);
	em.flush();
	System.out.println("Password updated successfully : /Relations.StudentLogin{EmailId:\"abhimani@hawk.iit.edu\", password:\""+NEW_PWD+"\"}\n");
    }

    @Override
    protected void delete()
	    throws Exception{
	//NOTHING TO DO HERE
//	Students s=em.createNamedQuery("Students.findByEmailId", Students.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
//	if(s.auth!=null)
//	    em.remove(s.auth);
//	System.out.println("Login dropped successfully : /Relations.StudentLogin{username:\""+TEST_USR+"\", password:\""+NEW_PWD+"\"}\n");
    }
}
