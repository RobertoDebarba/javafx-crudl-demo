package br.com.cedup.javafxinfo21;

/*
create database produtojavafx;

create table produto (
	codigo int primary key,
    nome varchar(255),
    preco double
);

insert into produto values (1, 'PC Gamer', 1000);
*/

public class Produto {
    
    int codigo;
    String nome;
    double preco;

    public Produto(int codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
    
}
