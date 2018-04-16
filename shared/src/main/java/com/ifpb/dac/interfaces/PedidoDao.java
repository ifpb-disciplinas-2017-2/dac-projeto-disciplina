package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.enums.Tipo;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface PedidoDao {
    
    void adicionar(Pedido p);
    void remover(Pedido p);
    void atualizar(Pedido p);
    List<Pedido> listarTodos();
    List<Pedido> listarTodosFiltro(Tipo usuario);
    Pedido buscarPorId(int id);
    Pedido buscarPorCredenciais(String email, String senha);
    
}
