package br.com.cedup.javafx.model.produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.cedup.javafx.ConnectionSingleton;

/**
 * DAO (Data Access Object) do POJO Produto
 */
public class ProdutoDAO {

    /**
     * Obtém um Produto com base no ID informado
     */
    public Produto getById(int id) {
        final String sql = "SELECT * FROM produto WHERE codigo = ?";
        try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (final ResultSet resultadoProdutos = preparedStatement.executeQuery()) {

                resultadoProdutos.next();
                int codigo = resultadoProdutos.getInt("codigo");
                String nome = resultadoProdutos.getString("nome");
                double preco = resultadoProdutos.getDouble("preco");

                return new Produto(codigo, nome, preco);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ontém todos os Produtos
     */
    public List<Produto> getAll() {
        final String sql = "SELECT * FROM produto";
        try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql); //
                final ResultSet resultadoProdutos = preparedStatement.executeQuery()) {

            List<Produto> resultadoComTodosOsProdutos = new ArrayList<>();

            while (resultadoProdutos.next()) {
                int codigo = resultadoProdutos.getInt("codigo");
                String nome = resultadoProdutos.getString("nome");
                double preco = resultadoProdutos.getDouble("preco");

                Produto produtoQueAcabeiDeObterDoBanco = new Produto(codigo, nome, preco);
                resultadoComTodosOsProdutos.add(produtoQueAcabeiDeObterDoBanco);
            }

            return resultadoComTodosOsProdutos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Salva um novo Produto
     */
    public void save(Produto novoProduto) {
        final String sql = "INSERT INTO produto (nome, preco) values (?, ?)";
        try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, novoProduto.getNome());
            preparedStatement.setDouble(2, novoProduto.getPreco());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Atualiza os campos nome e preço de um produto com base no ID
     */
    public void update(Produto produtoEditado) {
        final String sql = "UPDATE produto SET nome = ?, preco = ? WHERE codigo = ?";
        try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, produtoEditado.getNome());
            preparedStatement.setDouble(2, produtoEditado.getPreco());
            preparedStatement.setInt(3, produtoEditado.getCodigo());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Remove o Produto informado caso exista
     */
    public void delete(Produto produtoParaRemover) {
        final String sql = "DELETE FROM produto WHERE codigo = ?";
        try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setInt(1, produtoParaRemover.getCodigo());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
