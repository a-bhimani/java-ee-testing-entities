package edu.iit.sat.itmd4515.abhimani.mp2.entities;

import edu.iit.sat.itmd4515.abhimani.mp2.SuperEntityUnit;
import edu.iit.sat.itmd4515.abhimani.mp2.Venue_Type;
import edu.iit.sat.itmd4515.abhimani.mp2.Country_States;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
@Entity
@Table(name="venues")
@NamedQueries({
    @NamedQuery(name="Venues.retrieveAll", query="SELECT v FROM Venue AS v"),
    @NamedQuery(name="Venues.findByName", query="SELECT v FROM Venue As v WHERE v.Title=:Name")
})
public class Venue
	extends SuperEntityUnit
	implements Comparable<Venue>, Serializable{
    //COLUMNS
    @Column(name="Title", nullable=false, length=255, unique=true)
    private String Title;

    @Column(name="Type", nullable=false)
    private Venue_Type Type=Venue_Type.Open;

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

    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="Ven")
    private List<Event> lstEvents;

    //CONSTRUCTS
    public Venue(){
	super();
    }

    public Venue(String Title, Venue_Type Type, String Addr1, String Addr2, String City, Country_States CState, int Zip, int Zip_Ext){
	this.Title=Title.trim();
	this.Type=Type;
	this.Addr1=Addr1.trim();
	this.Addr2=Addr2.trim();
	this.City=City.trim();
	this.CState=CState;
	this.Zip=Zip;
	this.Zip_Ext=Zip_Ext;
    }

    public Venue(String Title, Venue_Type Type, String Addr1, String City, Country_States CState, int Zip){
	this.Title=Title.trim();
	this.Type=Type;
	this.Addr1=Addr1.trim();
	this.City=City.trim();
	this.CState=CState;
	this.Zip=Zip;
    }

    //PROPERTIES
    public String getTitle(){
	return Title;
    }

    public void setTitle(String Title){
	this.Title=Title.trim();
    }

    public String getType(){
	return Type.toString();
    }

    public void setType(Venue_Type Type){
	this.Type=Type;
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

    //RELATIONS
    public List<Event> getVenueEvents(){
	return lstEvents;
    }

    //IMPLEMENTATION
    @Override
    public int compareTo(Venue el){
	return (this.getTitle().compareTo(el.getTitle()));
    }

    //OVERRIDES
    @Override
    public boolean equals(Object el){
	if(!(el instanceof Venue))
	    return false;
	try{
	    Venue tstVenue=(Venue)el;
	    if(!(Integer.compare(this.hashCode(), tstVenue.hashCode())==0))
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
	    return ("/Entities.Venues{Id:"+this.getPid()+", Title:\""+this.getTitle()+"\", Type:\""+this.getType()+"\", Address:{Addr1:\""+this.getAddr1()+"\", Addr2:\""+((this.getAddr2()==null) ? "" : this.getAddr2())+"\", City:\""+this.getCity()+"\", State:"+this.getCState()+", Zip:"+this.getZip()+"}}");
	}catch(Exception ex){
	    ex.printStackTrace();
	    return ex.toString();
	}
    }
}
