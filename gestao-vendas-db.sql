CREATE DATABASE gestao_venda1;

USE gestao_venda1;

CREATE TABLE usuario(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(75) NOT NULL,
usuario VARCHAR(50) NOT NULL UNIQUE,
senha VARCHAR(100) NOT NULL,
perfil VARCHAR(10) NOT NULL DEFAULT 'PADRAO',
estado BOOLEAN NOT NULL DEFAULT TRUE,
data_hora_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
ultimo_login DATETIME DEFAULT '0001-01-01 00:00:00'
);

CREATE TABLE categoria(
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL UNIQUE,
descricao VARCHAR(200)
);

CREATE TABLE produto(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL UNIQUE,
descricao VARCHAR(200),
preco DECIMAL(10, 2) NOT NULL,
quantidade INT NULL,
categoria_id INT NOT NULL,
usuario_id BIGINT NOT NULL,
data_hora_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_produto_categoria FOREIGN KEY(categoria_id) REFERENCES categoria(id),
CONSTRAINT fk_produto_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id)
);

CREATE TABLE cliente(
id INT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50),
telefone VARCHAR(15),
endereco VARCHAR(100)
);

CREATE TABLE venda(
id INT PRIMARY KEY AUTO_INCREMENT,
total_venda DECIMAL(10, 2) NOT NULL,
valor_pago DECIMAL(10, 2) NOT NULL,
troco DECIMAL(10, 2) NOT NULL,
desconto DECIMAL(10, 2) NOT NULL,
cliente_id INT,
usuario_id BIGINT NOT NULL,
data_hora_criacao DATETIME DEFAULT CURRENT_TIMESTAMP,
ultima_atualizacao DATETIME DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT fk_venda_cliente FOREIGN KEY(cliente_id) REFERENCES cliente(id),
CONSTRAINT fk_venda_usuario FOREIGN KEY(usuario_id) REFERENCES usuario(id)
);

CREATE TABLE venda_item(
venda_id INT NOT NULL,
produto_id BIGINT NOT NULL,
quantidade INT NOT NULL,
total DECIMAL(10, 2) NOT NULL,
desconto DECIMAL(10, 2) NOT NULL,
CONSTRAINT fk_venda_item_venda FOREIGN KEY(venda_id) REFERENCES venda(id),
CONSTRAINT fk_venda_item_produto FOREIGN KEY(produto_id) REFERENCES produto(id)
);