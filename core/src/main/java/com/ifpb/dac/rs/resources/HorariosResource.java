package com.ifpb.dac.rs.resources;

import com.ifpb.dac.entidades.HorariosDTO;
import com.ifpb.dac.interfaces.HorariosDaoLocal;
import com.ifpb.dac.rs.security.Secure;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
@Path("horarios")
@Produces({MediaType.APPLICATION_JSON})
@Stateless
public class HorariosResource {
    @Inject
    private HorariosDaoLocal horariosDao;
    
    @GET
    @Secure
    @Path("sala/{nome}")
    public Response sala(@PathParam("nome") String sala){
        List<HorariosDTO> horarios = horariosDao.listarHorarioSala(sala);
        GenericEntity<List<HorariosDTO>> entity = new GenericEntity<List<HorariosDTO>>(horarios){};
        return Response.ok().entity(entity).build();
    }
    @GET
    @Secure
    @Path("laboratorio/{nome}")
    public Response laboratorio(@PathParam("nome") String laboratorio){
        List<HorariosDTO> horarios = horariosDao.listarHorarioLab(laboratorio);
        GenericEntity<List<HorariosDTO>> entity = new GenericEntity<List<HorariosDTO>>(horarios){};
        return Response.ok().entity(entity).build();
    }
    
    @GET
    @Secure
    @Path("professor/{nome}")
    public Response professor(@PathParam("nome") String professor){
        List<HorariosDTO> horarios = horariosDao.listarHorarioProf(professor);
        GenericEntity<List<HorariosDTO>> entity = new GenericEntity<List<HorariosDTO>>(horarios){};
        return Response.ok().entity(entity).build();
    }
    
    @GET
    @Secure
    @Path("{curso}/{disciplina}")
    public Response curso(@PathParam("curso") String curso, @PathParam("disciplina") String disciplina){
        List<HorariosDTO> horarios = horariosDao.listarHorarioCurso(curso, disciplina);
        GenericEntity<List<HorariosDTO>> entity = new GenericEntity<List<HorariosDTO>>(horarios){};
        return Response.ok().entity(entity).build();
    }
    
    
}
