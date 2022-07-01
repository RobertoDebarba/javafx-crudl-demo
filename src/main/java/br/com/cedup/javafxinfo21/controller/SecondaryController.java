package br.com.cedup.javafxinfo21.controller;

import br.com.cedup.javafxinfo21.App;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 * Classe controller do FXML secondary.
 */
public class SecondaryController {

    @FXML
    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}
