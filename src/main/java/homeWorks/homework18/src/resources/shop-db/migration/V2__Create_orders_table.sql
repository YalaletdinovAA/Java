CREATE TABLE orders (
id SERIAL PRIMARY KEY,
customer_id INTEGER NOT NULL,
order_date TIMESTAMP,
total_amount DECIMAL,
discount DECIMAL,
FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);