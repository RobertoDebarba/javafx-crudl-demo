package br.com.cedup.javafx.controller;

import br.com.cedup.javafx.App;
import br.com.cedup.javafx.model.produto.Produto;
import br.com.cedup.javafx.model.produto.ProdutoDAO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Classe controller do FXML produto.
 */
public class ProdutoController implements Initializable {

    @FXML
    private TableColumn<Produto, Integer> codigoProdutoColumn;

    @FXML
    private TableColumn<Produto, String> nomeProdutoColumn;

    @FXML
    private TableColumn<Produto, Integer> precoProdutoColumn;

    @FXML
    private TableView<Produto> produtoTable;

    private ObservableList<Produto> produtos;

    /**
     * Método executado todas as vezes que a tela é aberta, antes de qualquer
     * outro método.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        Configura as colunas da tabela da interface gráfica
         */
        this.codigoProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.nomeProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.precoProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));

        /*
        Obtém a lista de itens da interface gráfica para que possamos
        manipular nesse fonte.
         */
        this.produtos = this.produtoTable.getItems();

        // Bucar os produtos do banco de dados e adiciona na lista gráfica
        ProdutoDAO daoDeProdutos = new ProdutoDAO();
        List<Produto> produtosNoBanco = daoDeProdutos.getAll();

        this.produtos.addAll(produtosNoBanco);
    }

    @FXML
    public void editar() throws IOException {
        // Obtem o produto da linha selecionada na lista
        Produto produtoSelecionado = this.produtoTable.getSelectionModel().getSelectedItem();

        if (produtoSelecionado != null) {
            // Envia o produto para o model da edição
            CreateUpdateProdutoController.setProduto(produtoSelecionado);

            // Abre o modal de edição e espera o usuário clicar OK
            App.openModal("createUpdateProduto");

            // Obtém o produto alterado do modal de edição
            Produto produtoAlterado = CreateUpdateProdutoController.getProduto();

            // Altera o produto original com o alterado
            produtoSelecionado.setCodigo(produtoAlterado.getCodigo());
            produtoSelecionado.setNome(produtoAlterado.getNome());
            produtoSelecionado.setPreco(produtoAlterado.getPreco());

            // Atualiza a lista gráfica para aplicar as alterações do produto
            this.produtoTable.refresh();

            // Salva o  produto no banco de dados
            ProdutoDAO daoDoProduto = new ProdutoDAO();
            daoDoProduto.update(produtoSelecionado);
        }
    }

    @FXML
    public void novo() throws IOException {
        // Garante que a tela de edição está vazia
        CreateUpdateProdutoController.setProduto(null);
        // Mosta o modal de edição do produto e pausa o código até o usuário clicar OK
        App.openModal("createUpdateProduto");

        // Obtém o novo produto criado no modal de edição
        Produto novoProduto = CreateUpdateProdutoController.getProduto();
        if (novoProduto != null) {
            // Salva o produto no banco de dados
            ProdutoDAO daoDoProduto = new ProdutoDAO();
            novoProduto = daoDoProduto.save(novoProduto);

            // Adicionar na lista gráfica
            this.produtos.add(novoProduto);
        }
    }

    @FXML
    public void remover() {
        // Obtém o produto selecionado na lista gráfica
        Produto produtoSelecionado = this.produtoTable.getSelectionModel().getSelectedItem();

        /*
        Confirmação de remoção.
        Documentação sobre mensagens de diálogo no JavaFX
        https://code.makery.ch/blog/javafx-dialogs-official/
         */
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Remoção");
        alert.setHeaderText(produtoSelecionado.getCodigo() + " " + produtoSelecionado.getNome());
        alert.setContentText("Deseja remover o registro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // Remove o produto
            this.produtos.remove(produtoSelecionado);

            // Remove no banco de dados
            ProdutoDAO daoDoProduto = new ProdutoDAO();
            daoDoProduto.delete(produtoSelecionado);
        }
    }

}
