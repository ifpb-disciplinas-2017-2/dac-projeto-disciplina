package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Laboratorio;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface LaboratorioDao {
    
    void adicionar(Laboratorio lab);
    void remover(Laboratorio lab);
    void atualizar(Laboratorio lab);
    List<Laboratorio> listarTodos();
    Laboratorio buscarPorId(int id);
    List<String> listarNomeLaboratorios();
    
}
