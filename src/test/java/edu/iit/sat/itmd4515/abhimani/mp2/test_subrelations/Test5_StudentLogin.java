package edu.iit.sat.itmd4515.abhimani.mp2.test_subrelations;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractTestJUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Student;
import edu.iit.sat.itmd4515.abhimani.mp2.relations.StudentLogin;

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
	Student s=em.createNamedQuery("Students.findByEmailId", Student.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
	s.getAuth().setPassword(TEST_PWD);
	em.persist(s);
	em.flush();
	System.out.println("Password updated successfully : /Relations.StudentLogin{EmailId:\"abhimani@hawk.iit.edu\", password:\""+NEW_PWD+"\"}\n");
    }

    @Override
    protected void retrieve()
	    throws Exception{
	StudentLogin sl=em.createNamedQuery("Students.findByEmailId", Student.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult().getAuth();
	System.out.println("Login authentication with credentials : /Relations.StudentLogin{EmailId:\"abhimani@hawk.iit.edu\", password:\""+NEW_PWD+"\"}\n");
	System.out.println(sl.matchLogin(NEW_PWD));
	System.out.println();
	System.out.println();
	System.out.println();
	System.out.println("Login authenticated with credentials : /Relations.StudentLogin{EmailId:\"abhimani@hawk.iit.edu\", password:\""+TEST_PWD+"\"}\n");
	System.out.println(sl.matchLogin(TEST_PWD));
    }

    @Override
    protected void update()
	    throws Exception{
	Student s=em.createNamedQuery("Students.findByEmailId", Student.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
	s.getAuth().setPassword(NEW_PWD);
	em.persist(s);
	em.flush();
	System.out.println("Password updated successfully : /Relations.StudentLogin{EmailId:\"abhimani@hawk.iit.edu\", password:\""+NEW_PWD+"\"}\n");
    }

    @Override
    protected void delete()
	    throws Exception{
	//NOTHING TO DO HERE
//	Student s=em.createNamedQuery("Student.findByEmailId", Student.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
//	if(s.auth!=null)
//	    em.remove(s.auth);
//	System.out.println("Login dropped successfully : /Relations.StudentLogin{username:\""+TEST_USR+"\", password:\""+NEW_PWD+"\"}\n");
    }
}
