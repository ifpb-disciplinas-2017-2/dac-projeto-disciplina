package com.ifpb.dac.rs.resources;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.enums.Tipo;
import com.ifpb.dac.rs.interfaces.AlunoDaoLocal;
import com.ifpb.dac.rs.interfaces.CursoDaoLocal;
import com.ifpb.dac.rs.interfaces.PedidoDaoLocal;
import com.ifpb.dac.rs.interfaces.TurmaDaoLocal;
import com.ifpb.dac.rs.model.AlunoRest;
import com.ifpb.dac.rs.model.TurmaRest;
import com.ifpb.dac.rs.security.Secure;
import com.ifpb.dac.rs.security.Token;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
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
@Path("aluno")
@Produces({MediaType.APPLICATION_JSON})
@Stateless
public class AlunoResource {
    @Inject
    private AlunoDaoLocal alunoDao;
    @Inject 
    private CursoDaoLocal cursoDao;
    @Inject
    private PedidoDaoLocal pedidoDao;
    @Inject
    private TurmaDaoLocal turmaDao;
    @Inject
    private Token token;
    
    @POST
    public Response cadastro(Aluno aluno){
        boolean existe = alunoDao.verificarEmail(aluno.getEmail());
        if(existe){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }else{            
            int codigoCurso = aluno.getCurso().getCodigo_curso();
            Curso curso = cursoDao.buscarPorId(codigoCurso);
            aluno.setCurso(curso);
            aluno.setLogado(false);
            Pedido p = new Pedido(aluno.getNome(), aluno.getEmail(), aluno.getSenha(), Tipo.Aluno, 1);
            pedidoDao.adicionar(p);
            alunoDao.adicionar(aluno);
            return Response.status(Response.Status.CREATED).build();
        }
    }
    
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response login(@FormParam("email") String email, @FormParam("senha") String senha){
        System.out.println("Novo login-> email:"+email+ ", senha:"+senha);
        boolean existe = alunoDao.verificarEmail(email);
        if(!existe){
            //USUÁRIO NÃO EXISTE, STATUS PROIBIDO É ENVIADO: STATUS 403
            return Response.status(Response.Status.FORBIDDEN).build();
        }else{
            AlunoRest autenticado = alunoDao.autenticacaoRest(email, senha);
            if(autenticado == null){
                //USUÁRIO EXISTE, MAS DADOS INCORRETOS 
                return Response.status(Response.Status.BAD_REQUEST).build();
            }else{
                System.out.println(autenticado.toString());
                    if(autenticado.isLogado()){    
                        //USUÁRIO EXISTE E POSSUI PERMISSÃO PARA LOGAR: STATUS 200
                        String tokenString = token.create(autenticado.getEmail(), 3);
                        autenticado.setToken(tokenString);
                        //JsonObject entity = Json.createObjectBuilder().add("token", tokenString).build();
                        return Response.ok().entity(autenticado).build();
                    }else{
                        //USUÁRIO EXISTE, MAS AINDA NÃO TEM PERMISSÃO PARA LOGAR: STATUS 401
                        Pedido p = pedidoDao.buscarPorCredenciais(email,senha);
                        int incrementoPrioridade = p.getPrioridade() + 1;
                        p.setPrioridade(incrementoPrioridade);
                        pedidoDao.atualizar(p);
                        return Response.status(Response.Status.UNAUTHORIZED).build();
                    }   
            }
        }
    }
    
    @POST
    @Secure
    @Path("turma")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response cadastroEmTurma(@FormParam("aluno") String email, 
                                    @FormParam("disciplina") String disciplina,
                                    @FormParam("professor") String professor){
        Aluno aluno = alunoDao.buscarPorEmail(email);
        Turma turma = turmaDao.retornarDiscProf(disciplina, professor);
        Turma auxiliar = turmaDao.buscarPorId(turma.getCodigo_turma());
        int verificarAlunoTurma = turmaDao.verificarAlunoTurma(auxiliar.
                getCodigo_turma(), aluno.getId());
        if (verificarAlunoTurma > 0) {
            System.out.println("caiu no if");
            //já cadastrado
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            System.out.println("caiu no else");
            aluno.add(auxiliar);
            auxiliar.add(aluno);
            turmaDao.atualizar(auxiliar);
            JsonObject msg = Json.createObjectBuilder().add("msg", "Cadastro na turma foi realizado").build();
            return Response.ok().entity(msg).build();
        }
    }
    
    @GET
    @Secure
    @Path("{id}/turmas")
    public Response listarTurmasAluno(@PathParam("id") int idAluno){
        List<TurmaRest> turmasRest = alunoDao.listTurmasRestAluno(idAluno);
        GenericEntity<List<TurmaRest>> lista = new GenericEntity<List<TurmaRest>>(turmasRest){};
        return Response.ok().entity(lista).build();
    }
}

