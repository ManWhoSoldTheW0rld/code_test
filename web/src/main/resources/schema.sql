CREATE TABLE tournament(
  id INT PRIMARY KEY AUTO_INCREMENT,
  customer VARCHAR(255) Not NULL,
  total_loan DECIMAL(10, 2) NOT NULL,
  interest DECIMAL(10, 2)  NOT NULL,
  years INTEGER NOT NULL,
  monthly_payment DECIMAL(10, 2) NOT NULL
);