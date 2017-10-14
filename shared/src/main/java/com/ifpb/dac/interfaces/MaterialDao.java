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
    
}