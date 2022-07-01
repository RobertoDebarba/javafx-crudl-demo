package br.com.cedup.javafxinfo21.model.usuario;

/*
create table usuario (
	id int auto_increment primary key,
	usuario varchar (255),
    senha varchar (255)
);

insert into usuario (usuario, senha) values ("123", "123");
insert into usuario (usuario, senha) values ("roberto", "123");
 */
/**
 * POJO que representa um Usuario na aplicação.
 */
public class Usuario {

    private String usuario;
    private String senha;

    public Usuario(String usuario, String senha) {
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
