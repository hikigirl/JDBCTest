/*
 * hr.tblInsa
 * 1. 어떤 부서들이 있는지 출력 -> 7개
 * 2. 사용자 -> 부서명 1개를 입력
 * 3. 해당 부서의 직원 목록 출력(num, name, jikwi, basicpay)
*/
SELECT * FROM TBLINSA;
SELECT DISTINCT buseo FROM TBLINSA;
SELECT count(DISTINCT buseo) FROM TBLINSA;
SELECT num, name, jikwi, basicpay FROM TBLINSA WHERE BUSEO='총무부';