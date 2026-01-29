package model;

import java.util.Comparator;

public class Categoria implements Comparator<Categoria>{

	private int id;
	private String nome;

	public Categoria(int id, String nome){
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
		System.out.println("1 - Alimentício\n2 - Eletrônico\n3 - Limpeza\n4 - Vestuário");
	}

	private int compareTo(Categoria o2) {
        return Integer.compare(this.getId(), o2.getId());
	}

	@Override
	public String toString(){
		return getId()+getNome();
	}

	@Override
	public int compare(Categoria o1, Categoria o2) {
		return o1.compareTo(o2);
	}

}
