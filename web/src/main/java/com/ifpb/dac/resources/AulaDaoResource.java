package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.AulaDao;
import com.ifpb.dac.servicelocater.ServiceLocator;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

/**
 *
 * @author rodrigobento
 */
@ApplicationScoped
public class AulaDaoResource {
    
    private static final String RESOURCE = "java:global/core/AulaDaoImpl!com.ifpb.dac.interfaces.AulaDao";
    
    @Dependent
    @Produces
    @Default
    public static AulaDao getAulaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
