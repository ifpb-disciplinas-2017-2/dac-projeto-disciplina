package com.ifpb.dac.infra;

import com.ifpb.dac.interfaces.ProfessorDao;
import com.ifpb.dac.servicelocater.ServiceLocator;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class ProfessorDaoResource {
    
    private static final String RESOURCE = "java:global/core/ProfessorDaoImpl!com.ifpb.dac.interfaces.ProfessorDao";
    
    @Dependent
    @Produces
    @Default
    public static ProfessorDao getProfessorDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
