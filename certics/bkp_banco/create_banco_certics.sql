CREATE TABLE area_competencia (
  ACO_ID int(10) unsigned NOT NULL auto_increment,
  ACO_TIT varchar(100) NOT NULL,
  ACO_PERG_CHAVE varchar(255) NOT NULL,
  ACO_DESC varchar(1000) NOT NULL,
  PRIMARY KEY  (ACO_ID)
);

CREATE TABLE organizacao_solicitante (
  OSO_ID int(10) unsigned NOT NULL auto_increment,
  OSO_NOME varchar(255) NOT NULL,
  OSO_CNPJ varchar(20) NOT NULL,
  PRIMARY KEY (OSO_ID)
);

CREATE TABLE pessoa_fisica (
  PES_ID int(10) unsigned NOT NULL auto_increment,
  PES_NOME varchar(255) NOT NULL,
  PES_CPF varchar(20) NOT NULL,
  PES_SEXO varchar(20) NOT NULL,
  PRIMARY KEY (PES_ID)
);

CREATE TABLE usuario (
  USR_ID int(10) unsigned NOT NULL auto_increment,
  USR_EMAIL varchar(100) NOT NULL,
  USR_SENHA varchar(100) NOT NULL,
  USR_PERFIL varchar(20) NOT NULL,
  USR_PESID int(10) unsigned NOT NULL,
  PRIMARY KEY  USING BTREE (USR_ID),
  KEY FK_USR_PESID (USR_PESID),
  CONSTRAINT FK_USR_PESID FOREIGN KEY (USR_PESID) REFERENCES pessoa_fisica (PES_ID)
);

CREATE TABLE resultado_esperado (
  REP_ID int(10) unsigned NOT NULL auto_increment,
  REP_TIT varchar(50) NOT NULL,
  REP_DESC varchar(255) NOT NULL,
  REP_ACOID int(10) unsigned NOT NULL,
  PRIMARY KEY  (REP_ID),
  KEY FK_REP_ACOID (REP_ACOID),
  CONSTRAINT FK_REP_ACOID FOREIGN KEY (REP_ACOID) REFERENCES area_competencia (ACO_ID)
);

CREATE TABLE software (
  SOF_ID int(10) unsigned NOT NULL auto_increment,
  SOF_NOME varchar(255) NOT NULL,
  SOF_OSOID int(10) unsigned NOT NULL,
  PRIMARY KEY  (SOF_ID),
  KEY FK_SOF_OSOID (SOF_OSOID),
  CONSTRAINT FK_SOF_OSOID FOREIGN KEY (SOF_OSOID) REFERENCES organizacao_solicitante (OSO_ID)
);

CREATE TABLE avaliacao (
  AVA_ID int(10) unsigned NOT NULL auto_increment,
  AVA_PESID int(10) unsigned NOT NULL,
  AVA_SOFID int(10) unsigned NOT NULL,
  AVA_EPAVA varchar(45) NOT NULL,
  PRIMARY KEY  (AVA_ID),
  KEY AVA_PESID (AVA_PESID),
  KEY AVA_SOFID (AVA_SOFID),
  CONSTRAINT AVA_PESID FOREIGN KEY (AVA_PESID) REFERENCES pessoa_fisica (PES_ID),
  CONSTRAINT AVA_SOFID FOREIGN KEY (AVA_SOFID) REFERENCES software (SOF_ID)
);

CREATE TABLE pergunta_resposta (
  PRE_ID int(10) unsigned NOT NULL auto_increment,
  PRE_AVAID int(10) unsigned NOT NULL,
  PRE_REPID int(10) unsigned NOT NULL,
  PRE_EVI varchar(1000) NOT NULL,
  PRE_EPRE varchar(10) NOT NULL,
  PRIMARY KEY (PRE_ID),
  KEY FK_PRE_AVAID (PRE_AVAID),
  KEY FK_PRE_REPID (PRE_REPID),
  CONSTRAINT FK_PRE_AVAID FOREIGN KEY (PRE_AVAID) REFERENCES avaliacao (AVA_ID),
  CONSTRAINT FK_PRE_REPID FOREIGN KEY (PRE_REPID) REFERENCES resultado_esperado (REP_ID)
);

INSERT INTO pessoa_fisica (PES_NOME,PES_CPF,PES_SEXO) VALUES
 ('Vinicius Ferneda de Lima','06755043923','Masculino');

INSERT INTO usuario (USR_ID,USR_EMAIL,USR_SENHA,USR_PERFIL,USR_PESID) VALUES
 ('vinicius.ferneda@gmail.com','123456','Administrador',1);
