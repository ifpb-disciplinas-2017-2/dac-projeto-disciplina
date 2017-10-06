package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.LaboratorioDao;
import com.ifpb.dac.servicelocater.ServiceLocator;

public class LaboratorioDaoResource {
    
    private static final String RESOURCE = "java:global/core/LaboratorioDaoImpl";
    private static final String RESOURCE2 = "java:global/core/LaboratorioDaoImpl!com.ifpb.dac.interfaces.LaboratorioDao";
    
    public static LaboratorioDao getLaboratorioDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
