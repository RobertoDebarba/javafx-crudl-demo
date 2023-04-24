package br.com.cedup.javafx.controller;

import br.com.cedup.javafx.model.produto.Produto;
import br.com.cedup.javafx.App;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * Classe controller do FXML createupdateproduto.
 */
public class CreateUpdateProdutoController implements Initializable {

    private static Produto produto;

    @FXML
    private TextField codigoTextField;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField precoTextField;

    /**
     * Método executado todas as vezes que a tela é aberta, antes de qualquer
     * outro método.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*
        Preenche os campos da tela caso a variável que contém o produto
        não seja nula.
        */
        if (produto != null) {
            codigoTextField.setText(Integer.toString(produto.getCodigo()));
            nomeTextField.setText(produto.getNome());
            precoTextField.setText(Double.toString(produto.getPreco()));
        }
    }

    /**
     * Usado para trafegar um produto entro essa e outras telas.
     */
    public static void setProduto(Produto produto) {
        CreateUpdateProdutoController.produto = produto;
    }

    /**
     * Usado para trafegar um produto entro essa e outras telas.
     */
    public static Produto getProduto() {
        return CreateUpdateProdutoController.produto;
    }

    /**
     * Click do botão salvar na tela de edição.
     */
    @FXML
    public void salvar() {
        if (!this.codigoTextField.getText().isEmpty() //
                && !this.nomeTextField.getText().isEmpty() //
                && !this.precoTextField.getText().isEmpty()) {

            /*
            Lê as informações dos campos das telas e salva na variável de produto
            */
            produto = new Produto( //
                    Integer.parseInt(this.codigoTextField.getText()), // 
                    this.nomeTextField.getText(), //
                    Double.parseDouble(this.precoTextField.getText()) //
            );
        }

        /*
        Fecha a janela modal.
        */
        App.closeCurrentWindow();
    }

}
