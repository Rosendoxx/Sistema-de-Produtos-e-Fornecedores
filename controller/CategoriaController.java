package controller;

import dao.CategoriaDAO;
import model.Categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriaController {
    private CategoriaDAO cdao;

    public CategoriaController(){
        this.cdao = new CategoriaDAO();
    }

    public List<Categoria> listarTodos(){
        return new ArrayList<>(cdao.buscarTodos());
    }

    public void cadastrar(int id, String nome){
        if(!Objects.isNull(cdao.buscarPorId(id))){
            System.out.println("Essa categoria já existe\nNão é possível cadastrar");
            return;
        }
        if(cdao.buscarTodos().stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).isParallel()){
            System.out.println("Já existe uma categoria com esse nome\nNão é possível cadastrar");
            return;
        }
        try{
            cdao.inserir(new Categoria(id, nome)); //Observar abstração do ENUM
            System.out.println("Categoria adcionada com sucesso");
        } catch (RuntimeException e){
            System.out.println("Erro ao inserir categoria "+ e);
        }
    }

    public void excluir(int id){
        try{
            cdao.excluir(id);
            System.out.println("Categoria excluida com sucesso");
        } catch (RuntimeException e){
            System.out.println("Erro ao excluir categoria "+e);
        }
    }
}
