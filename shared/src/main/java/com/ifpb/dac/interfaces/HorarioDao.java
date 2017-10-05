package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Horario;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface HorarioDao {
    
    void adicionar(Horario h);
    void atualizar(Horario h);
    void remover(Horario h);
    List<Horario> listarTodos();
    Horario buscarPorId(int id);
    
}
