package br.com.cedup.javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.com.cedup.javafx.App;
import br.com.cedup.javafx.model.produto.Produto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

        /*
        Escuta as alterações no campo preço removendo qualquer carácter não numérico exceto ponto
         */
        precoTextField.textProperty().addListener((o, oldValue, newValue) -> {
            precoTextField.setText(newValue.replaceAll("[^\\d.]", ""));
        });
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
        if (!this.nomeTextField.getText().isEmpty() //
                && !this.precoTextField.getText().isEmpty()) {

            // Verifica se o preço possui um formato válido (13232 ou 1232.31231)
            if (this.precoTextField.getText().matches("\\d+\\.?\\d*")) {
                /*
                Lê as informações dos campos das telas e salva na variável de produto
                */
                produto = new Produto( //
                                       this.codigoTextField.getText().isBlank() ? 0 : Integer.parseInt(this.codigoTextField.getText()), //
                                       this.nomeTextField.getText(), //
                                       Double.parseDouble(this.precoTextField.getText()) //
                );

                /*
                Fecha a janela modal.
                */
                App.closeModal();

            } else {
                /*
                Exibe um alerta sobre o formato do preço
                 */
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Produto");
                alert.setHeaderText("Valores inválidos");
                alert.setContentText("O formato do preço é inválido (exemplo: 10.50).");

                alert.showAndWait();
            }
        } else {
            /*
            Exibe um alerta sobre os campos obrigatórios e não fecha a tela.
             */
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Produto");
            alert.setHeaderText("Valores inválidos");
            alert.setContentText("Todos os compos marcados com * são obrigatórios.");

            alert.showAndWait();
        }

    }

}
