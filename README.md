# Home-Loan-Management-System
Created an home loan management system application in java
Project Topic: Home Loan Automation System 
<h1>Please edit your mysql username and password in resourses/database.properties</h1>
Problem Statement: Loan Management System to manage loan details of customer and perform Loan related operations on Loan Details provided by end user.
Project databases:
Database name:loan
Data Base Tables:
<br>
1:LOANUSERS
<b>CREATE TABLE `loanusers` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `accountnumber` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`accountnumber`)
)</b> 
<br>
2:LOAN DETAILS
<b>CREATE TABLE `loandetails` (
  `accountnumber` int(11) NOT NULL,
  `loanamount` double NOT NULL,
  `loanperiod` int(11) NOT NULL,
  `loanremamount` double NOT NULL,
  `loanremperiod` int(11) NOT NULL,
  `loanmonthlyamt` double NOT NULL,
  `loantype` varchar(45) NOT NULL,
  KEY `accountnumber_idx` (`accountnumber`),
  CONSTRAINT `accountnumber` FOREIGN KEY (`accountnumber`) REFERENCES `loanusers` (`accountnumber`) ON DELETE NO ACTION ON UPDATE NO ACTION
);</b>
<br>
3:LOANTRANSACTIONS
<b>CREATE TABLE `loan`.`loantransactions` (
  `accountnumber` INT,
  `transactionamount` double,
    FOREIGN KEY (`accountnumber`)
    REFERENCES `loan`.`loanusers` (`accountnumber`)
    );
</b>
