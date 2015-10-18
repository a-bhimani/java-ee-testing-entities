package edu.iit.sat.itmd4515.abhimani.mp2.test_subrelations;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractTestJUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.Country_States;
import edu.iit.sat.itmd4515.abhimani.mp2.entities.Department;
import edu.iit.sat.itmd4515.abhimani.mp2.relations.DepartmentOffice;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
public class Test2_DepartmentOffice
	extends AbstractTestJUnit{
    //CRUD - calls
    private static int UPDATE_ID=0;
    private static int DELETE_ID=0;

    @Override
    protected void create()
	    throws Exception{
	DepartmentOffice do1=new DepartmentOffice("School of Applied Technology, Main Office", "Perlstein Hall", "10 W. 33rd St., Room 223", "Chicago", Country_States.IL, 60616, 0, 3125675290L, "atech@iit.edu"),
		do2=new DepartmentOffice("School of Applied Technology, MC", "6502 South Archer Road", "Bedford Park", Country_States.ID, 60501, 7085631576L, "atech.moffet@iit.edu"),
		do3=new DepartmentOffice("School of Applied Technology, Tech Park South", "4599 US-1 ALT", "Bladensburg", Country_States.MD, 20710, 3015673650L, "atech.tps@iit.edu"),
		do4=new DepartmentOffice("Chicago-Kent College of Law", "565 West Adams Street", "", "Chicago", Country_States.IL, 60661, 3691, 3129065000L, "contact@kentlaw.iit.edu");
	Department d1, d2;
	d1=em.createNamedQuery("Departments.findByName", Department.class).setParameter("Name", "School of Applied Technology").getSingleResult();
	d2=em.createNamedQuery("Departments.findByName", Department.class).setParameter("Name", "Kent College of Law").getSingleResult();
	em.flush();
	do1.setDepartment(d1);
	do2.setDepartment(d2);
	do3.setDepartment(d1);
	do4.setDepartment(d2);
	em.persist(do1);
	em.persist(do2);
	em.persist(do3);
	em.persist(do4);
	em.flush();
	UPDATE_ID=do2.getPid();
	DELETE_ID=do3.getPid();
	System.out.println("\n-->> 4 records inserted.\n");
    }

    @Override
    protected void retrieve()
	    throws Exception{
	int ix=0;
	List<Department> ds;
	ds=em.createNamedQuery("Departments.retrieveAll", Department.class).getResultList();
	Collections.sort(ds);
	for(Department d:ds){
	    List<DepartmentOffice> dos;
	    System.out.println(d.toString());
	    dos=em.createNamedQuery("DepartmentOffices.retrieveByDepartment", DepartmentOffice.class).setParameter("Dept", d).getResultList();
	    Collections.sort(dos);
	    for(DepartmentOffice d1:dos){
		System.out.println(d1.toString());
		ix++;
	    }
	    System.out.println();
	}
	System.out.println("\n-->> "+ix+" records retrieved.\n");
    }

    @Override
    protected void update()
	    throws Exception{
	DepartmentOffice d;
	Department d1;
	d1=em.createNamedQuery("Departments.findByName", Department.class).setParameter("Name", "School of Applied Technology").getSingleResult();
	em.flush();
	d=em.createNamedQuery("DepartmentOffices.findById", DepartmentOffice.class).setParameter("Id", UPDATE_ID).getSingleResult();
	if(d!=null){
	    d.setDepartment(d1);
	    d.setTitle("School of Applied Technology, Moffet Campus");
	    d.setCState(Country_States.IL);
	    d.setZip_Ext(1957);
	    d.setAddr2("-- This is an updated value --");
	}
	em.persist(d);
	em.flush();
	System.out.println("\n-->> 1 record updated : /Relations.DepartmentOffices{Department:\"School of Applied Technology\", Title:\"School of Applied Technology, Moffet Campus\", Addr1:\"6502 South Archer Road\", Addr2:\"-- This is an updated value --\", City:\"Bedford Park\", State:IL}\n");
    }

    @Override
    protected void delete()
	    throws Exception{
	DepartmentOffice d;
	d=em.createNamedQuery("DepartmentOffices.findById", DepartmentOffice.class).setParameter("Id", DELETE_ID).getSingleResult();
	if(d!=null)
	    em.remove(d);
	System.out.println("\n-->> 1 record deleted : /Relations.DepartmentOffices{Department:\"School of Applied Technology\", Title:\"School of Applied Technology, Tech Park South\", Addr1:\"4599 US-1 ALT\", City:\"Bladensburg\", State:MD}\n");
    }
}
