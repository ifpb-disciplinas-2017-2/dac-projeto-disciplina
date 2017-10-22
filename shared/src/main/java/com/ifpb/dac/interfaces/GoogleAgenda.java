
package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Atividade;

/**
 *
 * @author rodrigobento
 */
public interface GoogleAgenda {
    
    void cadastrarEvento(Atividade atividade);
    void removerEvento(Atividade atividade);
    
}
