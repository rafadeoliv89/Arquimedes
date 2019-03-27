package br.arquimedes.timer;

import br.arquimedes.model.Plano;
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
public class TarefaRecadastrarPlano extends TimerTask {

    public TarefaRecadastrarPlano() {
    }

    @Override
    public void run() {
        UserModDAO userModDao = new UserModDAO();
        Plano plano = new Plano();
        UserModDAO usermodDao = new UserModDAO();
        ConversorData dt = new ConversorData();
        Date date = new Date(System.currentTimeMillis());

        List<UserMod> listUserMod;
        try {
            listUserMod = userModDao.listarExp(dt.calculaDataFim4(date));

            listUserMod.forEach(list -> {
                try {
                    new UsuarioDAO().buscarId(list.getUsuario());
                    new PessoaDAO().buscar(list.getUsuario());
                    new ModuloDAO().buscarId(list.getModulo());
                    new PlanoDAO().buscar(list.getPlano());

                    usermodDao.reCadastrar(list.getModulo().getIdModulo(), list.getUsuario().getIdUsuario(), list.getPlano().getIdPlano(), dt.calculaDataFim2(date, list.getPlano().getTempoPlano()));
                    
                    //EmailRecadastroPlano mail = new EmailRecadastroPlano(list.getUsuario(), list.getModulo(), list.getPlano());
                    //mail.enviar();
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(TarefaRecadastrarPlano.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(TarefaRecadastrarPlano.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Teste de giro de recadastro");
    }
}
