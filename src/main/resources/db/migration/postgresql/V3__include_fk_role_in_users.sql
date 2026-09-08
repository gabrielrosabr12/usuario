ALTER TABLE tb_users
ADD COLUMN role_id BIGINT;

ALTER TABLE tb_users
ADD CONSTRAINT fk_role_users
FOREIGN KEY (role_id) REFERENCES tb_users_roles(id)