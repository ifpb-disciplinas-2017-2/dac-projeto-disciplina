
package com.ifpb.ads.main;

import com.ifpb.dac.interfaces.Teste;
import com.ifpb.dac.servicelocater.ServiceLocator;

public class Main {

    public static void main(String[] args) {
        
        Teste teste = new ServiceLocator().
                lookup("java:global/core/TesteEJB");
        System.out.println(teste.visualizar());
    }
    
}
