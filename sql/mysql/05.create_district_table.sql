CREATE TABLE DISTRICT (
  DISTRICT_ID varchar(32) NOT NULL,
  NAME varchar(255) NOT NULL,
  DESCRIPTION varchar(255),
  STATUS varchar(32) NOT NULL,
  REGION_ID varchar(32) NOT NULL,
  PRIMARY KEY (DISTRICT_ID),
  FOREIGN KEY (REGION_ID) REFERENCES REGION(REGION_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;