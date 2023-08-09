package sp.ManipulacaoFIles.model;

import javax.persistence.*;

@Entity
@Table(name = "Pessoa")
public class Pessoa {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_pessoa")
	private int cod_pessoa;

	@Column(name = "nome")
	private String nome;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "endereco")
	private String endereco;

	public Pessoa(String nome, String cpf, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
	}
	
	public int getCod_pessoa() {
		return this.cod_pessoa;
	}

	public String getNome() {
		return this.nome;

	}

	public String getCpf() {
		return this.cpf;
	}

	public String getEnderco() {
		return this.endereco;
	}
}
