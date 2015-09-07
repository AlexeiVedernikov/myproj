CREATE TABLE GROUP_USER (
  GROUP_ID varchar(32) NOT NULL,
  USER_ID varchar(32) NOT NULL,
  FOREIGN KEY (GROUP_ID) REFERENCES GROUPS(GROUP_ID),
  FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;