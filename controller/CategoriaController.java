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

    public String cadastrar(String nome){
        if(CDAO.buscarTodos().stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).isParallel())
            return "Já existe uma categoria com esse nome\nNão é possível cadastrar";
        try{
            CDAO.inserir(new Categoria(0, nome));
            return "Categoria adcionada com sucesso";
        } catch (RuntimeException e){
            return "Erro ao inserir categoria "+ e;
        }
    }

    public List<Categoria> listarTodos(){
        return new ArrayList<>(CDAO.buscarTodos());
    }

    public Categoria encontrar(int id){
        return CDAO.buscarPorId(id);
    }

    public String atualizarNome(int id, String valor){
        Categoria atualizadaCategoria = CDAO.buscarPorId(id);

        if(Objects.isNull(atualizadaCategoria)) return "Categoria não encontrado";

        atualizadaCategoria.setNome(valor);

        try{
            CDAO.atualizar(atualizadaCategoria);
            return "Categoria atualizada com sucesso";
        } catch(RuntimeException e){
            return "Não foi possível atualizar a categoria. " +e ;
        }
    }

    public String excluir(int id){
        try{
            CDAO.excluir(id);
            return "Categoria excluida com sucesso" ;
        } catch (RuntimeException e){
            return  "Erro ao excluir categoria "+e;
        }
    }
}
