package br.com.cedup.javafxinfo21;

import br.com.cedup.javafxinfo21.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    void switchToProduto() throws IOException {
        App.setRoot("produto");
    }
}
