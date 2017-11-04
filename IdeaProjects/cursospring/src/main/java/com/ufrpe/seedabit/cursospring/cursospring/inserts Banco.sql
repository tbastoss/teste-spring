
create database if not exists teste;

insert into autorizacao (id,nome) values (2,"DELETAR");
insert into autorizacao (id,nome) values (1,"VISUALIZAR");
insert into cargo (id,nome) values (1,"Presidente");
insert into cargo_autorizacao (id_cargo,id_autorizacao) values (1,1);
insert into usuario (cpf,nome,senha,id_cargo) values ("12345678910","Davi","presidente",1);
