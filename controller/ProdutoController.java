package controller;

import dao.CategoriaDAO;
import dao.FornecedorDAO;
import dao.ProdutoDAO;
import model.Categoria;
import model.Fornecedor;
import model.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProdutoController {
    private final ProdutoDAO PDAO;
    private final FornecedorDAO FDAO;
    private final CategoriaDAO CDAO;

    public ProdutoController(){
        this.PDAO = new ProdutoDAO();
        this.FDAO = new FornecedorDAO();
        this.CDAO = new CategoriaDAO();
    }

    public void cadastrar(String nome, double preco, int idFornecedor, int idCategoria){
        if(nome == null || nome.isEmpty()){
            System.out.println("Erro: o nome do produto é obrigatório\nNão é possivel cadastrar");
            return;
        }
        if(preco < 0){
            System.out.println("Erro: o preço não pode ser negativo\nNão é possível cadastrar");
            return;
        }
        Fornecedor fornecedor = FDAO.buscarPorId(idFornecedor);
        if (Objects.isNull(fornecedor)){
            System.out.println("Erro: fornecedor não encontrado\nNão é possível cadastrar");
            return;
        }
        Categoria categoria = CDAO.buscarPorId(idCategoria);
        if (Objects.isNull(categoria)) {
            System.out.println("Erro: categoria não encontrada\nNão é possível cadastrar");
            return;
        }

        Produto novoProduto = new Produto(0, nome, preco, fornecedor, categoria);

        try{
            PDAO.inserir(novoProduto);
            System.out.println("Produto cadastrado com sucesso!");
        } catch(RuntimeException e){
            System.out.println("Erro ao salvar produto no banco de dados "+e);
        }
    }

    public List<Produto> listarTodos(){
        return new ArrayList<>(PDAO.buscarTodos());
    }

    public Produto encontrar(int id){
        return PDAO.buscarPorId(id);
    }

    public void atualizar(int id, int parametro, String valor){
        Produto atualizadoProduto = PDAO.buscarPorId(id);

        if(Objects.isNull(atualizadoProduto)){
            System.out.println("Produto não encontrado");
            return;
        }

        switch (parametro){
            case 1: //Nome
                atualizadoProduto.setNome(valor);
                break;
            case 2: //Preço
                atualizadoProduto.setPreco(Double.parseDouble(valor));
                break;
            case 3: //Fornecedor
                atualizadoProduto.setFornecedor(FDAO.buscarPorId(Integer.parseInt(valor)));
                break;
            case 4: //Categoria
                atualizadoProduto.setCategoria(CDAO.buscarPorId(Integer.parseInt(valor)));
                break;
            default:
                System.out.println("Parametro inválido\nNão foi possível atualizar produto");
                return;
        }
        try{
            PDAO.atualizar(atualizadoProduto);
            System.out.println("Produto atualizado com sucesso");
        } catch (RuntimeException e) {
            System.out.println("Não foi possível atualizar o produto. "+e);
        }

    }

    public void excluir(int id){
        try{
            PDAO.excluir(id);
            System.out.println("Produto excluído com sucesso");
        } catch(RuntimeException e){
            System.out.println("Erro ao excluir produto "+e);
        }
    }


}
