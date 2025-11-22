package modelo;

import java.util.Comparator;

public enum Categoria implements Comparator<Categoria>{
	SEM_CATEGORIA, ALIMENTICIO, ELETRONICO, LIMPEZA, VESTUARIO;
	
	public static void mostrarCategorias() {
		System.out.println("1 - Alimentício |  2 - Eletrônico | 3 - Limpeza | 4 - Vestuário");
	}
	
	public static Categoria getPorId(int idCategoria) {
		for(Categoria c: values()) {
			if(c.ordinal() == idCategoria) {
				return c;
			}
		}
		return null; //retorna null se não encontrar categoria válida
	}
	@Override
	public int compare(Categoria o1, Categoria o2) {
		return o1.compareTo(o2);
	}
	
}
