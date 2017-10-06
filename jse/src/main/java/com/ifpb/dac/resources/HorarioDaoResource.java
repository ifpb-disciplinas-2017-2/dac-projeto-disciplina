package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.HorarioDao;
import com.ifpb.dac.servicelocater.ServiceLocator;

public class HorarioDaoResource {
    
//  java:global/core/HorarioDaoImpl!com.ifpb.dac.interfaces.HorarioDao, java:global/core/HorarioDaoImpl
    private static final String RESOURCE = "java:global/core/HorarioDaoImpl";
    private static final String RESOURCE2 = "java:global/core/HorarioDaoImpl";
    
    public static HorarioDao getHorarioDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
