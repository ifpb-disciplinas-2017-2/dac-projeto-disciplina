
package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.Dropbox;
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
public class DropboxResource {
    
    private static final String RESOURCE = "java:global/core/DropboxJMS!com.ifpb.dac.interfaces.Dropbox";
    
    @Dependent
    @Produces
    @Default
    public static Dropbox getAulaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
    
}
