drop database if exists projetocovid;
create database projetocovid;
use projetocovid;

/* CRIANDO AS ENTIDADES */

create table if not exists sexo(
	id int not null auto_increment primary key,
    descricao text
); 

insert into sexo (descricao) values ("Masculino"), ("Feminino"), ("Outros");

create table if not exists tipo_usuario(
	id int not null auto_increment primary key,
    descricao text
);

insert into tipo_usuario (descricao) values ("Pesquisador"), ("Voluntário"), ("Público");

create table if not exists usuario(
	id int not null auto_increment primary key,
    nome text,
    datanascimento date,
    sexo int not null,
    cpf text,
    tipo int not null,
    instituicao text,
    constraint fk_usuario_sexo
    foreign key(sexo) references sexo(id),
    constraint fk_usuario_tipo
    foreign key(tipo) references tipo_usuario(id)
);

create table if not exists estagio_pesquisa(
	id int not null auto_increment primary key,
    descricao text
);

insert into estagio_pesquisa (descricao) values ("Inicial"), ("Testes"), ("Aplicação em Massa");

create table if not exists vacina(
	id int not null auto_increment primary key,
    descricao text,
    pais_de_origem text,
    estagio int not null,
    data_inicio date,
    pesquisador int not null,
    constraint fk_vacina_estagio
    foreign key(estagio) references estagio_pesquisa(id),
    constraint fk_vacina_usuario
    foreign key(pesquisador) references usuario(id)
);

create table if not exists reacao(
	id int not null auto_increment primary key,
    descricao text
);

insert into reacao (descricao) values ("Péssima"), ("Ruim"), ("Regular"), ("Boa"), ("Ótima");

create table if not exists vacinacao(
	id int not null auto_increment primary key,
    idusuario int not null,
    idvacina int not null,
    reacao int not null,
    data date,
    constraint fk_vacinacao_usuario
    foreign key(idusuario) references usuario(id),
    constraint fk_vacinacao_vacina
    foreign key(idvacina) references vacina(id),
    constraint fk_vacinacao_reacao
    foreign key(reacao) references reacao(id)
);

-- VIEWS

-- listagem completa dos usuários
create view vw_usuario as
select 	usuario.id as codigo,
		usuario.datanascimento as nascimento_sql,
        usuario.nome as nome,
        date_format(usuario.datanascimento, "%d/%m/%Y") as nascimento,
        sexo.descricao as sexo,
        usuario.cpf as cpf,
        tipo_usuario.descricao as tipo,
        usuario.instituicao as instituicao
from usuario
inner join sexo on 
(usuario.sexo = sexo.id) 
inner join tipo_usuario on
(usuario.tipo = tipo_usuario.id)
order by codigo desc;

-- listagem completa das vacinas
create view vw_vacina as
select 	vacina.id as codigo,
		vacina.descricao as descricao,
        vacina.pais_de_origem as pais,
        estagio_pesquisa.descricao as estagio,
        date_format(vacina.data_inicio, "%d/%m/%Y") as data,
        usuario.nome as pesquisador
from vacina
inner join usuario on 
(vacina.pesquisador = usuario.id)
inner join estagio_pesquisa on
(vacina.estagio = estagio_pesquisa.id)
order by codigo desc;

-- listagem completa das vacinações
create view vw_vacinacao as
select 	vacinacao.id as codigo,
		usuario.nome as usuario,
        vacina.descricao as vacina,
        reacao.descricao as reacao,
        date_format(vacinacao.data, "%d/%m/%Y") as data
from vacinacao
inner join usuario on
(vacinacao.idusuario = usuario.id)
inner join vacina on
(vacinacao.idvacina = vacina.id)
inner join reacao on
(vacinacao.reacao = reacao.id)
order by codigo desc;

-- triggers
delimiter $$

-- usuarios
create trigger tg_usuario_bd before delete on usuario for each row
begin
	delete from vacina where pesquisador = old.id;
    delete from vacinacao where idusuario = old.id;
end $$

-- vacinas
create trigger tg_vacina_bd before delete on vacina for each row
begin
	delete from vacinacao where idvacina = old.id;
end $$

delimiter ;