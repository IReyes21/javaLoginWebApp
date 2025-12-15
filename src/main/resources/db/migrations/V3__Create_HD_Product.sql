CREATE TABLE HD_Product (
    product_id INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    product_name VARCHAR(100) NOT NULL,
    product_description VARCHAR(500),
    product_color VARCHAR(50),
    product_size VARCHAR(20),
    product_price DECIMAL(10, 2) NOT NULL
);

INSERT INTO HD_Product (product_name, product_description, product_color, product_size, product_price)
VALUES
    ('Nike Air Max', 'Running shoes with air cushioning', 'Black/White', '10', 129.99),
    ('Adidas Ultraboost', 'High-performance running shoes', 'White', '9.5', 179.99),
    ('Converse Chuck Taylor', 'Classic canvas sneakers', 'Red', '11', 59.99);
