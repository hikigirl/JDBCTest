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
insert into tblAddress(seq, name, age, gender, tel, address, regdate)
    values(seqAddress.nextVal, '홍길동', 20, 'm', '010-1234-5678', '서울시 강남구 대치동', default);
    
select * from tblAddress;

update tblAddress set age = age + 1 where seq = 1;

