/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.model.dao;

import br.arquimedes.model.Plano;
import br.arquimedes.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO {

    public void cadastrar(Plano plano) throws ClassNotFoundException, SQLException {
        System.out.println("-----Dentro da Cadastrar DAO-------");
        Connection con = FabricaConexao.getConexao();

        try {
            PreparedStatement comando = con.prepareStatement("INSERT INTO plano (nomeplano, valor, tempoplano, visibilidade) VALUES(?,?,?,'t')");
            comando.setString(1, plano.getNomePlano());
            comando.setDouble(2, plano.getValor());
            comando.setInt(3, plano.getTempoPlano());

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
    public boolean validaCadastro(Plano plano) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da validar cadastro----");

        Plano validar;
        boolean resposta = true;

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM plano WHERE nomeplano=?");
            comando.setString(1, plano.getNomePlano());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                validar = new Plano();
                validar.setNomePlano(resultado.getString("nomeplano"));
                resposta = false;
            } else {
                resposta = true;
            }

        } catch (Exception sqlErro) {
            System.out.println("Erro:" + sqlErro.getMessage());
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
    public List<Plano> listar() throws ClassNotFoundException, SQLException {        
        System.out.println("---Dentro da PlanoDAO Listar----");        
        Connection con = FabricaConexao.getConexao();
                
        List<Plano> lista = new ArrayList();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM  plano WHERE visibilidade='t'");
            ResultSet resultado = comando.executeQuery();
            
            while (resultado.next()) {
                Plano listagem = new Plano();
                
                listagem.setIdPlano(resultado.getInt("idplano"));
                listagem.setNomePlano(resultado.getString("nomeplano"));
                listagem.setValor(resultado.getDouble("valor"));
                listagem.setTempoPlano(resultado.getInt("tempoplano"));
                
                lista.add(listagem);
            }
            
        } catch (Exception sqlErro) {
            sqlErro.printStackTrace();
            System.out.println("Erro PlanoDAO Listar:" + sqlErro.getMessage());
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
    public Plano buscar(Plano plano) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro do Buscar DAO----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM  plano WHERE idplano=?");
            comando.setInt(1, plano.getIdPlano());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {

                plano.setIdPlano(resultado.getInt("idPlano"));
                plano.setNomePlano(resultado.getString("nomeplano"));
                plano.setValor(resultado.getDouble("valor"));
                plano.setTempoPlano(resultado.getInt("tempoplano"));
            }

        } catch (Exception sqlErro) {
            System.out.println("Erro:" + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return plano;
    }  
//************************************************************************************************************
    public void alterar(Plano plano) throws ClassNotFoundException, SQLException {
        System.out.println("------------Dentro Alterar Planos DAO-------------");

        try {

            Connection con = FabricaConexao.getConexao();

            PreparedStatement comando = con.prepareStatement("UPDATE plano SET nomeplano=?, valor=?, tempoplano=?  WHERE idplano=?");
            comando.setString(1, plano.getNomePlano());
            comando.setDouble(2, plano.getValor());
            comando.setInt(3, plano.getTempoPlano());
            
            comando.setInt(4, plano.getIdPlano());

            comando.execute();
            con.close();

        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        }
    }
//************************************************************************************************************
    public void excluir(Plano plano) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da excluir DAO----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("Delete * FROM plano WHERE idplano=?");
            comando.setInt(1, plano.getIdPlano());

            ResultSet resultado = comando.executeQuery();

        } catch (Exception sqlErro) {
            System.out.println("Erro:" + sqlErro.getMessage());
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

}

