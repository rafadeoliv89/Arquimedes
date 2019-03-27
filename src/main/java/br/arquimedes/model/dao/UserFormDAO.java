/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.model.dao;

import br.arquimedes.model.UserForm;
import br.arquimedes.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class UserFormDAO {

    public void cadastrar(UserForm userform) throws ClassNotFoundException, SQLException {

        System.out.println("-----Dentro da UserformDAO cadastrar-------");

        Connection con = FabricaConexao.getConexao();

        try {
            PreparedStatement comando = con.prepareStatement("INSERT INTO userform (fk_idmodulo,fk_idusuario,fk_idformula,status) VALUES(?,?,?,'t')");
            comando.setInt(1, userform.getModulo().getIdModulo());
            comando.setInt(2, userform.getUsuario().getIdUsuario());
            comando.setInt(3, userform.getFormula().getIdFormula());

            comando.execute();

        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
    }
//************************************************************************************************************

    public List<UserForm> listar() throws ClassNotFoundException, SQLException {

        System.out.println("---Dentro da UserFormDAO: Listar----");

        Connection con = FabricaConexao.getConexao();

        List<UserForm> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM userform WHERE status ='t'");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserForm listagem = new UserForm();

                listagem.getUsuario().setIdUsuario(resultado.getInt("fk_idusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("fk_idmodulo"));
                listagem.getFormula().setIdFormula(resultado.getInt("fk_idformula"));

                lista.add(listagem);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro UserFormDAO listar: " + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return lista;
    }
//************************************************************************************************************
    public List<UserForm> listarId(UserForm userform) throws ClassNotFoundException, SQLException {

        System.out.println("---Dentro da UserFormDAO: listar por id----");

        Connection con = FabricaConexao.getConexao();

        List<UserForm> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM userform WHERE fk_idusuario=? AND status='t'");
            comando.setInt(1, userform.getUsuario().getIdUsuario());

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserForm listagem = new UserForm();

                listagem.getUsuario().setIdUsuario(resultado.getInt("fk_idusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("fk_idmodulo"));
                listagem.getFormula().setIdFormula(resultado.getInt("fk_idformula"));

                lista.add(listagem);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro UserFormDAO ListarID: " + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return lista;
    }
//************************************************************************************************************
    public List<UserForm> listarPorModulo(UserForm userform) throws ClassNotFoundException, SQLException {

        System.out.println("---Dentro da UserFormDAO: listar por Modulo----");

        Connection con = FabricaConexao.getConexao();

        List<UserForm> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM userform WHERE fk_idusuario=? AND fk_idmodulo='?' AND status='t'");
            comando.setInt(1, userform.getUsuario().getIdUsuario());
            comando.setInt(2, userform.getModulo().getIdModulo());

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserForm listagem = new UserForm();

                listagem.getUsuario().setIdUsuario(resultado.getInt("fk_idusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("fk_idmodulo"));
                listagem.getFormula().setIdFormula(resultado.getInt("fk_idformula"));

                lista.add(listagem);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro UserFormDAO ListarID: " + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return lista;
    }
//************************************************************************************************************

    public void inativar(UserForm userform) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da UserFormDAO: inativar----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("UPDATE userform SET status='f' WHERE fk_idformula=? AND fk_idusuario=?");
            comando.setInt(1, userform.getFormula().getIdFormula());
            comando.setInt(2, userform.getUsuario().getIdUsuario());

            comando.executeQuery();

        } catch (Exception sqlErro) {
            System.out.println("Erro UserFormDAO inativar: " + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
    }
//************************************************************************************************************

    public void ativar(UserForm userform) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da UserFormDAO: ativar----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("UPDATE userform SET status='t' WHERE fk_idformula=? AND fk_idusuario=?");
            comando.setInt(1, userform.getFormula().getIdFormula());
            comando.setInt(2, userform.getUsuario().getIdUsuario());

            comando.executeQuery();

        } catch (Exception sqlErro) {
            System.out.println("Erro UserFormDAO ativar: " + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
    }
//************************************************************************************************************   
    public List<UserForm> listarMinhasFormulas(UserForm userform) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da UserFormDAO: Listar minhas formulas----");
        Connection con = FabricaConexao.getConexao();
        List<UserForm> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT U.idusuario, U.nomeusuario, M.idModulo, M.NomeModulo, F.idFormula, F.nomeFormula, F.mascaraFormula, F.calculoFormula, F.descricao, F.variaveisFormula FROM userform AS US  INNER JOIN usuario AS U ON US.fk_idUsuario = U.idusuario INNER JOIN Modulo AS M ON US.fk_idModulo = M.idModulo INNER JOIN Formula AS F ON US.fk_idFormula = F.idFormula WHERE US.status='t' AND US.fk_idUsuario=?");
            comando.setInt(1, userform.getUsuario().getIdUsuario());

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserForm listagem = new UserForm();

                listagem.getUsuario().setIdUsuario(resultado.getInt("idusuario"));
                listagem.getUsuario().setNomeUsuario(resultado.getString("nomeusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("idModulo"));
                listagem.getModulo().setNome(resultado.getString("NomeModulo"));
                listagem.getFormula().setDescricao(resultado.getString("descricao"));
                listagem.getFormula().setIdFormula(resultado.getInt("idFormula"));
                listagem.getFormula().setNomeFormula(resultado.getString("nomeFormula"));
                listagem.getFormula().setMascaraFormula(resultado.getString("mascaraFormula"));
                listagem.getFormula().setCalculoFormula(resultado.getString("calculoFormula"));
                
                String d = (resultado.getString("variaveisFormula"));
                String e = d.replace("{", "").replace("}", "");
                listagem.getFormula().setVariaveis2(e);
                String c = (resultado.getString("variaveisFormula"));
                String b = c.replace("{", "").replace("}", "");
                String[] v = b.split(",");
                ArrayList<String> variaveis3 = new ArrayList<String>();
                for (String variavel : v) {
                    variaveis3.add(variavel);
                }
                listagem.getFormula().setVariaveis3(variaveis3);

                lista.add(listagem);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro UserFormDAO Listar minhas formulas: " + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return lista;
    }
//************************************************************************************************************   
    public List<UserForm> listarMinhasFormulasCustom(UserForm userform) throws ClassNotFoundException, SQLException {

        System.out.println("---Dentro da UserFormDAO: Listar minhas formulas----");

        Connection con = FabricaConexao.getConexao();

        List<UserForm> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT U.idusuario, U.nomeusuario, M.idModulo, M.NomeModulo, F.idFormula, F.nomeFormula, F.mascaraFormula, F.calculoFormula, F.descricao, F.variaveisFormula FROM userform AS US  INNER JOIN usuario AS U ON US.fk_idUsuario = U.idusuario INNER JOIN Modulo AS M ON US.fk_idModulo = M.idModulo INNER JOIN Formula AS F ON US.fk_idFormula = F.idFormula WHERE US.status='t' AND US.fk_idUsuario=? AND US.fk_idModulo=2");
            comando.setInt(1, userform.getUsuario().getIdUsuario());

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserForm listagem = new UserForm();

                listagem.getUsuario().setIdUsuario(resultado.getInt("idusuario"));
                listagem.getUsuario().setNomeUsuario(resultado.getString("nomeusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("idModulo"));
                listagem.getModulo().setNome(resultado.getString("NomeModulo"));
                listagem.getFormula().setDescricao(resultado.getString("descricao"));
                listagem.getFormula().setIdFormula(resultado.getInt("idFormula"));
                listagem.getFormula().setNomeFormula(resultado.getString("nomeFormula"));
                listagem.getFormula().setMascaraFormula(resultado.getString("mascaraFormula"));
                listagem.getFormula().setCalculoFormula(resultado.getString("calculoFormula"));
                
                String d = (resultado.getString("variaveisFormula"));
                String e = d.replace("{", "").replace("}", "");
                listagem.getFormula().setVariaveis2(e);
                String c = (resultado.getString("variaveisFormula"));
                String b = c.replace("{", "").replace("}", "");
                String[] v = b.split(",");
                ArrayList<String> variaveis3 = new ArrayList<String>();
                for (String variavel : v) {
                    variaveis3.add(variavel);
                }
                listagem.getFormula().setVariaveis3(variaveis3);

                lista.add(listagem);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro UserFormDAO Listar minhas formulas: " + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return lista;
    }
//************************************************************************************************************   
    public List<UserForm> listarFormulasId(UserForm userform) throws ClassNotFoundException, SQLException {

        System.out.println("---Dentro da UserFormDAO: Listar minhas formulas----");

        Connection con = FabricaConexao.getConexao();

        List<UserForm> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT U.idusuario, U.nomeusuario, M.idModulo, M.NomeModulo, F.idFormula, F.nomeFormula, F.mascaraFormula, F.calculoFormula, F.descricao, F.variaveisFormula FROM userform AS US  INNER JOIN usuario AS U ON US.fk_idUsuario = U.idusuario INNER JOIN Modulo AS M ON US.fk_idModulo = M.idModulo INNER JOIN Formula AS F ON US.fk_idFormula = F.idFormula WHERE US.fk_idformula=?");
            comando.setInt(1, userform.getFormula().getIdFormula());

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserForm listagem = new UserForm();

                listagem.getUsuario().setIdUsuario(resultado.getInt("idusuario"));
                listagem.getUsuario().setNomeUsuario(resultado.getString("nomeusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("idModulo"));
                listagem.getModulo().setNome(resultado.getString("NomeModulo"));
                listagem.getFormula().setDescricao(resultado.getString("descricao"));
                listagem.getFormula().setIdFormula(resultado.getInt("idFormula"));
                listagem.getFormula().setNomeFormula(resultado.getString("nomeFormula"));
                listagem.getFormula().setMascaraFormula(resultado.getString("mascaraFormula"));
                listagem.getFormula().setCalculoFormula(resultado.getString("calculoFormula"));
                
                String d = (resultado.getString("variaveisFormula"));
                String e = d.replace("{", "").replace("}", "");
                listagem.getFormula().setVariaveis2(e);
                String c = (resultado.getString("variaveisFormula"));
                String b = c.replace("{", "").replace("}", "");
                String[] v = b.split(",");
                ArrayList<String> variaveis3 = new ArrayList<String>();
                for (String variavel : v) {
                    variaveis3.add(variavel);
                }
                listagem.getFormula().setVariaveis3(variaveis3);

                lista.add(listagem);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro UserFormDAO Listar minhas formulas: " + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return lista;
    }
//************************************************************************************************************   
    public List<UserForm> listarFormulasModulo(UserForm userform) throws ClassNotFoundException, SQLException {

        System.out.println("---Dentro da UserFormDAO: Listar formulas por modulos----");

        Connection con = FabricaConexao.getConexao();

        List<UserForm> lista = new ArrayList();
        
        try {
            PreparedStatement comando = con.prepareStatement("SELECT U.idusuario,U.nomeusuario,M.idModulo,M.NomeModulo,F.idFormula,F.nomeFormula,F.mascaraFormula,F.calculoFormula,F.descricao, F.variaveisFormula FROM userform AS US  INNER JOIN usuario AS U ON US.fk_idUsuario = U.idusuario INNER JOIN Modulo AS M ON US.fk_idModulo = M.idModulo INNER JOIN Formula AS F ON US.fk_idFormula = F.idFormula WHERE US.status='t' AND US.fk_idModulo=?");
            comando.setInt(1, userform.getModulo().getIdModulo());

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserForm listagem = new UserForm();

                listagem.getUsuario().setIdUsuario(resultado.getInt("idusuario"));
                listagem.getUsuario().setNomeUsuario(resultado.getString("nomeusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("idModulo"));
                listagem.getModulo().setNome(resultado.getString("NomeModulo"));
                listagem.getFormula().getModulo().setNome(resultado.getString("NomeModulo"));
                listagem.getFormula().setDescricao(resultado.getString("descricao"));
                listagem.getFormula().setIdFormula(resultado.getInt("idFormula"));
                listagem.getFormula().setNomeFormula(resultado.getString("nomeFormula"));
                listagem.getFormula().setMascaraFormula(resultado.getString("mascaraFormula"));
                listagem.getFormula().setCalculoFormula(resultado.getString("calculoFormula"));
                
                String d = (resultado.getString("variaveisFormula"));
                String e = d.replace("{", "").replace("}", "");
                listagem.getFormula().setVariaveis2(e);
                String c = (resultado.getString("variaveisFormula"));
                String b = c.replace("{", "").replace("}", "");
                String[] v = b.split(",");
                ArrayList<String> variaveis3 = new ArrayList<String>();
                for (String variavel : v) {
                    variaveis3.add(variavel);
                }
                listagem.getFormula().setVariaveis3(variaveis3);
                
                lista.add(listagem);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro UserFormDAO Listar minhas formulas: " + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return lista;
    }
//************************************************************************************************************   
    public List<UserForm> listarFormulas(UserForm userform) throws ClassNotFoundException, SQLException {

        System.out.println("---Dentro da UserFormDAO: Listar formulas----");

        Connection con = FabricaConexao.getConexao();

        List<UserForm> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT U.idusuario,U.nomeusuario,M.idModulo,M.NomeModulo,F.idFormula,F.nomeFormula,F.mascaraFormula,F.calculoFormula,F.descricao, F.variaveisFormula FROM userform AS US  INNER JOIN usuario AS U ON US.fk_idUsuario = U.idusuario INNER JOIN Modulo AS M ON US.fk_idModulo = M.idModulo INNER JOIN Formula AS F ON US.fk_idFormula = F.idFormula WHERE US.status='t'");

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserForm listagem = new UserForm();

                listagem.getUsuario().setIdUsuario(resultado.getInt("idusuario"));
                listagem.getUsuario().setNomeUsuario(resultado.getString("nomeusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("idModulo"));
                listagem.getModulo().setNome(resultado.getString("NomeModulo"));
                listagem.getFormula().setDescricao(resultado.getString("descricao"));
                listagem.getFormula().setIdFormula(resultado.getInt("idFormula"));
                listagem.getFormula().setNomeFormula(resultado.getString("nomeFormula"));
                listagem.getFormula().setMascaraFormula(resultado.getString("mascaraFormula"));
                listagem.getFormula().setCalculoFormula(resultado.getString("calculoFormula"));
                
                String d = (resultado.getString("variaveisFormula"));
                String e = d.replace("{", "").replace("}", "");
                listagem.getFormula().setVariaveis2(e);
                String c = (resultado.getString("variaveisFormula"));
                String b = c.replace("{", "").replace("}", "");
                String[] v = b.split(",");
                ArrayList<String> variaveis3 = new ArrayList<String>();
                for (String variavel : v) {
                    variaveis3.add(variavel);
                }
                listagem.getFormula().setVariaveis3(variaveis3);

                lista.add(listagem);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro UserFormDAO Listar formulas: " + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return lista;
    }
//************************************************************************************************************   
    public UserForm buscarFormulasId(UserForm userform) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da UserFormDAO: buscar formulas Id----");
        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT U.idusuario,U.nomeusuario,M.idModulo,M.NomeModulo,F.idFormula,F.nomeFormula,F.mascaraFormula,F.calculoFormula,F.descricao, F.variaveisFormula FROM userform AS US  INNER JOIN usuario AS U ON US.fk_idUsuario = U.idusuario INNER JOIN Modulo AS M ON US.fk_idModulo = M.idModulo INNER JOIN Formula AS F ON US.fk_idFormula = F.idFormula WHERE US.fk_idFormula=?");
            comando.setInt(1, userform.getFormula().getIdFormula());
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                userform.getUsuario().setIdUsuario(resultado.getInt("idusuario"));
                userform.getUsuario().setNomeUsuario(resultado.getString("nomeusuario"));
                userform.getModulo().setIdModulo(resultado.getInt("idModulo"));
                userform.getModulo().setNome(resultado.getString("NomeModulo"));
                userform.getFormula().setDescricao(resultado.getString("descricao"));
                userform.getFormula().setIdFormula(resultado.getInt("idFormula"));
                userform.getFormula().setNomeFormula(resultado.getString("nomeFormula"));
                userform.getFormula().setMascaraFormula(resultado.getString("mascaraFormula"));
                userform.getFormula().setCalculoFormula(resultado.getString("calculoFormula"));
                
                String d = (resultado.getString("variaveisFormula"));
                String e = d.replace("{", "").replace("}", "");
                userform.getFormula().setVariaveis2(e);
                String c = (resultado.getString("variaveisFormula"));
                String b = c.replace("{", "").replace("}", "");
                String[] v = b.split(",");
                ArrayList<String> variaveis3 = new ArrayList<String>();
                for (String variavel : v) {
                    variaveis3.add(variavel);
                }
                userform.getFormula().setVariaveis3(variaveis3);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro UserFormDAO buscar formulas por id: " + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return userform;
    }
//************************************************************************************************************   
    public List<UserForm> listarFormulasModulos(UserForm userform) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da UserFormDAO: Listar formulas por modulos----");

        Connection con = FabricaConexao.getConexao();

        List<UserForm> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT U.idusuario,U.nomeusuario,M.idModulo,M.NomeModulo,F.idFormula,F.nomeFormula,F.mascaraFormula,F.calculoFormula,F.descricao, F.variaveisFormula FROM userform AS US  INNER JOIN usuario AS U ON US.fk_idUsuario = U.idusuario INNER JOIN Modulo AS M ON US.fk_idModulo = M.idModulo INNER JOIN Formula AS F ON US.fk_idFormula = F.idFormula WHERE US.status='t' AND US.fk_idModulo=?");
            comando.setInt(1, userform.getModulo().getIdModulo());

            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserForm listagem = new UserForm();

                listagem.getUsuario().setIdUsuario(resultado.getInt("idusuario"));
                listagem.getUsuario().setNomeUsuario(resultado.getString("nomeusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("idModulo"));
                listagem.getModulo().setNome(resultado.getString("NomeModulo"));
                listagem.getFormula().setDescricao(resultado.getString("descricao"));
                listagem.getFormula().setIdFormula(resultado.getInt("idFormula"));
                listagem.getFormula().setNomeFormula(resultado.getString("nomeFormula"));
                listagem.getFormula().setMascaraFormula(resultado.getString("mascaraFormula"));
                listagem.getFormula().setCalculoFormula(resultado.getString("calculoFormula"));
                
                String d = (resultado.getString("variaveisFormula"));
                String e = d.replace("{", "").replace("}", "");
                listagem.getFormula().setVariaveis2(e);
                String c = (resultado.getString("variaveisFormula"));
                String b = c.replace("{", "").replace("}", "");
                String[] v = b.split(",");
                ArrayList<String> variaveis3 = new ArrayList<String>();
                for (String variavel : v) {
                    variaveis3.add(variavel);
                }
                listagem.getFormula().setVariaveis3(variaveis3);

                lista.add(listagem);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro UserFormDAO Listar formulas por modulos: " + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return lista;
    }
}