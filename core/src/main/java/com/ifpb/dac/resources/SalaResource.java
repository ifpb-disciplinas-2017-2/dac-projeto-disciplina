package com.ifpb.dac.resources;

import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.interfaces.CursoDaoLocal;
import com.ifpb.dac.interfaces.SalaDaoLocal;
import com.ifpb.dac.rs.model.CursoRest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author lyndemberg
 */
@Path("sala")
@Stateless
@Produces({MediaType.APPLICATION_JSON})
public class SalaResource {
    @Inject
    private SalaDaoLocal salaDao;
    
    @GET
    public Response listarNomeSalas(){
        List<String> listaNomes = salaDao.listarNomeSalas();
        JsonArray collect = listaNomes
                .stream()
                .collect(Collector.of(Json::createArrayBuilder,
                        (t, u) -> t.add(u),
                        (x, y) -> x.add(y)))
                .build();
        return Response.ok().entity(collect).build();
        
    }
    
    
}
