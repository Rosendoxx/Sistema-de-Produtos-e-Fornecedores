package model;

import java.util.Comparator;

public enum Categoria implements Comparator<Categoria>{
	SEM_CATEGORIA(0, "Sem Categoria"), ALIMENTICIO(1, "Alimentício"), ELETRONICO(2, "Eletrônico"), LIMPEZA(3, "Limpeza"), VESTUARIO(4, "Vestuário");

	private int id;
	private String nome;

	Categoria(int id, String nome){
		this.id = id;
		this.nome = nome;
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

	public static void mostrarCategorias() {
		System.out.println("1 - Alimentício |  2 - Eletrônico | 3 - Limpeza | 4 - Vestuário");
	}

	public static Categoria getPorId(int idCategoria) {
		for(Categoria c: values()) {
			if(c.getId() == idCategoria) {
				return c;
			}
		}
		return null; //retorna null se não encontrar categoria válida
	}

	@Override
	public String toString(){
		return getNome();
	}

	@Override
	public int compare(Categoria o1, Categoria o2) {
		return o1.compareTo(o2);
	}

}
