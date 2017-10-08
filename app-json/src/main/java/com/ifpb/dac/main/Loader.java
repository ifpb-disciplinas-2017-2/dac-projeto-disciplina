package com.ifpb.dac.main;

import com.ifpb.dac.entidades.Aula;
import com.ifpb.dac.entidades.Horario;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.infra.DaoPostgres;
import com.ifpb.dac.infra.ReadJSON;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Loader {

    public static void main(String[] args) {
        ReadJSON r = new ReadJSON();

        DaoPostgres dao = new DaoPostgres();

        r.objetosCurso().forEach(c -> dao.inserirCurso(c));
        r.objetosDisciplina().forEach(d -> dao.inserirDisciplina(d));
        r.objetosLaboratorios().forEach(l -> dao.inserirLaboratorio(l));
        r.objetosSala().forEach(s -> dao.inserirSala(s));
        r.objetosProfessor().forEach(p -> dao.inserirProfessores(p));
        r.objetosTurma().forEach(t -> dao.inserirTurma(t));
        
        List<Horario> h = r.objetosHora();
        Set<Integer> cod = new TreeSet<>();
        Set<String> desc = new TreeSet<>();
        Set<String> inicio = new TreeSet<>();
        Set<String> fim = new TreeSet<>();
        
        for (Horario aux : h) {
            cod.add(aux.getHor_cod());
            desc.add(aux.getHor_desc());
            inicio.add(aux.getHor_inicio());
            fim.add(aux.getHor_fim());
        }
        
        for (int i = 0; i < cod.size(); i++) {
            int c = (int) cod.toArray()[i];
            String d = (String) desc.toArray()[i];
            String ini = (String) inicio.toArray()[i];
            String f = (String) fim.toArray()[i];
            DateFormat formatador = new SimpleDateFormat("HH:mm:ss");
            try {
                Time horaInicio = new Time(formatador.parse(ini).getTime());
                Time horaFim = new Time(formatador.parse(f).getTime());
                dao.inserirHorario(c, d, horaInicio, horaFim);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        
        List<Aula> aulas = r.objetosAula();
        for(int i = 0; i < aulas.size(); i++){
            int auxiliar = i + 1;
            dao.inserirAula(aulas.get(i), auxiliar);
        }
        
        List<Professor> professores = r.objetosProfessor();
        for(int i = 0; i < professores.size(); i++){
            int auxiliar = i + 2;
            dao.inserirUsuarios(professores.get(i), auxiliar);
        }
        
        List<Professor> pedidos = r.objetosProfessor();
        for(int i = 0; i < pedidos.size(); i++){
            int auxiliar = i + 1;
            dao.inserirPedidos(pedidos.get(i), auxiliar);
        }
        
    }

}
