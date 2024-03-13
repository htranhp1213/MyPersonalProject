INSERT into `users` (`username`, `password_hash`, `email`, `first_name`, `last_name`)
VALUES ('aturing', 'b93727798b520dc10d145b53909c061f082ff14cd5f8cb4ab24c3b71bfa57d7e12e1296029be74c06a0d91ba32756f9fc978047fbe7232be67f94dfc1de9ced9', 'alan@enigma.com', 'Alan', 'Turing');

INSERT into `users` (`username`, `password_hash`, `email`, `first_name`, `last_name`)
VALUES ('dritchie', '67aff785bd17ac24448d491926ff7aadd8fa75e51a2f7a9bfc31889bad0adcd2989061a27ccd9eff9e5e31f2bc14b5c193727e116dc8dc48259acb3919171cd4', 'dennis@unix.com', 'Dennis', 'Ritchie');

INSERT into `users` (`username`, `password_hash`, `email`, `first_name`, `last_name`)
VALUES ('llamport', '9171d14954eeda4e70777c23d98e349818125cdaeb884ff97ebf8cc0a9c7778f54ce394256588148132a03ebea891e44077c659e6c0132fa87a8cf77e436ae11', 'leslie@paxos.com', 'Leslie', 'Lamport');

INSERT into `users` (`username`, `password_hash`, `email`, `first_name`, `last_name`)
VALUES ('bliskov', '1e4b9ae956cad1385cfa6fffd8323dd16c3fe18c54e6447e49bddef2138d042e84e1505a541c6ef19a5026e684b2559efd366145870a0a8d4d4173c0877f6cd2', 'barbara@thor.com', 'Barbara', 'Liskov');

INSERT into `users` (`username`, `password_hash`, `email`, `first_name`, `last_name`)
VALUES ('admin', 'admin', 'admin@admin', 'Admin', 'Admin');

INSERT into `inventory` (`item_name`, `info`, `price`, `image_url`, `category`)
VALUES ('Fruit Sponge Cake', 'A soft and light airy whipped cream with fruit cake ', 30.00, 'static/images/fruitCake.jpeg', 'Cake');

INSERT into `inventory` (`item_name`, `info`, `price`, `image_url`, `category`)
VALUES ('Flan', 'A baked custard dessert topped with caramel similar to the French crème caramel', 20.00, 'static/images/flan.jpeg', 'Cake');

INSERT into `inventory` (`item_name`, `info`, `price`, `image_url`, `category`)
VALUES ('Ube Honeycomb', 'A cake with honeycomb texture which is a litte crispy outside and chewy inside is extracted from ube', 20.00, 'static/images/ube_honeycomb.jpeg', 'Cake');

INSERT into `inventory` (`item_name`, `info`, `price`, `image_url`, `category`)
VALUES ('Customized Rectangle', 'A rectangle birthday flan cake can be customized with color, decoration, and flavor', 40.00, 'static/images/customized_rectangle.jpeg', 'Cake');

INSERT into `inventory` (`item_name`, `info`, `price`, `image_url`, `category`)
VALUES ('Customized Heart', 'A heart-shaped birthday flan cake can be customized with color, decoration, and flavor', 50.00, 'static/images/customized_heart.jpeg', 'Cake');

INSERT into `inventory` (`item_name`, `info`, `price`, `image_url`, `category`)
VALUES ('Customized Round', 'A round birthday flan cake can be customized with color, decoration, and flavor', 40.00, 'static/images/customized_round.jpeg', 'Cake');

INSERT into `inventory` (`item_name`, `info`, `price`, `image_url`, `category`)
VALUES ('Cupcake', 'Pink cupcakes with marble cream cheese frosting (sale by dozen)', 20.00, 'static/images/cupcakes.jpeg', 'Cupcake');

INSERT into `inventory` (`item_name`, `info`, `price`, `image_url`, `category`)
VALUES ('Chocolate cupcakes', 'Chocolate cupcakes with sprinkles', 15.00, 'static/images/chocolate_cupcakes.jpeg', 'Cupcake');

INSERT into `inventory` (`item_name`, `info`, `price`, `image_url`, `category`)
VALUES ('Milk Bread', 'Signature milk bread', 5.00, 'static/images/milkBread.jpeg', 'Bread');

INSERT into `inventory` (`item_name`, `info`, `price`, `image_url`, `category`)
VALUES ('French Bread', 'Baguette', 4.00, 'static/images/frenchBread.jpeg', 'Bread');

INSERT into `sales` (`transaction_id`, `username`, `item_id`, `quantity`, `sale_date`, `cost`)
VALUES ('1', 'aturing', '1', 10, '2022-12-21 7:30:30', 5.50);

INSERT into `sales` (`transaction_id`, `username`, `item_id`, `quantity`, `sale_date`, `cost`)
VALUES ('2', 'dritchie', '2', 10, '2022-12-21 7:30:30', 5.50);

INSERT into `sales` (`transaction_id`, `username`, `item_id`, `quantity`, `sale_date`, `cost`)
VALUES ('3', 'llamport', '3', 10, '2022-12-21 7:30:30', 5.50);

INSERT into `reviews` (`first_name`, `last_name`, `email`, `review`)
VALUES('Bubble', 'Gum', 'bgum@gmail.com', 'This is the best bakery in town!');

