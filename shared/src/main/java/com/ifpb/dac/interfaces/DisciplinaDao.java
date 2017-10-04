
package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Disciplina;
import java.util.List;

public interface DisciplinaDao {
    
    void adicionar(Disciplina disc);
    void remover(Disciplina disc);
    void atualizar(Disciplina disc);
    List<Disciplina> listarTodos();
    Disciplina buscarPorId(int id);
    
}
