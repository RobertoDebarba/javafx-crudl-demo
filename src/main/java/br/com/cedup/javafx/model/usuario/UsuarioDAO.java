package br.com.cedup.javafx.model.usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.cedup.javafx.ConnectionSingleton;

/**
 * DAO (Data Access Object) do POJO Usuario
 */
public class UsuarioDAO {

    /**
     * Verifica se um usuário existe com base no usuario e senha
     */
    public boolean exists(Usuario usuario) {
        final String sql = "SELECT COUNT(*) FROM usuario WHERE usuario = ? AND senha = ?";
        try (final PreparedStatement preparedStatement = ConnectionSingleton.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, usuario.getUsuario());
            preparedStatement.setString(2, usuario.getSenha());
            try (final ResultSet resultadoUsuarioExiste = preparedStatement.executeQuery()) {

                resultadoUsuarioExiste.next();
                int quantidadeDeUsuariosComEsseUsuarioESenha = resultadoUsuarioExiste.getInt(1);

                return quantidadeDeUsuariosComEsseUsuarioESenha > 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
