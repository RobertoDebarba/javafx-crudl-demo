package br.com.cedup.javafxinfo21.controller;

import br.com.cedup.javafxinfo21.App;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 * Classe controller do FXML primary.
 */
public class PrimaryController {

    @FXML
    public void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    public void switchToProduto() throws IOException {
        App.setRoot("produto");
    }
}
