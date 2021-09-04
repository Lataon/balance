DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, tariff, balance) VALUES
  ('Ivan Ivanov', 'fly on the internet', 1000),
  ('Ivan Alexeenko', 'fly on the internet', 2000),
  ('Alex Ivanov', 'fly on the internet', 6000),
  ('Ivan Ivanov', 'fly on the internet', 3000),
  ('Alex Alexeenko', 'all inclusive', 2000);

