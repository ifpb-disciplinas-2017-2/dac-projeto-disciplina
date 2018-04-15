package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aula;
import com.ifpb.dac.entidades.Disciplina;
import com.ifpb.dac.entidades.Horario;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.interfaces.AulaDao;
import com.ifpb.dac.interfaces.DisciplinaDao;
import com.ifpb.dac.interfaces.HorarioDao;
import com.ifpb.dac.interfaces.HorariosDao;
import com.ifpb.dac.interfaces.ProfessorDao;
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
public class ControladorEditarAula implements Serializable{
    @Inject
    private AulaDao aulaDao;
    @Inject
    private DisciplinaDao disciplinaDao;
    @Inject
    private ProfessorDao professorDao;
    @Inject
    private TurmaDao turmaDao;
    @Inject
    private HorarioDao horarioDao;
    @Inject
    private HorariosDao horariosDao;
    
    
    private List<Disciplina> disciplinas;
    private List<Professor> professores;
    private List<Aula> aulas;
    private List<Horario> horarios;
    private List<String> diasSemana = Arrays.asList("SEGUNDA-FEIRA","TERÇA-FEIRA","QUARTA-FEIRA","QUINTA-FEIRA","SEXTA-FEIRA");
    
    private String disciplinaSelect;
    private int professorSelect;
    private int horarioSelect;
    private boolean verProfessores = false;
    private boolean verAulas = false;
    private boolean editando = false;
    private Aula aula = null;
    
    
    private String diaSelect;
    private String localSelect;
    private String tipoLocal;
    
    
    
    public String atualizar(){
        if(diaSelect.equals(aula.getDia()) && horarioSelect == aula.getHorario().getCodigo_hora()){
            mostrarMensagem("ALTERE OS CAMPOS QUE DESEJA ATUALIZAR");
        }else{
            Horario horario = horarioDao.buscarPorId(horarioSelect);
            aula.setDia(diaSelect);
            aula.setHorario(horario);
            if(aula.getTipoLocal().equals("SALA")){
                boolean salaDisponivel = horariosDao.salaDisponivel(aula.getLocal(), diaSelect, horario);
                if(salaDisponivel){
                    if(horariosDao.professorDisponivel(aula.getProfessor(), diaSelect, horario)){
                        aulaDao.atualizar(aula);
                        aula = null;
                        diaSelect = null;
                        editando = false;
                    }else{
                        mostrarMensagem("PROFESSOR NÃO DISPONÍVEL");
                    }
                }else{
                    mostrarMensagem("SALA NÃO DISPONÍVEL");
                }
            }else{
                boolean laboratorioDisponivel = horariosDao.laboratorioDisponivel(aula.getLocal(), diaSelect, horario);
                if(laboratorioDisponivel){
                    if(horariosDao.professorDisponivel(aula.getProfessor(), diaSelect, horario)){
                        aulaDao.atualizar(aula);
                        aula = null;
                        editando = false;
                    }else{
                        mostrarMensagem("PROFESSOR NÃO DISPONÍVEL");
                    }
                }else{
                    mostrarMensagem("LABORATÓRIO NÃO DISPONÍVEL");
                }
            }
        }
        
        
        return null;
    }
    
    public String abrirEditar(Aula editar){
        aula = editar;
        editando = true;
        return null;
    }
    public String cancelarEdicao(){
        aula = null;
        editando = false;
        return null;
    }
    public String excluirAula(Aula excluir){
        //QUANDO O USUÁRIO CLICAR EM EXCLUIR -> A JANELA DE EDIÇÃO DEVE FECHAR
        editando = false;
        aula = null;
        
        aulaDao.remover(excluir);
        return null;
    }
    public String mostrarProfessores(){
        verProfessores = true;
        return null;
    }
    public String abrirAulas(){
        verAulas = true;
        return null;
    }
    public String getDisciplinaSelect() {
        return disciplinaSelect;
    }

    public void setDisciplinaSelect(String disciplinaSelect) {
        this.disciplinaSelect = disciplinaSelect;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinaDao.listarTodos();
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<Professor> getProfessores() {
        return turmaDao.professoresDeDisciplina(disciplinaSelect);
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }

    public int getProfessorSelect() {
        return professorSelect;
    }

    public List<String> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(List<String> diasSemana) {
        this.diasSemana = diasSemana;
    }

    public void setProfessorSelect(int professorSelect) {
        this.professorSelect = professorSelect;
    }

    public List<Aula> getAulas() {
        Professor professor = professorDao.buscarPorId(professorSelect);
        return aulaDao.listarAulasTurma(disciplinaSelect, professor);
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }
    public List<Horario> getHorarios() {
        return horarioDao.listarTodos();
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public boolean isVerProfessores() {
        return verProfessores;
    }

    public void setVerProfessores(boolean verProfessores) {
        this.verProfessores = verProfessores;
    }

    public boolean isVerAulas() {
        return verAulas;
    }

    public void setVerAulas(boolean verAulas) {
        this.verAulas = verAulas;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public String getDiaSelect() {
        return diaSelect;
    }

    public void setDiaSelect(String diaSelect) {
        this.diaSelect = diaSelect;
    }

    public int getHorarioSelect() {
        return horarioSelect;
    }

    public void setHorarioSelect(int horarioSelect) {
        this.horarioSelect = horarioSelect;
    }

    public String getLocalSelect() {
        return localSelect;
    }

    public void setLocalSelect(String localSelect) {
        this.localSelect = localSelect;
    }

    public void mostrarMensagem(String msg) {
        FacesMessage message = new FacesMessage(msg);
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage("Erro", message);
    }

    
    
    
}
