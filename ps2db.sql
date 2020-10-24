--1. Sprawdzić działanie funkcji matematycznych POWER, SQRT, ABS, MOD.
SELECT POWER(2,3) 
FROM DUAL;

SELECT SQRT(100)
FROM DUAL;

SELECT ABS(-542) 
FROM DUAL;

SELECT MOD(15,7)
FROM DUAL;

--2. Wyświetlić wynagrodzenia pracowników podniesione do kwadratu.
SELECT POWER(SAL, 2) 
FROM EMP, DUAL;

--3. Jaka data będzie za 100 dni.
SELECT SYSDATE + 100
FROM DUAL;
--4. Ile miesięcy pracują poszczególni pracownicy.

SELECT ENAME, TRUNC(MONTHS_BETWEEN(TO_DATE('2020-10-17', 'yyyy-mm-dd'), HIREDATE)), HIREDATE
FROM EMP;

--5. Jaką datę będziemy mieli za 10 miesięcy.
SELECT ADD_MONTHS(SYSDATE, 10)
FROM DUAL;

--6. Podać datę ostatniego dnia bieżącego miesiąca.
SELECT LAST_DAY(SYSDATE)
FROM DUAL;

--7. Podać nazwiska, wynagrodzenia, stopnie wynagrodzenia pracowników
--zatrudnionych w 1980 roku.
SELECT ENAME, SAL, HIREDATE, GRADE
FROM EMP, SALGRADE
WHERE EXTRACT(YEAR FROM HIREDATE)=1980 AND SAL > LOSAL AND SAL < HISAL;

--8. Podać nazwy projektów realizowanych w miesiącu styczniu.
SELECT PRONAME,START_DATE, END_DATE, PROJECT.PRONO, IMPLPROJECT.PRONO
FROM PROJECT, IMPLPROJECT
WHERE PROJECT.PRONO=IMPLPROJECT.PRONO AND EXTRACT(MONTH FROM START_DATE)=1 AND EXTRACT(MONTH FROM END_DATE)=1;

--9. Podać nazwiska pracowników realizujących projekty w pierszym kwartale 2008
--roku.


--10. Pobrać 4 pierwsze rekordy z tabeli z widełkami wynagrodzeń. Sprawdzić
--wpływ klauzuli Order by na wynik zapytania.
SELECT ENAME, LOSAL, SAL, HISAL
FROM EMP, SALGRADE
WHERE SAL>= LOSAL AND SAL <=HISAL AND ROWNUM<=4
ORDER BY ENAME;

--11. Zamienić wszystkie literki E w imionach pracowników na a przy pomocy
--funkcji translate.
SELECT ENAME, TRANSLATE(ENAME, 'E', 'a')
FROM EMP;

--12. Uzupełnić z prawej strony wynik kolumny dname znakami x do 15 znaków w
--polu, kolumnę loc uzupełnić z lewej strony znakiem”-„.
SELECT RPAD(DNAME,15,'x'), LPAD(LOC,15,'-')
FROM DEPT;

--13. Dla każdego pracownika podać lokalizację jego departamentu z pominiętym
--ostatnim znakiem.

SELECT ENAME, LOC, SUBSTR(LOC,0,LENGTH(LOC)-1)
FROM EMP e, DEPT d
WHERE e.DEPTNO=d.DEPTNO;
