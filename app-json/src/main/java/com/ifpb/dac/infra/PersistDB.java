package com.ifpb.dac.infra;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author rodrigobento
 */
public class PersistDB {
    
    private EntityManager em;
    private EntityTransaction tr;
    
    public PersistDB(){
        this.em = Persistence.createEntityManagerFactory("UPersistence").createEntityManager();
        this.tr = em.getTransaction();
    }
    
    public void add(Object o){
        tr.begin();
        em.persist(o);
        tr.commit();
    }
    
}
