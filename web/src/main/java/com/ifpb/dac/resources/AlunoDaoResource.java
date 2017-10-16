package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.AlunoDao;
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
public class AlunoDaoResource {
    
    private static final String RESOURCE = "java:global/core/AlunoDaoImpl!com.ifpb.dac.interfaces.AlunoDao";
    
    @Dependent
    @Produces
    @Default
    public static AlunoDao getAulaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
