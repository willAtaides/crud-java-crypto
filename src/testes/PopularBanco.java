package testes;

import java.util.Scanner;

import dao.CreatininaDAO;
import dao.SenhaDAO;
import dao.UsuarioDAO;

public class PopularBanco{
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            String login = "Clebinho";
            System.out.println("login: " + login);
            String senhaUsuario = "658548ssas9";
            System.out.println("senha: " + senhaUsuario);
            UsuarioDAO dao = new UsuarioDAO();
            dao.adiciona(login, senhaUsuario);
            System.out.println("Gravação do usuario e senha feita no banco de dados!");

            String senhaCriptografia = "fghjy567klo";
            System.out.println("Senha para criptografar a tabela Exame: " + senhaCriptografia);
            SenhaDAO daoSenha = new SenhaDAO();
            daoSenha.adiciona(senhaCriptografia, senhaUsuario);
            System.out.println("Gravação da senha para criptografar feita no banco de dados!");

            CreatininaDAO daoExame = new CreatininaDAO();
            daoExame.adiciona("28 mg/dL", 1, 1, senhaCriptografia);
            System.out.println("Gravação do exame de creatinina feita no banco de dados!");
            daoExame.adiciona("26 mg/dL", 2, 1, senhaCriptografia);
            System.out.println("Gravação do exame de creatinina feita no banco de dados!");
            daoExame.adiciona("30 mg/dL", 3, 2, senhaCriptografia);
            System.out.println("Gravação do exame de creatinina feita no banco de dados!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
