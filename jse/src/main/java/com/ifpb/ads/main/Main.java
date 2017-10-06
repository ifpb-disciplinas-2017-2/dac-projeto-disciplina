package com.ifpb.ads.main;

import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.interfaces.DisciplinaDao;
import com.ifpb.dac.interfaces.HorarioDao;
import com.ifpb.dac.interfaces.LaboratorioDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import com.ifpb.dac.interfaces.SalaDao;
import com.ifpb.dac.interfaces.TurmaDao;
import com.ifpb.dac.servicelocater.ServiceLocator;

public class Main {

    public static void main(String[] args) {
        // Teste Manual, todas as classes de criação ja estão definidas
//        SalaDao sala = ServiceLocator.lookup("java:global/core/SalaDaoImpl");
//        System.out.println(sala.buscarPorId(4).toString());
//        
//        CursoDao curso = ServiceLocator.lookup("java:global/core/CursoDaoImpl");
//        System.out.println(curso.buscarPorId(2).toString());
//        LaboratorioDao lab = ServiceLocator.lookup("java:global/core/LaboratorioDaoImpl");
//        System.out.println(lab.buscarPorId(2).toString());
//        DisciplinaDao disc = ServiceLocator.lookup("java:global/core/DisciplinaDaoImpl");
//        System.out.println(disc.buscarPorId(8277).toString());
//        
//        HorarioDao horaDao = ServiceLocator.lookup("java:global/core/HorarioDaoImpl");
//        System.out.println(horaDao.buscarPorId(2).toString());
//        
//        ProfessorDao pDao = ServiceLocator.lookup("java:global/core/ProfessorDaoImpl");
//        System.out.println(pDao.buscarPorId(9).toString());
//        
//        TurmaDao turmaDao = ServiceLocator.lookup("java:global/core/TurmaDaoImpl");
//        System.out.println(turmaDao.buscarPorId(20915).toString());
        
//        AulaDao aulaDao = ServiceLocator.lookup("java:global/core/AulaDaoImpl");        
//        System.out.println(aulaDao.buscarPorId(4).toString());
    }

}
