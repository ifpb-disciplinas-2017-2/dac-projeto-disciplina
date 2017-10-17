package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Atividade;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface AtividadeDao {
    
    void adicionar(Atividade ativ);
    void remover(Atividade ativ);
    void atualizar(Atividade ativ);
    List<Atividade> listarTodos();
    Atividade buscarPorId(String id);
    List<Atividade> atividadesProfessor(String professor);
    
}
