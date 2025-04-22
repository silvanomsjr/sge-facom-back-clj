CREATE TABLE IF NOT EXISTS userTypes (
  id INTEGER PRIMARY KEY UNIQUE NOT NULL,
  name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  email TEXT UNIQUE NOT NULL,
  password_hash TEXT NOT NULL,
  userTypeId INTEGER NOT NULL DEFAULT 1,
  createdAt DATETIME DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY(userTypeId) REFERENCES userTypes(id)
);

INSERT OR IGNORE INTO userTypes (id, name) VALUES
(1, 'ALUNO'),
(2, 'COORDENADOR DE ESTÁGIO'),
(3, 'SECRETÁRIO DE ESTÁGIO'),
(4, 'COORDENADOR DE CURSO'),
(5, 'ORIENTADOR DE CURSO');

INSERT OR IGNORE INTO users (email, password_hash, userTypeId) VALUES
("coordenador@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 4),
("orientador@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 5),
("prof.asores@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 2);
--
-- INSERT INTO user_account (institutional_email, secondary_email, user_name, gender, phone, password_hash, password_salt, creation_datetime) VALUES
-- 	("admin@ufu.br", "admin@gmail.com","Admin", 'M', "34111111111","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3",now()),
--     ("coordenador@ufu.br","coordenador@gmail.com", "Coordenador Curso", 'M', "34333333333","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3", now()),
-- 	("orientador@ufu.br", NULL, "Orientador Curso", 'M', "34333333333","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3", now()),
--     ("gina@ufu.br", NULL, "Gina Maira Barbosa de Oliveira", 'F', "34333333333","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3", now()),
--     ("luiz@ufu.br", NULL, "Luiz Gustavo Almeida Martins", 'M', "34333333333","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3", now()),
--     ("maia@ufu.br", NULL, "Marcelo de Almeida Maia", 'M', "34333333333","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3", now()),
--     ("stephane@ufu.br", NULL,"Stéphane Julia", 'M', "34333333333","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3",now()),
--     ("adriano@ufu.br", NULL,"Adriano Mendonça Rocha", 'M', "34333333333","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3",now()),
--     ("eliana@ufu.br", NULL,"Eliana Pantaleão", 'F', "34333333333","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3",now()),
--     ("ivan@ufu.br", NULL,"Ivan da Silva Sendin", 'M', "34333333333","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3",now()),
--     ("leandro@ufu.br", NULL,"Leandro Nogueira Couto", 'M', "34333333333","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3",now()),
--     ("mauricio@ufu.br", NULL,"Mauricio Cunha Escarpinati", 'M', "34333333333","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3",now()),
--     ("aluno@ufu.br", "aluno@gmail.com","Aluno BCC", 'M', "34222222222","6507d069ff5e932b093715ab9a9fd415d5666b6f46b4c4943e695eaf72c9b759","GKA43F4CU71p2YF3",now()),
--     ("viniciuscalixto.grad@ufu.br", NULL, "Vinicius Calixto Rocha", 'M', NULL, NULL, NULL, NULL),
--     ("silvanomsjr@ufu.br", NULL, "Silvano Martins da Silva Junior", 'M', "34999418527", "481edd8919647eed61d328409e4587ea28867b1a270f23650bad3bf0feb0b64b" , "m6ev9ChxQ7S1shH8", now());
--
