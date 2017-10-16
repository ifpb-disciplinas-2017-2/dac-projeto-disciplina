package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.enums.Tipo;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.interfaces.PedidoDao;
import com.ifpb.dac.interfaces.UsuarioDao;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rodrigobento
 */
@Named
@RequestScoped
public class ControladorCadastro implements Serializable {

    @Inject
    private CursoDao cursoDao;
    @Inject
    private PedidoDao pedidoDao;
    @Inject
    private UsuarioDao usuarioDao;
    @Inject
    private AlunoDao alunoDao;
    private String nome;
    private String email;
    private String senha;
    private String valorSelect;
    private List<String> cursos = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<String> getCursos() {
        return cursoDao.listarNomeCursos();
    }

    public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }

    public String getValorSelect() {
        return valorSelect;
    }

    public void setValorSelect(String valorSelect) {
        this.valorSelect = valorSelect;
    }

    public void cadastrar() {
        System.out.println(valorSelect);
        Curso curso = cursoDao.retornarPorNome(valorSelect);
        if (curso == null) {
            mostrarMensagem("Curso não encontrado");
        } else {
            Aluno aln = new Aluno(nome, email, senha, curso);
            alunoDao.adicionar(aln);
            mostrarMensagem("Aluno cadastrado");
        }
        limparCampos();
        redirecionar();
    }
    
    public void voltar(){
        redirecionar();
    }

    private void redirecionar() {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        try {
            externalContext.redirect("../index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void cadastrarProfessor() {
        Usuario usuario = new Usuario(nome, email, senha, Tipo.Professor, false);
        Pedido p = new Pedido(nome, email, senha, Tipo.Professor, 1);
        pedidoDao.adicionar(p);
        usuarioDao.adicionar(usuario);
        mostrarMensagem("Enviado pedido de acesso para o administrador");
        limparCampos();
        redirecionar();
    }

    private void mostrarMensagem(String titulo) {
        FacesMessage message = new FacesMessage(titulo);
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage("Acesso", message);
    }

    private void limparCampos() {
        nome = "";
        email = "";
        senha = "";
    }

}
