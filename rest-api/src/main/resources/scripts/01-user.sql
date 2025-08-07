-- Drop user first if they exist
DROP USER if exists 'springuser'@'%' ;

-- Now create user with prop privileges
CREATE USER 'springuser'@'%' IDENTIFIED BY 'springuser';

GRANT ALL PRIVILEGES ON * . * TO 'springuser'@'%';