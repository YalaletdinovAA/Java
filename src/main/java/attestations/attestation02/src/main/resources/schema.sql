-- Создание таблицы "Товар" для хранения информации о товарах
CREATE TABLE IF NOT EXISTS product (
id SERIAL PRIMARY KEY,
description TEXT NOT NULL,  -- описание товара
price DECIMAL(10, 2) NOT NULL,  -- стоимость товара
quantity INTEGER NOT NULL  -- количество товара на складе
);

COMMENT ON TABLE product IS 'Таблица для хранения информации о товарах интернет-магазина';
COMMENT ON COLUMN product.id IS 'Уникальный идентификатор товара';
COMMENT ON COLUMN product.description IS 'Наименование и описание товара';
COMMENT ON COLUMN product.price IS 'Цена товара в рублях';
COMMENT ON COLUMN product.quantity IS 'Количество единиц товара на складе';

-- Создание таблицы "Покупатель" для хранения информации о покупателях
CREATE TABLE IF NOT EXISTS customer (
id SERIAL PRIMARY KEY,
full_name TEXT NOT NULL  -- имя и фамилия покупателя
);

COMMENT ON TABLE customer IS 'Таблица для хранения информации о покупателях';
COMMENT ON COLUMN customer.id IS 'Уникальный идентификатор покупателя';
COMMENT ON COLUMN customer.full_name IS 'Полное имя покупателя (Фамилия Имя)';


-- Создание таблицы "Заказ" для хранения информации о заказах
-- Используем название "orders" вместо "order", так как "order" - зарезервированное слово
CREATE TABLE IF NOT EXISTS orders (
id SERIAL PRIMARY KEY,
product_id INTEGER NOT NULL,  -- ID товара (внешний ключ)
customer_id INTEGER NOT NULL,  -- ID покупателя (внешний ключ)
order_date DATE NOT NULL,  -- дата заказа
quantity INTEGER NOT NULL,  -- количество заказанных товаров
FOREIGN KEY (product_id) REFERENCES product(id),
FOREIGN KEY (customer_id) REFERENCES customer(id)
);

COMMENT ON TABLE orders IS 'Таблица для хранения информации о заказах покупателей';
COMMENT ON COLUMN orders.id IS 'Уникальный идентификатор заказа';
COMMENT ON COLUMN orders.product_id IS 'Ссылка на товар в заказе';
COMMENT ON COLUMN orders.customer_id IS 'Ссылка на покупателя';
COMMENT ON COLUMN orders.order_date IS 'Дата оформления заказа';
COMMENT ON COLUMN orders.quantity IS 'Количество заказанных единиц товара';

-- Заполнение таблицы "Товар" тестовыми данными
INSERT INTO product (description, price, quantity) VALUES
('Ноутбук', 999.99, 15),
('Смартфон', 699.99, 30),
('Наушники', 149.99, 50),
('Клавиатура', 59.99, 40),
('Мышь', 29.99, 60),
('Монитор', 249.99, 20),
('Принтер', 199.99, 10),
('Флеш-накопитель 32GB', 19.99, 100),
('Внешний жесткий диск 1TB', 89.99, 25),
('Кабель HDMI', 14.99, 75);

-- Заполнение таблицы "Покупатель" тестовыми данными
INSERT INTO customer (full_name) VALUES
('Иван Иванов'),
('Петр Петров'),
('Сергей Сергеев'),
('Анна Сидорова'),
('Мария Кузнецова'),
('Алексей Алексеев'),
('Елена Еленова'),
('Дмитрий Дмитриев'),
('Ольга Ольгова'),
('Николай Николаев');

-- Заполнение таблицы "Заказ" тестовыми данными
INSERT INTO orders (product_id, customer_id, order_date, quantity) VALUES
(1, 1, '2023-01-15', 1),
(2, 2, '2023-01-16', 2),
(3, 3, '2023-01-17', 1),
(4, 4, '2023-01-18', 1),
(5, 5, '2023-01-19', 3),
(6, 6, '2023-01-20', 1),
(7, 7, '2023-01-21', 1),
(8, 8, '2023-01-22', 5),
(9, 9, '2023-01-23', 1),
(10, 10, '2023-01-24', 2);