-- USER SQL
CREATE USER "CP" IDENTIFIED BY CP
DEFAULT TABLESPACE "USERS"
TEMPORARY TABLESPACE "TEMP"
ACCOUNT UNLOCK ;

-- QUOTAS
ALTER USER "CP" QUOTA UNLIMITED ON USERS;

-- ROLES
ALTER USER "CP" DEFAULT ROLE "CONNECT","RESOURCE";

-- SYSTEM PRIVILEGES
GRANT INSERT ANY TABLE TO "CP" ;
GRANT SELECT ANY TABLE TO "CP" ;
GRANT SELECT ANY SEQUENCE TO "CP";
--------------------------------------------------------
--  DDL for Table ACCOUNTS
--------------------------------------------------------

  CREATE TABLE "CP"."ACCOUNTS1" 
   (	"ID" NUMBER(*,0) DEFAULT cp.accounts_id_seq.nextval, 
	"ACCNUM" NUMBER(9,0) DEFAULT CP.ACCNUM_SEQ.nextval, 
	"ADDRESS" VARCHAR2(200 BYTE), 
	"PERSON_ID" NUMBER DEFAULT NULL
   ); 
  
   COMMENT ON COLUMN "CP"."ACCOUNTS1"."ID" IS 'Уникальный идентификатор записи';
   COMMENT ON COLUMN "CP"."ACCOUNTS1"."ACCNUM" IS 'Лицевой счет';
   COMMENT ON COLUMN "CP"."ACCOUNTS1"."ADDRESS" IS 'Адрес объекта';
   COMMENT ON COLUMN "CP"."ACCOUNTS1"."PERSON_ID" IS 'Идентификатор пользователя';
--------------------------------------------------------
--  DDL for Table PAYMENTS
--------------------------------------------------------

  CREATE TABLE "CP"."PAYMENTS" 
   (	"ID" NUMBER(38,0) DEFAULT cp.payments_id_seq.nextval, 
	"ACCOUNT_ID" NUMBER(38,0), 
	"PMT_TIME" TIMESTAMP (6) DEFAULT CURRENT_TIMESTAMP, 
	"SUMM" NUMBER(*,0)
   ) ;

   COMMENT ON COLUMN "CP"."PAYMENTS"."ID" IS 'Уникальный идентификатор записи';
   COMMENT ON COLUMN "CP"."PAYMENTS"."ACCOUNT_ID" IS 'Идентификатор лицевого счета
';
   COMMENT ON COLUMN "CP"."PAYMENTS"."PMT_TIME" IS 'Время совершения платежа';
   COMMENT ON COLUMN "CP"."PAYMENTS"."SUMM" IS 'Сумма платежа';
--------------------------------------------------------
--  DDL for Table PERSONS
--------------------------------------------------------

  CREATE TABLE "CP"."PERSONS" 
   (	"FAM" VARCHAR2(20 BYTE), 
	"IM" VARCHAR2(20 BYTE), 
	"OTCH" VARCHAR2(20 BYTE), 
	"PASSPORT" NUMBER(10,0), 
	"ID" NUMBER DEFAULT cp.persons_id_seq.nextval
   ) ;

   COMMENT ON COLUMN "CP"."PERSONS"."FAM" IS 'Фамилия';
   COMMENT ON COLUMN "CP"."PERSONS"."IM" IS 'Имя';
   COMMENT ON COLUMN "CP"."PERSONS"."OTCH" IS 'Отчество';
   COMMENT ON COLUMN "CP"."PERSONS"."PASSPORT" IS 'Серия и номер паспорта цифрами';
   COMMENT ON COLUMN "CP"."PERSONS"."ID" IS 'Уникальный идентификатор записи';
--------------------------------------------------------
--  DDL for Index ACCOUNTS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CP"."ACCOUNTS_PK" ON "CP"."ACCOUNTS" ("ID");
--------------------------------------------------------
--  DDL for Index ACCOUNTS_ACCNUM_IDX
--------------------------------------------------------

  CREATE UNIQUE INDEX "CP"."ACCOUNTS_ACCNUM_IDX" ON "CP"."ACCOUNTS" ("ACCNUM");
--------------------------------------------------------
--  DDL for Index ACCOUNTS_ADDRESS_IDX
--------------------------------------------------------

  CREATE INDEX "CP"."ACCOUNTS_ADDRESS_IDX" ON "CP"."ACCOUNTS" ("ADDRESS");
--------------------------------------------------------
--  DDL for Index ACCOUNTS_PERSONID_IDX
--------------------------------------------------------

  CREATE INDEX "CP"."ACCOUNTS_PERSONID_IDX" ON "CP"."ACCOUNTS" ("PERSON_ID");
--------------------------------------------------------
--  DDL for Index PAYMENTS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CP"."PAYMENTS_PK" ON "CP"."PAYMENTS" ("ID");
--------------------------------------------------------
--  DDL for Index PAYMENTS_ACCNUM_IDX
--------------------------------------------------------

  CREATE INDEX "CP"."PAYMENTS_ACCNUM_IDX" ON "CP"."PAYMENTS" ("ACCOUNT_ID") ;
--------------------------------------------------------
--  DDL for Index PERSON_PASSPORT_IDX
--------------------------------------------------------

  CREATE UNIQUE INDEX "CP"."PERSON_PASSPORT_IDX" ON "CP"."PERSONS" ("PASSPORT");
--------------------------------------------------------
--  DDL for Index PERSON_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "CP"."PERSON_PK" ON "CP"."PERSONS" ("ID");
--------------------------------------------------------
--  DDL for Index PERSON_FIO_IDX
--------------------------------------------------------

  CREATE INDEX "CP"."PERSON_FIO_IDX" ON "CP"."PERSONS" ("FAM", "IM", "OTCH");
--------------------------------------------------------
--  Constraints for Table ACCOUNTS
--------------------------------------------------------

  ALTER TABLE "CP"."ACCOUNTS" MODIFY ("ADDRESS" NOT NULL ENABLE);
  ALTER TABLE "CP"."ACCOUNTS" MODIFY ("PERSON_ID" NOT NULL ENABLE);
  ALTER TABLE "CP"."ACCOUNTS" ADD CONSTRAINT "ACCOUNTS_UK1" UNIQUE ("ACCNUM")
  USING INDEX (CREATE UNIQUE INDEX "CP"."ACCOUNTS_ACCNUM_IDX" ON "CP"."ACCOUNTS" ("ACCNUM") )  ENABLE;
  ALTER TABLE "CP"."ACCOUNTS" MODIFY ("ACCNUM" NOT NULL ENABLE);
  ALTER TABLE "CP"."ACCOUNTS" ADD CONSTRAINT "ACCOUNTS_PK" PRIMARY KEY ("ID")
  USING INDEX ENABLE;
  ALTER TABLE "CP"."ACCOUNTS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PAYMENTS
--------------------------------------------------------

  ALTER TABLE "CP"."PAYMENTS" ADD CONSTRAINT "PAYMENTS_PK" PRIMARY KEY ("ID")
  USING INDEX  ENABLE;
  ALTER TABLE "CP"."PAYMENTS" MODIFY ("SUMM" NOT NULL ENABLE);
  ALTER TABLE "CP"."PAYMENTS" MODIFY ("PMT_TIME" NOT NULL ENABLE);
  ALTER TABLE "CP"."PAYMENTS" MODIFY ("ACCOUNT_ID" NOT NULL ENABLE);
  ALTER TABLE "CP"."PAYMENTS" MODIFY ("ID" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PERSONS
--------------------------------------------------------

  ALTER TABLE "CP"."PERSONS" ADD CONSTRAINT "PERSON_PK" PRIMARY KEY ("ID")
  USING INDEX ENABLE;
  ALTER TABLE "CP"."PERSONS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "CP"."PERSONS" MODIFY ("PASSPORT" NOT NULL ENABLE);
  ALTER TABLE "CP"."PERSONS" ADD CONSTRAINT "PERSON_PASSPORT_IDX" UNIQUE ("PASSPORT")
  USING INDEX ENABLE;
  ALTER TABLE "CP"."PERSONS" MODIFY ("OTCH" NOT NULL ENABLE);
  ALTER TABLE "CP"."PERSONS" MODIFY ("IM" NOT NULL ENABLE);
  ALTER TABLE "CP"."PERSONS" MODIFY ("FAM" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table ACCOUNTS
--------------------------------------------------------

  ALTER TABLE "CP"."ACCOUNTS" ADD CONSTRAINT "ACCOUNTS_FK1" FOREIGN KEY ("PERSON_ID")
	  REFERENCES "CP"."PERSONS" ("ID") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table PAYMENTS
--------------------------------------------------------

  ALTER TABLE "CP"."PAYMENTS" ADD CONSTRAINT "PAYMENTS_FK1" FOREIGN KEY ("ACCOUNT_ID")
	  REFERENCES "CP"."ACCOUNTS" ("ID") ENABLE;
	  
	  
--------------------------------------------------------
--  DDL for Sequence ACCNUM_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "CP"."ACCNUM_SEQ"  MINVALUE 100000000 MAXVALUE 999999999 INCREMENT BY 1 START WITH 100000001 ;
--------------------------------------------------------
--  DDL for Sequence ACCOUNTS_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "CP"."ACCOUNTS_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 ;
--------------------------------------------------------
--  DDL for Sequence PAYMENTS_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "CP"."PAYMENTS_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 ;
--------------------------------------------------------
--  DDL for Sequence PERSONS_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "CP"."PERSONS_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1  ;

