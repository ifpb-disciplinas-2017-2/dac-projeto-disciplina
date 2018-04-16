
package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Aula;
import com.ifpb.dac.entidades.Disciplina;
import com.ifpb.dac.entidades.Professor;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface AulaDao {
    
    void adicionar(Aula a);
    void atualizar(Aula a);
    void remover(Aula a);
    List<Aula> listarTodos();
    List<Aula> listarAulasTurma(String disciplina, Professor professor);
    Aula buscarPorId(int id);
    
    
}
