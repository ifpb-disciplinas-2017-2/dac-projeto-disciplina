package com.ifpb.dac.infra;

import com.google.gson.Gson;
import com.ifpb.dac.entidades.Aula;
import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.DadoAula;
import com.ifpb.dac.entidades.DadoCurso;
import com.ifpb.dac.entidades.DadoDisciplina;
import com.ifpb.dac.entidades.DadoHora;
import com.ifpb.dac.entidades.DadoLaboratorio;
import com.ifpb.dac.entidades.DadoProfessor;
import com.ifpb.dac.entidades.DadoSala;
import com.ifpb.dac.entidades.DadoTurma;
import com.ifpb.dac.entidades.Disciplina;
import com.ifpb.dac.entidades.Horario;
import com.ifpb.dac.entidades.Laboratorio;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Sala;
import com.ifpb.dac.entidades.Turma;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

public class ReadJSON{
    
    private Gson gson;
    private Reader leitor;
    
    public ReadJSON(){
        this.gson = new Gson();
    }
    
    public List<Sala> objetosSala(){
        try {
            leitor = new FileReader("JSON/salas.json");
            return gson.fromJson(leitor, DadoSala.class).getData();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Aula> objetosAula(){
        try {
            leitor = new FileReader("JSON/aulas.json");
            DadoAula aula = gson.fromJson(leitor, DadoAula.class);
            return aula.getData();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Horario> objetosHora(){
        try {
            leitor = new FileReader("JSON/aulas.json");
            DadoHora aula = gson.fromJson(leitor, DadoHora.class);
            return aula.getData();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Curso> objetosCurso(){
        try {
            leitor = new FileReader("JSON/cursos.json");
            return gson.fromJson(leitor, DadoCurso.class).getData();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Disciplina> objetosDisciplina(){
        try {
            leitor = new FileReader("JSON/disciplinas.json");
            return gson.fromJson(leitor, DadoDisciplina.class).getData();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Laboratorio> objetosLaboratorios(){
        try {
            leitor = new FileReader("JSON/laboratorios.json");
            return gson.fromJson(leitor, DadoLaboratorio.class).getData();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Professor> objetosProfessor(){
        try {
            leitor = new FileReader("JSON/professores.json");
            return gson.fromJson(leitor, DadoProfessor.class).getData();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Turma> objetosTurma(){
        try {
            leitor = new FileReader("JSON/turmas.json");
            return gson.fromJson(leitor, DadoTurma.class).getData();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
