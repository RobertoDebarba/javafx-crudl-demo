# JavaFX CRUDL demo desktop application

CRUDL demo application developed with JavaFX 18. It contains:

* Login
* CRUDL for product entity

<img src="https://github.com/RobertoDebarba/javafx-crudl-demo/blob/main/screenshot/produtos.PNG" width="500" width="auto"><img src="https://github.com/RobertoDebarba/javafx-crudl-demo/blob/main/screenshot/produtos-edit.PNG" width="500" width="auto">
<img src="https://github.com/RobertoDebarba/javafx-crudl-demo/blob/main/screenshot/produtos-delete.PNG" width="500" width="auto"><img src="https://github.com/RobertoDebarba/javafx-crudl-demo/blob/main/screenshot/login.PNG" width="400" width="auto">

## How to run

### Prerequisites

* Java 11

### Import

* Import as a maven project in your favorite IDE
* Create the database in MySQL
```sql
create database produtojavafx;

create table produto (
	codigo int auto_increment primary key,
    nome varchar(255),
    preco double
);

insert into produto values (1, 'PC Gamer', 1000);

create table usuario (
	id int auto_increment primary key,
	usuario varchar (255),
    senha varchar (255)
);

insert into usuario (usuario, senha) values ("123", "123");
insert into usuario (usuario, senha) values ("roberto", "123");
```

## License

The codebase is licensed under [GPL v3.0](http://www.gnu.org/licenses/gpl-3.0.html).
