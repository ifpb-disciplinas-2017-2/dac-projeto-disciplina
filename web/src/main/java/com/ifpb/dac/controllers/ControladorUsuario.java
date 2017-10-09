package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.HorarioSalaDTO;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.enums.Tipo;
import com.ifpb.dac.interfaces.HorariosDao;
import com.ifpb.dac.interfaces.PedidoDao;
import com.ifpb.dac.interfaces.UsuarioDao;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rodrigobento
 */
@Named
@RequestScoped
public class ControladorUsuario implements Serializable {

    @Inject
    private UsuarioDao usuarioDao;
    @Inject
    private PedidoDao pedidoDao;
    @Inject
    private HorariosDao horariosDao;
    
    private String valorSelect;
    private List<String> tiposUsuario = Arrays.asList("Professor", "Publico");
    private Usuario usuario = new Usuario();

    public List<String> getTiposUsuario() {
        return tiposUsuario;
    }

    public void setTiposUsuario(List<String> palavras) {
        this.tiposUsuario = palavras;
    }

    public String getValorSelect() {
        return valorSelect;
    }

    public void setValorSelect(String valorSelect) {
        this.valorSelect = valorSelect;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
//    public String testeHorarios(){
//        List<HorarioSalaDTO> horarios = horariosDao.listarHorarioSala("Sala 01");
//        for(HorarioSalaDTO h: horarios){
//            System.out.println(h.toString());
//        }
//        return null;
//    }

    public String cadastrarUsuario() {
        Tipo tipo = Enum.valueOf(Tipo.class, valorSelect);
        usuario.setTipo(tipo);
        usuario.setLogado(false);
        usuarioDao.adicionar(usuario);
        Pedido p = new Pedido(usuario.getNome(), usuario.getEmail(), 
                usuario.getSenha(), tipo, 1);
        pedidoDao.adicionar(p);
        usuario = new Usuario();
        return "index.xhtml";
    }

    public String realizarLogin() {
        Tipo tipo = Enum.valueOf(Tipo.class, valorSelect);
        Usuario autenticado = usuarioDao.autentica(usuario.getEmail(),
                usuario.getSenha(), tipo);
        usuario = new Usuario();
        if (autenticado != null) {
            if (autenticado.isLogado()) {
                return "principal.xhtml";
            } else {
                Pedido p = new Pedido(autenticado.getNome(), 
                        autenticado.getEmail(), autenticado.getSenha(), tipo, 1);
                pedidoDao.atualizar(p);
                FacesMessage message = new FacesMessage("Enviado pedido de acesso para o administrador");
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext.getCurrentInstance().addMessage("Teste", message);
                return null;
            }
        } else {
            return null;
        }
    }

}
