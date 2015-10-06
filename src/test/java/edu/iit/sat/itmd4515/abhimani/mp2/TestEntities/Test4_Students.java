package edu.iit.sat.itmd4515.abhimani.mp2.TestEntities;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractTestJUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.Entities.Students;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
public class Test4_Students
	extends AbstractTestJUnit{
    //CRUD - calls
    @Override
    protected void create()
	    throws Exception{
	Students s1=new Students("Ankit", "Bhimani", 'M', 3125365229L, "abhimani@hawk.iit.edu", true, "Has undertaken Java EE in the first semester."),
		s2=new Students("Scott", "S", 'M', 0, "spyrison@iit.edu", false),
		s3=new Students("Leah", "Burrati", 'F', 3125555555L, "lea@hawk.iit.edu", false, "Final year student.");
	em.persist(s1);
	em.persist(s2);
	em.persist(s3);
	em.flush();
	System.out.println("\n-->> 3 records inserted.\n");
    }

    @Override
    protected void retrieve()
	    throws Exception{
	List<Students> ss;
	ss=em.createNamedQuery("Students.retrieveAll", Students.class).getResultList();
	Collections.sort(ss);
	for(Students s:ss)
	    System.out.println(s.toString());
	System.out.println("\n-->> "+ss.size()+" records retrieved.\n");
    }

    @Override
    protected void update()
	    throws Exception{
	Students s;
	s=em.createNamedQuery("Students.findByEmailId", Students.class).setParameter("EmailId", "spyrison@iit.edu").getSingleResult();
	if(s!=null){
	    s.setLName("Spyrison");
	    s.setNotifyEvents(true);
	    s.setSpecial("-- This is an updated value --");
	}
	em.persist(s);
	em.flush();
	System.out.println("\n-->> 1 record updated : /Entities.Students{Name:{First:\"Scott\", Last:\"Spyrison\"}, Contact:{Phone:0, EmailId:spyrison@iit.edu}, NotifyEvents:true, Special:\"-- This is an updated value --\"}\n");
    }

    @Override
    protected void delete()
	    throws Exception{
	Students s;
	s=em.createNamedQuery("Students.findByEmailId", Students.class).setParameter("EmailId", "lea@hawk.iit.edu").getSingleResult();
	if(s!=null)
	    em.remove(s);
	System.out.println("\n-->> 1 record deleted : /Entities.Students{Name:{First:\"Leah\", Last:\"Burrati\"}}\n");
    }
}
