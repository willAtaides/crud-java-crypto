package testes;

import java.util.List;
import java.util.Scanner;

import dao.CreatininaDAO;
import dao.SenhaDAO;
import dao.UsuarioDAO;
import modelo.Creatinina;
import modelo.Senha;
import modelo.Usuario;
import seguranca.Criptografia;

public class SelectEDecriptografia {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {

			int id = 1;
			String senhaUsuario = "658548ssas9";

			UsuarioDAO daoUsuario = new UsuarioDAO();
			Usuario usuario = daoUsuario.getUsuarioById(id);
			System.out.println("Hash da senha do Usuario (banco de dados): " + usuario.getSenha());

			CreatininaDAO daoExame = new CreatininaDAO();
			SenhaDAO daoSenha = new SenhaDAO();

			Senha senha = daoSenha.getSenhabyId(id);
			System.out.println("Chave criptografada da tabela senha (banco de dados): " + senha.getChaveSecreta());
			String senhaCriptografia = Criptografia.decriptografa(senha.getChaveSecreta(),
					Criptografia.criarChaveSecreta(senhaUsuario));
			System.out.println("Chave decriptografada: " + senhaCriptografia);

			List<Creatinina> exames = daoExame.getLista();

			for (Creatinina exame : exames) {
				System.out.println("-----------------------------------------------------------");
				System.out.println("Resultado do exame de creatinina do paciente: " + exame.getPaciente()
						+ " solicitado " + "pelo médico: " + exame.getMedico());
				System.out.println("Resultado do exame criptografado (banco de dados): " + exame.getResultado());
				String nomeDoExame = Criptografia.decriptografa(exame.getResultado(),
						Criptografia.criarChaveSecreta(senhaCriptografia));
				System.out.println("Resultado do exame decriptografado: " + nomeDoExame);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}