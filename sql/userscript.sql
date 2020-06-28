-- run before the project

CREATE USER IF NOT EXISTS testuser IDENTIFIED BY 'pass';

DROP DATABASE IF EXISTS testdb;
CREATE DATABASE testdb DEFAULT CHARACTER SET utf8;

USE testdb;

GRANT ALL ON testdb.* TO 'testuser'@'%';

FLUSH PRIVILEGES;