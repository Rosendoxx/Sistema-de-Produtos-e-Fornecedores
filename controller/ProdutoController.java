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
    private ProdutoDAO pdao;
    private FornecedorDAO fdao;
    private CategoriaDAO cdao;

    public ProdutoController(){
        this.pdao = new ProdutoDAO();
        this.fdao = new FornecedorDAO();
        this.cdao = new CategoriaDAO();
    }

    public List<Produto> listarTodos(){
        return new ArrayList<>(pdao.buscarTodos());
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
        Fornecedor fornecedor = fdao.buscarPorId(idFornecedor);
        if (Objects.isNull(fornecedor)){
            System.out.println("Erro: fornecedor não encontrado\nNão é possível cadastrar");
            return;
        }
        Categoria categoria = cdao.buscarPorId(idCategoria);
        if (Objects.isNull(categoria)) {
            System.out.println("Erro: categoria não encontrada\nNão é possível cadastrar");
            return;
        }

        Produto novoProduto = new Produto(0, nome, preco, fornecedor, categoria);

        try{
            pdao.inserir(novoProduto);
            System.out.println("Produto cadastrado com sucesso!");
        } catch(RuntimeException e){
            System.out.println("Erro ao salvar produto no banco de dados "+e);
        }
    }

    public void excluir(int id){
        try{
            pdao.excluir(id);
            System.out.println("Produto excluído com sucesso");
        } catch(RuntimeException e){
            System.out.println("Erro ao excluir produto "+e);
        }
    }


}
