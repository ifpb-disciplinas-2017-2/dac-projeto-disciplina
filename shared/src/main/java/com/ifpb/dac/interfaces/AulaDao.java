
package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Aula;
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
    Aula buscarPorId(int id);
    
}
