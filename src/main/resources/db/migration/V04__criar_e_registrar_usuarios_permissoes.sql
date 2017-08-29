CREATE TABLE usuarios(
	codigo BIGINT(20) PRIMARY KEY,
	nome VARCHAR(60) NOT NULL,
	email VARCHAR(100) NOT NULL,
	senha VARCHAR(100) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE permissoes(
	codigo BIGINT(20) PRIMARY KEY,
	nome VARCHAR(60) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissoes(
	codigo_usuario BIGINT(20),
	codigo_permissao BIGINT(20),
	PRIMARY KEY(codigo_usuario, codigo_permissao),
	FOREIGN KEY (codigo_usuario) REFERENCES usuarios(codigo),
	FOREIGN KEY (codigo_permissao) REFERENCES permissoes(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuarios (codigo, nome, email, senha) VALUES (1, 'Administrador', 'adminitrador@luizmoney.com.br', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuarios (codigo, nome, email, senha) VALUES (2, 'Maria', 'maria@luizmoney.com.br', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permissoes (codigo, nome) values (1, 'ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissoes (codigo, nome) values (2, 'ROLE_PESQUISAR_CATEGORIA');

INSERT INTO permissoes (codigo, nome) values (3, 'ROLE_CADASTRAR_PESSOA');
INSERT INTO permissoes (codigo, nome) values (4, 'ROLE_REMOVER_PESSOA');
INSERT INTO permissoes (codigo, nome) values (5, 'ROLE_PESQUISAR_PESSOA');

INSERT INTO permissoes (codigo, nome) values (6, 'ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissoes (codigo, nome) values (7, 'ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissoes (codigo, nome) values (8, 'ROLE_PESQUISAR_LANCAMENTO');

---- admin
INSERT INTO usuario_permissoes (codigo_usuario, codigo_permissao) values (1, 1);
INSERT INTO usuario_permissoes (codigo_usuario, codigo_permissao) values (1, 2);
INSERT INTO usuario_permissoes (codigo_usuario, codigo_permissao) values (1, 3);
INSERT INTO usuario_permissoes (codigo_usuario, codigo_permissao) values (1, 4);
INSERT INTO usuario_permissoes (codigo_usuario, codigo_permissao) values (1, 5);
INSERT INTO usuario_permissoes (codigo_usuario, codigo_permissao) values (1, 6);
INSERT INTO usuario_permissoes (codigo_usuario, codigo_permissao) values (1, 7);
INSERT INTO usuario_permissoes (codigo_usuario, codigo_permissao) values (1, 8);

---- maria

INSERT INTO usuario_permissoes (codigo_usuario, codigo_permissao) values (2, 2);
INSERT INTO usuario_permissoes (codigo_usuario, codigo_permissao) values (2, 5);
INSERT INTO usuario_permissoes (codigo_usuario, codigo_permissao) values (2, 8);