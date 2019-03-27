/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.arquimedes.model.dao;

import br.arquimedes.model.Modulo;
import br.arquimedes.model.Plano;
import br.arquimedes.model.UserMod;
import br.arquimedes.model.Usuario;
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
public class UserModDAO {
    
    public void associarTodosModulos(Usuario usuario, Modulo modulo) throws ClassNotFoundException, SQLException {

        System.out.println("-----Dentro da associarTodosModulos DAO-------");

        Connection con = FabricaConexao.getConexao();

        try {
                PreparedStatement comando = con.prepareStatement("INSERT INTO usermod (fk_idmodulo,fk_idusuario,fk_idplano,datacontrato,plano,dataexpiracao,status) VALUES(?,?,1,CURRENT_DATE,'','t')");
                comando.setInt(1, modulo.getIdModulo());
                comando.setInt(2, usuario.getIdUsuario());

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
    public void associaModuloBasico(Usuario usuario) throws ClassNotFoundException, SQLException {

        System.out.println("-----Dentro da associarModuloBasico DAO-------");

        Connection con = FabricaConexao.getConexao();

        try {
                PreparedStatement comando = con.prepareStatement("INSERT INTO usermod (fk_idmodulo,fk_idusuario,fk_idplano,datacontrato, status) VALUES(1,?,1,CURRENT_DATE,'t')");
                comando.setInt(1, usuario.getIdUsuario());

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
    public void associaMeuModulo(Usuario usuario) throws ClassNotFoundException, SQLException {

        System.out.println("-----Dentro da associarModuloBasico DAO-------");

        Connection con = FabricaConexao.getConexao();        

        try {
                PreparedStatement comando = con.prepareStatement("INSERT INTO usermod (fk_idmodulo,fk_idusuario,fk_idplano,datacontrato,status) VALUES(2,?,1,CURRENT_DATE,'t')");
                comando.setInt(1, usuario.getIdUsuario());

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
    public void cadastrar(UserMod usermod, java.sql.Date dataExp) throws ClassNotFoundException, SQLException {
        System.out.println("-----Dentro da Usermod cadastrar DAO-------");
        Connection con = FabricaConexao.getConexao();
        
        try {
                PreparedStatement comando = con.prepareStatement("INSERT INTO usermod (fk_idmodulo,fk_idusuario,fk_idplano,datacontrato,dataexpiracao,status) VALUES(?,?,?,CURRENT_DATE,?,'t')");
                comando.setInt(1, usermod.getModulo().getIdModulo());
                comando.setInt(2, usermod.getUsuario().getIdUsuario());
                comando.setInt(3, usermod.getPlano().getIdPlano());
                comando.setDate(4, dataExp);
                
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
    public void reCadastrar(int idModulo,int idUsuario,int idPlano, java.sql.Date dataExp) throws ClassNotFoundException, SQLException {
        System.out.println("-----Dentro da Usermod reCadastrar DAO-------");
        Connection con = FabricaConexao.getConexao();
        
        try {
                PreparedStatement comando = con.prepareStatement("UPDATE usermod SET dataexpiracao = ? WHERE fk_idmodulo = ? and fk_idusuario = ? and fk_idplano = ?");
                comando.setDate(1, dataExp);
                comando.setInt(2, idModulo);
                comando.setInt(3, idUsuario);
                comando.setInt(4, idPlano);
                
                comando.execute();
                System.out.println("Plano recadastrado");
                
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
    public void cadastrarADM(UserMod usermod) throws ClassNotFoundException, SQLException {

        System.out.println("-----Dentro da Usermod cadastrarADM DAO-------");

        Connection con = FabricaConexao.getConexao();
        
        try {
                PreparedStatement comando = con.prepareStatement("INSERT INTO usermod (fk_idmodulo,fk_idusuario,fk_idplano,datacontrato,status) VALUES(?,1,1,CURRENT_DATE,'t')");
                comando.setInt(1, usermod.getModulo().getIdModulo());
                
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
    public List<UserMod> listar() throws ClassNotFoundException, SQLException {
        
        System.out.println("---Dentro da DAO: Lista userMod----");
        
        Connection con = FabricaConexao.getConexao();
                
        List<UserMod> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM usermod WHERE status ='t'");
            ResultSet resultado = comando.executeQuery();   

            while (resultado.next()) {
                UserMod listagem = new UserMod();

                listagem.getUsuario().setIdUsuario(resultado.getInt("fk_idusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("fk_idmodulo"));
                listagem.getPlano().setIdPlano(resultado.getInt("fk_idplano"));
                listagem.setDataContrato(resultado.getDate("datacontrato"));
                listagem.setDataExpiracao(resultado.getDate("dataexpiracao"));
                
                lista.add(listagem);
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
        return lista;
    }
//************************************************************************************************************
    public List<UserMod> listarExp(java.sql.Date dataExp) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da DAO: Lista userMod----");
        Connection con = FabricaConexao.getConexao();
        List<UserMod> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM usermod WHERE dataexpiracao=? AND status='t'");
            comando.setDate(1, dataExp);
            
            ResultSet resultado = comando.executeQuery();   

            while (resultado.next()) {
                UserMod listagem = new UserMod();

                listagem.getUsuario().setIdUsuario(resultado.getInt("fk_idusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("fk_idmodulo"));
                listagem.getPlano().setIdPlano(resultado.getInt("fk_idplano"));
                listagem.setDataContrato(resultado.getDate("datacontrato"));
                listagem.setDataExpiracao(resultado.getDate("dataexpiracao"));
                
                lista.add(listagem);
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
        return lista;
    }
//************************************************************************************************************
    public List<UserMod> listarPorUsuario(UserMod usermod) throws ClassNotFoundException, SQLException {
        
        System.out.println("---Dentro da DAO: Lista por usuario userMod----");
        
        Connection con = FabricaConexao.getConexao();
                
        List<UserMod> lista = new ArrayList();
        
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM usermod WHERE fk_idusuario=? AND status='t'");
            comando.setInt(1, usermod.getUsuario().getIdUsuario());
            
            ResultSet resultado = comando.executeQuery();   

            while (resultado.next()) {
                UserMod listagem = new UserMod();

                listagem.getUsuario().setIdUsuario(resultado.getInt("fk_idusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("fk_idmodulo"));
                listagem.getPlano().setIdPlano(resultado.getInt("fk_idplano"));
                listagem.setDataContrato(resultado.getDate("datacontrato"));
                listagem.setDataExpiracao(resultado.getDate("dataexpiracao"));
                
                lista.add(listagem);
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
        return lista;
    }

//************************************************************************************************************
    public void inativar(UserMod usermod) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da inativar UserModDAO----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("UPDATE usermod SET status='false' WHERE fk_idmodulo=? AND fk_idusuario=?");
            comando.setInt(1, usermod.getModulo().getIdModulo());
            comando.setInt(2, usermod.getUsuario().getIdUsuario());

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
//************************************************************************************************************
    public void deletar(UserMod usermod) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da inativar UserModDAO----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("DELETE FROM usermod WHERE fk_idmodulo=? AND fk_idusuario=?");
            comando.setInt(1, usermod.getModulo().getIdModulo());
            comando.setInt(2, usermod.getUsuario().getIdUsuario());

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
//************************************************************************************************************
    public void cadastrarEncerramento(UserMod usermod) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da inativar UserModDAO----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("UPDATE usermod SET dataexpiracao=CURRENT_DATE WHERE fk_idmodulo=? AND fk_idusuario=?");
            comando.setInt(1, usermod.getModulo().getIdModulo());
            comando.setInt(2, usermod.getUsuario().getIdUsuario());

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
//************************************************************************************************************   
    public List<UserMod> listarMeusModulos(UserMod usermod) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da DAO: Listar Meus Modulos----");

        Connection con = FabricaConexao.getConexao();
        List<UserMod> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT U.idusuario,U.nomeusuario,M.idModulo,M.NomeModulo,M.descricao,P.idPlano,P.nomePlano,US.datacontrato,US.dataexpiracao FROM usermod AS US  INNER JOIN usuario AS U ON US.fk_idUsuario = U.idusuario INNER JOIN Modulo AS M ON US.fk_idModulo = M.idModulo INNER JOIN Plano AS P ON US.fk_idPlano = P.idPlano WHERE US.status='t' AND US.dataexpiracao is not null AND U.idusuario=?");
            comando.setInt(1, usermod.getUsuario().getIdUsuario());
            
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserMod listagem = new UserMod();
                
                listagem.getUsuario().setIdUsuario(resultado.getInt("idusuario"));
                listagem.getUsuario().setNomeUsuario(resultado.getString("nomeusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("idmodulo"));
                listagem.getModulo().setNome(resultado.getString("nomemodulo"));
                listagem.getModulo().setDescricao(resultado.getString("descricao"));
                listagem.getPlano().setIdPlano(resultado.getInt("idplano"));
                listagem.getPlano().setNomePlano(resultado.getString("nomeplano"));
                listagem.setDataContrato(resultado.getDate("datacontrato"));
                listagem.setDataExpiracao(resultado.getDate("dataexpiracao"));

                lista.add(listagem);
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
        return lista;
    }
//************************************************************************************************************   
    public List<UserMod> listarMeusModulosCadastro(UserMod usermod) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da DAO: Listar Meus Modulos Cadastro----");
        Connection con = FabricaConexao.getConexao();

        List<UserMod> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT U.idusuario,U.nomeusuario,M.idModulo,M.NomeModulo,M.descricao,P.idPlano,P.nomePlano,US.datacontrato,US.dataexpiracao FROM usermod AS US  INNER JOIN usuario AS U ON US.fk_idUsuario = U.idusuario INNER JOIN Modulo AS M ON US.fk_idModulo = M.idModulo INNER JOIN Plano AS P ON US.fk_idPlano = P.idPlano WHERE US.status='t' AND US.fk_idModulo != 1 AND U.idusuario=?");
            comando.setInt(1, usermod.getUsuario().getIdUsuario());
            
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserMod listagem = new UserMod();
                
                listagem.getUsuario().setIdUsuario(resultado.getInt("idusuario"));
                listagem.getUsuario().setNomeUsuario(resultado.getString("nomeusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("idmodulo"));
                listagem.getModulo().setNome(resultado.getString("nomemodulo"));
                listagem.getModulo().setDescricao(resultado.getString("descricao"));
                listagem.getPlano().setIdPlano(resultado.getInt("idplano"));
                listagem.getPlano().setNomePlano(resultado.getString("nomeplano"));
                listagem.setDataContrato(resultado.getDate("datacontrato"));
                listagem.setDataExpiracao(resultado.getDate("dataexpiracao"));

                lista.add(listagem);
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
        return lista;
    }
//************************************************************************************************************   
    public List<UserMod> listarMeusModulosCalculo(UserMod usermod) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da DAO: Listar Meus Modulos calculo----");
        Connection con = FabricaConexao.getConexao();
        List<UserMod> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT U.idusuario,U.nomeusuario,M.idModulo,M.NomeModulo,M.descricao,P.idPlano,P.nomePlano,US.datacontrato,US.dataexpiracao FROM usermod AS US  INNER JOIN usuario AS U ON US.fk_idUsuario = U.idusuario INNER JOIN Modulo AS M ON US.fk_idModulo = M.idModulo INNER JOIN Plano AS P ON US.fk_idPlano = P.idPlano WHERE US.status='t' AND U.idusuario=?");
            comando.setInt(1, usermod.getUsuario().getIdUsuario());
            
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserMod listagem = new UserMod();
                
                listagem.getUsuario().setIdUsuario(resultado.getInt("idusuario"));
                listagem.getUsuario().setNomeUsuario(resultado.getString("nomeusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("idmodulo"));
                listagem.getModulo().setNome(resultado.getString("nomemodulo"));
                listagem.getModulo().setDescricao(resultado.getString("descricao"));
                listagem.getPlano().setIdPlano(resultado.getInt("idplano"));
                listagem.getPlano().setNomePlano(resultado.getString("nomeplano"));
                listagem.setDataContrato(resultado.getDate("datacontrato"));
                listagem.setDataExpiracao(resultado.getDate("dataexpiracao"));

                lista.add(listagem);
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
        return lista;
    }
//************************************************************************************************************   
    public List<UserMod> listarModulosADM(UserMod usermod) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da DAO: Listar modulos ADM----");
        Connection con = FabricaConexao.getConexao();

        List<UserMod> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT U.idusuario,U.nomeusuario,M.idModulo,M.NomeModulo,M.descricao,P.idPlano,P.nomePlano,US.datacontrato,US.dataexpiracao FROM usermod AS US  INNER JOIN usuario AS U ON US.fk_idUsuario = U.idusuario INNER JOIN Modulo AS M ON US.fk_idModulo = M.idModulo INNER JOIN Plano AS P ON US.fk_idPlano = P.idPlano WHERE US.status='t' AND US.fk_idUsuario=?");
            comando.setInt(1, usermod.getUsuario().getIdUsuario());
            
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                UserMod listagem = new UserMod();    
                listagem.getUsuario().setIdUsuario(resultado.getInt("idusuario"));
                listagem.getUsuario().setNomeUsuario(resultado.getString("nomeusuario"));
                listagem.getModulo().setIdModulo(resultado.getInt("idmodulo"));
                listagem.getModulo().setNome(resultado.getString("nomemodulo"));
                listagem.getModulo().setDescricao(resultado.getString("descricao"));
                listagem.getPlano().setIdPlano(resultado.getInt("idplano"));
                listagem.getPlano().setNomePlano(resultado.getString("nomeplano"));
                listagem.setDataContrato(resultado.getDate("datacontrato"));
                listagem.setDataExpiracao(resultado.getDate("dataexpiracao"));
                lista.add(listagem);
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro Listar modulos ADM:" + sqlErro.getMessage());
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
    public Modulo contUsuarios(Modulo modulo) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da DAO: contar usuarios por modulo----");
        Connection con = FabricaConexao.getConexao();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT COUNT(Fk_idUsuario) FROM usermod WHERE Fk_idModulo=?");
            comando.setInt(1, modulo.getIdModulo());
            
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                modulo.setContador(resultado.getInt("count"));
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro Listar modulos ADM:" + sqlErro.getMessage());
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
    public Plano contUsuariosPlano(Plano plano) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da DAO: contar usuarios por plano----");
        Connection con = FabricaConexao.getConexao();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT COUNT(Fk_idUsuario) FROM usermod WHERE Fk_idPlano=?");
            comando.setInt(1, plano.getIdPlano());
            
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                plano.setContador(resultado.getInt("count"));
                System.out.println("dentro do if");
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro Listar planos:" + sqlErro.getMessage());
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
    public Usuario contModulosUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da DAO: contar modulos por Usuario----");
        Connection con = FabricaConexao.getConexao();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT COUNT(Fk_idModulo) FROM usermod WHERE Fk_idUsuario=?");
            comando.setInt(1, usuario.getIdUsuario());
            
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                usuario.setContador(resultado.getInt("count"));
            }
        } catch (Exception sqlErro) {
            System.out.println("Erro Listar modulos por usuario:" + sqlErro.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return usuario;
    }

//************************************************************************************************************
}
