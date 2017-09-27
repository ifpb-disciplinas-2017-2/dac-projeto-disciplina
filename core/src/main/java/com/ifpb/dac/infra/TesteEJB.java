package com.ifpb.dac.infra;

import com.ifpb.dac.interfaces.Teste;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote(Teste.class)
@Stateless
public class TesteEJB implements Teste {
    
    @Override
    public String visualizar(){
        return "Exemplo funcionando...";
    }
    
}
