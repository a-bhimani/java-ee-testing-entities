package edu.iit.sat.itmd4515.abhimani.mp2.TestEntities;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractTestJUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.Entities.Departments;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
public class Test1_Departments
	extends AbstractTestJUnit{
    //CRUD - calls
    @Override
    protected void create()
	    throws Exception{
	Departments d1=new Departments("School of Applied Technology", "Programs and courses at the School of Applied Technology provide a blend of theoretical content and practical application that utilizes a hands-on, reality-based approach to education. This allows students to apply what they learn in class to solve real-life problems. Students learn about new and emerging technologies and the application, integration, and administrative practices used in the effective management of these technologies."),
		d2=new Departments("Stuart School of Business", "The Stuart School’s curriculum is centered on the concepts of creativity, entrepreneurship, incisive decision-making, leadership, innovation, and sustainability. These concepts develop technical and quantitative expertise, with a broad foundation in the professional knowledge that is key to success in the global workplace."),
		d3=new Departments("Kent College"),
		d4=new Departments("College of Architecture", "IIT Architecture welcomes students, faculty, and guests from around the globe who share our interest in “Rethinking the Metropolis.” We will conduct research; we will analyze existing phenomena; we will learn from other disciplines."),
		d5=new Departments("Armour College of Engineering", "IIT Armour College of Engineering (ACE) traces its roots to Armour Institute; founded in 1890. Armour Institute was founded after minister Frank W. Gunsaulus gave what is now known as the “Million Dollar Sermon.” At a time when higher education was reserved for society’s elite, Mr. Gunsaulus called for the donation of a million dollars to build a school that would prepare students of all backgrounds for leadership roles primarily as engineers in a changing industrial society. Philip Danforth Armour a prominent Chicago meat packer and grain merchant heard Gunsaulus’ call and made the donation. Armour also stipulated that Gunsaulus become the first president of the school, a position Gunsaulus held from the school’s founding in 1890 until his death in 1921. Some innovative inventions that stem from research at ACE are the cell phone, magnetic tape recording, and barcodes.");
	em.persist(d1);
	em.persist(d2);
	em.persist(d3);
	em.persist(d4);
	em.persist(d5);
	em.flush();
	System.out.println("\n-->> 5 records inserted.\n");
    }

    @Override
    protected void retrieve()
	    throws Exception{
	List<Departments> ds;
	ds=em.createNamedQuery("Departments.retrieveAll", Departments.class).getResultList();
	Collections.sort(ds);
	for(Departments d:ds)
	    System.out.println(d.toString());
	System.out.println("\n-->> "+ds.size()+" records retrieved.\n");
    }

    @Override
    protected void update()
	    throws Exception{
	Departments d;
	d=em.createNamedQuery("Departments.findByName", Departments.class).setParameter("Name", "Kent College").getSingleResult();
	if(d!=null){
	    d.setName("Kent College of Law");
	    d.setDescription("-- This is an updated value --");
	}
	em.persist(d);
	em.flush();
	System.out.println("\n-->> 1 record updated : /Entities.Departments{Name:\"Kent College of Law\", Description:\"-- This is an updated value --\"}\n");
    }

    @Override
    protected void delete()
	    throws Exception{
	Departments d;
	d=em.createNamedQuery("Departments.findByName", Departments.class).setParameter("Name", "Stuart School of Business").getSingleResult();
	if(d!=null)
	    em.remove(d);
	System.out.println("\n-->> 1 record deleted : /Entities.Departments{Name:\"Stuart School of Business\"}\n");
    }
}
