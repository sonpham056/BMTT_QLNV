create database qlnv;
use qlnv;

Create table User
(
	userId Integer auto_increment NOT NULL,
	authorizationId Integer NOT NULL,
	roleId Integer NOT NULL,
	email Varchar(50) NOT NULL,
	password Varchar(20) NOT NULL,
	name Nvarchar(50) NULL,
	lastName Nvarchar(50) NULL,
	dateOfBirth Datetime NULL,
	isFollowedByAdmin Bit NULL,
Primary Key (userId)
) ;


Create table Role
(
	roleId Integer auto_increment NOT NULL,
	roleName Nvarchar(50) NOT NULL,
Primary Key (roleId)
) ;


Create table Audit
(
	auditId Integer auto_increment NOT NULL,
	type Nvarchar(50) NULL,
Primary Key (auditId)
) ;


Create table AuthorizationTable
(
	authorizationId Integer auto_increment NOT NULL,
	report Bit NOT NULL,
	userInfo Bit NOT NULL,
    timeKeeping Bit NOT NULL,
Primary Key (authorizationId)
) ;


Create table Report
(
	reportId Integer auto_increment NOT NULL,
	sender Integer NOT NULL,
	receiver Integer NOT NULL,
	title Nvarchar(100) NULL,
	content Nvarchar(1000) NULL,
	sendDate Datetime NULL,
	isRead Bit NULL,
Primary Key (reportId)
) ;


Create table AuditHistory
(
	auditHistoryId Integer auto_increment NOT NULL,
	userId Integer NOT NULL,
	auditId Integer NOT NULL,
	auditTime Datetime NULL,
Primary Key (auditHistoryId)
) ;


Create table TimeKeeping
(
	timeKeepingId Integer auto_increment NOT NULL,
	startTime Datetime NULL,
	endTime Datetime NULL,
	userId Integer NOT NULL,
Primary Key (timeKeepingId)
) ;



Alter table Report add  foreign key(sender) references User (userId)  on update cascade on delete cascade ;

Alter table Report add  foreign key(receiver) references User (userId)  on update cascade on delete cascade ;

Alter table AuditHistory add  foreign key(userId) references User (userId)  on update cascade on delete cascade ;

Alter table TimeKeeping add  foreign key(userId) references User (userId)  on update cascade on delete cascade ;

Alter table User add  foreign key(roleId) references Role (roleId)  on update cascade on delete cascade ;

Alter table AuditHistory add  foreign key(auditId) references Audit (auditId)  on update cascade on delete cascade ;

Alter table User add  foreign key(authorizationId) references AuthorizationTable (authorizationId)  on update cascade on delete cascade ;

INSERT INTO `audit` VALUES (1,'login'),(2,'logout'),(3,'insert'),(4,'update'),(5,'delete'),(6,'select'),(7,'start working'),(8,'end working'),(9,'send report'),(10,'read report'),(11,'change password');
INSERT INTO `role` VALUES (1,'admin'),(2,'user');



