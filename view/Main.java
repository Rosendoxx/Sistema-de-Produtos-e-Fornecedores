package
view;

import java.util.*;

import controller.CategoriaController;
import controller.FornecedorController;
import controller.ProdutoController;
import model.Categoria;
import model.Fornecedor;
import model.Produto;

public class Main {
	private static final ProdutoController PCONTROL = new ProdutoController();
	private static final FornecedorController FCONTROL = new FornecedorController();
	private static final CategoriaController CCONTROL = new CategoriaController();
	private static final Scanner IN = new Scanner(System.in);

	public static void main(String[] args) {
		int opcao = 0;
		while(opcao!=9) {
			menu();
			opcao = lerInt();
			switch (opcao) {
			case 1:
				criar();
				break;
			case 2:
				listar();
				break;
			case 3:
				alterar();
				break;
			case 4:
				excluir();
				break;
			case 5:
				System.out.println("Saindo...");
				break;
			default:
				System.out.println("Opção inválida");
				break;
			}
		}
	}

	private static void menu() {
		System.out.println("1 - Criar");
		System.out.println("2 - Listar");
		System.out.println("3 - Alterar");
		System.out.println("4 - Excluir");
		System.out.println("5 - Sair");
		System.out.println("Escolha: ");
	}

	private static void criar() {
		int escolha = 0;
		while (escolha<1 || escolha>3){
			System.out.println("1 - Criar Produto");
			System.out.println("2 - Criar Fornecedor");
			System.out.println("3 - Criar Categoria");
			System.out.println("Escolha: ");
			escolha = lerInt();
			if (escolha<1 || escolha>3) {
				System.out.println("Opção inválida\nTente novamente: ");
			}
		}
		switch (escolha){
			case 1:
				if (isFornecedorEmpity()) return;
				else if(isCategoriaEmpity()) return;
				criarProduto();
				break;
			case 2:
				criarFornecedor();
				break;
			case 3:
				criarCategoria();
				break;
			default:
				System.out.println("Opção inválida\nNão foi possível criar");
				break;
		}
	}

	private static void listar() {
		int escolha = 0;
		while (escolha<1 || escolha>3){
			System.out.println("1 - Listar Produto");
			System.out.println("2 - Listar Fornecedor");
			System.out.println("3 - Listar Categoria");
			System.out.println("Escolha: ");
			escolha = lerInt();
			if (escolha<1 || escolha>3) {
				System.out.println("Opção inválida\nTente novamente: ");
			}
		}
		switch (escolha){
			case 1:
				if(isProdutoEmpity()) return;
				listarProdutos();
				break;
			case 2:
				if(isFornecedorEmpity()) return;
				listarFornecedores();
				break;
			case 3:
				if(isCategoriaEmpity()) return;
				listarCategorias();
				break;
			default:
				System.out.println("Opção inválida\nNão foi possível listar");
				break;
		}
	}

	private static void alterar() {
		int escolha = 0;
		while (escolha<1 || escolha>3){
			System.out.println("1 - Alterar Produto");
			System.out.println("2 - Alterar Fornecedor");
			System.out.println("3 - Alterar Categoria");
			System.out.println("Escolha: ");
			escolha = lerInt();
			if (escolha<1 || escolha>3) {
				System.out.println("Opção inválida\nTente novamente: ");
			}
			switch(escolha){
				case 1:
					if(isProdutoEmpity()) return;
					System.out.println("Digite o ID do produto: ");
					int idProdutoAlterado = lerInt();
					Produto produtoAlterado = PCONTROL.encontrar(idProdutoAlterado);
					if(Objects.isNull(produtoAlterado)){
						System.out.println("Não é possível alterar o produto");
						return;
					}
					alterarProduto(produtoAlterado);
					break;
				case 2:
					if(isFornecedorEmpity()) return;
					System.out.println("Digite o ID do fornecedor: ");
					int idFornecedorAlterado = lerInt();
					Fornecedor fornecedorAlterado = FCONTROL.encontrar(idFornecedorAlterado);
					if(Objects.isNull(fornecedorAlterado)){
						System.out.println("Não é possível alterar o fornecedor");
						return;
					}
					alterarFornecedor(fornecedorAlterado);
					break;
				case 3:
					if(isCategoriaEmpity()) return;
					System.out.println("Digite o ID da categoria: ");
					int idCategoriaAlterado = lerInt();
					Categoria categoriaAlterada = CCONTROL.encontrar(idCategoriaAlterado);
					if(Objects.isNull(categoriaAlterada)){
						System.out.println("Não é possível alterar a categoria");
						return;
					}
					alterarCategoria(categoriaAlterada);
					break;
				default:
					System.out.println("Opção inválida\nNão foi possível alterar");
					break;
			}
		}
	}

	private static void excluir() {
		int escolha = 0;
		while (escolha<1 || escolha>3){
			System.out.println("1 - Listar Produto");
			System.out.println("2 - Listar Fornecedor");
			System.out.println("3 - Listar Categoria");
			System.out.println("Escolha: ");
			escolha = lerInt();
			if (escolha<1 || escolha>3) {
				System.out.println("Opção inválida\nTente novamente: ");
			}
		}
		switch (escolha){
			case 1:
				if(isProdutoEmpity()) return;
				System.out.println("Digite o ID do produto a ser excluído: ");
				Produto produtoExcluido = PCONTROL.encontrar(lerInt());
				if(Objects.isNull(produtoExcluido)){
					System.out.println("Produto não encontrado\nNão é possível excluir");
					return;
				}
				PCONTROL.excluir(produtoExcluido.getId());
				break;
			case 2:
				if(isFornecedorEmpity()) return;
				System.out.println("Digite o ID do fornecedor a ser excluído: ");
				Fornecedor fornecedorExcluido = FCONTROL.encontrar(lerInt());
				if(Objects.isNull(fornecedorExcluido)){
					System.out.println("Fornecedor não encontrado\nNão é possível excluir");
					return;
				}
				FCONTROL.excluir(fornecedorExcluido.getIdFornecedor());
				break;
			case 3:
				if(isCategoriaEmpity()) return;
				System.out.println("Digite o ID da categoria a ser excluído: ");
				Categoria categoriaExcluida = CCONTROL.encontrar(lerInt());
				if(Objects.isNull(categoriaExcluida)){
					System.out.println("Categoria não encontrado\nNão é possível excluir");
					return;
				}
				CCONTROL.excluir(categoriaExcluida.getId());
				break;
			default:
				System.out.println("Opção inválida\nNão foi possível excluir");
				break;
		}
	}

	private static void criarProduto() {
		String nome;
		double preco;
		int idFornecedor, idCategoria;

		System.out.println("Digite o nome do produto: ");
		nome = IN.nextLine();
		System.out.println("Digite o preço do produto: ");
		preco = lerDouble();
		System.out.println("Digite o id do fornecedor do produto: ");
		idFornecedor = lerInt();
		System.out.println("Digite o número da categoria desse produto: ");
		CCONTROL.listarTodos().stream().sorted().forEach(c -> System.out.println(c));
		idCategoria = lerInt();
		PCONTROL.cadastrar(nome, preco, idFornecedor, idCategoria);
	}

	private static void criarFornecedor() {
		String nome, cnpj, telefone;

		System.out.println("Digite o nome do fornecedor:");
		nome = IN.nextLine();
		System.out.println("Digite o CNPJ do fornecedor: ");
		cnpj = IN.nextLine();
		System.out.println("Digite o telefone do fornecedor: ");
		telefone = IN.nextLine();

		FCONTROL.cadastrar(nome, cnpj, telefone);
	}

	private static void criarCategoria() {
		String nome;

		System.out.println("Digite o nome da categoria: ");
		nome = IN.nextLine();

		CCONTROL.cadastrar(nome);
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
					produtosPorFornecedor();
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
			System.out.println("1 - Por ID");
			System.out.println("2 - Por nome");
			System.out.println("Escolha: ");
			switch (lerInt()) {
				case 1:
					fornecedoresPorId();
					return;
				case 2:
					fornecedoresPorNome();
					return;
				default:
					System.out.println("Opção inválida\nEscolha uma das opções: ");
					break;
			}
		}
	}

	private static void listarCategorias() {
		System.out.println("Mostrando categorias: ");
		CCONTROL.listarTodos()
				.stream()
				.sorted()
				.forEach(c -> System.out.println(c));
	}

	private static void produtosPorPreco() {
		System.out.println("Mostrando produtos por preço: ");
		PCONTROL.listarTodos()
				.stream().
				sorted((p1, p2) -> Double.compare(p1.getPreco(), p2.getPreco()))
				.forEach(p -> System.out.println(p));
	}

	private static void produtosPorNome() {
		System.out.println("Mostrando produtos por nome: ");
		PCONTROL.listarTodos()
				.stream().
				sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
				.forEach(p -> System.out.println(p));
	}

	private static void produtosPorFornecedor() {
		System.out.println("Digite o id do Fornecedor a ser mostrada: ");
		int idFornecedor = lerInt();

		if(Objects.isNull(FCONTROL.encontrar(idFornecedor))){
			System.out.println("Fornecedor não encontrada");
			return;
		}

		System.out.println("Mostrando produtos por fornecedor: ");
		PCONTROL.listarTodos()
				.stream()
				.filter(p -> p.getFornecedor().getIdFornecedor()==idFornecedor)
				.forEach(p -> System.out.println(p));
	}

	private static void produtosPorCategoria() {
		CCONTROL.listarTodos()
				.stream()
				.sorted()
				.forEach(c -> System.out.println(c));
		System.out.println("Digite o id da categoria a ser mostrada: ");
		int idCategoria = lerInt();

		if(Objects.isNull(CCONTROL.encontrar(idCategoria))){
			System.out.println("Categoria não encontrada");
			return;
		}

		System.out.println("Mostrando produtos por categoria: ");
		PCONTROL.listarTodos()
				.stream()
				.filter(p -> p.getCategoria().getId()==idCategoria)
				.forEach(p -> System.out.println(p));
	}

	private static void fornecedoresPorId() {
		System.out.println("Mostrando fornecedores por ID: ");
		FCONTROL.listarTodos()
				.stream()
				.sorted((f1, f2) -> Integer.compare(f1.getIdFornecedor(), f2.getIdFornecedor()))
				.forEach(f -> System.out.println(f));
	}

	private static void fornecedoresPorNome() {
		System.out.println("Mostrando fornecedores por nome: ");
		FCONTROL.listarTodos()
				.stream()
				.sorted((f1, f2) -> f1.getNome().compareTo(f2.getNome()))
				.forEach(f -> System.out.println(f));
	}

	private static void alterarProduto(Produto produto) {
		System.out.println("1 - Alterar Nome");
		System.out.println("2 - Alterar Preço");
		System.out.println("3 - Alterar Fornecedor");
		System.out.println("4 - Alterar Categoria");
		System.out.println("Escolha: ");
		switch(lerInt()) {
			case 1:
				System.out.println("Digite o novo nome do produto: ");
				alterarNome(produto);
				break;
			case 2:
				System.out.println("Digite o novo preço do produto: ");
				PCONTROL.atualizar(produto.getId(), 2, String.valueOf(lerDouble()));
				break;
			case 3:
				System.out.println("Digite o novo ID do fornecedor do produto: ");
				Fornecedor f = FCONTROL.encontrar(lerInt());
				if(Objects.isNull(f)) {
					System.out.println("Fornecedor não encontrado\nNão é possível alterar");
					return;
				}
				PCONTROL.atualizar(produto.getId(), 3, String.valueOf(f.getIdFornecedor()));
				break;
			case 4:
				System.out.println("Digite o novo ID da categoria do produto: ");
				Categoria c = CCONTROL.encontrar(lerInt());
				if(Objects.isNull(c)) {
					System.out.println("Categoria não encontrada\nNão é possível alterar");
					return;
				}
				PCONTROL.atualizar(produto.getId(), 4, String.valueOf(c.getId()));
				break;
			default:
				System.out.println("Opção inválida\nTente novamente ");
				break;
		}
	}


	private static void alterarFornecedor(Fornecedor fornecedor) {
		System.out.println("1 - Alterar Nome");
		System.out.println("2 - Alterar CNPJ");
		System.out.println("3 - Alterar Telefone");
		System.out.println("Escolha");
		switch(lerInt()) {
			case 1:
				System.out.println("Digite o novo nome do fornecedor: ");
				alterarNome(fornecedor);
				break;
			case 2:
				System.out.println("Digite o novo CNPJ do fornecedor: ");
				FCONTROL.atualizar(fornecedor.getIdFornecedor(), 2, IN.nextLine());
				break;
			case 3:
				System.out.println("Digite o novo telefone do fornecedor: ");
				FCONTROL.atualizar(fornecedor.getIdFornecedor(), 3, IN.nextLine());
				break;
			default:
				System.out.println("Opção inválida\nTente novamente ");
				break;
		}
	}


	private static void alterarCategoria(Categoria categoria){
		System.out.println("Digite o novo nome da categoria: ");
		alterarNome(categoria); //Única alteração possível para essa classe
	}

	private static void alterarNome(Object obj) {
		if(obj instanceof Produto) {
			PCONTROL.atualizar(((Produto) obj).getId(), 1, IN.nextLine());
		} else if(obj instanceof Fornecedor) {
			FCONTROL.atualizar(((Fornecedor) obj).getIdFornecedor(), 1, IN.nextLine());
		} else if(obj instanceof Categoria){
			CCONTROL.atualizar(((Categoria) obj).getId(), IN.nextLine());
		}
	}

	public static boolean isProdutoEmpity(){
		if(PCONTROL.listarTodos().isEmpty()){
			System.out.println("Nenhum produto cadastrado\nCadastre ao menos um antes dessa operação");
			return true;
		}
		return false;
	}

	public static boolean isFornecedorEmpity(){
		if(FCONTROL.listarTodos().isEmpty()){
			System.out.println("Nenhum fornecedor cadastrado\nCadastre ao menos um antes dessa operação");
			return true;
		}
		return false;
	}

	public static boolean isCategoriaEmpity(){
		if(CCONTROL.listarTodos().isEmpty()){
			System.out.println("Nenhuma categoria cadastrada\nCadastre ao menos uma antes dessa operação");
			return true;
		}
		return false;
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
