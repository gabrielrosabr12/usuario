ALTER TABLE tb_users
    ADD COLUMN
        id_adress BIGINT ,

    ADD CONSTRAINT
        fk_users_adress FOREIGN KEY (id_adress) REFERENCES tb_adress(id);



