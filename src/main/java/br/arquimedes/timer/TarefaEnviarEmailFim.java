package br.arquimedes.timer;

import br.arquimedes.email.EmailFimPlano;
import br.arquimedes.model.UserMod;
import br.arquimedes.model.dao.ModuloDAO;
import br.arquimedes.model.dao.PessoaDAO;
import br.arquimedes.model.dao.PlanoDAO;
import br.arquimedes.model.dao.UserModDAO;
import br.arquimedes.model.dao.UsuarioDAO;
import br.arquimedes.util.ConversorData;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class TarefaEnviarEmailFim extends TimerTask {

    public TarefaEnviarEmailFim() {
    }

    @Override
    public void run() {
        UserModDAO userModDao = new UserModDAO();
        ConversorData dt = new ConversorData();
        Date date = new Date(System.currentTimeMillis());

        List<UserMod> listUserMod;
        try {
            listUserMod = userModDao.listarExp(dt.calculaDataFim3(date));

            listUserMod.forEach(list -> {
                try {
                    new UsuarioDAO().buscarId(list.getUsuario());
                    new PessoaDAO().buscar(list.getUsuario());
                    new ModuloDAO().buscarId(list.getModulo());
                    new PlanoDAO().buscar(list.getPlano());
                    
                    EmailFimPlano mail = new EmailFimPlano(list.getUsuario(), list.getModulo(), list.getPlano());
                    mail.enviar();
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TarefaEnviarEmailFim.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TarefaEnviarEmailFim.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Teste de giro de envio");
    }

}
