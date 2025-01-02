CREATE TABLE Users(
    userid INT PRIMARY KEY IDENTITY,
    name VARCHAR(255) NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL
);