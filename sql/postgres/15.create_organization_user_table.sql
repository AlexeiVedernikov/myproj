CREATE TABLE ORGANIZATION_USER (
  ORGANIZATION_ID character varying(32) NOT NULL REFERENCES ORGANIZATION(ORGANIZATION_ID),
  USER_ID character varying(32) NOT NULL REFERENCES USERS(USER_ID)
) WITH (OIDS=FALSE);