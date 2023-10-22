--INSERT ROLES
--insert into roles (role_name)
--values
--('ADMIN'),
--('BASIC')

-- CREATE ADMIN USER

--INSERT INTO examportal.users
--(id, username, "password", first_name, last_name, email, phone, enabled, profile, created_by, created_on, modified_by, modified_on)
--values
--(nextval('examportal.users_id_seq'::regclass), 'admin', 'admin', 'ADMIN', 'USER', 'admin@demo.com', '123456789', true, '', 0, CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP);

