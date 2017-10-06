
package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.servicelocater.ServiceLocator;

public class CursoDaoResource {
    
    private static final String RESOURCE = "java:global/core/CursoDaoImpl";
            
    public static CursoDao getCursoDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
}
