
package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.servicelocater.ServiceLocator;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class CursoDaoResource {
    
    private static final String RESOURCE = "java:global/core/CursoDaoImpl!com.ifpb.dac.interfaces.CursoDao";
    
    @Dependent
    @Produces
    @Default
    public static CursoDao getCursoDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
}
