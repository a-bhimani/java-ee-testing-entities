package edu.iit.sat.itmd4515.abhimani.mp2.test_subrelations;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractTestJUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Department;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Event;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Student;
import java.util.List;
import javax.persistence.NoResultException;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
public class Test7_StudentAttendsEvent
	extends AbstractTestJUnit{
    //CRUD - calls
    @Override
    protected void create()
	    throws Exception{
	int ix=0;
	Student s;
	Event e1;
	List<Event> evts;
	s=em.createNamedQuery("Students.findByEmailId", Student.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
	em.flush();
	evts=em.createNamedQuery("Events.retrieveByDepartment", Event.class).setParameter("Dept", em.createNamedQuery("Departments.findByName", Department.class).setParameter("Name", "College of Architecture").getSingleResult()).getResultList();
	em.flush();
	for(Event e:evts){
	    s.attendEvent(e);
	    em.persist(s);
	    em.flush();
	    ix++;
	}
	System.out.println("\n-->> "+ix+" records inserted.\n");
	s=em.createNamedQuery("Students.findByEmailId", Student.class).setParameter("EmailId", "spyrison@iit.edu").getSingleResult();
	e1=evts.get(0);
	e1.addStudent(s);
	em.persist(e1);
	em.flush();
	System.out.println("\n-->> 1 records inserted.\n");
	assertTrue(em.createNamedQuery("Events.findById", Event.class).setParameter("EventId", e1.getPid()).getSingleResult().getStudentList().contains(s));
    }

    @Override
    protected void retrieve()
	    throws Exception{
	int ix=0;
	Student s;
	Event evt;
	s=em.createNamedQuery("Students.findByEmailId", Student.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
	evt=em.createNamedQuery("Events.retrieveByDepartment", Event.class).setParameter("Dept", em.createNamedQuery("Departments.findByName", Department.class).setParameter("Name", "College of Architecture").getSingleResult()).getResultList().get(0);
	em.flush();
	System.out.println("-------------------------------------------------\n-------------------------------------------------\nGrouped by a Student");
	System.out.println(s);
	for(Event e:s.getEventList()){
	    System.out.print(e);
	    assertNotNull(e.toString(), e);
	    ix++;
	    System.out.println();
	}
	System.out.println("-------------------------------------------------\n-------------------------------------------------\nGrouped by an Event");
	ix=0;
	for(Student s1:evt.getStudentList()){
	    System.out.print(s1);
	    assertNotNull(s.toString(), s);
	    ix++;
	    System.out.println();
	}
	System.out.println("-------------------------------------------------\n-------------------------------------------------\n");
	System.out.println("\n-->> "+ix+" records retrieved.\n");
    }

    @Override
    protected void update()
	    throws Exception{
	//NOTHING TO DO HERE
    }

    @Override
    protected void delete()
	    throws Exception{
	Student s;
	Event e;
	s=em.createNamedQuery("Students.findByEmailId", Student.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
	em.flush();
	e=s.getEventList().get(0);
	s.unAttendEvent(e);
	em.persist(s);
	forceCommit();
	System.out.println("\n-->> 1 record deleted : /Entities.Event{Department:\""+e.getDepartment().getName()+"\", Title:\""+e.getTitle()+"\"}\n");
	throw (new NoResultException());
    }
}
