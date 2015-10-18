package edu.iit.sat.itmd4515.abhimani.mp2.entities;

import edu.iit.sat.itmd4515.abhimani.mp2.SuperEntityUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.relations.DepartmentOffice;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
@Entity
@Table(name="departments")
@NamedQueries({
    @NamedQuery(name="Departments.retrieveAll", query="SELECT d FROM Department AS d"),
    @NamedQuery(name="Departments.findById", query="SELECT d FROM Department AS d WHERE d.PId=:Id"),
    @NamedQuery(name="Departments.findByName", query="SELECT d FROM Department AS d WHERE d.Name=:Name")
})
public class Department
	extends SuperEntityUnit
	implements Comparable<Department>, Serializable{
    //COLUMNS
    @Column(name="Dept_Name", nullable=false, length=255, unique=true)
    private String Name;

    @Column(name="Description", length=2000)
    private String Description;

    @OneToMany(cascade=CascadeType.REMOVE, mappedBy="Dept")
    private List<DepartmentOffice> lstOffices;

    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="Dept")
    private List<Event> lstEvents;

    //CONSTRUCTS
    public Department(){
	super();
    }

    public Department(String Name, String Description){
	this.Name=Name.trim();
	this.Description=Description.trim();
    }

    public Department(String Name){
	this.Name=Name.trim();
    }

    //PROPERTIES
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
    public List<DepartmentOffice> getDepartmentOfficesList(){
	return lstOffices;
    }

    public void addDepartmentOffice(DepartmentOffice office){
	lstOffices.add(office);
    }

    public List<Event> getDepartmentEvents(){
	return lstEvents;
    }

    //IMPLEMENTATION
    @Override
    public int compareTo(Department el){
	return (this.getName().compareTo(el.getName()));
    }

    //OVERRIDES
    @Override
    public boolean equals(Object el){
	if(!(el instanceof Department))
	    return false;
	try{
	    Department tstDept=(Department)el;
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
