package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.AulaDao;
import com.ifpb.dac.interfaces.HorariosDao;
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
public class HorariosDaoResource {
    
    private static final String RESOURCE = "java:global/core/HorariosDaoImpl!com.ifpb.dac.interfaces.HorariosDao";
    
    @Dependent
    @Produces
    @Default
    public static HorariosDao getAulaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
