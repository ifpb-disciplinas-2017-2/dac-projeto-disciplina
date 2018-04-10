package com.ifpb.dac.rs.resources;

import com.ifpb.dac.interfaces.ProfessorDaoLocal;
import com.ifpb.dac.rs.security.Secure;
import java.util.List;
import java.util.stream.Collector;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author lyndemberg
 */
@Path("professor")
@Stateless
@Produces({MediaType.APPLICATION_JSON})
public class ProfessorResource {
    @Inject
    private ProfessorDaoLocal professorDao;
    
    @GET
    @Secure
    public Response listarNomesProfessores(){
        List<String> listaNomes = professorDao.listarNomeProfessores();
        JsonArray collect = listaNomes
                .stream()
                .collect(Collector.of(Json::createArrayBuilder,
                        (t, u) -> t.add(u),
                        (x, y) -> x.add(y)))
                .build();
        return Response.ok().entity(collect).build();
        
    }
}
