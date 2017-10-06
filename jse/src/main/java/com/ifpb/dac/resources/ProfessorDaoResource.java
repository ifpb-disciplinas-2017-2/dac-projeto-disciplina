package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.ProfessorDao;
import com.ifpb.dac.servicelocater.ServiceLocator;

public class ProfessorDaoResource {
    
// java:global/core/ProfessorDaoImpl, java:global/core/ProfessorDaoImpl!com.ifpb.dac.interfaces.ProfessorDao    
    private static final String RESOURCE = "java:global/core/ProfessorDaoImpl";
    private static final String RESOURCE2 = "java:global/core/ProfessorDaoImpl!com.ifpb.dac.interfaces.ProfessorDao";
    
    public static ProfessorDao getProfessorDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
