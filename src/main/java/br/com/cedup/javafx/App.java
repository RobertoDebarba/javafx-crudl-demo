package br.com.cedup.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Classe principal da aplicação
 */
public class App extends Application {

    private static Scene scene;
    private static Stage modalStage;

    /**
     * Método de inicialização da aplicação JavaFX
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setWidth(401);
        stage.setHeight(200);
        stage.show();
    }

    /**
     * Define a tela sendo exibida com base no nome do arquivo fxml
     */
    public static void openWindow(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * Exibe uma janela em modo modal com base no nome do arquivo fxml
     */
    public static void openModal(String fxml) throws IOException {
        // Obtém a tela atual

        // Carrega a nova tela
        Scene modalScene = new Scene(loadFXML(fxml));

        // Abre o modal
        modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(scene.getRoot().getScene().getWindow());
        modalStage.setScene(modalScene);
        modalStage.showAndWait();
    }

    /**
     * Fecha a janela atual
     */
    public static void closeModal() {
        modalStage.close();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
