INSERT INTO tb_pessoa (id_pesssoa, nome, sobrenome, idade, cpf, endereco) VALUES (NEXTVAL('sq_pessoa'), 'Guilherme Ryan Erick', 'Rezende', 21, '033.617.153-69', 'Rua Expedito José Sousa Farias, 117');

INSERT INTO tb_usuario(id_usuario, login, senha, permissao, id_pessoa) VALUES (NEXTVAL('sq_usuario'), 'admin', '$2a$10$kuMckiWf8w4oG0SvmQObDeZF2Rlp.hsltHSHo/Vvs4VBiurW7bXF6', 'ADMIN', 1);