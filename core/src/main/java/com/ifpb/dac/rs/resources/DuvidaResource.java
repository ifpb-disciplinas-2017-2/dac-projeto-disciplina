package com.ifpb.dac.rs.resources;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Duvida;
import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.rs.interfaces.AlunoDaoLocal;
import com.ifpb.dac.rs.interfaces.DuvidaDaoLocal;
import com.ifpb.dac.rs.interfaces.TurmaDaoLocal;
import com.ifpb.dac.rs.model.DuvidaRest;
import com.ifpb.dac.rs.security.Secure;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

@Path("duvida")
@Produces({MediaType.APPLICATION_JSON})
@Stateless
public class DuvidaResource {
    
    @Inject
    private AlunoDaoLocal alunoDao;
    @Inject
    private TurmaDaoLocal turmaDao;
    @Inject
    private DuvidaDaoLocal duvidaDao;
    
    @POST
    @Secure
    public Response cadastrarDuvida(DuvidaRest nova){
        Aluno aluno = alunoDao.buscarPorId(nova.getIdAluno());
        Turma turma = turmaDao.buscarPorId(nova.getCodigo_turma());
        Duvida duvida = new Duvida(nova.getPergunta(), aluno, turma);
        duvidaDao.adicionar(duvida);
        JsonObject msg = Json.createObjectBuilder().add("msg", "Dúvida cadastrada").build();
        return Response.status(Response.Status.CREATED).entity(msg).build();
    }
    
    @POST
    @Path("responder")
    @Secure
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response responderDuvida(@FormParam("aluno") int idAluno, @FormParam("duvida") int idDuvida, @FormParam("resposta") String resposta){
        Aluno aluno = alunoDao.buscarPorId(idAluno);
        Duvida duvida = duvidaDao.buscarPorId(idDuvida);
        duvida.setResposta(resposta);
        duvida.setUsuario(aluno.getNome());
        duvidaDao.atualizar(duvida);
        JsonObject msg = Json.createObjectBuilder().add("msg", "Dúvida respondida").build();
        return Response.ok().entity(msg).build();
    }
    
    @GET
    @Path("aluno/{id}")
    @Secure
    public Response duvidasDoAluno(@PathParam("id") int idAluno){
        List<DuvidaRest> listaDuvidas = duvidaDao.listarDuvidasFeitasPorAlunoRest(idAluno);
        GenericEntity<List<DuvidaRest>> duvidas = new GenericEntity<List<DuvidaRest>>(listaDuvidas){};
        return Response.ok().entity(duvidas).build();
    }
    
    @GET
    @Path("aluno/{id}/turmas")
    @Secure
    public Response duvidasDeColegasDeTurma(@PathParam("id") int idAluno){
        List<DuvidaRest> listaDuvidas = duvidaDao.listarDuvidasNaoRespondidasTurmasAlunoRest(idAluno);
        GenericEntity<List<DuvidaRest>> duvidasDeColegas = new GenericEntity<List<DuvidaRest>>(listaDuvidas){};
        return Response.ok().entity(duvidasDeColegas).build();
    }
    
    
    
    
}
