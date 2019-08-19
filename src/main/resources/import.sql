INSERT INTO usuario (id_usuario, login, senha) VALUES (nextval('usuario_sequence'), 'admin', '123');
INSERT INTO public.pessoa (id_pessoa, data_cadastro, email, endereco, nome, cpf, origem_cadastro, sexo, id_usuario_cadastro) VALUES(nextval ('pessoa_sequence'), '2019-08-12 18:01:00', 'gustavo@email.com', 'Rua Eng. Sadi Castro 123, 456', 'Gustavo da Silva', '90168780097', 'I', 'M', '1');
