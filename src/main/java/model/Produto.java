package model;

import java.util.Objects;

public class Produto {
	private int id;
	private String nome;
	private double preco;
	private Fornecedor fornecedor;
	private Categoria categoria;
	private boolean ativo;

	public Produto(int id, String nome, double preco, Fornecedor fornecedor, Categoria categoria) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.fornecedor = fornecedor;
		this.categoria = categoria;
		this.ativo = true;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ativo, categoria, fornecedor, id, nome, preco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return ativo == other.ativo && categoria == other.categoria && Objects.equals(fornecedor, other.fornecedor)
				&& id == other.id && Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(preco) == Double.doubleToLongBits(other.preco);
	}

	@Override
	public String toString() {
		return String.format("Produto (%d): %s | Preço: %.2f | Categoria: %s | Fornecedor: %s - %s | Ativo: %b%n", getId(), getNome(), getPreco(), getCategoria().toString(), getFornecedor().getNome(), getFornecedor().getTelefone(), isAtivo());
	}

}
