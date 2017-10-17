/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.DuvidaDao;
import com.ifpb.dac.servicelocater.ServiceLocator;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

/**
 *
 * @author natan
 */
@ApplicationScoped
public class DuvidaDaoResource {
    private static final String RESOURCE = "java:global/core/DuvidaDaoImpl!com.ifpb.dac.interfaces.DuvidaDao";

    @Dependent
    @Produces
    @Default
    public static DuvidaDao getDuvidaDao(){
        return ServiceLocator.lookup(RESOURCE);
    }
}
