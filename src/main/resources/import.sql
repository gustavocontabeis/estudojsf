INSERT INTO usuario (id_usuario, login, senha) VALUES (nextval('usuario_sequence'), 'admin', '123');
INSERT INTO pessoa (id_pessoa, data_cadastro, email, endereco, nome, cpf, sexo, id_usuario) VALUES(nextval ('pessoa_sequence'), '2019-08-12 18:01:00', 'gustavo@email.com', 'Rua Eng. Sadi Castro 123, 456', 'Gustavo da Silva', '901.687.800-97', 'MASCULINO', 1);
INSERT INTO usuario (id_usuario, login, senha) VALUES (nextval('usuario_sequence'), 'user1', '123');
INSERT INTO pessoa (id_pessoa, data_cadastro, email, endereco, nome, cpf, sexo, id_usuario) VALUES(nextval ('pessoa_sequence'), '2075-07-09 18:01:00', 'lucas.gomes@yahoo.com', 'Quadra CLS 103 21', 'Lucas Machado Gomes', '877.137.094-36', 'MASCULINO', 2);
INSERT INTO usuario (id_usuario, login, senha) VALUES (nextval('usuario_sequence'), 'user2', '123');
INSERT INTO pessoa (id_pessoa, data_cadastro, email, endereco, nome, cpf, sexo, id_usuario) VALUES(nextval ('pessoa_sequence'), '1973-01-20 18:01:00', 'mauro.machado@globo.com', 'Rua �lvaro Anes, 281', 'Mauro Amaral Machado', '839.394.472-42', 'MASCULINO', 3);
INSERT INTO album (id_album, data_exclusao, nome) values (nextval('album_sequence'), NULL, 'Album 1');
INSERT INTO album (id_album, data_exclusao, nome) values (nextval('album_sequence'), NULL, 'Album 2');
INSERT INTO letra(id_letra, data_exclusao, ingles, nomeen, nomept, portugues, id_album) VALUES(nextval('letra_sequence'), NULL, 'What is Lorem Ipsum?\nLorem Ipsum is simply dummy text of the printing and typesetting industry.\nLorem Ipsum has been the industry''s standard dummy text ever since the 1500s,\nwhen an unknown printer took a galley of type and scrambled it to make a type specimen book.\nIt has survived not only five centuries, \nbut also the leap into electronic typesetting,\nremaining essentially unchanged. \nIt was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages,\nand more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n\nWhy do we use it?\nIt is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. \nThe point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, \nas opposed to using ''Content here, content here'', making it look like readable English. \nMany desktop publishing packages and web page editors now use Lorem Ipsum as their default model text,\nand a search for ''lorem ipsum'' will uncover many web sites still in their infancy. \nVarious versions have evolved over the years, \nsometimes by accident, sometimes on purpose (injected humour and the like).\nWhere does it come from?\n\nContrary to popular belief,\nLorem Ipsum is not simply random text.\nIt has roots in a piece of classical Latin literature from 45 BC,\nmaking it over 2000 years old. Richard McClintock, \na Latin professor at Hampden-Sydney College in Virginia,\nlooked up one of the more obscure Latin words, consectetur,\nfrom a Lorem Ipsum passage, and going through the cites of the word in classical literature,\ndiscovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum"\n(The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance.\nThe first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.', 'ererererere', 'qwewqewqeqwe', 'O Lorem Ipsum � um texto modelo da ind�stria tipogr�fica e de impress�o.\nO Lorem Ipsum tem vindo a ser o texto padr�o usado por estas ind�strias \ndesde o ano de 1500, quando uma misturou os caracteres de um texto \npara criar um esp�cime de livro. Este texto n�o s� sobreviveu 5 s�culos,\nmas tamb�m o salto para a tipografia electr�nica, \nmantendo-se essencialmente inalterada. \nFoi popularizada nos anos 60 com a disponibiliza��o das folhas de Letraset,\nque continham passagens com Lorem Ipsum, \ne mais recentemente com os programas de publica��o\ncomo o Aldus PageMaker que incluem vers�es do Lorem Ipsum\n� um facto estabelecido de que um leitor � distra�do pelo conte�do leg�vel de uma p�gina quando analisa a sua mancha gr�fica.\nLogo, \no uso de Lorem Ipsum leva a uma distribui��o mais ou menos normal de letras,\nao contr�rio do uso de "Conte�do aqui, conte�do aqui", \ntornando-o texto leg�vel. \nMuitas ferramentas de publica��o electr�nica e editores de p�ginas web usam actualmente o Lorem Ipsum como o modelo de texto usado por omiss�o,\ne uma pesquisa por "lorem ipsum" ir� encontrar muitos websites ainda na sua inf�ncia. \nV�rias vers�es t�m evolu�do ao longo dos anos, por vezes por acidente, \npor vezes propositadamente (como no caso do humor).\nDe onde � que ele vem?\n\nAo contr�rio da cren�a popular,\no Lorem Ipsum n�o � simplesmente texto aleat�rio.\nTem ra�zes numa pe�a de literatura cl�ssica em Latim,\nde 45 AC, tornando-o com mais de 2000 anos. Richard McClintock,\num professor de Latim no Col�gio Hampden-Sydney, na Virg�nia, \nprocurou uma das palavras em Latim mais obscuras (consectetur) numa passagem Lorem Ipsum,\ne atravessando as cidades do mundo na literatura cl�ssica, descobriu a sua origem. \nLorem Ipsum vem das sec��es 1.10.32 e 1.10.33 do "de Finibus Bonorum et Malorum" (Os Extremos do Bem e do Mal),\npor C�cero, escrito a 45AC. Este livro � um tratado na teoria da �tica, muito popular durante a Renascen�a. \nA primeira linha de Lorem Ipsum, "Lorem ipsum dolor sit amet..." aparece de uma linha na sec��o 1.10.32.\n', 1);