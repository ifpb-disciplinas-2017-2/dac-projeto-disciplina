
package com.ifpb.dac.controllers;

import com.ifpb.dac.interfaces.Teste;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ControladorTeste {
    
    @EJB
    private Teste teste;
    
    public String acao(){
        System.out.println(teste.visualizar());
        return null;
    }
    
}
