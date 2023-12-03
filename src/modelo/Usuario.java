package modelo;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
    private String login;
    private String senha;
    
    public Usuario() {
    	
    }

	public Usuario(int id, String login, String senha) {
		this.id = id;
		this.login = login;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, login, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return id == other.id && Objects.equals(login, other.login) && Objects.equals(senha, other.senha);
	}
    
}
