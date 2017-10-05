package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Turma;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface TurmaDao {
    
    void adicionar(Turma t);
    void atualizar(Turma t);
    void remover(Turma t);
    List<Turma> listarTodos();
    Turma buscarPorId(int id);
    
    
}
