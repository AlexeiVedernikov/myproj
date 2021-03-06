CREATE TABLE AUTH_STATE (
  USER_ID varchar(32) NOT NULL,
  AUTH_STATE decimal(5,1) DEFAULT NULL,
  EXPIRATION bigint(20) DEFAULT NULL,
  IP_ADDRESS varchar(20) DEFAULT NULL,
  LAST_LOGIN datetime DEFAULT NULL,
  TOKEN varchar(100) DEFAULT NULL,
  PRIMARY KEY (USER_ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;