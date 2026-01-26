package dao;

import connection.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class GenericDAO <E, T> {
    protected Connection connection;

    public GenericDAO(){
        this.connection = DataBaseConnection.getInstance().connection();
    }

    protected abstract String getInsertSQL();
    protected abstract String getSelectAllSQL();
    protected abstract String getSelectByIdSQL();
    protected abstract String getTableName();
    protected abstract void setInsertParametros(PreparedStatement stmt, T entidadeOrigem) throws SQLException;
    protected abstract T mapResultSet(ResultSet rs) throws SQLException;

    public void inserir(T entidade){
        try (PreparedStatement stmt = connection.prepareStatement(getInsertSQL())){
            setInsertParametros(stmt, entidade);
            stmt.executeUpdate();
            System.out.println("Objeto inserido com sucesso");
        } catch(SQLException e){
            throw new RuntimeException("Erro ao inserir objeto "+e);
        }
    }

    public List<T> buscarTodos(){
        List<T> lista = new ArrayList<T>();

        try(PreparedStatement stmt = connection.prepareStatement(getSelectAllSQL()); ResultSet rs = stmt.executeQuery()){
            while(rs.next()){
                T entidade = mapResultSet(rs);
                lista.add(entidade);
            }
        } catch(SQLException e){
            throw new RuntimeException("Erro ao buscar "+e);
        }
        return lista;
    }

    public T buscarPorId(Integer id){
        try(PreparedStatement stmt = connection.prepareStatement(getSelectByIdSQL())){
            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    return mapResultSet(rs);
                }
            }
        } catch (SQLException e){
            throw new RuntimeException("Erro ao buscar objeto "+e);
        }
        return null;
    }

    public void atualizar(T objeto, String parametro, String atualizacao, String identificador, String objetoIdentificador){
        String sql = "UPDATE ? SET ? = ? WHERE ? = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, String.valueOf(objeto.getClass()));
            stmt.setString(2, parametro);
            stmt.setString(3, atualizacao);
            stmt.setString(4, identificador);
            stmt.setString(5, objetoIdentificador);
            stmt.executeUpdate();
            System.out.println("Objeto atualizado com sucesso!");
        } catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar objeto "+e);
        }

    }

    public void excluir(int id){
        String sql = "DELETE FROM "+ getTableName()+" WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Objeto excluido com sucesso");
        } catch(SQLException e){
            throw new RuntimeException("Erro ao excluir objeto "+e);
        }
    }
}
