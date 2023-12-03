package modelo;

import java.io.Serializable;
import java.util.Objects;

public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
    private String nome;
    private String cpf;
    private String especialidade;
    
    public Medico() {
    	
    }

	public Medico(int id, String nome, String cpf, String especialidade) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.especialidade = especialidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	@Override
	public String toString() {
		return "Medico [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", especialidade=" + especialidade + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, especialidade, id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(especialidade, other.especialidade) && id == other.id
				&& Objects.equals(nome, other.nome);
	}
     
}
