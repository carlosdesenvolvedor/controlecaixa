package br.com.centromusical.conexao;

import java.sql.*;

public class ModuloConexao {
    //metodo responsavel por estabelecer a conexão com o banco de dados.

    public static Connection conector() {
        Connection conexao = null;
        // a linha abaixo chama o drive que foi importado para Libraries
        String driver = "com.mysql.cj.jdbc.Driver";

        //Armazenando informações referente ao banco
        String url = "jdbc:mysql://localhost:3306/dbunicesumar";
        String user = "root";
        String password = "ce134679";
        
        //Estabelecendo a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        } catch (Exception e) {
            //a linha abaixo serve de abpoio para saber o problema
            //System.out.println(e);
            //a linha abaixo serve de apoio ao status da coneção
            
            return null;
        }
        

        
    }

}
