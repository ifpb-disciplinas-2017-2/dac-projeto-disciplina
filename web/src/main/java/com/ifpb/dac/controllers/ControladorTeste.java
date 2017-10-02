
package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.enums.Regime;
import com.ifpb.dac.enums.Unidade;
import com.ifpb.dac.enums.Vinculo;
import com.ifpb.dac.interfaces.ProfessorDao;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ControladorTeste {    
    
    @EJB
    private ProfessorDao dao;
    
    public String acao(){
        dao.adicionar(new Professor("X", "X", Regime.DE, 
                Unidade.UNIND, Vinculo.Efetivo));
        return null;
    }
    
}
