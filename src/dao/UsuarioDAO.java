package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javaDB.FabricaDeConexoes;
import modelo.Usuario;
import seguranca.Hash;

public class UsuarioDAO {

    private Connection con;

    public UsuarioDAO() throws SQLException {
        this.con = FabricaDeConexoes.getConnection();
    }

    public void adiciona(String login, String senha) throws SQLException {
        String sql = "INSERT INTO usuario (login, senha) VALUES (?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, login);
            stmt.setString(2, Hash.gerarHash(senha));
            stmt.execute();
        }
    }

    public Usuario getUsuarioById(int id) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rset = stmt.executeQuery()) {
                if (rset.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setLogin(rset.getString("login"));
                    usuario.setSenha(rset.getString("senha"));
                    return usuario;
                }
            }
        }
        return null;
    }
}
