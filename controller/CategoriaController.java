package controller;

import dao.CategoriaDAO;
import model.Categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriaController {
    private final CategoriaDAO CDAO;

    public CategoriaController(){
        this.CDAO = new CategoriaDAO();
    }

    public void cadastrar(int id, String nome){
        if(!Objects.isNull(CDAO.buscarPorId(id))){
            System.out.println("Essa categoria já existe\nNão é possível cadastrar");
            return;
        }
        if(CDAO.buscarTodos().stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).isParallel()){
            System.out.println("Já existe uma categoria com esse nome\nNão é possível cadastrar");
            return;
        }
        try{
            CDAO.inserir(new Categoria(id, nome)); //Observar abstração do ENUM
            System.out.println("Categoria adcionada com sucesso");
        } catch (RuntimeException e){
            System.out.println("Erro ao inserir categoria "+ e);
        }
    }

    public List<Categoria> listarTodos(){
        return new ArrayList<>(CDAO.buscarTodos());
    }

    public void atualizar(int id, String valor){
        Categoria atualizadaCategoria = CDAO.buscarPorId(id);

        if(Objects.isNull(atualizadaCategoria)){
            System.out.println("Categoria não encontrado");
            return;
        }

        atualizadaCategoria.setNome(valor);

        try{
            CDAO.atualizar(atualizadaCategoria);
            System.out.println("Categoria atualizada com sucesso");
        } catch(RuntimeException e){
            System.out.println("Não foi possível atualizar a categoria. "+e);
        }
    }

    public void excluir(int id){
        try{
            CDAO.excluir(id);
            System.out.println("Categoria excluida com sucesso");
        } catch (RuntimeException e){
            System.out.println("Erro ao excluir categoria "+e);
        }
    }
}
