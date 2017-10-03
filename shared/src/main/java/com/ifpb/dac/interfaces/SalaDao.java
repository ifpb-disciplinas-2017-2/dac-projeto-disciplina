package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Sala;
import java.util.List;

public interface SalaDao {

    void adicionar(Sala sala);
    void remover(Sala sala);
    void atualizar(Sala sala);
    List<Sala> listarTodos();
    Sala buscarPorId(int id);
    
}
