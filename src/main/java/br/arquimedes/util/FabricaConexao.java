package br.arquimedes.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    public static Connection getConexao() throws ClassNotFoundException, SQLException{
        try{
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Arquimedes","postgres","postgres");

            return con;
        
        }catch(SQLException erro){
            throw new RuntimeException(erro);
        }
    }
}
