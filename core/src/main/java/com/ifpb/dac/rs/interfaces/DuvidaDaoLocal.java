package com.ifpb.dac.rs.interfaces;

import com.ifpb.dac.entidades.Duvida;
import com.ifpb.dac.rs.model.DuvidaRest;
import java.util.List;

/**
 *
 * @author lyndemberg
 */
public interface DuvidaDaoLocal {
    void adicionar(Duvida duvida);
    Duvida buscarPorId(int id);    
    void atualizar(Duvida duvida);
    List<DuvidaRest> listarDuvidasFeitasPorAlunoRest(int idAluno);
    List<DuvidaRest> listarDuvidasNaoRespondidasTurmasAlunoRest(int idAluno);   
}
