package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.MensagemDao;
import com.ifpb.dac.servicelocater.ServiceLocator;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

/**
 *
 * @author jozimar
 */
@ApplicationScoped
public class MensagemDaoResource {

    private static final String RESOURCE = "java:global/core/MensagemDaoImpl!com.ifpb.dac.interfaces.MensagemDao";

    @Dependent
    @Produces
    @Default
    public static MensagemDao getMensagemDao() {
        return ServiceLocator.lookup(RESOURCE);
    }
}
