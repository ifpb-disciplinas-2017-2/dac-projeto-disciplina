package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.EnviarEmail;
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
public class EnviarEmailResource {
    
    private static final String RESOURCE = "java:global/core/EnviarEmailImpl!com.ifpb.dac.interfaces.EnviarEmail";
    
    @Dependent
    @Produces
    @Default
    public static EnviarEmail getAulaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
