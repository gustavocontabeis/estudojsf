INSERT INTO usuario (id_usuario, login, senha) VALUES (nextval('usuario_sequence'), 'admin', '123');
INSERT INTO public.pessoa (id_pessoa, data_cadastro, email, endereco, nome, cpf, sexo, id_usuario) VALUES(nextval ('pessoa_sequence'), '2019-08-12 18:01:00', 'gustavo@email.com', 'Rua Eng. Sadi Castro 123, 456', 'Gustavo da Silva', '901.687.800-97', 'MASCULINO', 1);
INSERT INTO usuario (id_usuario, login, senha) VALUES (nextval('usuario_sequence'), 'user1', '123');
INSERT INTO public.pessoa (id_pessoa, data_cadastro, email, endereco, nome, cpf, sexo, id_usuario) VALUES(nextval ('pessoa_sequence'), '2075-07-09 18:01:00', 'lucas.gomes@yahoo.com', 'Quadra CLS 103 21', 'Lucas Machado Gomes', '877.137.094-36', 'MASCULINO', 2);
INSERT INTO usuario (id_usuario, login, senha) VALUES (nextval('usuario_sequence'), 'user2', '123');
INSERT INTO public.pessoa (id_pessoa, data_cadastro, email, endereco, nome, cpf, sexo, id_usuario) VALUES(nextval ('pessoa_sequence'), '1973-01-20 18:01:00', 'mauro.machado@globo.com', 'Rua Álvaro Anes, 281', 'Mauro Amaral Machado', '839.394.472-42', 'MASCULINO', 3);
INSERT INTO public.album (id_album, data_exclusao, nome) values (nextval('album_sequence'), NULL, 'Album 1');
INSERT INTO public.album (id_album, data_exclusao, nome) values (nextval('album_sequence'), NULL, 'Album 2');
