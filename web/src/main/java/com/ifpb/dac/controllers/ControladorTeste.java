
package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Sala;
import com.ifpb.dac.enums.Regime;
import com.ifpb.dac.enums.Unidade;
import com.ifpb.dac.enums.Vinculo;
import com.ifpb.dac.interfaces.ProfessorDao;
import com.ifpb.dac.interfaces.SalaDao;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ControladorTeste {    
    
    @EJB
    private ProfessorDao dao;
    @EJB
    private SalaDao salaDao;
    private List<Professor> todosProfessores = new ArrayList<>();

    public List<Professor> getTodosProfessores() {
        return dao.listarTodos();
    }

    public void setTodosProfessores(List<Professor> todosProfessores) {
        this.todosProfessores = todosProfessores;
    }
    
    public String acao(){
        dao.adicionar(new Professor("X", "X", Regime.DE, 
                Unidade.UNIND, Vinculo.Efetivo));
        salaDao.adicionar(new Sala("Teste", "Teste"));
        return null;
    }
    
}
