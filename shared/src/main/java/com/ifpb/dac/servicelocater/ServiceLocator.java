package com.ifpb.dac.servicelocater;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator {

    public static <T> T lookup(String resource) {
        try {
            Properties properties = new Properties();
            properties.put(InitialContext.INITIAL_CONTEXT_FACTORY,
                    "com.sun.enterprise.naming.SerialInitContextFactory");
            properties.setProperty("org.omg.CORBA.ORBInitialHost", "host-core");
            properties.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
            InitialContext context = new InitialContext(properties);
            return (T) context.lookup(resource);
        } catch (NamingException ex) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
