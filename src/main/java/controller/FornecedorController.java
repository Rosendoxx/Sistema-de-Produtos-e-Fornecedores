package controller;

import dao.FornecedorDAO;
import model.Fornecedor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FornecedorController {
    private final FornecedorDAO FDAO;

    public FornecedorController(){
        this.FDAO = new FornecedorDAO();
    }

    public String cadastrar(String nome, String cnpj, String telefone){
        Fornecedor f = new Fornecedor(0, nome, cnpj, telefone);
        try{
            FDAO.inserir(f);
            return "Fornecedor inserido com sucesso!";
        } catch (RuntimeException e){
            return e.toString();
        }
    }

    public List<Fornecedor> listarTodos(){
        return new ArrayList<>(FDAO.buscarTodos());
    }

    public Fornecedor encontrar(int id){
        return FDAO.buscarPorId(id);
    }

    public String atualizarNome(int id, String valor){
        Fornecedor atualizadoFornecedor = FDAO.buscarPorId(id);

        if(Objects.isNull(atualizadoFornecedor)) return "Fornecedor não encontrado";

        atualizadoFornecedor.setNome(valor);

        try{
            FDAO.atualizar(atualizadoFornecedor);
            return "Fornecedor atualizado com sucesso";
        } catch(RuntimeException e){
            return e.toString();
        }
    }

    public String atualizarCnpj(int id, String valor){
        Fornecedor atualizadoFornecedor = FDAO.buscarPorId(id);

        if(Objects.isNull(atualizadoFornecedor)) return  "Fornecedor não encontrado";

        atualizadoFornecedor.setCnpj(valor);

        try{
            FDAO.atualizar(atualizadoFornecedor);
            return "Fornecedor atualizado com sucesso";
        } catch(RuntimeException e){
            return e.toString();
        }
    }

    public String atualizarTelefone(int id, String valor){
        Fornecedor atualizadoFornecedor = FDAO.buscarPorId(id);

        if(Objects.isNull(atualizadoFornecedor)) return "Fornecedor não encontrado";

        atualizadoFornecedor.setTelefone(valor);

        try{
            FDAO.atualizar(atualizadoFornecedor);
            return "Fornecedor atualizado com sucesso";
        } catch(RuntimeException e){
            return e.toString();
        }
    }

    public String excluir(int id){
        try{
            FDAO.excluir(id);
            return "Fornecedor excluído com sucesso!";
        } catch(RuntimeException e){
            return e.toString();
        }
    }
}
