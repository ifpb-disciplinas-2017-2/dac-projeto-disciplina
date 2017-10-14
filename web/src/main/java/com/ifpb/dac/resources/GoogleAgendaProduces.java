
package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.GoogleAgenda;
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
public class GoogleAgendaProduces {
    
    private static final String RESOURCE = "java:global/core/GoogleAgendaJMS!com.ifpb.dac.interfaces.GoogleAgenda";
    
    @Dependent
    @Produces
    @Default
    public static GoogleAgenda getAulaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
