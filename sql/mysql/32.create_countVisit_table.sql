CREATE TABLE COUNT_VISIT (
  COUNT_VISIT_ID varchar(32) NOT NULL,
  NAME varchar(255) NOT NULL,
  DESCRIPTION varchar(255),
  STATUS varchar(32),
  PRIMARY KEY (COUNT_VISIT_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;