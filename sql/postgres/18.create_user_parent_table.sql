CREATE TABLE USER_PARENT (
  PARENT_USER_ID character varying(32) NOT NULL REFERENCES USERS(USER_ID),
  USER_ID character varying(32) REFERENCES USERS(USER_ID),
  PARENT_TYPE character varying(32)
) WITH (OIDS=FALSE);
