package br.com.cedup.javafx.model.usuario;

import br.com.cedup.javafx.ConnectionSingleton;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * DAO (Data Access Object) do POJO Usuario
 */
public class UsuarioDAO {

    /**
     * Verifica se um usuário existe com base no usuario e senha
     */
    public boolean exists(Usuario usuario) {
        try {
            Connection connection = ConnectionSingleton.getConnection();
            ResultSet resultadoUsuarioExiste = connection. //
                    createStatement(). //
                    executeQuery("select count(*) from usuario " //
                            + " where usuario = '" + usuario.getUsuario() + "'"//
                            + " and senha = '" + usuario.getSenha() + "'");

            resultadoUsuarioExiste.next();
            int quantidadeDeUsuariosComEsseUsuarioESenha = resultadoUsuarioExiste.getInt(1);

            return quantidadeDeUsuariosComEsseUsuarioESenha > 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
