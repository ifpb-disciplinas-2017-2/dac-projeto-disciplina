package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.AulaDao;
import com.ifpb.dac.servicelocater.ServiceLocator;

public class AulaDaoResource {
    
    private static final String RESOURCE = "java:global/core/AulaDaoImpl";
    
    public static AulaDao getAulaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
