/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.model.dao;

import br.arquimedes.model.Modulo;
import br.arquimedes.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ModuloDAO {

    public Modulo cadastrar(Modulo modulo) throws ClassNotFoundException, SQLException {

        System.out.println("-----Dentro da CadastrarModuloDAO-------");

        Connection con = FabricaConexao.getConexao();

        try {

            PreparedStatement comando = con.prepareStatement("INSERT INTO modulo (nomemodulo,descricao,statusmodulo,visibilidade) VALUES(?,?,'f','t')", Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, modulo.getNome());
            comando.setString(2, modulo.getDescricao());

            comando.execute();
            
            ResultSet resultado = comando.getGeneratedKeys();
            
            if(resultado.next()){
                modulo.setIdModulo(resultado.getInt("idmodulo"));
            }
            
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
        return modulo;
    }
//************************************************************************************************************
    public boolean validaCadastro(Modulo modulo) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da validar cadastro----");

        Modulo validaModulo;
        boolean resposta = true;

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM modulo WHERE nomemodulo=?");
            comando.setString(1, modulo.getNome());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                validaModulo = new Modulo();
                validaModulo.setNome(resultado.getString("nome"));
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
    public List<Modulo> listar() throws ClassNotFoundException, SQLException {
        
        System.out.println("---Dentro da DAO: ListaModulo----");
        
        Connection con = FabricaConexao.getConexao();
                
        List<Modulo> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM modulo WHERE visibilidade='t'");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Modulo listagemMod = new Modulo();

                listagemMod.setIdModulo(resultado.getInt("idmodulo"));
                listagemMod.setNome(resultado.getString("nomemodulo"));
                listagemMod.setDescricao(resultado.getString("descricao"));
                listagemMod.setStatusModulo(resultado.getBoolean("statusmodulo"));
                
                lista.add(listagemMod);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro no listar Modulo DAO");
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
        return lista;
    }
//************************************************************************************************************
    public List<Modulo> listaModuloAtivo() throws ClassNotFoundException, SQLException {
        
        System.out.println("---Dentro da DAO: ListaModuloAtivo----");
        
        Connection con = FabricaConexao.getConexao();                
        List<Modulo> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM modulo WHERE statusmodulo='t' AND visibilidade='t'");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Modulo listagemMod = new Modulo();

                listagemMod.setIdModulo(resultado.getInt("idmodulo"));
                listagemMod.setNome(resultado.getString("nomemodulo"));
                listagemMod.setStatusModulo(resultado.getBoolean("statusmodulo"));
                
                lista.add(listagemMod);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro no Listar modulo Ativo DAO");
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
        return lista;
    }
//************************************************************************************************************
    public Modulo Buscar(Modulo modulo) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro do BuscarModuloDAO----");

        Modulo moduloBusca = null;

        Connection con = FabricaConexao.getConexao();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM modulo WHERE nomemodulo =? AND visibilidade ='t'");
            comando.setString(1, modulo.getNome());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                moduloBusca = new Modulo();

                moduloBusca.setIdModulo(resultado.getInt("idmodulo"));
                moduloBusca.setNome(resultado.getString("nomemodulo"));
                moduloBusca.setDescricao(resultado.getString("descricao"));
                moduloBusca.setStatusModulo(resultado.getBoolean("statusmodulo"));
            }

        } catch (Exception sqlErro) {
            System.out.println("--Erro na Busca Modulo DAO--");
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
        return moduloBusca;
    }
//************************************************************************************************************
    public Modulo buscarId(Modulo modulo) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro do BuscarModuloDAO----");

        Connection con = FabricaConexao.getConexao();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM modulo WHERE idmodulo =?");
            comando.setInt(1, modulo.getIdModulo());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                modulo.setIdModulo(resultado.getInt("idmodulo"));
                modulo.setNome(resultado.getString("nomemodulo"));
                modulo.setDescricao(resultado.getString("descricao"));
                modulo.setStatusModulo(resultado.getBoolean("statusmodulo"));
            }

        } catch (Exception sqlErro) {
            System.out.println("--Erro na Busca Modulo DAO--");
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
        return modulo;
    }
//************************************************************************************************************
    public void alterar(Modulo modulo) throws ClassNotFoundException, SQLException {
        System.out.println("------------Dentro do ModuloDAO/Alterar-------------");

        try {

            Connection con = FabricaConexao.getConexao();
            PreparedStatement comando = con.prepareStatement("UPDATE modulo SET nomemodulo=?, descricao=? WHERE idModulo=?");
            comando.setString(1, modulo.getNome());
            comando.setString(2, modulo.getDescricao());
            comando.setInt(3, modulo.getIdModulo());

            comando.execute();
            con.close();

        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        }
    }
//************************************************************************************************************
    public void inativar(Modulo modulo) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da inativarModulo----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("UPDATE modulo SET statusmodulo='false' WHERE idmodulo=?");
            comando.setInt(1, modulo.getIdModulo());

            ResultSet resultado = comando.executeQuery();

        } catch (Exception sqlErro) {
            System.out.println("Erro no inativar modulo DAO");
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
//************************************************************************************************************
    public void ativar(Modulo modulo) throws ClassNotFoundException, SQLException {
        
        System.out.println("---Dentro da AtivarModulo----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("UPDATE modulo SET statusmodulo='true' WHERE idmodulo=?");
            comando.setInt(1, modulo.getIdModulo());

            ResultSet resultado = comando.executeQuery();

        } catch (Exception sqlErro) {
            System.out.println("Erro no ativar modulo DAO");
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
//************************************************************************************************************
    public int contarModulos() throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da Contar Modulos----");

        Connection con = FabricaConexao.getConexao();
        
        int qntModulos = 0;
        try {
            PreparedStatement comando = con.prepareStatement("SELECT COUNT (*) FROM modulo WHERE visibilidade='t'");

            ResultSet resultado = comando.executeQuery();
             while (resultado.next()) {
                 qntModulos++;
            }
            
        } catch (Exception sqlErro) {
            System.out.println("Erro no contar modulo DAO");
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
    return qntModulos;
    }
}

