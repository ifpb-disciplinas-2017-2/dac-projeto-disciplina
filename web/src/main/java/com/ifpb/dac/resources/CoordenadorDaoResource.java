
package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.CoordenadorDao;
import com.ifpb.dac.servicelocater.ServiceLocator;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

/**
 *
 * @author lyndemberg
 */
@ApplicationScoped
public class CoordenadorDaoResource {
    private static final String RESOURCE = "java:global/core/CoordenadorDaoImpl!com.ifpb.dac.interfaces.CoordenadorDao";
    
    @Dependent
    @Produces
    @Default
    public static CoordenadorDao getCoordenadorDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
}
