package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javaDB.FabricaDeConexoes;

public class MedicoDAO {
	
    private Connection con;
	
    public MedicoDAO() throws SQLException {
        this.con =  FabricaDeConexoes.getConnection();
    }

    public boolean getMedicobyId(int id) throws SQLException {
        String sql = "SELECT * FROM medico WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rset = stmt.executeQuery()) {
                return rset.next();
            }
        }
    }
}
