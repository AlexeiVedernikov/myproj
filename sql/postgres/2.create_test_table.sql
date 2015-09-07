CREATE TABLE TEST (
  TEST_ID character varying(32) PRIMARY KEY,
  DESCRIPTION character varying(512)
) WITH (OIDS=FALSE);


insert into TEST(TEST_ID, DESCRIPTION) VALUES ('1','<p style="font-size: 22px;font-weight:bold;">Проект "Вся Школа".</p> Email адрес: info@my3o.ru');

