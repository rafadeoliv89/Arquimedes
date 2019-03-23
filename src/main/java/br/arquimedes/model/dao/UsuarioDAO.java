package br.arquimedes.model.dao;

import br.arquimedes.model.PerfilAcesso;
import br.arquimedes.model.Usuario;
import br.arquimedes.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario cadastrar(Usuario usuario) throws ClassNotFoundException, SQLException {

        System.out.println("-----Dentro da Cadastrar Usuario DAO-------");

        Connection con = FabricaConexao.getConexao();

        try {
            PreparedStatement comando = con.prepareStatement("INSERT INTO usuario (fk_idPessoa,perfilacesso,nomeusuario,senha,status,logado) VALUES(?,?,?,?,'t','t')", Statement.RETURN_GENERATED_KEYS);
            comando.setInt(1, usuario.getIdPessoa());
            comando.setString(2, usuario.getPerfil().toString());
            comando.setString(3, usuario.getNomeUsuario());
            comando.setString(4, usuario.getSenha());

            comando.execute();
            
            ResultSet resultado = comando.getGeneratedKeys();
            
            if(resultado.next()){
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
            }

        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception sqlErro2) {
                    System.out.println("Erro na Cadastrar Usuario DAO: "+sqlErro2.getMessage());
                    throw new RuntimeException(sqlErro2);
                }
            }
        }
    return usuario;
    }
//************************************************************************************************************
    public void alterar(Usuario usuario) throws ClassNotFoundException, SQLException {
        System.out.println("------------Dentro do Usuario DAO/Alterar-------------");

        try {
            Connection con = FabricaConexao.getConexao();

            PreparedStatement comando = con.prepareStatement("UPDATE usuario SET fk_idPessoa =?, perfilacesso=? WHERE idUsuario=?");
            comando.setInt(1, usuario.getIdPessoa());
            comando.setString(2, usuario.getPerfil().toString());

            comando.setInt(3, usuario.getIdUsuario());

            comando.execute();
            con.close();

        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        }
    }
//************************************************************************************************************
    public Usuario autenticaUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da Autenticar Usuario DAO----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM usuario WHERE nomeusuario=? and senha=?");
            comando.setString(1, usuario.getNomeUsuario());
            comando.setString(2, usuario.getSenha());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                usuario.setIdUsuario(resultado.getInt("idUsuario"));
                usuario.setIdPessoa(resultado.getInt("fk_idpessoa"));
                usuario.setNomeUsuario(resultado.getString("nomeusuario"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setPerfil(PerfilAcesso.valueOf(resultado.getString("perfilacesso")));
                usuario.setStatus(resultado.getBoolean("status"));
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
    public void statusLogado(Usuario usuario) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da statusLogado----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("UPDATE pessoa SET logado=1 WHERE nomeUsuario=?");
            comando.setString(1, usuario.getNomeUsuario());

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
    public void statusDeslogado(Usuario usuario) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da StatusDeslogado----");
        System.out.println("---Login: " + usuario.getNomeUsuario());

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("UPDATE pessoa SET logado=0 WHERE nomeUsuario=?");
            comando.setString(1, usuario.getNomeUsuario());

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
    public boolean validaCadastro(Usuario usuario) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da UsuarioDAO validar cadastro----");

        Usuario validaUsuario;
        boolean resposta = true;

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM usuario WHERE nomeusuario=?");
            comando.setString(1, usuario.getNomeUsuario());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                validaUsuario = new Usuario();
                validaUsuario.setNomeUsuario(resultado.getString("nomeUsuario"));
                resposta = false;
            } else {
                resposta = true;
            }

        } catch (Exception sqlErro) {
            System.out.println("Erro na UsuarioDAO validar cadastro:" + sqlErro.getMessage());
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
        System.out.println("---Dentro do Buscar UsuarioDAO----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM usuario WHERE nomeusuario=?");
            comando.setString(1, usuario.getNomeUsuario());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                usuario.setIdUsuario(resultado.getInt("idusuario"));
                usuario.setIdPessoa(resultado.getInt("fk_idpessoa"));
                usuario.setNomeUsuario(resultado.getString("nomeusuario"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setPerfil(PerfilAcesso.valueOf(resultado.getString("perfilacesso")));
                usuario.setStatusUsuario(resultado.getBoolean("status"));
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
    public Usuario buscarX(Usuario usuario) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro do Buscar UsuarioDAO----");

        Usuario usuarioBusca = null;

        Connection con = FabricaConexao.getConexao();
        System.out.println("Nome usuário: " + usuario.getNomeUsuario());
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM usuario WHERE nomeusuario=?");
            comando.setString(1, usuario.getNomeUsuario());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                usuarioBusca = new Usuario();

                usuarioBusca.setIdUsuario(resultado.getInt("idusuario"));
                usuarioBusca.setIdPessoa(resultado.getInt("fk_idpessoa"));
                usuarioBusca.setNomeUsuario(resultado.getString("nomeusuario"));
                usuarioBusca.setSenha(resultado.getString("senha"));
                usuarioBusca.setPerfil(PerfilAcesso.valueOf(resultado.getString("perfilacesso")));
                usuarioBusca.setStatusUsuario(resultado.getBoolean("status"));
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
        return usuarioBusca;
    }
//************************************************************************************************************
    public Usuario buscarId(Usuario usuario) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro do Buscar UsuarioDAO----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM usuario WHERE idUsuario=?");
            comando.setInt(1, usuario.getIdUsuario());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {

                usuario.setIdUsuario(resultado.getInt("idusuario"));
                usuario.setIdPessoa(resultado.getInt("fk_idpessoa"));
                usuario.setNomeUsuario(resultado.getString("nomeusuario"));
                usuario.setSenha(resultado.getString("senha"));
                usuario.setPerfil(PerfilAcesso.valueOf(resultado.getString("perfilacesso")));
                usuario.setStatusUsuario(resultado.getBoolean("status"));
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
    public Usuario buscarPorIdPessoa(Usuario usuario) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro do Buscar UsuarioDAO----");

        Usuario usuarioBusca = null;;

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM usuario WHERE fk_idPessoa=?");
            comando.setInt(1, usuario.getIdPessoa());

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                usuarioBusca = new Usuario();

                usuarioBusca.setIdUsuario(resultado.getInt("idusuario"));
                usuarioBusca.setIdPessoa(resultado.getInt("fk_idpessoa"));
                usuarioBusca.setNomeUsuario(resultado.getString("nomeusuario"));
                usuarioBusca.setSenha(resultado.getString("senha"));
                usuarioBusca.setPerfil(PerfilAcesso.valueOf(resultado.getString("perfilacesso")));
                usuarioBusca.setStatusUsuario(resultado.getBoolean("status"));
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
        return usuarioBusca;
    }
//************************************************************************************************************
    public void inativar(Usuario usuario) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da inativar Usuario----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("UPDATE usuario SET status='f' WHERE idUsuario=?");
            comando.setInt(1, usuario.getIdUsuario());

            comando.executeQuery();
            
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
    public void ativar(Usuario usuario) throws ClassNotFoundException, SQLException {
        System.out.println("---Dentro da AtivarUsuario----");

        Connection con = FabricaConexao.getConexao();
        try {
            PreparedStatement comando = con.prepareStatement("UPDATE usuario SET status='t' WHERE idUsuario=?");
            comando.setInt(1, usuario.getIdUsuario());

            comando.executeQuery();

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
    public List<Usuario> listar() throws ClassNotFoundException, SQLException {

        System.out.println("---Dentro da DAO: Listar Usuario----");

        Connection con = FabricaConexao.getConexao();

        List<Usuario> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT U.idusuario,U.nomeusuario,U.perfilacesso,U.status,P.idpessoa, P.nome,P.datanascimento,P.sexo,P.email,P.cpf FROM ((usuario AS U INNER JOIN pessoa AS P ON P.idpessoa = U.fk_idpessoa))");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Usuario listagem = new Usuario();

                listagem.setIdPessoa(resultado.getInt("idpessoa"));
                listagem.setNomePessoa(resultado.getString("nome"));
                listagem.setDataNascimento(resultado.getDate("datanascimento"));
                listagem.setCPF(resultado.getString("cpf"));
                listagem.setEmail(resultado.getString("email"));
                listagem.setIdUsuario(resultado.getInt("idusuario"));
                listagem.setNomeUsuario(resultado.getString("nomeusuario"));
                listagem.setPerfil(PerfilAcesso.valueOf(resultado.getString("perfilacesso")));
                listagem.setStatusUsuario(resultado.getBoolean("status"));

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
    public List<Usuario> listarX() throws ClassNotFoundException, SQLException {

        System.out.println("---Dentro da DAO: Listar Usuario----");

        Connection con = FabricaConexao.getConexao();

        List<Usuario> lista = new ArrayList();

        try {
            PreparedStatement comando = con.prepareStatement("SELECT * FROM usuario");
            ResultSet resultado = comando.executeQuery();

            while (resultado.next()) {
                Usuario listagem = new Usuario();

                listagem.setIdUsuario(resultado.getInt("idusuario"));
                listagem.setIdPessoa(resultado.getInt("fk_idpessoa"));
                listagem.setNomeUsuario(resultado.getString("nomeusuario"));
                listagem.setSenha(resultado.getString("senha"));
                listagem.setPerfil(PerfilAcesso.valueOf(resultado.getString("perfilacesso")));
                listagem.setStatusUsuario(resultado.getBoolean("status"));

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
