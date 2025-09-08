-- JDBCTest > script.sql

show user;

alter session set "_ORACLE_SCRIPT" = true;

create user server identified by java1234;
grant connect, resource, dba to server;
alter user server default tablespace users; --hr > users

drop table tblAddress;
drop sequence seqAddress;

create table tblAddress (
    seq number primary key,
    name varchar2(30) not null,
    age number(3) not null check(age between 0 and 120),
    gender char(1) not null check(gender in ('m', 'f')),
    tel varchar2(15) not null,
    address varchar2(300) not null,
    regdate date default sysdate not null
);

create sequence seqAddress;


--업무 sql 미리 작성해두면
-- 1. DB테스트 용이
-- 2. JDBC 작업 용이

--CRUD
SELECT * FROM tblAddress;


insert into tblAddress(seq, name, age, gender, tel, address, regdate) values(seqAddress.nextVal, '홍길동', 20, 'm', '010-1234-5678', '서울시 강남구 대치동', default);
    
UPDATE tblAddress SET age = age + 1 WHERE seq = 1;

--DELETE FROM tblAddress WHERE seq = 21;
--DELETE FROM tblAddress WHERE seq = 22;
--DELETE FROM tblAddress WHERE seq = 23;

SELECT count(*) AS count FROM tblAddress;

SELECT name FROM tblAddress WHERE seq = 24;

SELECT * FROM TBLADDRESS WHERE seq = 24;

SELECT name FROM TBLADDRESS;



--Ex07_CallableStatement
--m1() 인자값x, 반환값x
CREATE OR REPLACE PROCEDURE procM1
IS
BEGIN
	UPDATE tblAddress SET age = age + 1 WHERE seq=104;
END procM1;

SELECT * FROM tblAddress ORDER BY seq ASC;


--m2() 인자값O, 반환값x
CREATE OR REPLACE PROCEDURE procM2 (
	pname varchar2,
	page NUMBER,
	pgender varchar2,
	ptel varchar2,
	paddress varchar2
)
IS
BEGIN
	insert into tblAddress(seq, name, age, gender, tel, address, regdate) values(seqAddress.nextVal, pname, page, pgender, ptel, paddress, default);
END procM2;

--m3() 인자값X, 반환값O(out 파라미터)
CREATE OR REPLACE PROCEDURE procM3 (
	pcount OUT NUMBER
)
IS
BEGIN
	SELECT count(*) INTO pcount FROM tblAddress;
END procM3;


--m4() 인자값O, 반환값O(out 파라미터)
CREATE OR REPLACE PROCEDURE procM4 (
	pseq 		IN NUMBER,
	pname		OUT varchar2,
	page 		OUT NUMBER,
	pgender 	OUT varchar2,
	ptel		OUT varchar2,
	paddress	OUT varchar2
)
IS
BEGIN
	SELECT name, age, gender, tel, address
		INTO pname, page, pgender, ptel, paddress
		FROM tblAddress WHERE seq = pseq;
END procM4;

--m5() 다중 레코드 반환 -> 커서 반환
CREATE OR REPLACE PROCEDURE procM5 (
	pgender		IN varchar2,
	pcursor		OUT sys_refcursor
)
IS
BEGIN
	OPEN pcursor
	FOR
	SELECT * FROM tblAddress WHERE gender = pgender;
END procM5;


