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

CREATE TABLE IF NOT EXISTS rules (
  id INTEGER PRIMARY KEY UNIQUE NOT NULL,
  text TEXT NOT NULL,
  description TEXT,
  active BOOLEAN DEFAULT 1,
  internship_type TEXT CHECK (internship_type IN ('OBRIGATORIO', 'NAO_OBRIGATORIO', 'AMBOS')) NOT NULL DEFAULT 'OBRIGATORIO'
);

INSERT OR IGNORE INTO userTypes (id, name) VALUES
(1, 'ALUNO'),
(2, 'COORDENADOR DE ESTÁGIO'),
(3, 'SECRETÁRIO DE ESTÁGIO'),
(4, 'COORDENADOR DE CURSO'),
(5, 'ORIENTADOR');

INSERT OR IGNORE INTO users (email, password_hash, userTypeId) VALUES
("coordenadorestagio@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 2),
("secretario@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 3),
("coordenador@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 4),
("orientador@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 5),
("prof.asores@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 2),
("mauricio@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 5),
("leandro@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 5),
("ivan@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 5),
("eliana@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 5),
("adriano@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 5),
("stephane@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 5),
("maia@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 5),
("luiz@ufu.br", "bcrypt+sha512$badde1c5e998b98485d012ebc39026ea$12$1e60de877ccaaa7fe48aa1f13ef6463d4db985c9d7ca719f", 5),
("silvanomsjr@ufu.br", "bcrypt+sha512$a9e50b201e1a0ac008fbdecf4267c70c$12$6bbda35c8bc98961c7373d08ec93907ec539ec689831dc89", 4);
