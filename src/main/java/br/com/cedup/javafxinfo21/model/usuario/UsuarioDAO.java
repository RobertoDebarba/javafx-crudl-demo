package br.com.cedup.javafxinfo21.model.usuario;

import br.com.cedup.javafxinfo21.ConnectionSingleton;
import java.sql.Connection;
import java.sql.ResultSet;

public class UsuarioDAO {

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
