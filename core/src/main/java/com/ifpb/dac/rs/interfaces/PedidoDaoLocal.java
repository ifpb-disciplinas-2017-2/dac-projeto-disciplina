package com.ifpb.dac.rs.interfaces;

import com.ifpb.dac.entidades.Pedido;

/**
 *
 * @author lyndemberg
 */
public interface PedidoDaoLocal {
    void adicionar(Pedido p);
    void atualizar(Pedido p);
    Pedido buscarPorCredenciais(String email, String senha);
}
