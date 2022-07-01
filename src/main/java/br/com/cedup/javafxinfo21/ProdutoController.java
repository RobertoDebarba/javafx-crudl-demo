package br.com.cedup.javafxinfo21;

import br.com.cedup.javafxinfo21.App;
import br.com.cedup.javafxinfo21.ProdutoDAO;
import br.com.cedup.javafxinfo21.Produto;
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

public class ProdutoController implements Initializable {

    @FXML
    private TableColumn<Produto, Integer> codigoProdutoColumn;

    @FXML
    private TableColumn<Produto, String> nomeProdutoColumn;

    @FXML
    private TableColumn<Produto, Integer> precoProdutoColumn;

    @FXML
    private TableView<Produto> produtoTable;

    ObservableList<Produto> produtos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Início da configuração da tabela ---
        this.codigoProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        this.nomeProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.precoProdutoColumn.setCellValueFactory(new PropertyValueFactory<>("preco"));

        this.produtos = this.produtoTable.getItems();
        // Fim da configuração da tabela ---

        // Adicionando produtos na lista
//        Produto maca = new Produto(1, "Maçã", 2.99);
//        Produto banana = new Produto(2, "Banana", 1.5);
//        Produto pcGamer = new Produto(3, "PC Gamer", 15000);
//
//        this.produtos.add(maca);
//        this.produtos.add(banana);
//        this.produtos.add(pcGamer);

        // Bucar os produtos do banco de dados
        ProdutoDAO daoDeProdutos = new ProdutoDAO();
        List<Produto> produtosNoBanco = daoDeProdutos.getAll();
        
        this.produtos.addAll(produtosNoBanco);
    }

    //http://dontpad.com/info21ceduptimbo
    @FXML
    void editar() throws IOException {
        // Obtem o produto da linha selecionada na lista
        Produto produtoSelecionado = this.produtoTable.getSelectionModel().getSelectedItem();

        if (produtoSelecionado != null) {
            CreateUpdateProdutoController.setProduto(produtoSelecionado);

            App.showModal("createUpdateProduto");

            Produto produtoAlterado = CreateUpdateProdutoController.getProduto();

            produtoSelecionado.setCodigo(produtoAlterado.getCodigo());
            produtoSelecionado.setNome(produtoAlterado.getNome());
            produtoSelecionado.setPreco(produtoAlterado.getPreco());

            this.produtoTable.refresh();
            
            // Salvar no banco de dados
            ProdutoDAO daoDoProduto = new ProdutoDAO();
            daoDoProduto.update(produtoSelecionado);
        }
    }

    @FXML
    void novo() throws IOException {
        CreateUpdateProdutoController.setProduto(null);
        App.showModal("createUpdateProduto");

        Produto novoProduto = CreateUpdateProdutoController.getProduto();
        if (novoProduto != null) {
            // Adicionar na lista da tela
            this.produtos.add(novoProduto);
            
            // Salvar no banco de dados
            ProdutoDAO daoDoProduto = new ProdutoDAO();
            daoDoProduto.save(novoProduto);
        }
    }

    //https://code.makery.ch/blog/javafx-dialogs-official/
    @FXML
    void remover() {
        // Identifico qual o produto selecionado
        Produto produtoSelecionado = this.produtoTable.getSelectionModel().getSelectedItem();

        // Confirmação de remoção
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Remoção");
        alert.setHeaderText(produtoSelecionado.getCodigo() + " " + produtoSelecionado.getNome());
        alert.setContentText("Deseja remover o registro?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // Removo o produto
            this.produtos.remove(produtoSelecionado);
            
            // Remove no banco de dados
            ProdutoDAO daoDoProduto = new ProdutoDAO();
            daoDoProduto.delete(produtoSelecionado);
        }
    }

}