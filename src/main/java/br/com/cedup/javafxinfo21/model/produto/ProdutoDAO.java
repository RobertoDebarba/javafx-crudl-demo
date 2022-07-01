package br.com.cedup.javafxinfo21.model.produto;

import br.com.cedup.javafxinfo21.ConnectionSingleton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

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
