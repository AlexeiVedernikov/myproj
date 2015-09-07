CREATE TABLE SHEDULES (
  SHEDULE_ID varchar(32) NOT NULL,
  DISCIPLINE_ID varchar(32) NOT NULL,
  USER_ID varchar(32) NOT NULL,
  GROUP_ID varchar(32) NOT NULL,
  YEAR varchar(4) NOT NULL,
  CABINET varchar(32),
  FROM_TIMES varchar(32),
  TO_TIMES varchar(32),
  STATUS varchar(32) NOT NULL,
  DESCRIPTION varchar(255),
  PRIMARY KEY (SHEDULE_ID),
  FOREIGN KEY (DISCIPLINE_ID) REFERENCES DISCIPLINE(DISCIPLINE_ID),
  FOREIGN KEY (GROUP_ID) REFERENCES GROUPS(GROUP_ID),
  FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;