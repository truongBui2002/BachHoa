USE [master]
GO
IF EXISTS (SELECT * FROM sys.databases WHERE name = 'ProjectPRJ')
	DROP DATABASE ProjectPRJ
GO
CREATE DATABASE ProjectPRJ
GO
use ProjectPRJ
GO
CREATE TABLE [Account] (
    userName VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50),
	email VARCHAR(50) UNIQUE NOT NULL,
    role VARCHAR(10)
);
GO
CREATE TABLE [Admin] (
	adminId INT PRIMARY KEY,
    userName VARCHAR(50),
    fullName NVARCHAR(100),
    FOREIGN KEY (userName) REFERENCES Account(userName)
);
GO
CREATE TABLE [Customer] (
    customerId INT PRIMARY KEY IDENTITY,
    userName VARCHAR(50) UNIQUE NOT NULL,
    fullName NVARCHAR(100),
    address NVARCHAR(200),
    phone VARCHAR(15),
    FOREIGN KEY (userName) REFERENCES Account(userName)
);
GO
CREATE TABLE [Product] (
    productId INT PRIMARY KEY,
    productName VARCHAR(200),
	brand VARCHAR(20),
    description VARCHAR(2000),
    price DECIMAL(18, 2),
	quantity INT,
	img VARCHAR(100),
);
GO
CREATE TABLE [Order] (
    orderId INT PRIMARY KEY IDENTITY,
    customerId INT,
    orderDate DATETIME,
	shipMode VARCHAR(50),
    FOREIGN KEY (customerId) REFERENCES Customer(customerId)
);
GO
CREATE TABLE [OrderDetails] (
    orderId INT,
    productId INT,
    quantity INT,
    discount FLOAT DEFAULT 0,
    FOREIGN KEY (orderId) REFERENCES [Order](orderId),
    FOREIGN KEY (productId) REFERENCES Product(productId)
);
GO
INSERT INTO Account(userName, passWord, email, role) VALUES ('truongbui', '123abc', 'buivantruong16082002@gmail.com', 'admin')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser1', '123abc', 'buivantruong10012002@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser2', '123abc', 'buivantruong100120022@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser3', '123abc', 'buivantruong100120024@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser4', '123abc', 'buivantruong100120025@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser5', '123abc', 'buivantruong100120026@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser6', '123abc', 'buivantruong100120027@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser7', '123abc', 'buivantruong100120028@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser8', '123abc', 'buivantruong100120029@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser9', '123abc', 'buivantruong1001200210@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser10', '123abc', 'buivantruong1001200211@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser11', '123abc', 'buivantruong1001200212@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser12', '123abc', 'buivantruong1001200213@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser13', '123abc', 'buivantruong1001200214@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser14', '123abc', 'buivantruong1001200215@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser15', '123abc', 'buivantruong1001200216@gmail.com', 'customer')
INSERT INTO Account(userName, passWord, email, role) VALUES ('AccUser16', '123abc', 'buivantruong1001200217@gmail.com', 'customer')


GO
INSERT INTO Admin (adminId, userName, fullName) VALUES (1, 'truongbui', N'Bùi Văn Trường')
GO
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser1', 'I am Customer', N'Ninh Bình1', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser2', 'I am Customer', N'Ninh Bình2', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser3', 'I am Customer', N'Ninh Bình3', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser4', 'I am Customer', N'Ninh Bình4', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser5', 'I am Customer', N'Ninh Bình5', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser6', 'I am Customer', N'Ninh Bình6', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser7', 'I am Customer', N'Ninh Bình7', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser8', 'I am Customer', N'Ninh Bình8', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser9', 'I am Customer', N'Ninh Bình9', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser10', 'I am Customer', N'Ninh Bình10', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser11', 'I am Customer', N'Ninh Bình11', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser12', 'I am Customer', N'Ninh Bình12', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser13', 'I am Customer', N'Ninh Bình13', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser14', 'I am Customer', N'Ninh Bình14', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser15', 'I am Customer', N'Ninh Bình15', '0384761608')
INSERT INTO Customer(userName, fullName, address, phone) VALUES ('AccUser16', 'I am Customer', N'Ninh Bình16', '0384761608')

GO

INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(404, 'Hao Hao', 'Acecook', 'Hao Hao noodles are delicious, many attractive flavors, delicious noodles, good prices, guaranteed quality.', 3500, 500, 'img/haohao.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(405, 'Vinamilk A&D3', 'Vinamilk', 'Vinamilk A&D3 pasteurized milk with added sugar, more delicious and easy to drink, is fortified with many essential nutrients such as vitamins A, D3 and calcium, supporting the development of vision and strong bones.', 5000, 110, 'img/VinamilkA&D3.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(406, 'G7 3 in 1 milk coffee 288g', 'G7', 'Different, rich taste, unique and seductive aroma with the essence of selected pure coffee beans and modern technology to produce a standard cup of coffee to help refresh the spirit, focus on working and studying.', 60000, 50, 'img/g73in1.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(407, 'Today is mixed noodles with spicy and sour sauce Vina AceCook bowl 90g', 'Acecook', 'The delicious yellow noodles are mixed in the Vina Acecook mi sauce, the rich spicy and sour seafood flavor permeates each strand with an ecstatic, enticing aroma. Today is seafood noodles with spicy and sour sauce Vina AceCook 90g bowl is very convenient and delicious, which is an attractive choice for quick meals.', 15000, 70, 'img/mitron520.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(408, 'G7 milk coffee strong taste X2 300g', 'G7', 'Instant coffee has a real strong taste, strong taste, and unique and attractive aroma to help refresh the spirit, focus on working and studying. G7 instant milk coffee 3 in 1 strong strong box of 12 packs of G7 brand instant coffee.', 54000, 20, 'img/gun.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(409, 'Vinamilk Yogurt Aloe Vera 100g', 'Vinamilk', 'Vinamilk Aloe Vera yogurt with crunchy, crunchy aloe vera seeds not only helps to supplement nutrients but also brings a delicious taste to stimulate the taste buds of children.', 32000, 45, 'img/yogurt.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(410, 'Cooking oil 1l', 'Simply', 'Soybean oil is one of the most widely used vegetable oils in the world because of its health benefits, high smoking point, and its composition and antioxidants that remain in the oil even after refining. training. Pure soybean oil contains up to 80% unsaturated fatty acids, has the effect of significantly reducing blood cholesterol, especially bad cholesterol.', 67000, 100, 'img/dauan1l.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(411, 'Cooking oil 2l', 'Simply', 'Soybean oil is one of the most widely used vegetable oils in the world because of its health benefits, high smoking point, and its composition and antioxidants that remain in the oil even after refining. training. Pure soybean oil contains up to 80% unsaturated fatty acids, has the effect of significantly reducing blood cholesterol, especially bad cholesterol.', 110000, 500, 'img/dauan2l.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(412, 'Hao Hao 30', 'Acecook', 'Hao Hao noodles are delicious, many attractive flavors, delicious noodles, good prices, guaranteed quality.', 95000, 500, 'img/haohao30.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(413, 'Aromatic Rice 5kg', 'ST21', 'Clear white. Seeds are elongated and transparent, average length of seeds: 6.8mm. Light jasmine fragrance, sweet rice, very flexible, sticky rice grains. The rice remains soft when cooled.', 100000, 50, 'img/gao5kg.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(414, 'Sting strawberry', 'Sting', 'Energy drink carbonated, refreshing, energizing, delicious strawberry flavor, safe, no impurities', 9500, 150, 'img/stingdau.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(415, 'Sting gold', 'Sting', 'Energy drink carbonated, refreshing, energizing, delicious strawberry flavor, safe, no impurities', 9500, 110, 'img/stinggold.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(416, 'Sting strawberry 24', 'Sting', 'Sting energy drink with delicious, refreshing taste, supplemented with quality red ginseng. Sting helps the body replenish water, replenish energy, vitamins C and E, help dispel thirst and fatigue, and strawberries for lightness and comfort. Commitment to genuine, quality and safety', 230000, 30, 'img/stingthung.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(417, 'Coca-Cola bottle', 'Coca cola', 'Coca-Cola Carbonated Beverage with a large amount of gas will help you dispel all feelings of fatigue, stress, bring the most comfortable feeling after each use, so it is especially suitable for use when doing many outdoor activities. God. In particular, the product has an attractive sweet taste, helping to digest food faster, allowing you to easily enjoy more dishes in each meal.', 7000, 220, 'img/cocachai.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(418, 'Coca-Cola cans', 'Coca cola', 'Coca-Cola Carbonated Beverage with a large amount of gas will help you dispel all feelings of fatigue, stress, bring the most comfortable feeling after each use, so it is especially suitable for use when doing many outdoor activities. God. In particular, the product has an attractive sweet taste, helping to digest food faster, allowing you to easily enjoy more dishes in each meal.', 10000, 100, 'img/cocalon.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(419, 'Dynamite Mint 120g', 'Dynamite', 'Refreshing cool mint flavor combined with chocolate filling gives a great feeling. Contains many essential nutrients for the body. Helps you quickly regain your spirit during stressful working hours. Peppermint essence has high antibacterial ability, helps protect teeth, nose and throat effectively. Chocolate mint flavor candy gives you a new feeling with the combination of mint and chocolate.', 20000, 50, 'img/bacha.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(420, 'Dynamite Chews ', 'Dynamite', 'Chocolate flavor, made into round, quilted candies, easy to eat, delicious, attractive taste. Candy does not contain harmful substances, safe for health. Suitable for all ages. The amount of sugar in candy provides temporary energy for the body.', 15000, 30, 'img/bachado.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(421, 'Dynamite Orange ', 'Dynamite', 'Dynamite candies are delicious with quality chocolate orange flavor, delicious and delicious. Dynamite orange chocolate candy pack 330g large package, more for you and your family and friends to enjoy together. Delicious sweets, used for snacks, creating energy for you to work.', 1500, 50, 'img/bachacam.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(422, 'Omachi with stewed beef sauce', 'Omachi', 'Noodles from eggs and golden potatoes are delicious blended in Omachi noodle soup with rich stewed beef sauce and nose-cracking aroma to create a super noodle product with a unique and harmonious flavor combination. Omachi potato noodles with stewed beef sauce 80g convenient, delicious and irresistible', 6000, 200, 'img/omachibo.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(423, 'Omachi with sour and spicy shrimp', 'Omachi', 'The genuine nutritious potato noodle product of the Omachi brand is loved by many people. Omachi Potato Noodles with Thai Spicy Shrimps and Spicy Shrimp 80g is made from rich juice, chewy and attractive yellow noodles, with a spicy and sour taste that many people like, suitable for the whole familyis taste.', 6000, 50, 'img/omachixanh.png')
INSERT INTO Product (productId, productName, brand, description, price, quantity, img) VALUES(424, 'Omachi with stewed beef sauce x5', 'Omachi', 'Noodles from eggs and golden potatoes are delicious blended in Omachi noodle soup with rich stewed beef sauce and nose-cracking aroma to create a super noodle product with a unique and harmonious flavor combination. Omachi potato noodles with stewed beef sauce 80g convenient, delicious and irresistible', 28000, 20, 'img/omachi5.png')
GO
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (7, '2014-09-07 00:00:00', 'Fast')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (2, '2014-09-07 00:00:00', 'Fast')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (3, '2014-04-04 00:00:00', 'Standard')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (4, '2014-05-24 00:00:00', 'Fast')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (1, '2014-05-16 00:00:00', 'Standard')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (2, '2014-11-09 00:00:00', 'Fast')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (1, '2014-09-10 00:00:00', 'Standard')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (3, '2014-12-29 00:00:00', 'Fast')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (2, '2014-04-06 00:00:00', 'Standard')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (1, '2014-09-09 00:00:00', 'Fast')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (12, '2014-09-09 00:00:00', 'Standard')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (2, '2014-11-30 00:00:00', 'Fast')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (7, '2014-08-25 00:00:00', 'Standard')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (4, '2014-02-04 00:00:00', 'Fast')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (6, '2014-03-03 00:00:00', 'Standard')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (13, '2014-01-06 00:00:00', 'Fast')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (2, '2014-02-04 00:00:00', 'Standard')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (4, '2014-10-28 00:00:00', 'Fast')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (5, '2014-10-24 00:00:00', 'Standard')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (5, '2014-01-13 00:00:00', 'Fast')
INSERT INTO [Order] (customerId, orderDate, shipMode) VALUES (8, '2014-08-09 00:00:00', 'Standard')


GO
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (1, 404, 5, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (1, 407, 2, 0.05)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (2, 408, 1, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (3, 409, 2, 0.3)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (3, 406, 1, 0.2)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (3, 407, 1, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (4, 408, 9, 0.3)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (5, 409, 8, 0.4)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (7, 404, 6, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (7, 404, 3, 0.2)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (8, 405, 7, 0.3)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (9, 406, 7, 0.05)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (10, 407, 4, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (11, 410, 9, 0.2)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (12, 416, 2, 0.01)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (13, 411, 0, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (14, 412, 1, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (15, 409, 2, 0.0)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (16, 406, 3, 0.3)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (17, 408, 7, 0.2)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (18, 407, 6, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (19, 409, 4, 0.05)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (20, 410, 3, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (20, 411, 3, 0.05)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (17, 407, 4, 0.2)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (21, 409, 6, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (8, 417, 3, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (3, 422, 2, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (4, 416, 4, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (6, 417, 5, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (13, 415, 2, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (17, 418, 1, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity, discount) VALUES (9, 420, 5, 0.1)
INSERT INTO [OrderDetails] (orderId, productId, quantity) VALUES (15, 413, 3)
GO
select*from Product
GO
select * from [Admin]
GO
select*from Account
GO
select*from Customer
GO
select*from [Order] where customerId = 15
GO
select*from OrderDetails