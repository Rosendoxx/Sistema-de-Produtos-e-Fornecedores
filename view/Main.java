package
view;

import java.util.*;

import dao.CategoriaDAO;
import dao.FornecedorDAO;
import dao.ProdutoDAO;
import model.Categoria;
import model.Fornecedor;
import model.Produto;

public class Main {
	private static Map<Integer, Produto> produtos = new HashMap<Integer, Produto>();
	private static Map<Integer, Fornecedor> fornecedores = new HashMap<Integer, Fornecedor>();
	private static final ProdutoDAO PDAO = new ProdutoDAO();
	private static final FornecedorDAO FDAO = new FornecedorDAO();
	private static CategoriaDAO cdao = new CategoriaDAO();
	private static int indexProduto = 0;
	private static int indexFornecedor = 0;
	private static final Scanner IN = new Scanner(System.in);
	
	public static void main(String[] args) {
		produtos = PDAO.buscarTodos();
		fornecedores = FDAO.buscarTodos();
		int opcao = 0;
		while(opcao!=9) {
			menu();
			opcao = lerInt();
			switch (opcao) {
			case 1:
				System.out.println("==== CRIANDO PRODUTO ====");
				Produto p = null;
				while(Objects.isNull(p)){
					p = criarProduto();
					if(Objects.isNull(p)) System.out.println("Erro ao criar fornecedor\nTente novamente: ");
				}
				produtos.put(indexProduto, p);
				PDAO.inserir(p);
				indexProduto++;
				break;
			case 2:
				System.out.println("==== CRIANDO FORNECEDOR ====");
				Fornecedor f = null;
				while(Objects.isNull(f)){
					f = criarFornecedor();
					if(Objects.isNull(f)) System.out.println("Erro ao criar fornecedor\nTente novamente: ");
				}
				fornecedores.put(indexFornecedor, f);
				FDAO.inserir(f);
				indexFornecedor++;
				break;
			case 3:
				System.out.println("==== LISTANDO PRODUTOS ====");
				listarProdutos();
				break;
			case 4:
				System.out.println("==== LISTANDO FORNECEDORES ====");
				listarFornecedores();
				break;
			case 5:
				System.out.println("==== ALTERANDO PRODUTO ====");
				alterarProduto(encontrarProduto(lerInt()));
				break;
			case 6:
				System.out.println("==== ALTERANDO FORNECEDOR ====");
				alterarFornecedor(encontrarFornecedor(lerInt()));
				break;
			case 7:
				System.out.println("==== EXCLUINDO PRODUTO ====");
				excluirProduto(encontrarProduto(lerInt()));
				break;
			case 8:
				System.out.println("==== EXCLUINDO FORNECEDOR ====");
				excluirFornecedor(encontrarFornecedor(lerInt()));
				break;
			case 9:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida");
				break;
			}
		}
	}
	
	private static void menu() {
		System.out.println("1 - Adicionar Produto");
		System.out.println("2 - Adicionar Fornecedor");
		System.out.println("3 - Listar Produto");
		System.out.println("4 - Listar Fornecedor");
		System.out.println("5 - Alterar Produto");
		System.out.println("6 - Alterar Fornecedor");
		System.out.println("7 - Excluir Produto");
		System.out.println("8 - Excluir Fornecedor");
		System.out.println("9 - Sair");
		System.out.println("Escolha: ");
	}
	
	private static Produto criarProduto() {
		String nome;
		double preco;
		Fornecedor fornecedor;
		Categoria categoria;
		
		System.out.println("Digite o nome do produto: ");
		nome = IN.nextLine();
		System.out.println("Digite o preço do produto: ");
		preco = lerDouble();
		System.out.println("Digite o id do fornecedor do produto: ");
		fornecedor = encontrarFornecedor(lerInt());
		System.out.println("Digite o número da categoria desse produto: ");
		Categoria.mostrarCategorias();
		categoria = Categoria.getPorId(lerInt());
		while(categoria == null) {
			System.out.println("Categoria inválida\nTente novamente");
			categoria = Categoria.getPorId(lerInt());
		}
		return new Produto(indexProduto, nome, preco, fornecedor, categoria);
	}
	
	private static Fornecedor criarFornecedor() {
		String nome, cnpj, telefone;
		
		System.out.println("Digite o nome do fornecedor:");
		nome = IN.nextLine();
		System.out.println("Digite o CNPJ do fornecedor: ");
		cnpj = IN.nextLine();
		System.out.println("Digite o telefone do fornecedor: ");
		telefone = IN.nextLine();
		
		return new Fornecedor(indexFornecedor, nome, cnpj, telefone);
	}
	
	private static Produto encontrarProduto(int idProduto) {
		while(true) {
			if (produtos.get(idProduto) != null) {
				return produtos.get(idProduto);
			}
			
			System.out.println("Produto não encontrado! \nEsse produto já está cadastrado? (1 - sim, 2 - não");
			int opcao = lerInt();
			switch(opcao) {
			case 1:
				System.out.println("Digite o ID novamente: ");
				idProduto = lerInt();
				break;
			case 2:
				System.out.println("Cadastrando novo produto: ");
				produtos.put(indexProduto + 1, criarProduto());
				indexProduto++;
				return produtos.get(indexProduto);
			default:
				System.out.println("Opção inválida, digite novamente:");
				break;
			}
		}
	}
	
	private static Fornecedor encontrarFornecedor(int idFornecedor) {
		while(true) {
			if (fornecedores.get(idFornecedor) != null) {
				return fornecedores.get(idFornecedor);
			}
				
			System.out.println("Fornecedor não encontrado! \nEsse fornecedor já está cadastrado? (1 - sim, 2 - não");
			int opcao = lerInt();
			switch(opcao) {
				case 1:
					System.out.println("Digite o ID novamente: ");
					idFornecedor = lerInt();
					break;
				case 2:
					System.out.println("Cadastrando novo fornecedor: ");
					fornecedores.put(indexFornecedor + 1, criarFornecedor());
					indexFornecedor++;
					return fornecedores.get(indexFornecedor);
				default:
					System.out.println("Opção inválida, digite novamente:");
					break;
			}
		}
	}
	
	private static void excluirProduto(Produto produto) {
		produto.setAtivo(false);
		System.out.println("Produto excluido");
	}
	private static void excluirFornecedor(Fornecedor fornecedor) {
		fornecedor.setAtivo(false);
		produtos.entrySet()
		.stream()
		.filter(produto -> produto.getValue().getFornecedor().equals(fornecedor))
		.forEach(produto -> produto.getValue().setAtivo(false));
		System.out.println("Fornecedor excluido");
	}
	
	private static void listarProdutos() {
		while(true) {
			System.out.println("1 - Por preço");
			System.out.println("2 - Por nome");
			System.out.println("3 - Por fornecedor");
			System.out.println("4 - Por categoria");
			System.out.println("Escolha: ");
			switch (lerInt()) {
			case 1:
				produtosPorPreco();
				return;
			case 2:
				produtosPorNome();
				return;
			case 3:
				produtosPorFornecedor(encontrarFornecedor(lerInt()));
				return;
			case 4:
				produtosPorCategoria();
				return;
			default:
				System.out.println("Opção inválida \nEscolha uma das opções: ");
				break;
			}
		}
	}
	
	private static void listarFornecedores() {
		while(true) {
			System.out.println("1 - Por nome");
			System.out.println("2 - Por ID");
			System.out.println("Escolha: ");
			switch (lerInt()) {
			case 1:
				fornecedoresPorNome();
				return;
			case 2:
				fornecedoresPorId();
				return;
			default:
				System.out.println("Opção inválida\nEscolha uma das opções: ");
				break;
			}
		}
	}
	
	private static void fornecedoresPorId() {
		System.out.println("Mostrando fornecedores por ID: ");
		fornecedores.entrySet()
		.stream()
		.sorted((f1, f2) -> Integer.compare(f1.getValue().getIdFornecedor(), f2.getValue().getIdFornecedor()))
		.forEach(fornecedor -> System.out.println(fornecedor.getValue()));
	}

	private static void fornecedoresPorNome() {
		System.out.println("Mostrando fornecedores por nome: ");
		fornecedores.entrySet()
		.stream()
		.sorted((f1, f2) -> f1.getValue().getNome().compareToIgnoreCase(f2.getValue().getNome()))
		.forEach(fornecedor -> System.out.println(fornecedor));
	}

	private static void produtosPorNome() {
		System.out.println("Mostrando produtos por nome: "); 
		produtos.entrySet()
		 .stream()
		 .sorted((p1, p2) -> p1.getValue().getNome().compareToIgnoreCase(p2.getValue().getNome()))
		 .forEach(produto -> System.out.println(produto.getValue()));
	}
	
	private static void produtosPorPreco() {
		System.out.println("Mostrando produtos por preço: "); 
		produtos.entrySet()
		.stream()
		.sorted((p1, p2) -> Double.compare(p1.getValue().getPreco(), p2.getValue().getPreco()))
		.forEach(produto -> System.out.println(produto.getValue()));
	}
	
	private static void produtosPorCategoria() {
		System.out.println("Mostrando produtos por categoria: "); 
		produtos.entrySet()
		.stream()
		.sorted((p1, p2) -> p1.getValue().getCategoria().compareTo(p2.getValue().getCategoria()))
		.forEach(produto -> System.out.println(produto.getValue()));
	}
	
	private static void produtosPorFornecedor(Fornecedor fornecedor) {
		System.out.println("Mostrando produtos por fornecedor: "); 
		produtos.entrySet()
		.stream()
		.filter(produto -> produto.getValue().getFornecedor().equals(fornecedor))
		.forEach(produto -> System.out.println(produto.getValue()));
	}
	
	private static void alterarProduto(Produto produto) {
		while(true) {
			System.out.println("1 - Alterar Nome");
			System.out.println("2 - Alterar Preço");
			System.out.println("3 - Alterar Fornecedor");
			System.out.println("4 - Alterar Categoria");
			System.out.println("Escolha: ");
			switch(lerInt()) {
			case 1:
				alterarNome(produto);
				PDAO.atualizar(produto, "nome", produto.getNome(), "id" , String.valueOf(produto.getId()));
				return;
			case 2:
				alterarPreco(produto);
				PDAO.atualizar(produto, "preco", String.valueOf(produto.getPreco()), "id" , String.valueOf(produto.getId()));
				return;
			case 3:
				alterarFornecedor(produto, encontrarFornecedor(lerInt()));
				PDAO.atualizar(produto, "id_fornecedor", String.valueOf(produto.getFornecedor().getIdFornecedor()), "id" , String.valueOf(produto.getId()));
				return;
			case 4:
				alterarCategoria(produto);
				PDAO.atualizar(produto, "id_categoria", String.valueOf(produto.getCategoria().ordinal()), "id" , String.valueOf(produto.getId()));
				return;
			default:
				System.out.println("Opção inválida\nTente novamente ");
				break;
			}
		}
	}
	
	private static void alterarFornecedor(Fornecedor fornecedor) {
		while(true) {
			System.out.println("1 - Alterar Nome");
			System.out.println("2 - Alterar CNPJ");
			System.out.println("3 - Alterar Telefone");
			System.out.println("Escolha");
			switch(lerInt()) {
			case 1:
				alterarNome(fornecedor);
				FDAO.atualizar(fornecedor, "nome", fornecedor.getNome(), "id", String.valueOf(fornecedor.getIdFornecedor()));
				return;
			case 2:
				alterarCnpj(fornecedor);
				FDAO.atualizar(fornecedor, "cnpj", fornecedor.getCnpj(), "id", String.valueOf(fornecedor.getIdFornecedor()));
				return;
			case 3:
				alterarTelefone(fornecedor);
				FDAO.atualizar(fornecedor, "telefone", fornecedor.getTelefone(), "id", String.valueOf(fornecedor.getIdFornecedor()));
				return;
			default:
				System.out.println("Opção inválida\nTente novamente ");
				break;
			}
		}
	}
	
	private static void alterarNome(Object obj) {
		System.out.println("Digite o novo nome: ");
		if(obj instanceof Produto) {
			((Produto)obj).setNome(IN.nextLine());
			System.out.println(((Produto)obj));
		} else if(obj instanceof Fornecedor) {
			((Fornecedor)obj).setNome(IN.nextLine());
			System.out.println(((Fornecedor)obj));
		}
	}
	
	private static void alterarPreco(Produto produto) {
		System.out.println("Digite o novo preço: ");
		produto.setPreco(lerDouble());
		System.out.println(produto);
	}
	
	private static void alterarFornecedor(Produto produto, Fornecedor fornecedor) {
		produto.setFornecedor(fornecedor);
		System.out.println(produto);
	}
	
	private static void alterarCategoria(Produto produto) {
		System.out.println("Escolha a nova categoria: ");
		Categoria.mostrarCategorias();
		produto.setCategoria(Categoria.getPorId(lerInt()));
		while(produto.getCategoria() == null) {
			System.out.println("Categoria inválida\nTente novamente");
			produto.setCategoria(Categoria.getPorId(lerInt()));

		}
		System.out.println(produto);
	}
	
	private static void alterarCnpj(Fornecedor fornecedor) {
		System.out.println("Digite o novo CNPJ: ");
		fornecedor.setCnpj(IN.nextLine());
		System.out.println(fornecedor);
	}
	
	private static void alterarTelefone(Fornecedor fornecedor) {
		System.out.println("Digite o novo telefone: ");
		fornecedor.setTelefone(IN.nextLine());
		System.out.println(fornecedor);
	}
	
	public static int lerInt() {
		int i = 0;
		while(true) {
			try {
				i = IN.nextInt(); IN.nextLine();
				return i;
			} catch(InputMismatchException e) {
				IN.nextLine();
				System.out.println("Informe um número inteiro! \nTente novamente:");
			}
		}
	}
	
	public static double lerDouble() {
		double d = 0.0;
		while(true) {
			try {
				d = IN.nextDouble(); IN.nextLine();
				return d;
			} catch(InputMismatchException e) {
				IN.nextLine();
				System.out.println("Informe um número double! \nTente novamente:");
			}
		}
	}
}
