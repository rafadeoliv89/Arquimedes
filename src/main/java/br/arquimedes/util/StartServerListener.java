/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.util;

import br.arquimedes.timer.TarefaEnviarEmailFim;
import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartServerListener  implements ServletContextListener{

   @Override
   public void contextInitialized(ServletContextEvent contextEvent) {
       System.out.println("Servidor iniciado");
       Timer t = new Timer();
       TarefaEnviarEmailFim tEmail = new TarefaEnviarEmailFim();

        t.scheduleAtFixedRate(tEmail, 0, 86400000);
        //24h = 86400000
   }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       System.out.println("Servidor caiu");
    }
    
}
