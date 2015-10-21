package edu.iit.sat.itmd4515.abhimani.mp2.test_entities;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractTestJUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Department;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Event;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Venue;
import edu.iit.sat.itmd4515.abhimani.mp2.EventState;
import java.util.GregorianCalendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
public class Test6_Event
	extends AbstractTestJUnit{
    //CRUD - calls
    private long UPDATE_ID=0;
    private long DELETE_ID=0;

    @Override
    protected void create()
	    throws Exception{
	Event e1=new Event("International Student Orientation", "All F1 and J1 international students (both graduate and undergraduate) are required to attend International Student SOAR.", (new GregorianCalendar(2015, 8, 21, 9, 30, 0)).getTime(), (new GregorianCalendar(2015, 8, 21, 13, 0, 0)).getTime()),
		e2=new Event("ITA Tech Challenge", "In its 6th year, the ITA Tech Challenge is a programming and coding skills competition for students at targeted Midwest universities.", (new GregorianCalendar(2015, 9, 21, 17, 0, 0)).getTime(), (new GregorianCalendar(2015, 9, 21, 19, 30, 0)).getTime()),
		e3=new Event("Fall 2015 Career Fair", "If you are in need professional attire, OCL will have a clothing closet in the lower level of Hermann Hall.", (new GregorianCalendar(2015, 9, 24, 12, 40, 0)).getTime(), (new GregorianCalendar(2015, 9, 24, 16, 0, 0)).getTime()),
		e4=new Event("Party", "", (new GregorianCalendar(2015, 9, 28, 10, 30, 0)).getTime(), (new GregorianCalendar(2015, 9, 29, 3, 30, 0)).getTime(), EventState.Hold);
	Department d1, d2, d3;
	Venue v1, v2;
	d1=em.createNamedQuery("Departments.findByName", Department.class).setParameter("Name", "School of Applied Technology").getSingleResult();
	d2=em.createNamedQuery("Departments.findByName", Department.class).setParameter("Name", "Armour College of Engineering").getSingleResult();
	d3=em.createNamedQuery("Departments.findByName", Department.class).setParameter("Name", "College of Architecture").getSingleResult();
	v1=em.createNamedQuery("Venues.findByName", Venue.class).setParameter("Name", "Herman Hall Ballroom").getSingleResult();
	v2=em.createNamedQuery("Venues.findByName", Venue.class).setParameter("Name", "McCormick Tribute Campus Center").getSingleResult();
	em.flush();
	e1.setDepartment(d3);
	e2.setDepartment(d1);
	e3.setDepartment(d1);
	e4.setDepartment(d2);
	e1.setVenue(v2);
	e2.setVenue(v2);
	e3.setVenue(v1);
	e4.setVenue(v1);
	em.persist(e1);
	em.persist(e2);
	em.persist(e3);
	em.persist(e4);
	em.flush();
	UPDATE_ID=e4.getPid();
	DELETE_ID=e2.getPid();
	System.out.println("\n-->> 4 records inserted.\n");
	assertNotNull("/Entities/Event e1->Id : ", e1.getPid());
	assertEquals("/Entities/Event e1->Title : ", e1.getTitle(), "International Student Orientation");
	assertNotNull("/Entities/Event e2->Id : ", e2.getPid());
	assertEquals("/Entities/Event e2->Title : ", e2.getTitle(), "ITA Tech Challenge");
	assertNotNull("/Entities/Event e3->Id : ", e3.getPid());
	assertEquals("/Entities/Event e3->Title : ", e3.getTitle(), "Fall 2015 Career Fair");
	assertNotNull("/Entities/Event e4->Id : ", e4.getPid());
	assertEquals("/Entities/Event e4->Title : ", e4.getTitle(), "Party");
    }

    @Override
    protected void retrieve()
	    throws Exception{
	int ix=0;
	List<Department> ds;
	List<Venue> vs;
	System.out.println("-------------------------------------------------\n-------------------------------------------------\nGrouped by Departments");
	ds=em.createNamedQuery("Departments.retrieveAll", Department.class).getResultList();
	vs=em.createNamedQuery("Venues.retrieveAll", Venue.class).getResultList();
	em.flush();
	for(Department d:ds){
	    List<Event> es;
	    System.out.println(d.toString());
	    es=em.createNamedQuery("Events.retrieveByDepartment", Event.class).setParameter("Dept", d).getResultList();
	    for(Event evt:es){
		System.out.println(evt.toString());
		assertNotNull(evt.toString(), evt);
		ix++;
	    }
	    System.out.println();
	}
	System.out.println("\n-->> "+ix+" records retrieved.\n");
	System.out.println("-------------------------------------------------\n-------------------------------------------------\nGrouped by Venues");
	ix=0;
	for(Venue v:vs){
	    List<Event> es;
	    System.out.println(v.toString());
	    es=em.createNamedQuery("Events.retrieveByVenue", Event.class).setParameter("Ven", v).getResultList();
	    for(Event evt:es){
		System.out.println(evt.toString());
		assertNotNull(evt.toString(), evt);
		ix++;
	    }
	    System.out.println();
	}
	System.out.println("\n-->> "+ix+" records retrieved.\n");
	System.out.println("-------------------------------------------------\n-------------------------------------------------\n");
    }

    @Override
    protected void update()
	    throws Exception{
	Event e;
	Department d1;
	Venue v1;
	d1=em.createNamedQuery("Departments.findByName", Department.class).setParameter("Name", "College of Architecture").getSingleResult();
	v1=em.createNamedQuery("Venues.findByName", Venue.class).setParameter("Name", "The BOG").getSingleResult();
	em.flush();
	e=em.createNamedQuery("Events.findById", Event.class).setParameter("EventId", UPDATE_ID).getSingleResult();
	if(e!=null){
	    e.setDepartment(d1);
	    e.setVenue(v1);
	    e.setTitle("Homecoming Bog Party");
	    e.setDescription("-- This is an updated value --");
	    e.setAState(EventState.Active);
	}
	em.persist(e);
	em.flush();
	System.out.println("\n-->> 1 record updated : /Entities.Event{Department:\"College of Architecture\", Venue:\"The BOG\", Title:\"Homecoming Bog Party\", Description:\"-- This is an updated value --\", State:ACTIVE}\n");
	assertSame(e.getDepartment(), d1);
	assertSame(e.getVenue(), v1);
	assertEquals(e.getTitle(), "Homecoming Bog Party");
	assertEquals(e.getDescription(), "-- This is an updated value --");
    }

    @Override
    protected void delete()
	    throws Exception{
	Event e;
	e=em.createNamedQuery("Events.findById", Event.class).setParameter("EventId", DELETE_ID).getSingleResult();
	if(e!=null)
	    em.remove(e);
	forceCommit();
	System.out.println("\n-->> 1 record deleted : /Entities.Event{Department:\"School of Applied Technology\", Venue:\"McCormick Tribute Campus Center\", Title:\"ITA Tech Challenge\", Description:\"In its 6th year, the ITA Tech Challenge is a programming and coding skills competition for students at targeted Midwest universities.\", State:ACTIVE}\n");
	assertNull(em.createNamedQuery("Events.findById", Event.class).setParameter("EventId", DELETE_ID).getSingleResult());
    }
}
