CREATE TABLE TEST (
  TEST_ID varchar(32) NOT NULL,
  DESCRIPTION varchar(512),
  PRIMARY KEY (TEST_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert into TEST(TEST_ID, DESCRIPTION) VALUES ('1','<p style="font-size: 22px;font-weight:bold;">Проект "Вся Школа".</p> Email адрес: info@my3o.ru');

