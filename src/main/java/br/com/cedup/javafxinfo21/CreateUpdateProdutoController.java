package br.com.cedup.javafxinfo21;

import br.com.cedup.javafxinfo21.App;
import br.com.cedup.javafxinfo21.Produto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class CreateUpdateProdutoController implements Initializable {

    static Produto produto;

    @FXML
    private TextField codigoTextField;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField precoTextField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (produto != null) {
            codigoTextField.setText(Integer.toString(produto.getCodigo()));
            nomeTextField.setText(produto.getNome());
            precoTextField.setText(Double.toString(produto.getPreco()));
        }
    }

    static void setProduto(Produto produto) {
        CreateUpdateProdutoController.produto = produto;
    }

    static Produto getProduto() {
        return CreateUpdateProdutoController.produto;
    }

    @FXML
    void salvar() {
        if (!this.codigoTextField.getText().isEmpty() //
                && !this.nomeTextField.getText().isEmpty() //
                && !this.precoTextField.getText().isEmpty()) {

            produto = new Produto( //
                    Integer.parseInt(this.codigoTextField.getText()), // 
                    this.nomeTextField.getText(), //
                    Double.parseDouble(this.precoTextField.getText()) //
            );
        }

        App.closeCurrentWindow();
    }

}
