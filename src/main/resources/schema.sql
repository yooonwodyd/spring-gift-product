CREATE TABLE IF NOT EXISTS products (
                                        id BIGINT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
    price BIGINT NOT NULL,
    imageUrl VARCHAR(255)
    );