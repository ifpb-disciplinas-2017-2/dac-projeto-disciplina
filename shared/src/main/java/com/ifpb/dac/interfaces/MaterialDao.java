package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Material;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface MaterialDao {
    
    void adicionar(Material m);
    void remover(Material m);
    void atualizar(Material m);
    List<Material> listarTodos();
    Material buscaPorId(int id);
    List<Material> materiaisProfessor(String professor);
    List<Material> materiaisAluno(int id);
    
}
