package com.ifpb.dac.rs.resources;

import com.ifpb.dac.rs.interfaces.CursoDaoLocal;
import com.ifpb.dac.rs.interfaces.DisciplinaDaoLocal;
import com.ifpb.dac.rs.model.CursoRest;
import com.ifpb.dac.rs.security.Secure;
import java.util.List;
import java.util.stream.Collector;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author lyndemberg
 */
@Path("curso")
@Stateless
@Produces({MediaType.APPLICATION_JSON})
public class CursoResource {
    @Inject
    private CursoDaoLocal cursoDao;
    @Inject
    private DisciplinaDaoLocal disciplinaDao;
    
    @GET
    public Response listarCursos(){ 
        List<CursoRest> listCursos = cursoDao.listCursos();
        GenericEntity<List<CursoRest>> entity = new GenericEntity<List<CursoRest>>(listCursos){};
        return Response.ok().entity(entity).build();
    }
    
    @GET
    @Secure
    @Path("{nome}/disciplinas")
    public Response listarDisciplinasCurso(@PathParam("nome") String curso){
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
