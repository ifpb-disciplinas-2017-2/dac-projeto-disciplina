package com.ifpb.dac.rs.resources;

import com.ifpb.dac.rs.interfaces.DisciplinaDaoLocal;
import com.ifpb.dac.rs.security.Secure;
import java.util.List;
import java.util.stream.Collector;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author lyndemberg
 */

@Path("disciplina")
@Stateless
@Produces({MediaType.APPLICATION_JSON})
public class DisciplinaResource {
    @Inject
    private DisciplinaDaoLocal disciplinaDao;
    
    @GET
    @Path("{curso}")
    @Secure
    public Response listarDisciplinasCurso(@PathParam("curso") String curso){
        List<String> disciplinas = disciplinaDao.listarDisciplinaCurso(curso);
        JsonArray collect = disciplinas
        .stream()
        .collect(Collector.of(Json::createArrayBuilder,
                (t, u) -> t.add(u),
                (x, y) -> x.add(y)))
        .build();
        return Response.ok().entity(collect).build();
    }
}
