INSERT INTO tb_pessoa (id_pesssoa, nome, sobrenome, idade, cpf, endereco) VALUES (NEXTVAL('sq_pessoa'), 'Murilo Breno', 'Theo Almada', 32, '227.137.059-08', 'Avenida Principal, 888');

INSERT INTO tb_usuario(id_usuario, login, senha, permissao, id_pessoa) VALUES (NEXTVAL('sq_usuario'), 'murilo.breno', '$2a$10$kuMckiWf8w4oG0SvmQObDeZF2Rlp.hsltHSHo/Vvs4VBiurW7bXF6', 'USER', CURRVAL('sq_pessoa'));