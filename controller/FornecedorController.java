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

    public void cadastrar(String nome, String cnpj, String telefone){
        Fornecedor f = new Fornecedor(0, nome, cnpj, telefone);
        try{
            fdao.inserir(f);
            System.out.println("Fornecedor inserido com sucesso!");
        } catch (RuntimeException e){
            System.out.println("Erro ao inserir usuário "+e+"\nTente novamente!");
        }
    }

    public List<Fornecedor> listarTodos(){
        return new ArrayList<>(fdao.buscarTodos());
    }

    public void atualizar(int id, int parametro, String valor){
        Fornecedor atualizadoFornecedor = fdao.buscarPorId(id);

        if(Objects.isNull(atualizadoFornecedor)){
            System.out.println("Fornecedor não encontrado");
            return;
        }

        switch (parametro){
            case 1: //Nome
                atualizadoFornecedor.setNome(valor);
                break;
            case 2: //CNPJ
                atualizadoFornecedor.setCnpj(valor);
                break;
            case 3: //Telefone
                atualizadoFornecedor.setTelefone(valor);
                break;
            default:
                System.out.println("Parametro inválido\nNão foi possível atualizar o fornecedor");
                return;
        }

        try{
            fdao.atualizar(atualizadoFornecedor);
            System.out.println("Fornecedor atualizado com sucesso");
        } catch(RuntimeException e){
            System.out.println("Erro ao atualizar o fornecedor. "+e);
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
