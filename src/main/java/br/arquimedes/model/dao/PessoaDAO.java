package br.arquimedes.model.dao;

import br.arquimedes.model.Usuario;
import br.arquimedes.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    public Usuario cadastrar(Usuario usuario) throws ClassNotFoundException, SQLException {
       System.out.println("---Dentro da PessoaDAO cadastrar---");
        
        Connection con = FabricaConexao.getConexao();

        try {
            PreparedStatement comando = con.prepareStatement("INSERT INTO pessoa (nome,datanascimento,sexo,email,cpf) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            comando.setString(1, usuario.getNomePessoa());
            comando.setDate(2, new java.sql.Date(usuario.getDataNascimento().getTime()));
            comando.setString(3, usuario.getSexo());
            comando.setString(4, usuario.getEmail());
            comando.setString(5, usuario.getCPF());

            comando.execute();
            
            ResultSet resultado = comando.getGeneratedKeys();
            
            if(resultado.next()){
                usuario.setIdPessoa(resultado.getInt("idPessoa"));
            }

        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    System.out.println("erro na PessoaDAO cadastrar: "+sqlErro2.getMessage());
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
        return usuario;
    }
//************************************************************************************************************
    public void alterar(Usuario usuario) throws ClassNotFoundException, SQLException {
        System.out.println("Dentro da DAO Pessoa Alterar");
        try {
            Connection con = FabricaConexao.getConexao();

            PreparedStatement comando = con.prepareStatement("UPDATE pessoa SET nome =?, datanascimento=?, email=?, cpf=? WHERE idPessoa=?");
            comando.setString(1, usuario.getNomePessoa());
            comando.setDate(2, new java.sql.Date(usuario.getDataNascimento().getTime()));
            comando.setString(3, usuario.getEmail());
            comando.setString(4, usuario.getCPF());
            comando.setInt(5, usuario.getIdPessoa());

            comando.execute();
            con.close();

        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        }
    }
//************************************************************************************************************
    public boolean validaCPF(Usuario usuario) throws ClassNotFoundException, SQLException {
        Usuario validaCPF;
        boolean resposta = true;

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM pessoa WHERE CPF=?");
            comando.setString(1, usuario.getCPF());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                validaCPF = new Usuario();
                validaCPF.setCPF(resultado.getString("cpf"));
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
    public boolean validaEmail(Usuario usuario) throws ClassNotFoundException, SQLException {
        Usuario validaEmail;
        boolean resposta = true;

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM pessoa WHERE email=?");
            comando.setString(1, usuario.getEmail());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                validaEmail = new Usuario();
                validaEmail.setEmail(resultado.getString("email"));
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
    public Usuario buscar(Usuario usuario) throws ClassNotFoundException, SQLException {
        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM pessoa WHERE idPessoa=?");
            comando.setInt(1, usuario.getIdPessoa());
           
            ResultSet resultado = comando.executeQuery();
           
            if (resultado.next()) {
                usuario.setIdPessoa(resultado.getInt("idpessoa"));
                usuario.setNomePessoa(resultado.getString("nome"));
                usuario.setDataNascimento(resultado.getDate("datanascimento"));
                usuario.setSexo(resultado.getString("sexo"));
                usuario.setCPF(resultado.getString("cpf"));
                usuario.setEmail(resultado.getString("email"));
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
        return usuario;
    }
//************************************************************************************************************
    public Usuario buscarCPF(Usuario usuario) throws ClassNotFoundException, SQLException {
        Usuario busca = null;
        Connection con = FabricaConexao.getConexao();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM pessoa WHERE cpf=?");
            comando.setString(1, usuario.getCPF());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                busca = new Usuario();

                busca.setIdPessoa(resultado.getInt("idpessoa"));
                busca.setNomePessoa(resultado.getString("nome"));
                busca.setDataNascimento(resultado.getDate("datanascimento"));
                busca.setCPF(resultado.getString("cpf"));
                busca.setEmail(resultado.getString("email"));
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
        return busca;
    }
//************************************************************************************************************   
    public List<Usuario> listar(Usuario usuario) throws ClassNotFoundException, SQLException {
        
        System.out.println("---Dentro da DAO: lista pessoa----");
        
        Connection con = FabricaConexao.getConexao();
                
        List<Usuario> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM pessoa WHERE idPessoa=?");
            comando.setInt(1, usuario.getIdPessoa());
            
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Usuario listagem = new Usuario();

                listagem.setIdPessoa(resultado.getInt("idpessoa"));
                listagem.setNomePessoa(resultado.getString("nome"));
                listagem.setDataNascimento(resultado.getDate("datanascimento"));
                listagem.setCPF(resultado.getString("cpf"));
                listagem.setEmail(resultado.getString("email"));

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
}
