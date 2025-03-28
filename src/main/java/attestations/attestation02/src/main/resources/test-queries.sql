-- 1. Чтение данных: Вывести все товары дороже 100 рублей
SELECT * FROM product WHERE price > 100.00;

-- 2. Чтение данных: Вывести все заказы с именем покупателя и названием товара
SELECT o.id, c.full_name, p.description, o.order_date, o.quantity
FROM orders o
         JOIN customer c ON o.customer_id = c.id
         JOIN product p ON o.product_id = p.id;

-- 3. Изменение данных: Обновить количество товара (id=1) на складе
UPDATE product SET quantity = 10 WHERE id = 1;

-- 4. Изменение данных: Увеличить цену всех товаров на 10%
UPDATE product SET price = price * 1.1;

-- 5. Удаление данных: Удалить все заказы, сделанные до 2023-01-20
DELETE FROM orders WHERE order_date < '2023-01-20';

-- 6. Чтение данных: Вывести топ-3 самых популярных товаров (по количеству заказов)
SELECT p.description, SUM(o.quantity) as total_ordered
FROM orders o
         JOIN product p ON o.product_id = p.id
GROUP BY p.id, p.description
ORDER BY total_ordered DESC
LIMIT 3;

-- 7. Удаление данных: Удалить покупателя с id=10 (при условии, что у него нет заказов)
DELETE FROM customer WHERE id = 10 AND NOT EXISTS (
    SELECT 1 FROM orders WHERE customer_id = 10
);