package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import javaDB.FabricaDeConexoes;
import modelo.Creatinina;
import modelo.Medico;
import modelo.Paciente;
import seguranca.Criptografia;

public class CreatininaDAO {

    private Connection con;

    public CreatininaDAO() throws SQLException {
        this.con = FabricaDeConexoes.getConnection();
    }

    public void adiciona(String resultado, int id_paciente, int id_medico, String chave) throws Exception {
        if (procuraMedicoEPaciente(id_paciente, id_medico)) {
            SecretKey chave2 = Criptografia.criarChaveSecreta(chave);

            String sql = "INSERT INTO creatinina (resultado, id_paciente, id_medico) VALUES (?,?,?)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, Criptografia.criptografa(resultado, chave2));
                stmt.setInt(2, id_paciente);
                stmt.setInt(3, id_medico);
                stmt.execute();
            }
        }
    }

    public List<Creatinina> getLista() throws SQLException {
        String sql = "SELECT a.id, a.resultado, m.id AS medico_id, m.nome AS medico_nome, m.cpf AS medico_cpf, m.especialidade,"
                + "p.id AS paciente_id, p.nome AS paciente_nome, p.cpf AS paciente_cpf FROM creatinina a "
                + "JOIN medico m ON a.id_medico = m.id "
                + "JOIN paciente p ON a.id_paciente = p.id";
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rset = stmt.executeQuery()) {

            List<Creatinina> exames = new ArrayList<>();

            while (rset.next()) {
                Medico medico = new Medico(rset.getInt("medico_id"), rset.getString("medico_nome"),
                        rset.getString("medico_cpf"), rset.getString("especialidade"));
                Paciente paciente = new Paciente(rset.getInt("paciente_id"), rset.getString("paciente_nome"),
                        rset.getString("paciente_cpf"));
                Creatinina exame = new Creatinina(rset.getInt("id"), rset.getString("resultado"), paciente, medico);
                exames.add(exame);
            }

            return exames;
        }
    }

    public Creatinina getCreatininabyId(int id) throws SQLException {
        String sql = "SELECT a.id, a.resultado, m.id AS medico_id, m.nome AS medico_nome, m.cpf AS medico_cpf, m.especialidade,"
                + "p.id AS paciente_id, p.nome AS paciente_nome, p.cpf AS paciente_cpf FROM creatinina a "
                + "JOIN medico m ON a.id_medico = m.id "
                + "JOIN paciente p ON a.id_paciente = p.id "
                + "WHERE a.id = ?";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rset = stmt.executeQuery()) {
                if (rset.next()) {
                    Medico medico = new Medico(rset.getInt("medico_id"), rset.getString("medico_nome"),
                            rset.getString("medico_cpf"), rset.getString("especialidade"));
                    Paciente paciente = new Paciente(rset.getInt("paciente_id"), rset.getString("paciente_nome"),
                            rset.getString("paciente_cpf"));
                    return new Creatinina(rset.getInt("id"), rset.getString("resultado"), paciente, medico);
                }
            }
        }
        return null;
    }

    public void atualiza(int id, String resultado, int id_paciente, int id_medico, String chave) throws Exception {
        Creatinina ac = getCreatininabyId(id);
        if (ac != null && procuraMedicoEPaciente(id_paciente, id_medico)) {
            SecretKey chave2 = Criptografia.criarChaveSecreta(chave);

            String sql = "UPDATE creatinina SET resultado = ?, id_paciente = ?, id_medico = ? WHERE id = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, Criptografia.criptografa(resultado, chave2));
                stmt.setInt(2, id_paciente);
                stmt.setInt(3, id_medico);
                stmt.setInt(4, id);
                stmt.execute();
                stmt.close();
            }
        }
    }

    public void deleta(int id) throws Exception {
        Creatinina ac = getCreatininabyId(id);
        if (ac != null) {
            String sql = "DELETE FROM creatinina WHERE id = ?";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.execute();
                stmt.close();
            }
        }
    }

    private boolean procuraMedicoEPaciente(int id_paciente, int id_medico) throws SQLException {
        MedicoDAO daoMed = new MedicoDAO();
        PacienteDAO daoPac = new PacienteDAO();
        return daoPac.getPacientebyId(id_paciente) && daoMed.getMedicobyId(id_medico);
    }
}
