-- 신고테이블
CREATE TABLE REPORT(
    SID INT PRIMARY KEY,
    BID INT NOT NULL,
    RID INT DEFAULT 0
);

SELECT * FROM REPORT;
DROP TABLE REPORT;