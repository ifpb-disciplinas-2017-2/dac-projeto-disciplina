package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.MaterialDao;
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
public class MaterialDaoResource {
    
    private static final String RESOURCE = "java:global/core/MaterialDaoImpl!com.ifpb.dac.interfaces.MaterialDao";
    
    @Dependent
    @Produces
    @Default
    public static MaterialDao getLaboratorioDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
