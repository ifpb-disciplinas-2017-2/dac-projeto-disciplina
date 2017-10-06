package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.TurmaDao;
import com.ifpb.dac.servicelocater.ServiceLocator;

public class TurmaDaoResource {
    
    private static final String RESOURCE = "java:global/core/TurmaDaoImpl";
   
    public static TurmaDao getTurmaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
