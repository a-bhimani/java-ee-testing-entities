package edu.iit.sat.itmd4515.abhimani.mp2;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
@MappedSuperclass
public abstract class AbstractEntityUnit{
    //COLUMNS
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(updatable=false, nullable=false)
    protected long PId;

    @Version
    @Column(name="version")
    private int version;

    //CONSTRUCTS
    public AbstractEntityUnit(){
	this.PId=0;
    }

    //PROPERTIES
    public long getPid(){
	return this.PId;
    }

    public long getId(){
	return this.getPid();
    }

    public int getVersion(){
	return this.version;
    }

    public void setVersion(final int version){
	this.version=version;
    }

    //OVERRIDES
    @Override
    public int hashCode(){
	return ((this.getPid()>0) ? Long.hashCode(this.getPid()) : 0);
    }

    @Override
    public abstract boolean equals(Object el);

    @Override
    public abstract String toString();
}
