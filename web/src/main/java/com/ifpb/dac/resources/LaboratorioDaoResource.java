package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.LaboratorioDao;
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
public class LaboratorioDaoResource {
    
    private static final String RESOURCE = "java:global/core/LaboratorioDaoImpl!com.ifpb.dac.interfaces.LaboratorioDao";
    
    @Dependent
    @Produces
    @Default
    public static LaboratorioDao getLaboratorioDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
