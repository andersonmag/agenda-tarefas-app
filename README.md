<h2 align="center"> To-Do App(Agenda de Tarefas)</h2>

## Para testar

  - Clone esse repositório
  - Projeto utilizando banco de dados PostgreSQL
  - Use os comandos sql abaixo:

```
create database todo_db;
use todo_db;

create table item(
  id bigint not null, 
  descricao varchar(255),
  dataCriacao date not null, 
  ativo boolean not null, 
  realizado boolean not null, 
  primary key (id)
);

create SEQUENCE item_sequence  increment 1 minvalue 1 maxvalue 9223372036854775807 start 1;
alter table item alter column id set default nextval('item_sequence' :: regclass);
  
```

> Projeto feito para prova de seleção de estágio!