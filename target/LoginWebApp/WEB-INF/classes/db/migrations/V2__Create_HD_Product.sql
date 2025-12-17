CREATE TABLE HD_Product
(
    product_id          INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    product_name        VARCHAR(50)   NOT NULL,
    product_description VARCHAR(255)  NOT NULL,
    product_color       VARCHAR(20)   NOT NULL,
    product_size        VARCHAR(20)   NOT NULL,
    product_price       DECIMAL(8, 2) NOT NULL
);
