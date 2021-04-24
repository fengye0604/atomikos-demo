DROP TABLE [COM].[nameDev]
GO
CREATE TABLE [COM].[nameDev] (
[id] bigint NOT NULL IDENTITY(1,1) ,
[nameDev] varchar(20) NULL 
)


--------------------------------------------


DROP TABLE [DL].[nameQa]
GO
CREATE TABLE [DL].[nameQa] (
[id] bigint NOT NULL IDENTITY(1,1) ,
[nameQa] varchar(20) NULL 
)