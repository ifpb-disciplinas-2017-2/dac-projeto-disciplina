package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Duvida;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.interfaces.DuvidaDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import com.ifpb.dac.interfaces.TurmaDao;
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
 * @author natan
 */
@Named
@SessionScoped
public class ControladorDuvidaAluno implements Serializable {

    @Inject
    private DuvidaDao duvidaDao;
    @Inject
    private TurmaDao turmaDao;    
    @Inject
    private AlunoDao alunoDao;
    private List<Duvida> duvidasDoAluno;
    
    private List<Duvida> duvidasTurmas;
    
    private List<Turma> turmasAluno;
    private String pergunta;
    private int codigoTurma;
    private HttpSession sessao;
    private Aluno logado;
    private Duvida duvida = new Duvida();
    private boolean visualizando = false;
    private boolean cadastrando = true;
    
    private boolean respondendo = false;
    private Duvida duvidaParaResponder = new Duvida();
    
    
    @PostConstruct
    private void iniciarSessao() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(false);
        logado = (Aluno) sessao.getAttribute("aluno");
    }

    public List<Turma> getTurmasAluno() {
        return alunoDao.listarTurmasAluno(logado);
    }

    public void setTurmasAluno(List<Turma> turmasAluno) {
        this.turmasAluno = turmasAluno;
    }

    
    public String cadastrarDuvida(){
        Turma turma = turmaDao.buscarPorId(codigoTurma);
        Duvida duvida = new Duvida(pergunta, logado, turma);
        duvidaDao.adicionar(duvida);
        pergunta = null;
        
        return null;
        
    }
    
    public String visualizar(Duvida d){
        visualizando = true;
        cadastrando = false;
        duvida = d;
        return null;
    }
    
    public String mostrarParaResponder(Duvida d){
        respondendo = true;
        duvidaParaResponder = d;
        return null;
    }
    public String responder(){
        duvidaParaResponder.setUsuario(logado.getNome());
        duvidaDao.atualizar(duvidaParaResponder);
        duvidaParaResponder = new Duvida();
        respondendo = false;
        return null;
    }
    
    public String fecharVisualizacao(){
        visualizando = false;
        cadastrando = true;
        duvida = new Duvida();
        return null;
    }

    public List<Duvida> getDuvidasTurmas() {
        return duvidaDao.listarDuvidasNaoRespondidasTurmasAluno(logado);
    }

    public void setDuvidasTurmas(List<Duvida> duvidasTurmas) {
        this.duvidasTurmas = duvidasTurmas;
    }
    
    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public int getCodigoTurma() {
        return codigoTurma;
    }

    public boolean isRespondendo() {
        return respondendo;
    }

    public void setRespondendo(boolean respondendo) {
        this.respondendo = respondendo;
    }

    public void setCodigoTurma(int codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public Duvida getDuvida() {
        return duvida;
    }

    public void setDuvida(Duvida duvida) {
        this.duvida = duvida;
    }

    public Duvida getDuvidaParaResponder() {
        return duvidaParaResponder;
    }

    public void setDuvidaParaResponder(Duvida duvidaParaResponder) {
        this.duvidaParaResponder = duvidaParaResponder;
    }

    

    public List<Duvida> getDuvidasDoAluno() {
        return duvidaDao.listarDuvidasFeitasPorAluno(logado);
    }

    public void setDuvidasDoAluno(List<Duvida> duvidasDoAluno) {
        this.duvidasDoAluno = duvidasDoAluno;
    }

    public boolean isVisualizando() {
        return visualizando;
    }

    public void setVisualizando(boolean visualizando) {
        this.visualizando = visualizando;
    }

    public boolean isCadastrando() {
        return cadastrando;
    }

    public void setCadastrando(boolean cadastrando) {
        this.cadastrando = cadastrando;
    }
    
    
    
    
    
    
    
   
}
