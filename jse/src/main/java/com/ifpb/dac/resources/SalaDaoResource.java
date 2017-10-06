package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.SalaDao;
import com.ifpb.dac.servicelocater.ServiceLocator;

public class SalaDaoResource {
    
//java:global/core/SalaDaoImpl!com.ifpb.dac.interfaces.SalaDao, java:global/core/SalaDaoImpl
    private static final String RESOURCE = "java:global/core/SalaDaoImpl";
    private static final String RESOURCE2 = "java:global/core/SalaDaoImpl!com.ifpb.dac.interfaces.SalaDao";
    
    public static SalaDao getSalaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
