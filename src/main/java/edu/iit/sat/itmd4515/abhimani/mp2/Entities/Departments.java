package edu.iit.sat.itmd4515.abhimani.mp2.Entities;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractEntityUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.Relations.DepartmentOffices;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Departments.retrieveAll", query="SELECT d FROM Departments AS d"),
    @NamedQuery(name="Departments.findById", query="SELECT d FROM Departments AS d WHERE d.PId=:Id"),
    @NamedQuery(name="Departments.findByName", query="SELECT d FROM Departments AS d WHERE d.Name=:Name")
})
public class Departments
	extends AbstractEntityUnit
	implements Comparable<Departments>{
    //COLUMNS
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Dept_Id", nullable=false)
    protected int PId;

    @Column(name="Dept_Name", nullable=false, length=255, unique=true)
    private String Name;

    @Column(name="Description", length=2000)
    private String Description;

    @OneToMany(cascade=CascadeType.REMOVE, mappedBy="Dept")
    private List<DepartmentOffices> lstOffices;

    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="Dept")
    private List<Events> lstEvents;

    //CONSTRUCTS
    public Departments(){
	this.PId=0;
    }

    public Departments(String Name, String Description){
	this.Name=Name.trim();
	this.Description=Description.trim();
    }

    public Departments(String Name){
	this.Name=Name.trim();
    }

    //PROPERTIES
    public int getPid(){
	return this.PId;
    }

    public String getName(){
	return this.Name;
    }

    public void setName(String Name){
	this.Name=Name.trim();
    }

    public String getDescription(){
	return this.Description;
    }

    public void setDescription(String Description){
	this.Description=Description.trim();
    }

    //RELATIONS
    public List<DepartmentOffices> getDepartmentOfficesList(){
	return lstOffices;
    }

    public void addDepartmentOffice(DepartmentOffices office){
	lstOffices.add(office);
    }

    public List<Events> getDepartmentEvents(){
	return lstEvents;
    }

    //IMPLEMENTATION
    @Override
    public int compareTo(Departments el){
	return (this.getName().compareTo(el.getName()));
    }

    //OVERRIDES
    @Override
    public int hashCode(){
	return ((this.getPid()>0) ? Integer.hashCode(this.getPid()) : 0);
    }

    @Override
    public boolean equals(Object el){
	if(!(el instanceof Departments))
	    return false;
	try{
	    Departments tstDept=(Departments)el;
	    if(!(Integer.compare(this.hashCode(), tstDept.hashCode())==0))
		return false;
	}catch(Exception ex){
	    ex.printStackTrace();
	    return false;
	}
	return true;
    }

    @Override
    public String toString(){
	try{
	    return ("/Entities.Departments{Id:"+this.getPid()+", Name:\""+this.getName()+"\", Description:{Length:"+((this.getDescription()==null) ? 0 : this.getDescription().length())+"}, Offices:"+this.getDepartmentOfficesList().size()+", Events:"+this.getDepartmentEvents().size()+"}");
	}catch(Exception ex){
	    ex.printStackTrace();
	    return ex.toString();
	}
    }
}
