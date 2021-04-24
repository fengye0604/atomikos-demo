--创建表
create table T_ACCOUNT
(
  ID      CHAR(20),
  MONEY   VARCHAR2(500),
  USER_ID VARCHAR2(50)
)

--创建序列
create sequence SEQ_T_ACCOUNT
minvalue 10000000
maxvalue 90000000
start with 10000000
increment by 1
nocache;