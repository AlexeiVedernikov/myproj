CREATE TABLE ORGANIZATION_USER (
  ORGANIZATION_ID varchar(32) NOT NULL,
  USER_ID varchar(32) NOT NULL,
  FOREIGN KEY (ORGANIZATION_ID) REFERENCES ORGANIZATION(ORGANIZATION_ID),
  FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
