package edu.iit.sat.itmd4515.abhimani.mp2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Ankit Bhimani (abhimani) on edu.iit.sat.itmd4515.abhimani.mp2
 */
public abstract class AbstractTestJUnit{
    public static final String STR_PERS_UNIT="abhimaniPU";
    private static EntityManagerFactory emf;
    private EntityTransaction tsact;
    protected EntityManager em;

    @BeforeClass
    public static void setUpClass(){
	emf=Persistence.createEntityManagerFactory(STR_PERS_UNIT);
    }

    @Before
    public void setUp(){
	em=emf.createEntityManager();
	tsact=em.getTransaction();
    }

    @After
    public void tearDown(){
	em.close();
    }

    @AfterClass
    public static void tearDownClass(){
	emf.close();
    }

    public void forceCommit(){
	tsact.commit();
    }

    //CRUD - sequenced
    protected abstract void create() throws Exception;

    protected abstract void retrieve() throws Exception;

    protected abstract void update() throws Exception;

    /**
     * MUST THROW NoResultException() TO TEST THE DELETED FUNCTIONALITY OF A
     * PERSIST.
     */
    protected abstract void delete() throws Exception;

    //CRUD - invoke_all
    /**
     * This is where the CRUD is invoked in a sequence.
     */
    @Test(expected=NoResultException.class)
    public void triggerCRUD()
	    throws Exception{
	assertTrue(true);
	System.out.println("\n***************************************************************************");
	System.out.println("CREATE :: BEGIN");
	System.out.println("***************************************************************************");
	//try{
	tsact.begin();
	this.create();
	tsact.commit();
	//}catch(Exception ex){
	//    System.out.print("[C]:::");
	//    ex.printStackTrace();
	//}
	System.out.println("\n***************************************************************************");
	System.out.println("CREATE :: END");
	System.out.println("***************************************************************************\n");
	System.out.println("\n***************************************************************************");
	System.out.println("READ :: BEGIN");
	System.out.println("***************************************************************************");
	//try{
	tsact.begin();
	this.retrieve();
	tsact.commit();
	//}catch(Exception ex){
	//    System.out.print("[R]:::");
	//    ex.printStackTrace();
	//}
	System.out.println("\n***************************************************************************");
	System.out.println("READ :: END");
	System.out.println("***************************************************************************\n");
	System.out.println("\n***************************************************************************");
	System.out.println("UPDATE :: BEGIN");
	System.out.println("***************************************************************************");
	//try{
	tsact.begin();
	this.update();
	tsact.commit();
	tsact.begin();
	this.retrieve();
	tsact.commit();
	//}catch(Exception ex){
	//    System.out.print("[U]:::");
	//    ex.printStackTrace();
	//}
	System.out.println("\n***************************************************************************");
	System.out.println("UPDATE :: END");
	System.out.println("***************************************************************************\n");
	System.out.println("\n***************************************************************************");
	System.out.println("DELETE :: BEGIN");
	System.out.println("***************************************************************************");
	//try{
	tsact.begin();
	this.delete();
	tsact.commit();
	tsact.begin();
	this.retrieve();
	//tsact.commit();
	//}catch(Exception ex){
	//    System.out.print("[D]:::");
	//    ex.printStackTrace();
	//}
	System.out.println("\n***************************************************************************");
	System.out.println("DELETE :: END");
	System.out.println("***************************************************************************\n");
	System.out.println("\n\n\n");
    }
}
