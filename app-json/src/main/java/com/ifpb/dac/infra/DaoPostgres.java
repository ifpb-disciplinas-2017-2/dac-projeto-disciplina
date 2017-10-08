package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Aula;
import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Disciplina;
import com.ifpb.dac.entidades.Laboratorio;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Sala;
import com.ifpb.dac.entidades.Turma;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoPostgres {

    private Connection con;

    public DaoPostgres() {
        this.con = ConFactory.getConnection();
    }

    public boolean inserirCurso(Curso c) {
        boolean cond = false;
        String sql = "INSERT INTO curso(codigo_curso, abreviacao, descricao, periodo, unidade)"
                + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, c.getCodigo());
            stmt.setString(2, c.getAbreviacao());
            stmt.setString(3, c.getDescricao());
            stmt.setInt(4, c.getPeriodo());
            stmt.setString(5, c.getUnidade());
            cond = stmt.executeUpdate() > 0;
            stmt.close();
            return cond;
        } catch (SQLException ex) {
            Logger.getLogger(DaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cond;
    }

    public boolean inserirDisciplina(Disciplina d) {
        boolean cond = false;
        String sql = "INSERT INTO disciplina(codigo_disc, abreviacao, "
                + "aulas_semana, carga_horaria, descricao, codigo_curso)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, d.getCodigo());
            stmt.setString(2, d.getAbreviacao());
            stmt.setInt(3, d.getAulas_semana());
            stmt.setInt(4, d.getCarga_horaria());
            stmt.setString(5, d.getDescricao());
            stmt.setInt(6, d.getCurso());
            cond = stmt.executeUpdate() > 0;
            stmt.close();
            return cond;
        } catch (SQLException ex) {
            Logger.getLogger(DaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cond;
    }

    public boolean inserirLaboratorio(Laboratorio l) {
        boolean cond = false;
        String sql = "INSERT INTO laboratorio(codigo_lab, abreviacao, descricao)"
                + " VALUES(?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, l.getCodigo());
            stmt.setString(2, l.getDescricao());
            stmt.setString(3, l.getDescricao());
            cond = stmt.executeUpdate() > 0;
            stmt.close();
            return cond;
        } catch (SQLException ex) {
            Logger.getLogger(DaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cond;
    }

    public boolean inserirSala(Sala s) {
        boolean cond = false;
        String sql = "INSERT INTO sala(codigo_sala, abreviacao, descricao)"
                + " VALUES(?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, s.getCodigo());
            stmt.setString(2, s.getDescricao());
            stmt.setString(3, s.getDescricao());
            cond = stmt.executeUpdate() > 0;
            stmt.close();
            return cond;
        } catch (SQLException ex) {
            Logger.getLogger(DaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cond;
    }

    public boolean inserirHorario(int c, String d, Time i, Time f) {
        boolean cond = false;
        String sql = "INSERT INTO horario(codigo_hora, descricao, inicio, fim)"
                + " VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, c);
            stmt.setString(2, d);
            stmt.setTime(3, i);
            stmt.setTime(4, f);
            cond = stmt.executeUpdate() > 0;
            stmt.close();
            return cond;
        } catch (SQLException ex) {
            Logger.getLogger(DaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cond;
    }

    public boolean inserirProfessores(Professor p){
        String sql = "INSERT INTO professor (codigo_prof, email, nome, regime,"
                + " unidade, vinculo) VALUES (?, ?, ?, ?, ?, ?)";
        boolean cond = false;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, p.getCodigo());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getNome());
            stmt.setString(4, p.getRegime());
            stmt.setString(5, p.getUnidade());
            stmt.setString(6, p.getVinculo());
            cond = stmt.executeUpdate() > 0;
            stmt.close();
            return cond;
        } catch (SQLException ex) {
            Logger.getLogger(DaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean inserirTurma(Turma t) {
        if (verificaProfessor(t.getProfessor())) {
            return false;
        } else {
            boolean cond = false;
            String sql = "INSERT INTO turma(codigo_turma, identificacao, disciplina,"
                    + "codigo_curso, codigo_prof) VALUES(?, ?, ?, ?, ?)";
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, t.getCodigo());
                stmt.setString(2, t.getIdentificacao());
                stmt.setString(3, t.getDisciplina());
                stmt.setInt(4, t.getCurso());
                stmt.setInt(5, t.getProfessor());
                cond = stmt.executeUpdate() > 0;
                stmt.close();
                return cond;
            } catch (SQLException ex) {
                Logger.getLogger(DaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            }
            return cond;
        }

    }

    public boolean inserirAula(Aula a, int codigo_aula) {
        int codigoLab = a.getLaboratorio();
        int codigoSala = a.getSala();
        if (codigoLab == 0) {
            codigoLab = 36;
        }
        if (codigoSala == 0) {
            codigoSala = 37;
        }
        if (verificaProfessor(a.getProf_cod())) {
            return false;
        } else {
            boolean cond = false;
            String sql = "INSERT INTO aula(cod_aula, dia, codigo_curso, codigo_disc,"
                    + "codigo_hora, codigo_prof, codigo_sala, codigo_lab, codigo_turma)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setInt(1, codigo_aula);
                stmt.setString(2, a.getDia_desc());
                stmt.setInt(3, a.getCur_cod());
                stmt.setInt(4, a.getDisc_cod());
                stmt.setInt(5, a.getHor_cod());
                stmt.setInt(6, a.getProf_cod());
                stmt.setInt(7, codigoSala);
                stmt.setInt(8, codigoLab);
                stmt.setInt(9, a.getTur_cod());
                cond = stmt.executeUpdate() > 0;
                stmt.close();
                return cond;
            } catch (SQLException ex) {
                Logger.getLogger(DaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
            }
            return cond;
        }
    }

    public boolean inserirUsuarios(Professor prof, int id) {
        boolean cond = false;
        String sql = "INSERT INTO usuario (id, nome, email, senha, tipo, logado)"
                + "VALUES (?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, prof.getNome());
            stmt.setString(3, prof.getEmail());
            stmt.setString(4, "12345");
            stmt.setString(5, "Professor");
            stmt.setBoolean(6, false);
            cond = stmt.executeUpdate() > 0;
            stmt.close();
            return cond;
        } catch (SQLException ex) {
            Logger.getLogger(DaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cond;
    }
    
    public boolean inserirPedidos(Professor p, int id){
        String sql = "INSERT INTO pedido (id, nome, email, senha, tipo, prioridade) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        boolean cond = false;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setString(2, p.getNome());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, "12345");
            stmt.setString(5 , "Professor");
            stmt.setInt(6, 0);
            cond = stmt.executeUpdate() > 0;
            return cond;
        } catch (SQLException ex) {
            Logger.getLogger(DaoPostgres.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cond;
    }

    public boolean verificaProfessor(int codigo) {
        return codigo == 143 || codigo == 146 || codigo == 147
                || codigo == 175 || codigo == 206;
    }

}