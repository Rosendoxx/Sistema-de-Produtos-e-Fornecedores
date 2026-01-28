package dao;

import model.Fornecedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FornecedorDAO extends GenericDAO<Fornecedor> {
    @Override
    protected String getInsertSQL() {
        return "INSERT INTO Fornecedor (id, nome, cnpj, telefone) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getSelectAllSQL() {
        return "SELECT * FROM Fornecedor";
    }

    @Override
    protected String getSelectByIdSQL() {
        return "SELECT * FROM Fornecedor WHERE id = ?";
    }

    @Override
    protected String getTableName() {
        return "Fornecedor";
    }

    @Override
    protected void setInsertParametros(PreparedStatement stmt, Fornecedor f) throws SQLException {
        stmt.setInt(1, f.getIdFornecedor());
        stmt.setString(2, f.getNome());
        stmt.setString(3, f.getCnpj());
        stmt.setString(4, f.getTelefone());
    }

    @Override
    protected Fornecedor mapResultSet(ResultSet rs) throws SQLException {
        int idFornecedor = rs.getInt("id");
        String nome = rs.getString("nome");
        String cnpj = rs.getString("cnpj");
        String telefone = rs.getString("telefone");

        return new Fornecedor(idFornecedor, nome, cnpj, telefone);
    }

    @Override
    public void atualizar(Fornecedor entidade) {
        String sql = "UPDATE Fornecedor SET nome = ?, cnpj = ?, telefone = ? WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, entidade.getNome());
            stmt.setString(2, entidade.getCnpj());
            stmt.setString(3, entidade.getTelefone());
            stmt.setInt(4, entidade.getIdFornecedor());

            stmt.executeUpdate();
        } catch(SQLException e){
            throw new RuntimeException("Erro ao atualizar fornecedor "+e);
        }
    }
}
