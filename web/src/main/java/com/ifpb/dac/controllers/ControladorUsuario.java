package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Atividade;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.enums.Tipo;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.interfaces.AtividadeDao;
import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.interfaces.PedidoDao;
import com.ifpb.dac.interfaces.TurmaDao;
import com.ifpb.dac.interfaces.UsuarioDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

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
    private AlunoDao alunoDao;
    @Inject
    private CursoDao cursoDao;
    @Inject
    private TurmaDao dao;
    private String valorSelect;
    private boolean cad = false;
    private List<String> tiposUsuario = Arrays.asList("Professor", "Aluno");
    private Usuario usuario = new Usuario();
    private HttpSession sessao;

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

    public boolean isCad() {
        return cad;
    }

    public void setCad(boolean cad) {
        this.cad = cad;
    }

    public String cadastrarUsuario() {
        Tipo tipo = Enum.valueOf(Tipo.class, valorSelect);
        usuario.setTipo(tipo);
        if (tipo.equals(Tipo.Aluno)) {
            usuario.setLogado(true);
        } else {
            usuario.setLogado(false);
            Pedido p = new Pedido(usuario.getNome(), usuario.getEmail(),
                    usuario.getSenha(), tipo, 1);
            pedidoDao.adicionar(p);
            mostrarMensagem("Enviado pedido de acesso para o administrador");
        }
        usuarioDao.adicionar(usuario);
        usuario = new Usuario();
        return "index.xhtml";
    }

    public String navegarCadastro() {
        cad = true;
        return null;
    }

    private void iniciarSessao() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);
    }

    public String realizarLogin() {
        Tipo tipo = Enum.valueOf(Tipo.class, valorSelect);
        // Caso seja login de professor
        if (tipo.equals(Tipo.Professor)) {
            Usuario autenticado = usuarioDao.autentica(usuario.getEmail(),
                    usuario.getSenha(), tipo);
            usuario = new Usuario();
            if (autenticado != null) {
                if (autenticado.isLogado()) {
                    iniciarSessao();
                    sessao.setAttribute("usuario", autenticado);
                    sessao.setAttribute("credenciais", true);
                    return "principal.xhtml";
                } else {
                    Pedido p = pedidoDao.buscarPorCredenciais(autenticado.getEmail(),
                            autenticado.getSenha());
                    int incrementoPrioridade = p.getPrioridade() + 1;
                    p.setPrioridade(incrementoPrioridade);
                    pedidoDao.atualizar(p);
                    mostrarMensagem("Enviado pedido de acesso para o administrador");
                    return null;
                }
            } else {
                return null;
            }
        } else {
            Aluno autenticado = alunoDao.autentica(usuario.getEmail(),
                    usuario.getSenha());
            usuario = new Usuario();
            if (autenticado == null) {
                return null;
            } else {
                iniciarSessao();
                sessao.setAttribute("aluno", autenticado);
                sessao.setAttribute("credenciais", false);
                return "principal.xhtml";
            }
        }
    }

    public String entrarComoPublico() {
        Usuario usu = new Usuario();
        usu.setTipo(Tipo.Publico);
        iniciarSessao();
        sessao.setAttribute("usuario", usu);
        sessao.setAttribute("credenciais", false);
        return "principal.xhtml";
    }

    public void mostrarMensagem(String msg) {
        FacesMessage message = new FacesMessage(msg);
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage("Acesso", message);
    }

//    public String addAlunoTurma() {
//        Curso curso = cursoDao.buscarPorId(5);
//        Aluno usu = new Aluno("Rodrigo Rodrigues", "rodrigobentorodrigues@gmail.com",
//                "123", curso);
//        Turma turma = dao.buscarPorId(21213);
//        System.out.println(turma.getNome_disciplina());
//        usu.add(turma);
//        turma.add(usu);
//        dao.atualizar(turma);
//        return null;     
//    }
}
