

Listar usuários:
	postgres=# \du
										  List of roles
		Role name    |                         Attributes                         | Member of 
	-----------------+------------------------------------------------------------+-----------
	 coder_blog_user |                                                            | {}
	 loja            |                                                            | {}
	 mga_usr_001     |                                                            | {}
	 postgres        | Superuser, Create role, Create DB, Replication, Bypass RLS | {}

	postgres=# 




--se precisar excluir o usuario


sudo -i -u postgres
psql

ou

sudo -u postgres psql

drop database estudojsf;
DROP USER user_estudojsf;


postgres=# create database estudojsf;
postgres=# create user user_estudojsf with encrypted password 'gtrfdswed1654';
postgres=# grant all privileges on database estudojsf to user_estudojsf;


FAZENDO DUMP DE DES

	gustavo@foguetinho:~$ sudo -i -u postgres
	postgres@foguetinho:~$ pg_dump estudojsf -h localhost -U user_estudojsf > estudojsf.sql
	Password: 
	postgres@foguetinho:~$ ls
	estudojsf.sql
	postgres@foguetinho:~$ pwd
	/var/lib/postgresql
	postgres@foguetinho:~$ exit
	gustavo@foguetinho:~$ 

COLOCANDO O BANCO EM PRD
	gustavo@foguetinho:~$ scp /var/lib/postgresql/estudojsf.sql coder1@codersistemas.com.br:~
	gustavo@foguetinho:~$ ssh coder1@codersistemas.com.br
	root@ubuntu-s-1vcpu-2gb-nyc3-01:/home/coder1# sudo -i -u postgres
	postgres@ubuntu-s-1vcpu-2gb-nyc3-01:~$ psql estudojsf -f /home/coder1/estudojsf.sql 


DEPLOY APLICAÇÃO

	mvn clean install
	scp /home/gustavo/dev/workspace/estudos-jsf/estudojsf/target/estudojsf.war coder1@codersistemas.com.br:~
	ssh coder1@codersistemas.com.br
	sudo sh /opt/tomcat8/bin/shutdown.sh
	sudo ls -lah /opt/tomcat8/work/
	sudo rm -r /opt/tomcat8/work/

segue conforme documento de deploy...


http://codersistemas.com.br:8080/estudojsf/sistema/home.xhtml
