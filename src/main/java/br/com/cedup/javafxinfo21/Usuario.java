
package br.com.cedup.javafxinfo21;

/*
create table usuario (
	id int auto_increment primary key,
	usuario varchar (255),
    senha varchar (255)
);

insert into usuario (usuario, senha) values ("123", "123");
insert into usuario (usuario, senha) values ("roberto", "123");
*/

public class Usuario {
    
    public String usuario;
    public String senha;
    
    Usuario (String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
    
    
}
