package br.com.cedup.javafxinfo21.controller;

import br.com.cedup.javafxinfo21.App;
import br.com.cedup.javafxinfo21.model.usuario.Usuario;
import br.com.cedup.javafxinfo21.model.usuario.UsuarioDAO;
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

/**
 * Classe controller do FXML login.
 */
public class LoginController implements Initializable {

    @FXML
    TextField usuarioTextField;

    @FXML
    PasswordField senhaPasswordField;

    /**
     * Método executado todas as vezes que a tela é aberta, antes de qualquer
     * outro método.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Busca se o usuário existe no banco com base nas informações informadas
     * na tela.
     */
    @FXML
    public void login() throws IOException {
        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuarioLogin = new Usuario(usuarioTextField.getText(), senhaPasswordField.getText());
        boolean usuarioExiste = dao.exists(usuarioLogin);

        if (usuarioExiste) {
            /*
            Caso o usuário exista leva para a tela principal
            */
            Stage stage = (Stage) this.usuarioTextField.getScene().getWindow();
            stage.setMaximized(true);
            stage.setResizable(true);

            App.setRoot("primary");
        } else {
            /*
            Caso o usuário não exista exibe uma mensagem de erro
            */
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Autenticação");
            alert.setHeaderText(null);
            alert.setContentText("Usuário ou senha inválidos");

            alert.showAndWait();
        }
    }

    @FXML
    public void onUserTyped() {
        //System.out.println("Tecla digitada no campo usuário");
    }

}
