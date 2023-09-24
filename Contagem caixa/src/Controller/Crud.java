package Controller;

import br.com.centromusical.conexao.ModuloConexao;
import contagem.caixa.TelaUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Classe responsável por operações CRUD relacionadas a usuários.
 */
public class Crud {

    private Connection conexao;
    ResultSet rs = null;

    String nome;
    String telefone;
    String login;
    String senha;
    String perfil;

    /**
     * Construtor da classe.
     *
     * @param conexao A conexão com o banco de dados.
     */
    public Crud(Connection conexao) {
        this.conexao = conexao;
    }

    /**
     * Adiciona um novo usuário ao banco de dados.
     *
     * @param id O ID do usuário.
     * @param nome O nome do usuário.
     * @param telefone O telefone do usuário.
     * @param login O login do usuário.
     * @param senha A senha do usuário.
     * @param perfil O perfil do usuário.
     * @throws SQLException Se ocorrer um erro ao executar a operação no banco
     * de dados.
     */
    public void adicionarUsuario(String id, String nome, String telefone, String login, String senha, String perfil) throws SQLException {
        String sql = "INSERT INTO tbusuarios(iduser, usuario, fone, login, senha, perfil) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, id);
            pst.setString(2, nome);
            pst.setString(3, telefone);
            pst.setString(4, login);
            pst.setString(5, senha);
            pst.setString(6, perfil);

            int adicionado = pst.executeUpdate();
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "Usuário adicinado com sucesso!!!");
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possivel adicionar usuário");
            }
        }
    }

    public void consultarId(String procura) throws SQLException {
        String sql = "select * from tbusuarios where iduser = ?";
        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setString(1, procura);
            rs = pst.executeQuery();
            if (rs.next()) {
                setNome(rs.getString(2));
                setTelefone(rs.getString(3));
                setLogin(rs.getString(4));
                setSenha(rs.getString(5));
                setPerfil(rs.getString(6));

            } else {
                JOptionPane.showMessageDialog(null, "não foi possivel localizar usuário");
                setNome(null);
                setTelefone(null);
                setLogin(null);
                setSenha(null);
                setPerfil(null);
            }

        }

    }

    //Gets Seter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

}
