CREATE TABLE DISCIPLINE (
  DISCIPLINE_ID varchar(32) NOT NULL,
  NAME varchar(32) NOT NULL,
  DESCRIPTION varchar(512),
  STATUS varchar(32) NOT NULL,
  PRIMARY KEY (DISCIPLINE_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;