package modelo;

public class Senha {
	private int id;
	private String chaveSecreta;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChaveSecreta() {
		return chaveSecreta;
	}
	public void setChaveSecreta(String chaveSecreta) {
		this.chaveSecreta = chaveSecreta;
	}
	@Override
	public String toString() {
		return "Senha [id=" + id + ", chaveSecreta=" + chaveSecreta + "]";
	}

	
}
