package br.com.cedup.javafx.controller;

import br.com.cedup.javafx.App;
import java.io.IOException;
import javafx.fxml.FXML;

/**
 * Classe controller do FXML secondary.
 */
public class SecondaryController {

    @FXML
    public void switchToPrimary() throws IOException {
        App.openWindow("primary");
    }
}
