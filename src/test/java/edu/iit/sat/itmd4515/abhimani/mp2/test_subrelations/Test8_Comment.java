package edu.iit.sat.itmd4515.abhimani.mp2.test_subrelations;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractTestJUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Student;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Event;
import edu.iit.sat.itmd4515.abhimani.mp2.relations.Comment;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
public class Test8_Comment
	extends AbstractTestJUnit{
    //CRUD - calls
    private static long UPDATE_ID=0;
    private static long DELETE_ID=0;

    @Override
    protected void create()
	    throws Exception{
	Comment c1=new Comment("Hi"),
		c2=new Comment("Hello!"),
		c3=new Comment("Tomorrow is Tuesday."),
		c4=new Comment("Java EE is fun.");
	Event e1, e2;
	Student s1, s2;
	e1=em.createNamedQuery("Events.retrieveAll", Event.class).getResultList().get(0);
	e2=em.createNamedQuery("Events.retrieveAll", Event.class).getResultList().get(1);
	s1=em.createNamedQuery("Students.findByEmailId", Student.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
	s2=em.createNamedQuery("Students.findByEmailId", Student.class).setParameter("EmailId", "spyrison@iit.edu").getSingleResult();
	em.flush();
	c1.setEvent(e1);
	c2.setEvent(e2);
	c3.setEvent(e2);
	c4.setEvent(e1);
	c1.setStudent(s2);
	c2.setStudent(s1);
	c3.setStudent(s2);
	c4.setStudent(s1);
	em.persist(c1);
	em.persist(c2);
	em.persist(c3);
	em.persist(c4);
	em.flush();
	UPDATE_ID=c2.getPid();
	DELETE_ID=c3.getPid();
	System.out.println("\n-->> 4 records inserted.\n");
	assertNotNull("/Relations/Comment c1->Id : ", c1.getPid());
	assertEquals("/Relations/Comment c1->Comment : ", c1.getComment(), "Hi");
	assertNotNull("/Relations/Comment c2->Id : ", c2.getPid());
	assertEquals("/Relations/Comment c2->Comment : ", c2.getComment(), "Hello!");
	assertNotNull("/Relations/Comment c3->Id : ", c3.getPid());
	assertEquals("/Relations/Comment c3->Comment : ", c3.getComment(), "Tomorrow is Tuesday.");
	assertNotNull("/Relations/Comment c4->Id : ", c4.getPid());
	assertEquals("/Relations/Comment c4->Comment : ", c4.getComment(), "Java EE is fun.");
    }

    @Override
    protected void retrieve()
	    throws Exception{
	List<Comment> cs;
	cs=em.createNamedQuery("Comments.retrieveAll", Comment.class).getResultList();
	Collections.sort(cs);
	for(Comment c:cs){
	    System.out.println(c);
	    assertNotNull(c.toString(), c);
	}
	System.out.println("\n-->> "+cs.size()+" records retrieved.\n");
    }

    @Override
    protected void update()
	    throws Exception{
	Comment c;
	c=em.createNamedQuery("Comments.findById", Comment.class).setParameter("Id", UPDATE_ID).getSingleResult();
	c.setComment("-- This is an updated value --");
	em.persist(c);
	em.flush();
	System.out.println("\n-->> 1 record deleted : /Relations.Comment{Comment:\"\", Student_Email_Id:abhimani@hawk.iit.edu, Event:\""+c.getEvent().getTitle()+"\"}\n");
	assertEquals(c.getComment(), "-- This is an updated value --");
    }

    @Override
    protected void delete()
	    throws Exception{
	Comment c;
	c=em.createNamedQuery("Comments.findById", Comment.class).setParameter("Id", DELETE_ID).getSingleResult();
	if(c!=null)
	    em.remove(c);
	forceCommit();
	System.out.println("\n-->> 1 record deleted : /Relations.Comment{Comment:\"\", Student_Email_Id:spyrison@iit.edu}\n");
	assertNull(em.createNamedQuery("Comments.findById", Comment.class).setParameter("Id", DELETE_ID).getSingleResult());
    }
}
