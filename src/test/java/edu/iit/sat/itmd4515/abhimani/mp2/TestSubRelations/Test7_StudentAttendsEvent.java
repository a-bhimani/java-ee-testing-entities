package edu.iit.sat.itmd4515.abhimani.mp2.TestSubRelations;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractTestJUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.Entities.Departments;
import edu.iit.sat.itmd4515.abhimani.mp2.Entities.Events;
import edu.iit.sat.itmd4515.abhimani.mp2.Entities.Students;
import java.util.List;

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
	Students s;
	Events e1;
	List<Events> evts;
	s=em.createNamedQuery("Students.findByEmailId", Students.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
	em.flush();
	evts=em.createNamedQuery("Events.retrieveByDepartment", Events.class).setParameter("Dept", em.createNamedQuery("Departments.findByName", Departments.class).setParameter("Name", "College of Architecture").getSingleResult()).getResultList();
	em.flush();
	for(Events e:evts){
	    s.attendEvent(e);
	    em.persist(s);
	    em.flush();
	    ix++;
	}
	System.out.println("\n-->> "+ix+" records inserted.\n");
	s=em.createNamedQuery("Students.findByEmailId", Students.class).setParameter("EmailId", "spyrison@iit.edu").getSingleResult();
	e1=evts.get(0);
	e1.addStudent(s);
	em.persist(e1);
	em.flush();
	System.out.println("\n-->> 1 records inserted.\n");
    }

    @Override
    protected void retrieve()
	    throws Exception{
	int ix=0;
	Students s;
	Events evt;
	s=em.createNamedQuery("Students.findByEmailId", Students.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
	evt=em.createNamedQuery("Events.retrieveByDepartment", Events.class).setParameter("Dept", em.createNamedQuery("Departments.findByName", Departments.class).setParameter("Name", "College of Architecture").getSingleResult()).getResultList().get(0);
	em.flush();
	System.out.println("-------------------------------------------------\n-------------------------------------------------\nGrouped by a Student");
	System.out.println(s);
	for(Events e:s.getEventList()){
	    System.out.print(e);
	    ix++;
	    System.out.println();
	}
	System.out.println("-------------------------------------------------\n-------------------------------------------------\nGrouped by an Event");
	ix=0;
	for(Students s1:evt.getStudentList()){
	    System.out.print(s1);
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
	Students s;
	Events e;
	s=em.createNamedQuery("Students.findByEmailId", Students.class).setParameter("EmailId", "abhimani@hawk.iit.edu").getSingleResult();
	em.flush();
	e=s.getEventList().get(0);
	s.unAttendEvent(e);
	em.persist(s);
	em.flush();
	System.out.println("\n-->> 1 record deleted : /Entities.Events{Department:\""+e.getDepartment().getName()+"\", Title:\""+e.getTitle()+"\"}\n");
    }
}
