-- run after the project starts (passwords are the same as user names)

INSERT INTO customer(id, name) values(1, 'Jack');
INSERT INTO customer(id, name) values(2, 'Jill');

INSERT INTO user(id, name, password, authority) values(1, 'test1', '$2y$12$rNfigpQatcCjIStkCEz.x.6WVc9tetkNknEQBW6LklK9F465bgDQC', 'user');
INSERT INTO user(id, name, password, authority) values(2, 'test2', '$2y$12$8zlSHIhOBofPCc/Yco8hOey4YmbuvKZvucWfjhp9eavrhkbAXbBES', 'user');
INSERT INTO user(id, name, password, authority) values(3, 'test3', '$2y$12$QtXnN676lrtsMWMFNdHu9ex5vf7NYF3qVwUOpVFzh7qtqUG2s9cBe', 'user');
INSERT INTO user(id, name, password, authority) values(4, 'test4', '$2y$12$M1FAP5D9HDUsiMDzxxxZRuB4rkCyJUgMiYleQdBo897NOSyKZZE0O', 'user');

INSERT INTO account(id, name, customer_id, user_id) values(1, 'account1', 1, 1);
INSERT INTO account(id, name, customer_id, user_id) values(2, 'account2', 1, 2);
INSERT INTO account(id, name, customer_id, user_id) values(3, 'account3', 2, 3);
INSERT INTO account(id, name, customer_id, user_id) values(4, 'account4', 2, 4);
INSERT INTO account(id, name, customer_id, user_id) values(5, 'account5', 2, 4);

INSERT INTO farm(id, name, account_id) values(1, 'farm1', 1);
INSERT INTO farm(id, name, account_id) values(2, 'farm2', 2);
INSERT INTO farm(id, name, account_id) values(3, 'farm3', 3);
INSERT INTO farm(id, name, account_id) values(4, 'farm4', 4);
INSERT INTO farm(id, name, account_id) values(5, 'farm5', 5);