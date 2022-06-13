insert into hardware(code, name, price, type, amountInStorage)
    values ('00000001', 'Intel Core i3 10105', 219, 'CPU', 10),
           ('00000002', 'AMD Radeon RX 6800', 329, 'GPU', 3),
           ('00000003', 'Intel Core i5 10103H', 280, 'CPU', 4);

insert into review(title, text, rating, hardware_code)
    values ('Excellent hardware!!!', 'I am just so glad to have this', 4, '00000001'),
           ('Great for Programming!', 'This hardware is the best!', 5, '00000001'),
           ('Bad!', 'It is not very good', 2, '00000002'),
           ('Is good!', 'I can play all games', 5, '00000003');

insert into user(username, password)
values ('user', '$2a$12$nadMBKF7x/dSqXBjza9GVezRVu54TIFHbzdRkdjLWMAlWEZOlr.eO'),
       ('admin', '$2a$12$S.r13vFNy5OqKyM8RqxAmOUdp8o9VJ2f0THS9D0HQiZNLLc7tp7l.');

insert into authority (authority_name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into user_authority (user_id, authority_id)
values (1, 2),
       (2, 1);