package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aula;
import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Disciplina;
import com.ifpb.dac.entidades.Horario;
import com.ifpb.dac.entidades.Laboratorio;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Sala;
import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.interfaces.AulaDao;
import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.interfaces.DisciplinaDao;
import com.ifpb.dac.interfaces.HorarioDao;
import com.ifpb.dac.interfaces.HorariosDao;
import com.ifpb.dac.interfaces.LaboratorioDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import com.ifpb.dac.interfaces.SalaDao;
import com.ifpb.dac.interfaces.TurmaDao;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author romulo
 */
@Named
@SessionScoped
public class ControladorCadastrarAula implements Serializable{
    @Inject
    private CursoDao cursoDao;
    @Inject
    private HorarioDao horarioDao;
    @Inject
    private TurmaDao turmaDao;
    @Inject
    private DisciplinaDao disciplinaDao;
    @Inject
    private ProfessorDao professorDao;
    @Inject
    private SalaDao salaDao;
    @Inject
    private LaboratorioDao laboratorioDao;
    @Inject
    private HorariosDao horariosDao;
    @Inject
    private AulaDao aulaDao;
    
    //AUXILIARES PARA EXIBIÇÃO EM PASSOS
    private boolean verDisciplinas = false;
    private boolean verLocais = false;
    private boolean verProfessores = false;
    private boolean verCampos = false;
    
    //O QUE É PASSADO
    private int disciplinaSelect;
    private int professorSelect;
    private String diaSelect;
    private String localSelect;
    private String tipoLocal;
    private int horarioSelect;
    
    //FONTES PARA EXIBIÇÃO
    private List<Disciplina> disciplinas;
    private List<Professor> professores;
    private List<String> diasSemana = Arrays.asList("SEGUNDA-FEIRA","TERÇA-FEIRA","QUARTA-FEIRA","QUINTA-FEIRA","SEXTA-FEIRA");
    private List<String> tiposLocais = Arrays.asList("SALA", "LABORATÓRIO");
    private List<String> locais;
    private List<Horario> horarios;
    
    private Disciplina disciplina = new Disciplina();
   
    
    public List<Disciplina> getDisciplinas(){
        return disciplinaDao.listarTodos();
    }
    public String buscarDisciplinas(){
        verDisciplinas = true;
        return null;
    }
    public String buscarLocais(){
        if(tipoLocal.equals("SALA")){
            locais = salaDao.listarNomeSalas();
        }else{
            locais = laboratorioDao.listarNomeLaboratorios();
        }
        verLocais = true;
        return null;
    }
    
    public String buscarProfessoresDisciplina(){
        disciplina = disciplinaDao.buscarPorId(disciplinaSelect);
        professores = turmaDao.professoresDeDisciplina(disciplina.getDescricao());
        verProfessores = true;
        return null;
    }
    public String abrirCampos(){
        verCampos = true;
        return null;
    }
    public void mostrarMensagem(String msg) {
        FacesMessage message = new FacesMessage(msg);
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage("Erro", message);
    }
    public String cadastrar(){
        disciplina = disciplinaDao.buscarPorId(disciplinaSelect);
        Curso curso = disciplina.getCurso();
        Professor professor = professorDao.buscarPorId(professorSelect);
        Turma turma = turmaDao.retornarDiscProf(disciplina.getDescricao(), professor.getNome());
        Horario horario = horarioDao.buscarPorId(horarioSelect);
        if(tipoLocal.equals("SALA")){
            boolean salaDisponivel = horariosDao.salaDisponivel(localSelect, diaSelect, horario);
            if(salaDisponivel){
                //SALA DISPONÍVEL
                if(horariosDao.professorDisponivel(professor, diaSelect, horario)){
                    Sala sala = salaDao.buscarPorNome(localSelect);
                    Laboratorio labNao = laboratorioDao.buscarPorId(36);
                    Aula aula = new Aula(diaSelect, curso, disciplina, horario, professor, sala,labNao,turma);
                    aulaDao.adicionar(aula);
                    verDisciplinas = false;
                    verLocais = false;
                    verProfessores = false;
                    verCampos = false;
                    disciplina = new Disciplina();
                }else{
                    mostrarMensagem("PROFESSOR NÃO DISPONÍVEL");
                }
            }else{
                mostrarMensagem("SALA NÃO DISPONÍVEL");
            }
        }else{
            boolean laboratorioDisponivel = horariosDao.laboratorioDisponivel(localSelect, diaSelect, horario);
            if(laboratorioDisponivel){
                if(horariosDao.professorDisponivel(professor, diaSelect, horario)){
                    Sala sala = salaDao.buscarPorId(37);
                    Laboratorio labNao = laboratorioDao.buscarPorNome(localSelect);
                    Aula aula = new Aula(diaSelect, curso, disciplina, horario, professor, sala,labNao,turma);
                    aulaDao.adicionar(aula);
                    verDisciplinas = false;
                    verLocais = false;
                    verProfessores = false;
                    verCampos = false;
                    disciplina = new Disciplina();
                }else{
                    mostrarMensagem("PROFESSOR NÃO DISPONÍVEL");
                }
            }else{
                mostrarMensagem("LABORATÓRIO NÃO DISPONÍVEL");
            }
        }
        return null;
    }

    public String getLocalSelect() {
        return localSelect;
    }

    public void setLocalSelect(String localSelect) {
        this.localSelect = localSelect;
    }

    public String getTipoLocal() {
        return tipoLocal;
    }

    public void setTipoLocal(String tipoLocal) {
        this.tipoLocal = tipoLocal;
    }

    public List<String> getTiposLocais() {
        return tiposLocais;
    }

    public int getHorarioSelect() {
        return horarioSelect;
    }

    public List<Horario> getHorarios() {
        return horarioDao.listarTodos();
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public void setHorarioSelect(int horarioSelect) {
        this.horarioSelect = horarioSelect;
    }

    public void setTiposLocais(List<String> tiposLocais) {
        this.tiposLocais = tiposLocais;
    }

    public List<String> getLocais() {
        return locais;
    }

    public void setLocais(List<String> locais) {
        this.locais = locais;
    }


    public boolean isVerCampos() {
        return verCampos;
    }

    public void setVerCampos(boolean verCampos) {
        this.verCampos = verCampos;
    }

    public String getDiaSelect() {
        return diaSelect;
    }

    public void setDiaSelect(String diaSelect) {
        this.diaSelect = diaSelect;
    }

    public boolean isVerLocais() {
        return verLocais;
    }

    public void setVerLocais(boolean verLocais) {
        this.verLocais = verLocais;
    }

    public int getDisciplinaSelect() {
        return disciplinaSelect;
    }

    public void setDisciplinaSelect(int disciplinaSelect) {
        this.disciplinaSelect = disciplinaSelect;
    }

    public int getProfessorSelect() {
        return professorSelect;
    }

    public void setProfessorSelect(int professorSelect) {
        this.professorSelect = professorSelect;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }


    public boolean isVerDisciplinas() {
        return verDisciplinas;
    }

    public List<String> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<String> diasSemana) {
        this.diasSemana = diasSemana;
    }

    public void setVerDisciplinas(boolean verDisciplinas) {
        this.verDisciplinas = verDisciplinas;
    }

    public boolean isVerProfessores() {
        return verProfessores;
    }

    public void setVerProfessores(boolean verProfessores) {
        this.verProfessores = verProfessores;
    }

}
