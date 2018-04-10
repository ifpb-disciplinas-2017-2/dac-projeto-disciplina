package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Mensagem;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.enums.TipoUsuarioMensagem;
import java.util.List;

/**
 *
 * @author jozimar
 */
public interface MensagemDao {

    void enviarMensagem(Mensagem mensagem);

    List<Mensagem> getHistoricoMensagens(int remetente, TipoUsuarioMensagem tipoRemetente, int destinatario, TipoUsuarioMensagem tipoDestinatario);
}
