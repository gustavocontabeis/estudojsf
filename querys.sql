SELECT pes.id_pessoa, pes.cpf, pes.data_cadastro, pes.email, pes.endereco, pes.data_exclusao, pes.nome, pes.sexo, pes.id_usuario
FROM public.pessoa pes
LEFT JOIN public.usuario usu on usu.id_usuario = pes.id_usuario;

SELECT * FROM usuario;