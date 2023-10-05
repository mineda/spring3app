insert into usr_usuario (usr_nome, usr_senha)
    values ('admin', '$2a$10$i3.Z8Yv1Fwl0I5SNjdCGkOTRGQjGvHjh/gMZhdc3e7LIovAklqM6C');
insert into aut_autorizacao (aut_nome)
    values ('ROLE_ADMIN');
insert into uau_usuario_autorizacao (usr_id, aut_id) 
    values (1, 1);
insert into ant_anotacao (ant_texto, ant_usr_id) 
    values ('Esta é uma anotação de teste!', 1);