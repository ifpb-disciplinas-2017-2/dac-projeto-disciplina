package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.PedidoDao;
import com.ifpb.dac.servicelocater.ServiceLocator;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

/**
 *
 * @author rodrigobento
 */
@ApplicationScoped
public class PedidoDaoResource {
    
    private static final String RESOURCE = "java:global/core/PedidoDaoImpl!com.ifpb.dac.interfaces.PedidoDao";
    
    @Dependent
    @Produces
    @Default
    public static PedidoDao getPedidoDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
