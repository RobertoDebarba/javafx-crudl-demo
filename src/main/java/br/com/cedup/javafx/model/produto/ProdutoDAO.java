package br.com.cedup.javafx.model.produto;

import br.com.cedup.javafx.ConnectionSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) do POJO Produto
 */
public class ProdutoDAO {

    /**
     * Obtém um Produto com base no ID informado
     */
    public Produto getById(int id) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            ResultSet resultadoProdutos = connection. //
                    createStatement(). //
                    executeQuery("SELECT * FROM produto WHERE codigo = " + id);

            resultadoProdutos.next();
            int codigo = resultadoProdutos.getInt("codigo");
            String nome = resultadoProdutos.getString("nome");
            double preco = resultadoProdutos.getDouble("preco");

            return new Produto(codigo, nome, preco);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Ontém todos os Produtos
     */
    public List<Produto> getAll() {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            ResultSet resultadoProdutos = connection. //
                    createStatement(). //
                    executeQuery("SELECT * FROM produto");

            List<Produto> resultadoComTodosOsProdutos = new ArrayList<>();

            while (!resultadoProdutos.isLast()) {
                resultadoProdutos.next();

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
        try {
            Connection connection = ConnectionSingleton.getConnection();
            connection.createStatement(). //
                    executeUpdate("INSERT INTO produto values (" //
                            + novoProduto.getCodigo() //
                            + ", '" + novoProduto.getNome() + "'" //
                            + ", " + novoProduto.getPreco() + ")");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Atualiza os campos nome e preço de um produto com base no ID
     */
    public void update(Produto produtoEditado) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            connection.createStatement(). //
                    executeUpdate("UPDATE produto SET " //
                            + "nome = '" + produtoEditado.getNome() + "'" //
                            + ", preco = " + produtoEditado.getPreco() //
                            + " WHERE codigo = " + produtoEditado.getCodigo() //
                    );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Remove o Produto informado caso exista
     */
    public void delete(Produto produtoParaRemover) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            connection.createStatement(). //
                    executeUpdate("DELETE FROM produto where codigo = " + produtoParaRemover.getCodigo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
