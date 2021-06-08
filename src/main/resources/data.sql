insert into TB_USERS (user_name, is_seller) values ('usuario1', false);
insert into TB_USERS (user_name, is_seller) values ('vendedor1', true);

INSERT INTO TB_USERS_FOLLOWED (FOLLOWERS_ID,FOLLOWED_ID) VALUES (1,2);

INSERT INTO
    TB_PRODUCTS
(ID, BRAND, COLOR, NOTES, PRODUCT_NAME, TYPE)
VALUES
(1, 'K&N', 'red', 'Filter for motor sport', 'Filter K&N Audi A1', 'cars');

INSERT
INTO
    TB_POSTS
(ID, CATEGORY, DATE, PRODUCT_ID, DISCOUNT, HAS_PROMO, PRICE, USER_ID)
VALUES
(1, 1, '2021-05-07', 1, 0, 'false', 699.99, 2);

INSERT INTO
    TB_PRODUCTS
(ID, BRAND, COLOR, NOTES, PRODUCT_NAME, TYPE)
VALUES
(2, 'Macena', 'inox', 'Downpipe', 'Downpipe Audi A1', 'cars');

INSERT
INTO
    TB_POSTS
(ID, CATEGORY, DATE, PRODUCT_ID, DISCOUNT, HAS_PROMO, PRICE, USER_ID)
VALUES
(2, 1, '2021-06-07', 2, 0.2, 'true', 1060.50, 2);