DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id) VALUES
  ('2018-02-25 04:15:16', 'Завтрак', '500', 100000),
  ('2018-02-25 07:15:16', 'Обед', '800', 100000),
  ('2018-02-25 15:15:16', 'Ужин', '700', 100000),
  ('2018-02-26 09:15:16', 'Завтра', '600', 100000),
  ('2018-02-26 15:15:16', 'Обед', '900', 100000),
  ('2018-02-26 21:15:16', 'Ужин', '700', 100000),
  ('2018-02-28 11:15:16', 'Ланч', '300', 100001),
  ('2018-02-28 16:15:16', 'Обед', '700', 100001),
  ('2018-02-28 19:15:16', 'Ужин', '600', 100001);
