
package com.ifpb.dac.rs.model;

import java.io.Serializable;

/**
 *
 * @author lyndemberg
 */
public class DuvidaRest implements Serializable{
    private int id;
    private String pergunta;
    private String resposta;    
    private int idAluno;
    private String nomeAluno;
    private String usuario;
    private int codigo_turma;
    private String identificacao_turma;
    private String disciplina_turma;
    private String status;

    public DuvidaRest() {
    }

    public DuvidaRest(int id, String pergunta, String resposta, 
                    int idAluno, String nomeAluno, String usuario, 
                    int codigo_turma, String identificacao_turma, String disciplina_turma) {
        this.id = id;
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.idAluno = idAluno;
        this.nomeAluno = nomeAluno;
        this.usuario = usuario;
        this.codigo_turma = codigo_turma;
        this.identificacao_turma = identificacao_turma;
        this.disciplina_turma = disciplina_turma;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getCodigo_turma() {
        return codigo_turma;
    }

    public void setCodigo_turma(int codigo_turma) {
        this.codigo_turma = codigo_turma;
    }

    public String getIdentificacao_turma() {
        return identificacao_turma;
    }

    public void setIdentificacao_turma(String identificacao_turma) {
        this.identificacao_turma = identificacao_turma;
    }

    public String getDisciplina_turma() {
        return disciplina_turma;
    }

    public void setDisciplina_turma(String disciplina_turma) {
        this.disciplina_turma = disciplina_turma;
    }

    public String getStatus() {
        if(resposta != null){
            return "Respondida";
        }else{
            return "Não respondida";
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

 
    
    
    
    
    
}
