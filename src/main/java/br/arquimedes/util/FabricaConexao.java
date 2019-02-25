package br.arquimedes.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    public static Connection getConexao() throws ClassNotFoundException, SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Arquimedes","postgres","postgres");

            return con;
        
        }catch(SQLException erro){
            throw new RuntimeException(erro);
        }
    }
}
