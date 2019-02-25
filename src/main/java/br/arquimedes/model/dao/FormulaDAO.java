/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.model.dao;

import br.arquimedes.model.Formula;
import br.arquimedes.util.FabricaConexao;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FormulaDAO {

    public Formula buscar(Formula formula) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da FormulaDAO Buscar----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM  Formula WHERE nomeFormula=?");
            comando.setString(1, formula.getNomeFormula());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                formula.setIdFormula(resultado.getInt("idFormula"));
                formula.getUsuario().setIdUsuario(resultado.getInt("fk_idusuario"));
                formula.setNomeFormula(resultado.getString("nomeFormula"));
                formula.setDescricao(resultado.getString("descricao"));
                formula.setMascaraFormula(resultado.getString("mascaraformula"));
                formula.setCalculoFormula(resultado.getString("calculoformula"));
                formula.setVariaveis2(resultado.getString("variaveisformula"));
                formula.setIdFormulaDependente(resultado.getInt("idformuladependente"));
                formula.setStatus(resultado.getBoolean("statusformula"));
            }

        } catch (Exception sqlErro) {
            System.out.println("Erro FormulaDAO buscar:" + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return formula;
    }
//************************************************************************************************************
public Formula buscarId(Formula formula) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da FormulaDAO BuscarId----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM  Formula WHERE idFormula=?");
            comando.setInt(1, formula.getIdFormula());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                formula.setIdFormula(resultado.getInt("idFormula"));
                formula.getUsuario().setIdUsuario(resultado.getInt("fk_idusuario"));
                formula.setNomeFormula(resultado.getString("nomeFormula"));
                formula.setDescricao(resultado.getString("descricao"));
                formula.setMascaraFormula(resultado.getString("mascaraformula"));
                formula.setCalculoFormula(resultado.getString("calculoformula"));
                formula.setVariaveis2(resultado.getString("variaveisformula"));
                formula.setIdFormulaDependente(resultado.getInt("idformuladependente"));
                formula.setStatus(resultado.getBoolean("statusformula"));
            }

        } catch (Exception sqlErro) {
            System.out.println("Erro FormulaDAO buscar:" + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return formula;
    }
//************************************************************************************************************    
    public Formula cadastrar(Formula formula) throws SQLException, ClassNotFoundException {

        System.out.println("---Dentro do FormulasDAO Cadastrar");

        Connection con = FabricaConexao.getConexao();

        try {
            PreparedStatement comando = con.prepareStatement("INSERT INTO formula (nomeFormula, fk_idusuario, descricao, mascaraFormula, calculoFormula, variaveisFormula, idformuladependente, statusformula) VALUES (?,?,?,?,?,?,?,'t')", Statement.RETURN_GENERATED_KEYS);;

            String[] a = formula.getVariaveis();

            Array array = con.createArrayOf("varchar", a);

            comando.setString(1, formula.getNomeFormula());
            comando.setInt(2, formula.getUsuario().getIdUsuario());
            comando.setString(3, formula.getDescricao());
            comando.setString(4, formula.getMascaraFormula());
            comando.setString(5, formula.getCalculoFormula());
            comando.setArray(6, array);
            comando.setInt(7, formula.getIdFormulaDependente());
            comando.execute();
            
            ResultSet resultado = comando.getGeneratedKeys();
            
            if(resultado.next()){
                formula.setIdFormula(resultado.getInt("idFormula"));
            }

        } catch (Exception sqlErro) {
            System.out.println("Erro FormulaDAO cadastrar:" + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return formula;
    }
//************************************************************************************************************
    public List<Formula> listar() throws SQLException, ClassNotFoundException {

        System.out.println("---Dentro do FormulasDAO Listar");

        Connection con = FabricaConexao.getConexao();
        List<Formula> lista = new ArrayList();
        
        try {
            
            PreparedStatement comando = con.prepareStatement("Select * from formula WHERE statusformula='t'");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Formula form = new Formula();
                form.setIdFormula(resultado.getInt("idFormula"));
                form.getUsuario().setIdUsuario(resultado.getInt("fk_idusuario"));
                form.setNomeFormula(resultado.getString("nomeFormula"));
                form.setDescricao(resultado.getString("descricao"));
                form.setMascaraFormula(resultado.getString("mascaraformula"));
                form.setCalculoFormula(resultado.getString("calculoformula"));
                form.setVariaveis2(resultado.getString("variaveisformula"));
                form.setIdFormulaDependente(resultado.getInt("idformuladependente"));
                form.setStatus(resultado.getBoolean("statusformula"));

                lista.add(form);

            }
        } catch (Exception sqlErro) {
            System.out.println("Erro FormulaDAO listar:" + sqlErro.getMessage());
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
    public List<Formula> listarGeral() throws SQLException, ClassNotFoundException {

        System.out.println("---Dentro do FormulasDAO ListarGeral");

        Connection con = FabricaConexao.getConexao();
        List<Formula> lista = new ArrayList();
        
        try {
            
            PreparedStatement comando = con.prepareStatement("Select * from formula WHERE fk_idModulo!=2");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Formula form = new Formula();
                form.setIdFormula(resultado.getInt("idFormula"));
                form.getUsuario().setIdUsuario(resultado.getInt("fk_idusuario"));
                form.setNomeFormula(resultado.getString("nomeFormula"));
                form.setDescricao(resultado.getString("descricao"));
                form.setMascaraFormula(resultado.getString("mascaraformula"));
                form.setCalculoFormula(resultado.getString("calculoformula"));
                form.setVariaveis2(resultado.getString("variaveisformula"));
                form.setIdFormulaDependente(resultado.getInt("idformuladependente"));
                form.setStatus(resultado.getBoolean("statusformula"));

                lista.add(form);

            }
        } catch (Exception sqlErro) {
            System.out.println("Erro FormulaDAO listar:" + sqlErro.getMessage());
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
    public List listarId(Formula formula) throws SQLException, ClassNotFoundException {
        
        System.out.println("---Dentro do FormulasDAO ListarId");

        Connection con = FabricaConexao.getConexao();
        List<Formula> lista = new ArrayList();
        
        try {
            
            PreparedStatement comando = con.prepareStatement("Select * from formula where fk_idusuario=?");
            comando.setInt(1, formula.getUsuario().getIdUsuario());
               
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Formula form = new Formula();
                form.setIdFormula(resultado.getInt("idFormula"));
                form.getUsuario().setIdUsuario(resultado.getInt("fk_idusuario"));
                form.setNomeFormula(resultado.getString("nomeFormula"));
                form.setDescricao(resultado.getString("descricao"));
                form.setMascaraFormula(resultado.getString("mascaraformula"));
                form.setCalculoFormula(resultado.getString("calculoformula"));
                form.setVariaveis2(resultado.getString("variaveisformula"));
                form.setIdFormulaDependente(resultado.getInt("idformuladependente"));
                form.setStatus(resultado.getBoolean("statusformula"));

                lista.add(form);

            }
        } catch (Exception sqlErro) {
            System.out.println("Erro FormulaDAO listarId:" + sqlErro.getMessage());
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
    public void inativar(Formula formula) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da UsuarioDAO inativar----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("UPDATE formula SET statusformula='f' WHERE idformula=?");
            comando.setInt(1, formula.getIdFormula());
            comando.executeQuery();

        } catch (Exception sqlErro) {
            System.out.println("Erro FormulaDAO inativar:" + sqlErro.getMessage());
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
    public void ativar(Formula formula) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da UsuarioDAO ativar----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("UPDATE formula SET statusformula='t' WHERE idformula=?");
            comando.setInt(1, formula.getIdFormula());
            comando.executeQuery();

        } catch (Exception sqlErro) {
            System.out.println("Erro FormulaDAO ativar:" + sqlErro.getMessage());
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
    public boolean validaNome(Formula formula) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da FormulaDAO validar nome----");

        Formula validar;
        boolean resposta = true;

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM formula WHERE nomeFormula=?");
            comando.setString(1, formula.getNomeFormula());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                validar = new Formula();
                validar.setNomeFormula(resultado.getString("nomeformula"));
                resposta = false;
            } else {
                resposta = true;
            }

        } catch (Exception sqlErro) {
            System.out.println("Erro :" + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return resposta;
    }
//************************************************************************************************************
    public Formula validarMascara(Formula formula) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da FormulaDAO validar mascaraADM----");
        Connection con = FabricaConexao.getConexao();
        Formula validar = null;
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM  Formula WHERE mascaraFormula=?");
            comando.setString(1, formula.getMascaraFormula());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                validar = new Formula();
                validar.setIdFormula(resultado.getInt("idFormula"));
                validar.getUsuario().setIdUsuario(resultado.getInt("fk_idusuario"));
                validar.setNomeFormula(resultado.getString("nomeFormula"));
                validar.setDescricao(resultado.getString("descricao"));
                validar.setMascaraFormula(resultado.getString("mascaraformula"));
                validar.setCalculoFormula(resultado.getString("calculoformula"));
                validar.setVariaveis2(resultado.getString("variaveisformula"));
                validar.setIdFormulaDependente(resultado.getInt("idformuladependente"));
                validar.setStatus(resultado.getBoolean("statusformula"));
            }

        } catch (Exception sqlErro) {
            System.out.println("Erro FormulaDAO validar mascara:" + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return validar;
    }

//************************************************************************************************************
}
