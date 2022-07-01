
package br.com.cedup.javafxinfo21;

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
                            + " where usuario = '" + usuario.usuario + "'"//
                            + " and senha = '" + usuario.senha + "'");
            
            resultadoUsuarioExiste.next();
            int quantidadeDeUsuariosComEsseUsuarioESenha = resultadoUsuarioExiste.getInt(1);
            
//            if (quantidadeDeUsuariosComEsseUsuarioESenha > 0) {
//                return true;
//            } else {
//                return false;
//            }

             return quantidadeDeUsuariosComEsseUsuarioESenha > 0;
    
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
