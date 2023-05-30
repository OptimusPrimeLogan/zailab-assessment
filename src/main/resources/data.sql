DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS account;

CREATE TABLE account
(
    id               integer PRIMARY KEY AUTO_INCREMENT,
    CUSTOMER_NUMBER   BIGINT,
    balance          BIGINT,
    OVER_DRAFT_BALANCE  BIGINT,
    type            VARCHAR(10)
);

CREATE TABLE transaction
(
    id      integer PRIMARY KEY,
    account integer,
    type    VARCHAR(10)
);

ALTER TABLE transaction
    ADD FOREIGN KEY (account) REFERENCES account (id);

-- a. Savings account (customerNum=1, balance = R2000)
-- b. Savings account (customerNum=2, balance = R5000)
-- c. Current account (customerNum=3, positive balance = R1000, overdraft = R10000):
-- d. Current account (customerNum=4, negative balance = R5000, overdraft = R20000)
INSERT INTO account (CUSTOMER_NUMBER, balance, OVER_DRAFT_BALANCE, type)
VALUES (1, 2000, 0, 'SAVINGS');
INSERT INTO account (CUSTOMER_NUMBER, balance, OVER_DRAFT_BALANCE, type)
VALUES (2, 5000, 0, 'SAVINGS');
INSERT INTO account (CUSTOMER_NUMBER, balance, OVER_DRAFT_BALANCE, type)
VALUES (3, 1000, 10000, 'CURRENT');
INSERT INTO account (CUSTOMER_NUMBER, balance, OVER_DRAFT_BALANCE, type)
VALUES (4, -5000, 20000, 'CURRENT');