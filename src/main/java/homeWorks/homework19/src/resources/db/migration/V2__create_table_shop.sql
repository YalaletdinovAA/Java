CREATE TABLE shop.customer (
id SERIAL PRIMARY KEY,
first_name VARCHAR(100),
last_name VARCHAR(100)
);

CREATE TABLE shop.orders (
id SERIAL PRIMARY KEY,
customer_id INTEGER NOT NULL,
order_date TIMESTAMP,
total_amount DECIMAL,
discount DECIMAL,
FOREIGN KEY (customer_id) REFERENCES shop.customer(id) ON DELETE CASCADE
);