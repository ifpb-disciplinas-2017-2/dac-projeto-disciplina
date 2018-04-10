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
public class ControladorMensagem implements Serializable {
    
    @Inject
    private MensagemDao mensagemDao;
    
    @Inject
    private ProfessorDao professorDao;
    
    @Inject
    private AlunoDao alunoDao;
    
    private int remetente;
    private int destinatario;
    private int codigoProfessor;
    private int codigoAluno;
    private List<Professor> professores;
    private List<Aluno> alunos;
    private String corpoMensagem;
    private Professor professor;
    private Aluno aluno;
    private Aluno alunoBusca;
    private Mensagem mensagem;
    private boolean visualizarCampoMensagem = false;
    private boolean visualizarCampoMensagemAluno = false;
    private HttpSession sessao;
    
    @PostConstruct
    public void instanceObjects() {
        sessao = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        aluno = (Aluno) sessao.getAttribute("aluno");
        this.professor = new Professor();
        this.remetente = aluno.getId();
        this.mensagem = new Mensagem();
        this.professores = new ArrayList<>();
        this.alunos = new ArrayList<>();
        this.alunoBusca = new Aluno();
    }
    
    public String enviarMensagem() {
        mensagem.setRemetente(remetente);
        mensagem.setTipoRemetente(TipoUsuarioMensagem.ALUNO);
        mensagem.setDestinatario(destinatario);
        mensagem.setTipoDestinatario(TipoUsuarioMensagem.PROFESSOR);
        mensagem.setNomeRemetente(aluno.getNome());
        mensagem.setMensagem(corpoMensagem);
        mensagemDao.enviarMensagem(mensagem);
        corpoMensagem = null;
        return null;
    }
    
    public String enviarMensagemAluno() {
        mensagem.setRemetente(remetente);
        mensagem.setTipoRemetente(TipoUsuarioMensagem.ALUNO);
        mensagem.setDestinatario(destinatario);
        mensagem.setTipoDestinatario(TipoUsuarioMensagem.ALUNO);
        mensagem.setNomeRemetente(aluno.getNome());
        mensagem.setMensagem(corpoMensagem);
        mensagemDao.enviarMensagem(mensagem);
        corpoMensagem = null;
        return null;
    }
    
    public List<Mensagem> historicoMensagens() {
        return mensagemDao.getHistoricoMensagens(remetente, TipoUsuarioMensagem.ALUNO, destinatario, TipoUsuarioMensagem.PROFESSOR);
    }
    
    public List<Mensagem> historicoMensagensAluno() {
        return mensagemDao.getHistoricoMensagens(remetente, TipoUsuarioMensagem.ALUNO, destinatario, TipoUsuarioMensagem.ALUNO);
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
    
    public String ativarCampoMensagem() {
        professor = professorDao.buscarPorId(codigoProfessor);
        destinatario = professor.getCodigo();
        this.visualizarCampoMensagem = true;
        this.visualizarCampoMensagemAluno = false;
        return null;
    }
    
    public String ativarCampoMensagemAluno() {
        alunoBusca = alunoDao.buscarPorId(codigoAluno);
        destinatario = alunoBusca.getId();
        this.visualizarCampoMensagemAluno = true;
        this.visualizarCampoMensagem = false;
        return null;
    }
    
    public boolean isVisualizarCampoMensagem() {
        return visualizarCampoMensagem;
    }
    
    public void setVisualizarCampoMensagem(boolean visualizarCampoMensagem) {
        this.visualizarCampoMensagem = visualizarCampoMensagem;
    }
    
    public int getCodigoProfessor() {
        return codigoProfessor;
    }
    
    public void setCodigoProfessor(int codigoProfessor) {
        this.codigoProfessor = codigoProfessor;
    }
    
    public List<Professor> getProfessores() {
        return professorDao.listarTodos();
    }
    
    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
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
    
    public Aluno getAluno() {
        return aluno;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public List<Aluno> getAlunos() {
        return alunoDao.listarTodosOsAlunos(aluno.getId());
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
