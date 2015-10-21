package edu.iit.sat.itmd4515.abhimani.mp2.test_entities;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractTestJUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Student;
import edu.iit.sat.itmd4515.abhimani.mp2.relations.StudentLogin;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
public class Test4_Student
	extends AbstractTestJUnit{
    //CRUD - calls
    @Override
    protected void create()
	    throws Exception{
	Student s1=new Student("Ankit", "Bhimani", 'M', 3125365229L, "abhimani@hawk.iit.edu", true, "Has undertaken Java EE in the first semester."),
		s2=new Student("Scott", "S", 'M', 0, "spyrison@iit.edu", false),
		s3=new Student("Leah", "Burrati", 'F', 3125555555L, "lea@hawk.iit.edu", false, "Final year student.");
	StudentLogin sl1=new StudentLogin("abhimani");
	sl1.setPassword("hello_world");
	s1.setAuth(sl1);
	s2.setAuth(new StudentLogin("sspyrison"));
	s3.setAuth(new StudentLogin("lleah"));
	em.persist(s1);
	em.persist(s2);
	em.persist(s3);
	em.flush();
	System.out.println("\n-->> 3 records inserted.\n");
	assertNotNull("/Entities/Student s1->Id : ", s1.getPid());
	assertEquals("/Entities/Student s1->EmailId : ", s1.getEmailId(), "abhimani@hawk.iit.edu");
	assertNotNull("/Entities/Student s2->Id : ", s2.getPid());
	assertEquals("/Entities/Student s2->EmailId : ", s2.getEmailId(), "spyrison@iit.edu");
	assertNotNull("/Entities/Student s3->Id : ", s3.getPid());
	assertEquals("/Entities/Student s3->EmailId : ", s3.getEmailId(), "lea@hawk.iit.edu");
    }

    @Override
    protected void retrieve()
	    throws Exception{
	List<Student> ss;
	ss=em.createNamedQuery("Students.retrieveAll", Student.class).getResultList();
	Collections.sort(ss);
	for(Student s:ss){
	    System.out.println(s.toString());
	    assertNotNull(s.toString(), s);
	}
	System.out.println("\n-->> "+ss.size()+" records retrieved.\n");
    }

    @Override
    protected void update()
	    throws Exception{
	Student s;
	s=em.createNamedQuery("Students.findByEmailId", Student.class).setParameter("EmailId", "spyrison@iit.edu").getSingleResult();
	if(s!=null){
	    s.setLName("Spyrison");
	    s.setNotifyEvents(true);
	    s.setSpecial("-- This is an updated value --");
	}
	em.persist(s);
	em.flush();
	System.out.println("\n-->> 1 record updated : /Entities.Student{Name:{First:\"Scott\", Last:\"Spyrison\"}, Contact:{Phone:0, EmailId:spyrison@iit.edu}, NotifyEvents:true, Special:\"-- This is an updated value --\"}\n");
	assertEquals(s.getLName(), "Spyrison");
	assertTrue(s.isNotifyEvents());
	assertEquals(s.getSpecial(), "-- This is an updated value --");
    }

    @Override
    protected void delete()
	    throws Exception{
	Student s;
	s=em.createNamedQuery("Students.findByEmailId", Student.class).setParameter("EmailId", "lea@hawk.iit.edu").getSingleResult();
	if(s!=null)
	    em.remove(s);
	forceCommit();
	System.out.println("\n-->> 1 record deleted : /Entities.Student{Name:{First:\"Leah\", Last:\"Burrati\"}}\n");
	assertNull(em.createNamedQuery("Students.findByEmailId", Student.class).setParameter("EmailId", "lea@hawk.iit.edu").getSingleResult());
    }
}
