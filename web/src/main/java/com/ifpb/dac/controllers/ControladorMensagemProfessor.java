package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Mensagem;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.enums.TipoUsuarioMensagem;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.interfaces.MensagemDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jozimar
 */
@Named
@SessionScoped
public class ControladorMensagemProfessor implements Serializable {

    @Inject
    private MensagemDao mensagemDao;

    @Inject
    private ProfessorDao professorDao;

    @Inject
    private AlunoDao alunoDao;

    private int remetente;
    private int destinatario;
    private int codigoAluno;
    private List<Aluno> alunos;
    private String corpoMensagem;
    private Professor professor;
    private Aluno alunoBusca;
    private Mensagem mensagem;
    private boolean visualizarCampoMensagemAluno = false;
    private HttpSession sessao;

    @PostConstruct
    public void instanceObjects() {
        sessao = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        professor = (Professor) sessao.getAttribute("usuario");
        this.remetente = professor.getCodigo();
        this.mensagem = new Mensagem();
        this.alunos = new ArrayList<>();
        this.alunoBusca = new Aluno();
    }

    public String enviarMensagemAluno() {
        mensagem.setRemetente(remetente);
        mensagem.setTipoRemetente(TipoUsuarioMensagem.PROFESSOR);
        mensagem.setDestinatario(destinatario);
        mensagem.setTipoDestinatario(TipoUsuarioMensagem.ALUNO);
        mensagem.setNomeRemetente(professor.getNome());
        mensagem.setMensagem(corpoMensagem);
        mensagemDao.enviarMensagem(mensagem);
        corpoMensagem = null;
        return null;
    }

    public List<Mensagem> historicoMensagensAluno() {
        return mensagemDao.getHistoricoMensagens(remetente, TipoUsuarioMensagem.PROFESSOR, destinatario, TipoUsuarioMensagem.ALUNO);
    }

    public int getRemetente() {
        return remetente;
    }

    public void setRemetente(int remetente) {
        this.remetente = remetente;
    }

    public int getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(int destinatario) {
        this.destinatario = destinatario;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    public String ativarCampoMensagemAluno() {
        alunoBusca = alunoDao.buscarPorId(codigoAluno);
        destinatario = alunoBusca.getId();
        this.visualizarCampoMensagemAluno = true;
        return null;
    }

    public String getCorpoMensagem() {
        return corpoMensagem;
    }

    public void setCorpoMensagem(String corpoMensagem) {
        this.corpoMensagem = corpoMensagem;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunoDao.listarTodos();
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public int getCodigoAluno() {
        return codigoAluno;
    }

    public void setCodigoAluno(int codigoAluno) {
        this.codigoAluno = codigoAluno;
    }

    public Aluno getAlunoBusca() {
        return alunoBusca;
    }

    public void setAlunoBusca(Aluno alunoBusca) {
        this.alunoBusca = alunoBusca;
    }

    public boolean isVisualizarCampoMensagemAluno() {
        return visualizarCampoMensagemAluno;
    }

    public void setVisualizarCampoMensagemAluno(boolean visualizarCampoMensagemAluno) {
        this.visualizarCampoMensagemAluno = visualizarCampoMensagemAluno;
    }
}
