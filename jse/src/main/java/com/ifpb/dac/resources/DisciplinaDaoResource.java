package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.DisciplinaDao;
import com.ifpb.dac.servicelocater.ServiceLocator;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class DisciplinaDaoResource {
    
    private static final String RESOURCE = "java:global/core/DisciplinaDaoImpl!com.ifpb.dac.interfaces.DisciplinaDao";
    
    @Dependent
    @Produces
    @Default
    public static DisciplinaDao getDisciplinaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
}
