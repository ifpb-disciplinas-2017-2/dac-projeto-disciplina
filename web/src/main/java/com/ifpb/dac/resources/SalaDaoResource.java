package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.SalaDao;
import com.ifpb.dac.servicelocater.ServiceLocator;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class SalaDaoResource {
    
    private static final String RESOURCE = "java:global/core/SalaDaoImpl!com.ifpb.dac.interfaces.SalaDao";
    
    @Dependent
    @Produces
    @Default
    public static SalaDao getSalaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
