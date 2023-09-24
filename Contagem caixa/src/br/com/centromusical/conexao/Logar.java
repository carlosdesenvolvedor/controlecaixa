package br.com.centromusical.conexao;

import java.sql.*;
public class Logar {
    private Connection conexao = null;
    private PreparedStatement pst = null; 
    private ResultSet rs = null;

    public Connection getConexao() {
        return conexao;
    }

    public void setConexao(Connection conexao) {
        this.conexao = conexao;
    }

    public PreparedStatement getPst() {
        return pst;
    }

    public void setPst(PreparedStatement pst) {
        this.pst = pst;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }
    
    
    public void logar(){
        String sql = "select * tbusuarios where login = ? and senha = ?"; //crio o select
    }
    
    
}
