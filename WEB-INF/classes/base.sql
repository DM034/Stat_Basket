DROP TABLE Match cascade;
DROP TABLE Joueur cascade;
DROP TABLE Passe cascade;
DROP TABLE Tir cascade;
DROP TABLE Rebond;
DROP TABLE duree;

CREATE DATABASE BasketPro;

\c Basket;

CREATE TABLE Match(
    id VARCHAR PRIMARY KEY
);

CREATE TABLE Joueur (
    id INTEGER,
    name VARCHAR,
    idMatch VARCHAR,
    FOREIGN KEY (idMatch) REFERENCES Match(id)
);

CREATE TABLE Passe (
    idP1 INTEGER,
    idP2 INTEGER,
    type VARCHAR,
    idMatch VARCHAR,
    FOREIGN KEY (idMatch) REFERENCES Match(id)
);

CREATE TABLE Tir(
    idP INTEGER,
    point INTEGER,
    idMatch VARCHAR,
    FOREIGN KEY (idMatch) REFERENCES Match(id)
);

CREATE TABLE Rebond(
    idP INTEGER,
    value VARCHAR,
    idMatch VARCHAR,
    FOREIGN KEY (idMatch) REFERENCES Match(id)
);

CREATE TABLE duree(
    idp INTEGER,
    duree INTEGER,
    idMatch VARCHAR,
    FOREIGN KEY (idMatch) REFERENCES Match(id)
);

ALTER TABLE duree ADD COLUMN quart_tps INTEGER;
ALTER TABLE Match ADD COLUMN quart_tps INTEGER;
ALTER TABLE Passe ADD COLUMN quart_tps INTEGER;
ALTER TABLE Tir ADD COLUMN quart_tps INTEGER;
ALTER TABLE Rebond ADD COLUMN quart_tps INTEGER;

CREATE SEQUENCE idMatch_seq INCREMENT BY 1 START WITH 1 MINVALUE 1 OWNED BY Match.id;

ALTER SEQUENCE idMatch_seq RESTART WITH 1;

SELECT Joueur.id,Joueur.name,sum(tir.point),Joueur.idMatch,count(tir.point), tir.quart_tps FROM tir
    RIGHT OUTER JOIN Joueur on tir.idP=Joueur.id and tir.idMatch=Joueur.idMatch
    WHERE Joueur.idMatch='M0002' AND tir.quart_tps='1'
    GROUP BY Joueur.id,Joueur.name,Joueur.idMatch,tir.quart_tps
    ORDER BY Joueur.id;

-- alter table passe add column duree int;
-- alter table tir add column duree int;

SELECT Joueur.id,Joueur.name,sum(tir.duree),Joueur.idMatch,count(tir.point) FROM tir LEFT OUTER JOIN Joueur on tir.idP=Joueur.id and tir.idMatch=Joueur.idMatch WHERE Joueur.idMatch='M0001' GROUP BY Joueur.id,Joueur.name,Joueur.idMatch ORDER BY Joueur.id;

CREATE VIEW dureetir as SELECT sum(tir.duree),Joueur.idMatch FROM tir RIGHT OUTER JOIN Joueur on tir.idP=Joueur.id and tir.idMatch=Joueur.idMatch  GROUP BY Joueur.idMatch;

CREATE VIEW dureetpasse as SELECT sum(passe.duree),Joueur.idMatch FROM passe RIGHT OUTER JOIN Joueur on passe.idP2=Joueur.id and passe.idMatch=Joueur.idMatch  GROUP BY Joueur.idMatch;

SELECT distinct(idP2),((sum(passe.duree)+sum(tir.duree))) from Passe
        RIGHT OUTER JOIN tir on passe.idP2=tir.idP and passe.idMatch=tir.idMatch
        where passe.idMatch='M0011'
        GROUP BY passe.idP2
        
CREATE VIEW timepasse AS  SELECT distinct(idP2),sum(passe.duree),idMatch from passe group by passe.idP2,idMatch; 

CREATE VIEW timetir AS  SELECT distinct(idP),sum(tir.duree),idMatch from tir group by tir.idP,idMatch; 

SELECT Joueur.id,Joueur.name,timetir.sum,timepasse.sum from Joueur
        LEFT OUTER JOIN timetir on timetir.idp=Joueur.id and timetir.idMatch=joueur.idMatch
        LEFT OUTER JOIN timepasse on timepasse.idp2=Joueur.id and timepasse.idMatch=joueur.idMatch 
        WHERE joueur.idMatch='M0012' 

SELECT Joueur.id,Joueur.name,timetir.sum from Joueur
        LEFT OUTER JOIN timetir on timetir.idp=Joueur.id and timetir.idMatch=joueur.idMatch
        WHERE joueur.idMatch='M0011'

SELECT Joueur.id,Joueur.name,timepasse.sum from Joueur
            LEFT OUTER JOIN timepasse on timepasse.idp2=Joueur.id and timepasse.idMatch=joueur.idMatch
            WHERE joueur.idMatch='M0011'

SELECT timetir.idP,timepasse.idP2,timetir.sum,timepasse.sum from timepasse 
            JOIN timetir on timetir.idp=timepasse.idp2 and timepasse.idMatch=timetir.idMatch
            where timepasse.idMatch='M0011' and timetir.idP='9';

SELECT sum(duree.duree) from duree where idmatch='M0015';

SELECT sum(duree.duree) from duree where idmatch='M0015' and idp<5;

SELECT sum(duree.duree) from duree where idmatch='M0015' and idp>=5;

SELECT idp,sum(duree) from duree where idmatch='M0015' group by idp;

select distinct quart_tps from duree where idmatch='M0001' and quart_tps!=0;

select sum(point) from tir  WHERE idMatch='M0004' and idP='0' and quart_tps='1';

select count(point) from tir  WHERE idMatch='M0002' and idP='0' and quart_tps='1';

select distinct(value),count(value),joueur.name,joueur.id from rebond JOIN joueur on joueur.id=rebond.idP and rebond.idMatch=joueur.idMatch where rebond.idMatch='M0002' and quart_tps='1' group by value,rebond.idP,joueur.name,joueur.id

create view bestShooter as select idp,sum(point),idMatch,quart_tps from tir where point != 0 group by idp,idMatch,quart_tps;

select tir.idp,max(bestshooter.count) from tir join bestshooter on tir.idp = bestshooter.idp group by tir.idp;

select count(idp) from bestshooter;

select joueur.name,tir.idp,max(bestshooter.count) from tir join bestshooter on tir.idp = bestshooter.idp join joueur on tir.idp = joueur.id where tir.quart_tps = '1' and tir.idMatch='M0007' group by joueur.name,tir.idp;

select idp,sum(duree.duree) from duree  WHERE idMatch='M0013' and quart_tps='1' group by idp;

 select joueur.name,duree.idp,sum(duree.duree) from duree join joueur on joueur.id = duree.idp  WHERE idMatch='M0013' and quart_tps='1' group by joueur.name,duree.idp order by sum desc limit 1;