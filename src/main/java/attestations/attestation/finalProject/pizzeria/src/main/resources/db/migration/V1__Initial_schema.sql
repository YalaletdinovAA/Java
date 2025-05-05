-- Create customer table
CREATE TABLE customer (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(100) NOT NULL,
phone VARCHAR(20) NOT NULL,
address VARCHAR(255) NOT NULL,
email VARCHAR(100),
active BOOLEAN DEFAULT TRUE
);

-- Create pizza table
CREATE TABLE pizza (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(100) NOT NULL UNIQUE,
description VARCHAR(255),
price NUMERIC(10, 2) NOT NULL,
available BOOLEAN DEFAULT TRUE
);

-- Create pizza_orders table
CREATE TABLE pizza_orders (
id BIGSERIAL PRIMARY KEY,
customer_id BIGINT NOT NULL REFERENCES customer(id),
order_date TIMESTAMP NOT NULL,
delivery_address VARCHAR(255) NOT NULL,
status VARCHAR(20) NOT NULL,
total_price NUMERIC(10, 2) NOT NULL,
active BOOLEAN DEFAULT TRUE
);

-- Create order_item table
CREATE TABLE order_item (
id BIGSERIAL PRIMARY KEY,
order_id BIGINT NOT NULL REFERENCES pizza_orders(id),
pizza_id BIGINT NOT NULL REFERENCES pizza(id),
quantity INTEGER NOT NULL,
item_price NUMERIC(10, 2) NOT NULL
);

-- Create indexes
CREATE INDEX idx_customer_active ON customer(active);
CREATE INDEX idx_pizza_available ON pizza(available);
CREATE INDEX idx_order_active ON pizza_orders(active);
CREATE INDEX idx_order_customer ON pizza_orders(customer_id);
CREATE INDEX idx_order_item_order ON order_item(order_id);