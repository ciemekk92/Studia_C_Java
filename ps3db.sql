--1. Podać nazwiska osób, których pensja i stanowisko są takie same jak p. Forda.
SELECT EMPNO, ENAME, SAL
FROM EMP
WHERE SAL=(SELECT SAL
            FROM EMP
            WHERE ENAME='FORD')
AND JOB=(SELECT JOB FROM EMP WHERE ENAME='FORD');

--2. Podać nazwisko, stanowisko, wynagrodzenia osób mających pensję > od p.
--Millera i < od p. Forda.
SELECT ENAME, JOB, SAL+COMM, SAL, COMM
FROM EMP
WHERE SAL+NVL(COMM, 0)<(SELECT SAL FROM EMP WHERE INITCAP(ENAME)='Ford')
AND SAL+NVL(COMM, 0)>(SELECT SAL FROM EMP WHERE INITCAP(ENAME)='Miller');

--3. Podać nazwisko, stopień wynagrodzenia osób, których wynagrodzenie jest inne
--niż wynosi średnie wynagrodzenie osób na stanowisku Clerk.
SELECT ENAME, GRADE, SAL+COMM, SAL, COMM
FROM EMP, SALGRADE
WHERE SAL+NVL(COMM, 0)!= (SELECT AVG(NVL(SAL,0) + NVL(COMM,0)) FROM EMP WHERE INITCAP(JOB)='Clerk')
AND SAL>=LOSAL AND SAL <=HISAL;

--4. Podać nazwisko i wynagrodzenie zwierzchnika p. Adams.
SELECT ENAME, NVL(SAL, 0)+NVL(COMM, 0) AS SALARY 
FROM EMP
WHERE EMPNO=(SELECT MGR FROM EMP WHERE INITCAP(ENAME)='Adams');

--5. Podać nazwę projektu realizowanego więcej razy niż projekt o numerze 1001.
SELECT P.PRONAME
FROM PROJECT P, IMPLPROJECT IP
WHERE P.PRONO=IP.PRONO
GROUP BY P.PRONO, P.PRONAME
HAVING COUNT(1)>(SELECT COUNT(1) FROM IMPLPROJECT WHERE PRONO=1002);

--6. Podać nazwiska pracowników, którzy brali więcej razy udział w realizacji
--projektów niż którykolwiek pracownik z departamentu 30 .
SELECT E.ENAME, COUNT(1)
FROM EMP E, IMPLEMP IM
WHERE E.EMPNO=IM.EMPNO
GROUP BY E.EMPNO, E.ENAME
HAVING COUNT(1)>
        (SELECT MIN(COUNT(1)) 
        FROM EMP E, IMPLEMP IM 
        WHERE E.EMPNO=IM.EMPNO 
        AND E.DEPTNO=30 
        GROUP BY E.EMPNO);

--7. Podać nazwisko, stanowisko, wynagrodzenie osób zarabiających więcej niż
--wynoszą średnie zarobki na ich stanowisku.
SELECT E1.ENAME, E1.JOB, E1.SAL
FROM EMP E1
WHERE SAL > (SELECT AVG(SAL) 
            FROM EMP E2 
            WHERE E2.JOB = E1.JOB);

--8. Dla każdego departamentu podać pracownika najwięcej razy realizującego
--projekty.
SELECT ENAME, DEPTNO, COUNT(*)
FROM EMP E1, IMPLEMP IM1
WHERE E1.EMPNO=IM1.EMPNO
GROUP BY DEPTNO, E1.EMPNO, ENAME
HAVING COUNT(*) = (SELECT MAX(COUNT(*))
                    FROM IMPLEMP IM2, EMP E2
                    WHERE IM2.EMPNO=E2.EMPNO
                    AND E1.DEPTNO = E2.DEPTNO
                    GROUP BY E2.EMPNO)
ORDER BY DEPTNO;

--9. Podać nazwy stanowisk na których pracuje nie mniej niż 2 pracowników i nie
--więcej niż 4.
--10. Podać nazwy projektów, których wartość realizacji była większa w 2008r niż
--wynosi maksymalna realizacje projektów w roku 2009.
--11. Podać nazwy projektów, dla których różnica dat rozpoczęcia i zakończenia
--projektu była większa lub równa 3 miesiące.
--12. Podać nazwy departamentów z których pracownicy na stanowisku MANAGER
--realizowali najmniej projektów spośród pracowników na stanowisku MANAGER.
