package dao;

import model.Categoria;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoriaDAO extends GenericDAO<CategoriaDAO, Categoria> {
    @Override
    protected String getInsertSQL() {
        return "INSERT INTO Categoria (nome) VALUES(?)";
    }

    @Override
    protected String getSelectAllSQL() {
        return "SELECT * FROM Categoria";
    }

    @Override
    protected String getSelectByIdSQL() {
        return "SELECT * FROM Categoria WHERE id = ?";
    }

    @Override
    protected String getTableName() {
        return "Categoria";
    }

    @Override
    protected void setInsertParametros(PreparedStatement stmt, Categoria entidadeOrigem) throws SQLException {
        stmt.setInt(1, entidadeOrigem.getId());
        stmt.setString(1, entidadeOrigem.getNome());
    }

    @Override
    protected Categoria mapResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        return new Categoria(id, nome);
    }
}
