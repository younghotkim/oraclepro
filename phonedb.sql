DROP TABLE person;

DROP SEQUENCE seq_person_id;

CREATE TABLE person (
            person_id NUMBER(5) PRIMARY KEY,
            name VARCHAR2(30) NOT NULL,
            hp VARCHAR2(20),
            company VARCHAR2(20)
            );
            
CREATE SEQUENCE seq_person_id
    INCREMENT BY 1
    START WITH 1
    NOCACHE;
    
INSERT INTO person VALUES(seq_person_id.NEXTVAL, 
                        '이효리', '010-1111-1111', '02-1111-1111');
                        
INSERT INTO person VALUES(seq_person_id.NEXTVAL, 
                        '정우성', '010-2222-2222', '02-2222-2222');
                        
INSERT INTO person VALUES(seq_person_id.NEXTVAL, 
                        '유재석', '010-3333-3333', '02-3333-3333'); 

INSERT INTO person VALUES(seq_person_id.NEXTVAL, 
                        '이정재', '010-4444-4444', '02-4444-4444');

INSERT INTO person VALUES(seq_person_id.NEXTVAL, 
                        '서장훈', '010-5555-5555', '02-5555-5555');
                     
commit;                    
                
UPDATE person
SET name = '유정재',
    hp = '010-9999-9999',
    company = '02-9999-9999'
WHERE person_id = 4; 


DELETE FROM person
WHERE person_id = 5;

SELECT person_id,
       name,
       hp,
       company
FROM person;

SELECT person_id,
       name,
       hp,
       company
FROM person
WHERE name LIKE '%이%'
or hp LIKE '%1%'
or company LIKE '%1%';
