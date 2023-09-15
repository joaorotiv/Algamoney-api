CREATE TABLE launch(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    due_date DATE NOT NULL,
    payment_date DATE,
    price DECIMAL(10,2) NOT NULL,
    observation VARCHAR(100),
    type VARCHAR(20) NOT NULL,
    category_id BIGINT(20) NOT NULL,
    person_id BIGINT(20) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categorias(id),
    FOREIGN KEY (person_id) REFERENCES person(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;