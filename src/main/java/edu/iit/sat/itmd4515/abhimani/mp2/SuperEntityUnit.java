package edu.iit.sat.itmd4515.abhimani.mp2;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
@MappedSuperclass
public abstract class SuperEntityUnit{
    //COLUMNS
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(nullable=false)
    protected int PId;

    //CONSTRUCTS
    public SuperEntityUnit(){
	this.PId=0;
    }

    //PROPERTIES
    public int getPid(){
	return this.PId;
    }
    
    //OVERRIDES
    @Override
    public int hashCode(){
	return ((this.getPid()>0) ? Integer.hashCode(this.getPid()) : 0);
    }

    @Override
    public abstract boolean equals(Object el);

    @Override
    public abstract String toString();
}
