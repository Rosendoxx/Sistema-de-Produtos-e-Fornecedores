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

    public String cadastrar(String nome, double preco, int idFornecedor, int idCategoria){
        if(nome == null || nome.isEmpty()) return "Erro: o nome do produto é obrigatório\nNão é possivel cadastrar";
        if(preco < 0) return "Erro: o preço não pode ser negativo\nNão é possível cadastrar";
        Fornecedor fornecedor = FDAO.buscarPorId(idFornecedor);
        if (Objects.isNull(fornecedor)) return "Erro: fornecedor não encontrado\nNão é possível cadastrar";
        Categoria categoria = CDAO.buscarPorId(idCategoria);
        if (Objects.isNull(categoria)) return "Erro: categoria não encontrada\nNão é possível cadastrar";

        Produto novoProduto = new Produto(0, nome, preco, fornecedor, categoria);

        try{
            PDAO.inserir(novoProduto);
            return "Produto cadastrado com sucesso!";
        } catch(RuntimeException e){
            return e.toString();
        }
    }

    public List<Produto> listarTodos(){
        return new ArrayList<>(PDAO.buscarTodos());
    }

    public Produto encontrar(int id){
        return PDAO.buscarPorId(id);
    }

    public String atualizarNome(int id, String valor){
        Produto atualizadoProduto = PDAO.buscarPorId(id);

        if(Objects.isNull(atualizadoProduto)) return "Produto não encontrado";

        atualizadoProduto.setNome(valor);

        try{
            PDAO.atualizar(atualizadoProduto);
            return "Produto atualizado com sucesso";
        } catch (RuntimeException e) {
            return e.toString();
        }

    }

    public String atualizarPreco(int id, Double valor){
        Produto atualizadoProduto = PDAO.buscarPorId(id);

        if(Objects.isNull(atualizadoProduto)) return "Produto não encontrado";

        atualizadoProduto.setPreco(valor);

        try{
            PDAO.atualizar(atualizadoProduto);
            return "Produto atualizado com sucesso";
        } catch (RuntimeException e) {
            return e.toString();
        }

    }

    public String atualizarFornecedor(int id, int idFornecedor){
        Produto atualizadoProduto = PDAO.buscarPorId(id);

        if(Objects.isNull(atualizadoProduto)) return "Produto não encontrado";

        atualizadoProduto.setFornecedor(FDAO.buscarPorId(idFornecedor));

        try{
            PDAO.atualizar(atualizadoProduto);
            return "Produto atualizado com sucesso";
        } catch (RuntimeException e) {
            return e.toString();
        }

    }

    public String atualizarCategoria(int id, int idCategoria){
        Produto atualizadoProduto = PDAO.buscarPorId(id);

        if(Objects.isNull(atualizadoProduto)) return "Produto não encontrado";

        atualizadoProduto.setCategoria(CDAO.buscarPorId(idCategoria));

        try{
            PDAO.atualizar(atualizadoProduto);
            return "Produto atualizado com sucesso";
        } catch (RuntimeException e) {
            return e.toString();
        }

    }

    public String excluir(int id){
        try{
            PDAO.excluir(id);
            return "Produto excluído com sucesso";
        } catch(RuntimeException e){
            return e.toString();
        }
    }


}
