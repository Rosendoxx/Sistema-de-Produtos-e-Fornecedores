package dao;

import model.Categoria;
import model.Fornecedor;
import model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoDAO extends GenericDAO<Produto>{
    private final FornecedorDAO FDAO = new FornecedorDAO();
    private final CategoriaDAO CDAO = new CategoriaDAO();

    @Override
    protected String getInsertSQL() {
        return "INSERT INTO Produto (nome, preco, fornecedor, categoria) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getSelectAllSQL() {
        return "SELECT p.*, f.nome AS fornecedor, c.nome as categoria, " +
                "FROM Produto as p" +
                "INNER JOIN Fornecedor AS f ON p.id_fornecedor = f.id" +
                "INNER JOIN Categoria AS c ON p.id_categoria = c.id";
    }

    @Override
    protected String getSelectByIdSQL() {
        return "SELECT * FROM Produto WHERE id = ?";
    }

    @Override
    protected String getTableName() {
        return "Produto";
    }

    @Override
    protected void setInsertParametros(PreparedStatement stmt, Produto p) throws SQLException {
        stmt.setInt(1, p.getId());
        stmt.setString(2, p.getNome());
        stmt.setDouble(3, p.getPreco());
        stmt.setInt(4, p.getFornecedor().getIdFornecedor());
        stmt.setInt(5, p.getCategoria().getId());
    }

    @Override
    protected Produto mapResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        double preco = rs.getDouble("preco");
        int idFornecedor = rs.getInt("id_fornecedor");
        int idCategoria = rs.getInt("id_categoria");
        Fornecedor fornecedor = FDAO.buscarPorId(idFornecedor);
        Categoria categoria = CDAO.buscarPorId(idCategoria);
        return new Produto(id, nome, preco, fornecedor, categoria);
    }

    @Override
    public void atualizar(Produto entidade) {
        String sql = "UPDATE Produto SET nome = ?, preco = ?, id_fornecedor = ?, id_categoria = ? WHERE id = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            stmt.setString(1, entidade.getNome());
            stmt.setDouble(2, entidade.getPreco());
            stmt.setInt(3, entidade.getFornecedor().getIdFornecedor());
            stmt.setInt(4,entidade.getCategoria().getId());
            stmt.setInt(5, entidade.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto "+e);
        }
    }
}
