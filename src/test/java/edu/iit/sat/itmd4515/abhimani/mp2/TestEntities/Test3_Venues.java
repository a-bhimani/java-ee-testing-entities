package edu.iit.sat.itmd4515.abhimani.mp2.TestEntities;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractTestJUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.Venue_Type;
import edu.iit.sat.itmd4515.abhimani.mp2.Country_States;
import edu.iit.sat.itmd4515.abhimani.mp2.Entities.Venues;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
public class Test3_Venues
	extends AbstractTestJUnit{
    //CRUD - calls
    @Override
    protected void create()
	    throws Exception{
	Venues v1=new Venues("Herman Hall Ballroom", Venue_Type.Hall, "3241 S. Federal Street, Hermann Hall", "Chicago", Country_States.IL, 60616),
		v2=new Venues("Bog", Venue_Type.Hall, "3241 S. Federal Street, Hermann Hall", "Chicago", Country_States.IL, 60616),
		v3=new Venues("McCormick Tribute Campus Center", Venue_Type.Exhibition, "3300 S. Federal Street", "MTCC", "Chicago", Country_States.IL, 60616, 3793),
		v4=new Venues("The Wide Open Area", Venue_Type.Open, "1430 NW Blvd", "Vineland", Country_States.NJ, 8360);
	em.persist(v1);
	em.persist(v2);
	em.persist(v3);
	em.persist(v4);
	em.flush();
	System.out.println("\n-->> 4 records inserted.\n");
    }

    @Override
    protected void retrieve()
	    throws Exception{
	List<Venues> vs;
	vs=em.createNamedQuery("Venues.retrieveAll", Venues.class).getResultList();
	Collections.sort(vs);
	for(Venues v:vs)
	    System.out.println(v.toString());
	System.out.println("\n-->> "+vs.size()+" records retrieved.\n");
    }

    @Override
    protected void update()
	    throws Exception{
	Venues v;
	v=em.createNamedQuery("Venues.findByName", Venues.class).setParameter("Name", "Bog").getSingleResult();
	if(v!=null){
	    v.setTitle("The BOG");
	    v.setType(Venue_Type.Room);
	    v.setAddr2("-- This is an updated value --");
	}
	em.persist(v);
	em.flush();
	System.out.println("\n-->> 1 record updated : /Entities.Venues{Title:\"The BOG\", Type:\"Room\", Addr2:\"-- This is an updated value --\"}\n");
    }

    @Override
    protected void delete()
	    throws Exception{
	Venues v;
	v=em.createNamedQuery("Venues.findByName", Venues.class).setParameter("Name", "The Wide Open Area").getSingleResult();
	if(v!=null)
	    em.remove(v);
	System.out.println("\n-->> 1 record deleted : /Entities.Venues{Title:\"The Wide Open Area\", Type:\"Open\"}\n");
    }
}
