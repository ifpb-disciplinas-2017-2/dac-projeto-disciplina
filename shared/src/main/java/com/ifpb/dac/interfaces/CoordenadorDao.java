package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Coordenador;
import java.util.List;

/**
 *
 * @author lyndemberg
 */
public interface CoordenadorDao {
    void adicionar(Coordenador coordenador);
    void atualizar(Coordenador coordenador);
    void remover(Coordenador coordenador);
    List<Coordenador> listarTodos();
    Coordenador buscarPorCodigo(int codigo);
    Coordenador autentica(String email, String senha);
    boolean verificarEmail(String email);
}
