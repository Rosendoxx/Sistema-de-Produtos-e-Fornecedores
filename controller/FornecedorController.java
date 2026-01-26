package controller;

import dao.FornecedorDAO;
import model.Fornecedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FornecedorController {
    private FornecedorDAO fdao;

    public FornecedorController(){
        this.fdao = new FornecedorDAO();
    }

    public List<Fornecedor> listarTodos(){
        return new ArrayList<>(fdao.buscarTodos());
    }

    public void cadastrar(int id, String nome, String cnpj, String telefone){
        Fornecedor f = fdao.buscarPorId(id);
        if(!Objects.isNull(f)){
            System.out.println("Erro: esse funcionário já existe no banco de dados!");
            return;
        }
        f = new Fornecedor(id, nome, cnpj, telefone);
        try{
            fdao.inserir(f);
            System.out.println("Fornecedor inserido com sucesso!");
        } catch (RuntimeException e){
            System.out.println("Erro ao inserir usuário "+e+"\nTente novamente!");
        }
    }

    public void excluir(int id){
        try{
            fdao.excluir(id);
            System.out.println("Fornecedor excluido com sucesso!");
        } catch(RuntimeException e){
            System.out.println("Erro ao excluir fornecedor "+e);
        }
    }
}
