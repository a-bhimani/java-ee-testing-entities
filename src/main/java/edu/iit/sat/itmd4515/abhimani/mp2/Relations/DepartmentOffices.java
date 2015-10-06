package edu.iit.sat.itmd4515.abhimani.mp2.Relations;

import edu.iit.sat.itmd4515.abhimani.mp2.AbstractEntityUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.Country_States;
import edu.iit.sat.itmd4515.abhimani.mp2.Entities.Departments;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
@Entity
@Table(name="department_offices")
@NamedQueries({
    @NamedQuery(name="DepartmentOffices.retrieveAll", query="SELECT do FROM DepartmentOffices AS do"),
    @NamedQuery(name="DepartmentOffices.retrieveByDepartment", query="SELECT do FROM DepartmentOffices AS do WHERE do.Dept=:Dept"),
    @NamedQuery(name="DepartmentOffices.findById", query="SELECT do FROM DepartmentOffices AS do WHERE do.PId=:Id"),
    @NamedQuery(name="DepartmentOffices.findByTitle", query="SELECT do FROM DepartmentOffices AS do WHERE do.Title=:Name")

})
public class DepartmentOffices
	extends AbstractEntityUnit
	implements Comparable<DepartmentOffices>{
    //COLUMNS
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="Dept_Contact_Id", nullable=false)
    protected int PId;

    @JoinColumn(name="Office_Dept_Id", referencedColumnName="Dept_Id", nullable=false)
    @ManyToOne(optional=false)
    private Departments Dept;

    @Column(name="Title", nullable=false, length=255, unique=true)
    private String Title;

    @Column(name="Addr1", nullable=false, length=255)
    private String Addr1;

    @Column(name="Addr2", length=255)
    private String Addr2;

    @Column(name="City", nullable=false, length=255)
    private String City;

    @Column(name="CState", nullable=false, length=255)
    private Country_States CState;

    @Column(name="Zip", nullable=false, length=5)
    @Min(1000)
    @Max(99999)
    private int Zip;

    @Column(name="Zip_Ext", length=4)
    @Min(100)
    @Max(9999)
    private int Zip_Ext=0;

    @Column(name="Phone", nullable=false, length=10)
    @Min(1000000000)
    private long Phone=0;

    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."+"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"+"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid Email Id.")
    @Column(name="EmailId", nullable=false, length=255, unique=true)
    private String EmailId;

    //CONSTRUCTS
    public DepartmentOffices(){
	this.PId=0;
    }

    public DepartmentOffices(String Title, String Addr1, String Addr2, String City, Country_States CState, int Zip, int Zip_Ext, long Phone, String EmailId){
	this.Title=Title.trim();
	this.Addr1=Addr1.trim();
	this.Addr2=Addr2.trim();
	this.City=City.trim();
	this.CState=CState;
	this.Zip=Zip;
	this.Zip_Ext=Zip_Ext;
	this.Phone=Phone;
	this.EmailId=EmailId.trim();
    }

    public DepartmentOffices(String Title, String Addr1, String City, Country_States CState, int Zip, long Phone, String EmailId){
	this.Title=Title.trim();
	this.Addr1=Addr1.trim();
	this.City=City.trim();
	this.CState=CState;
	this.Zip=Zip;
	this.Phone=Phone;
	this.EmailId=EmailId.trim();
    }

    public DepartmentOffices(Departments Dept, String Title, String Addr1, String Addr2, String City, Country_States CState, int Zip, int Zip_Ext, long Phone, String EmailId){
	this.Dept=Dept;
	this.Title=Title.trim();
	this.Addr1=Addr1.trim();
	this.Addr2=Addr2.trim();
	this.City=City.trim();
	this.CState=CState;
	this.Zip=Zip;
	this.Zip_Ext=Zip_Ext;
	this.Phone=Phone;
	this.EmailId=EmailId.trim();
    }

    public DepartmentOffices(Departments Dept, String Title, String Addr1, String City, Country_States CState, int Zip, long Phone, String EmailId){
	this.Dept=Dept;
	this.Title=Title.trim();
	this.Addr1=Addr1.trim();
	this.City=City.trim();
	this.CState=CState;
	this.Zip=Zip;
	this.Phone=Phone;
	this.EmailId=EmailId.trim();
    }

    //PROPERTIES
    public int getPid(){
	return this.PId;
    }

    public Departments getDepartment(){
	return Dept;
    }

    public void setDepartment(Departments Dept){
	this.Dept=Dept;
    }

    public String getTitle(){
	return Title;
    }

    public void setTitle(String Title){
	this.Title=Title.trim();
    }

    public String getAddr1(){
	return Addr1;
    }

    public void setAddr1(String Addr1){
	this.Addr1=Addr1.trim();
    }

    public String getAddr2(){
	return Addr2;
    }

    public void setAddr2(String Addr2){
	this.Addr2=Addr2.trim();
    }

    public String getCity(){
	return City;
    }

    public void setCity(String City){
	this.City=City.trim();
    }

    public String getCState(){
	return CState.toString();
    }

    public void setCState(Country_States CState){
	this.CState=CState;
    }

    public String getZip(){
	return ((this.Zip_Ext>0) ? (Zip+"-"+Zip_Ext) : Integer.toString(Zip));
    }

    public void setZip(int Zip){
	this.Zip=Zip;
    }

    public int getZip_Ext(){
	return Zip_Ext;
    }

    public void setZip_Ext(int Ext){
	this.Zip_Ext=Ext;
    }

    public long getPhone(){
	return Phone;
    }

    public void setPhone(long Phone){
	this.Phone=Phone;
    }

    public String getEmailId(){
	return EmailId;
    }

    public void setEmailId(String EmailId){
	this.EmailId=EmailId.trim();
    }

    //IMPLEMENTATION
    @Override
    public int compareTo(DepartmentOffices el){
	return (this.getTitle().compareTo(el.getTitle()));
    }

    //OVERRIDES
    @Override
    public int hashCode(){
	return ((this.getPid()>0) ? Integer.hashCode(this.getPid()) : 0);
    }

    @Override
    public boolean equals(Object el){
	if(!(el instanceof DepartmentOffices))
	    return false;
	try{
	    DepartmentOffices tstDeptO=(DepartmentOffices)el;
	    if(!(Integer.compare(this.hashCode(), tstDeptO.hashCode())==0))
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
	    return ("/Relations.DepartmentOffices{Id:"+this.getPid()+", Dept_Id:"+this.getDepartment().getPid()+", Department:\""+this.getDepartment().getName()+"\", Title:\""+this.getTitle()+"\", Address:{Addr1:\""+this.getAddr1()+"\", Addr2:\""+((this.getAddr2()==null) ? "" : this.getAddr2())+"\", City:\""+this.getCity()+"\", State:"+this.getCState()+", Zip:"+this.getZip()+"}, Contact:{Phone:"+this.getPhone()+", EmailId:"+this.getEmailId()+"}}");
	}catch(Exception ex){
	    ex.printStackTrace();
	    return ex.toString();
	}
    }
}
