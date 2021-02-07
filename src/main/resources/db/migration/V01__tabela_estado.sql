CREATE TABLE estado (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
	sigla VARCHAR(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into estado (nome, sigla) values ('Acre', 'AC');
insert into estado (nome, sigla) values ('Alagoas', 'AL');
insert into estado (nome, sigla) values ('Amapá', 'AP');
insert into estado (nome, sigla) values ('Amazonas', 'AM');
insert into estado (nome, sigla) values ('Bahia', 'BA');
insert into estado (nome, sigla) values ('Ceará', 'CE');
insert into estado (nome, sigla) values ('Distrito Federal', 'DF');
insert into estado (nome, sigla) values ('Espírito Santo', 'ES');
insert into estado (nome, sigla) values ('Goiás', 'GO');
insert into estado (nome, sigla) values ('Maranhão', 'MA');
insert into estado (nome, sigla) values ('Mato Grosso', 'MT');
insert into estado (nome, sigla) values ('Mato Grosso do Sul', 'MS');
insert into estado (nome, sigla) values ('Minas Gerais', 'MG');
insert into estado (nome, sigla) values ('Pará', 'PA');
insert into estado (nome, sigla) values ('Paraíba', 'PB');
insert into estado (nome, sigla) values ('Paraná', 'PR');
insert into estado (nome, sigla) values ('Pernambuco', 'PE');
insert into estado (nome, sigla) values ('Piauí', 'PI');
insert into estado (nome, sigla) values ('Rio de Janeiro', 'RJ');
insert into estado (nome, sigla) values ('Rio Grande do Norte', 'RN');
insert into estado (nome, sigla) values ('Rio Grande do Sul', 'RS');
insert into estado (nome, sigla) values ('Rondônia', 'RO');
insert into estado (nome, sigla) values ('Roraima', 'RR');
insert into estado (nome, sigla) values ('Santa Catarina', 'SC');
insert into estado (nome, sigla) values ('São Paulo', 'SP');
insert into estado (nome, sigla) values ('Sergipe', 'SE');
insert into estado (nome, sigla) values ('Tocantins', 'TO');
