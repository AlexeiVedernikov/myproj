CREATE TABLE TOWN_AREA (
  TOWN_AREA_ID varchar(32) NOT NULL,
  NAME varchar(255) NOT NULL,
  DESCRIPTION varchar(255),
  STATUS varchar(32) NOT NULL,
  TOWN_ID varchar(32) NOT NULL,
  PRIMARY KEY (TOWN_AREA_ID),
  FOREIGN KEY (TOWN_ID) REFERENCES TOWN(TOWN_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;