package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.DisciplinaDao;
import com.ifpb.dac.servicelocater.ServiceLocator;

public class DisciplinaDaoResource {
    
//    java:global/core/DisciplinaDaoImpl, java:global/core/DisciplinaDaoImpl!com.ifpb.dac.interfaces.DisciplinaDao
    private static final String RESOURCE = "java:global/core/DisciplinaDaoImpl";
    private static final String R2 = "java:global/core/DisciplinaDaoImpl!com.ifpb.dac.interfaces.DisciplinaDao";
    
    public static DisciplinaDao getDisciplinaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
}
