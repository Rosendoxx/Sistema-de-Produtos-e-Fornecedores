package modelo;

import java.util.Objects;

public class Fornecedor {
	private String nome, cnpj, telefone;
	private int idFornecedor;
	private boolean ativo;

	public Fornecedor(int id, String nome, String cnpj, String telefone) {
		this.idFornecedor = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.ativo = true;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ativo, cnpj, idFornecedor, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fornecedor other = (Fornecedor) obj;
		return ativo == other.ativo && Objects.equals(cnpj, other.cnpj) && idFornecedor == other.idFornecedor
				&& Objects.equals(nome, other.nome) && Objects.equals(telefone, other.telefone);
	}

	@Override
	public String toString() {
		return String.format("Fornecedor (%d) : %s | CNPJ: %s | Telefone: %s | Ativo: %b%n", getIdFornecedor(), getNome(), getCnpj(), getTelefone(), isAtivo());
	}

}
