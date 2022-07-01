package br.com.cedup.javafxinfo21;

import br.com.cedup.javafxinfo21.App;
import br.com.cedup.javafxinfo21.Usuario;
import br.com.cedup.javafxinfo21.UsuarioDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController implements Initializable {
    
    @FXML
    TextField usuarioTextField;
    
    @FXML
    PasswordField senhaPasswordField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    void login() throws IOException {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuarioLogin = new Usuario(usuarioTextField.getText(), senhaPasswordField.getText());
        boolean usuarioExiste = dao.exists(usuarioLogin);
        
        if (usuarioExiste) {
            Stage stage = (Stage) this.usuarioTextField.getScene().getWindow();
            stage.setMaximized(true);
            stage.setResizable(true);
            
            App.setRoot("primary");
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Autenticação");
            alert.setHeaderText(null);
            alert.setContentText("Usuário ou senha inválidos");

            alert.showAndWait();
        }
    }
    
    @FXML
    void onUserTyped() {
        //System.out.println("Tecla digitada no campo usuário");
    }
    
}
