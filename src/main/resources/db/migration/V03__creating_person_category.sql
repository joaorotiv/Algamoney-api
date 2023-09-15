CREATE TABLE person(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    place VARCHAR(30),
    house_number VARCHAR(10),
    complement VARCHAR(30),
    neighborhood VARCHAR(20),
    postal_code VARCHAR(10),
    city VARCHAR(20),
    state VARCHAR(15),
    activity BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;