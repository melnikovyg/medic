DELETE FROM roles;
DELETE FROM users;
ALTER SEQUENCE global_seq
RESTART WITH 1000;

INSERT INTO USERS (LOGIN, PASSWORD, FULL_NAME, PHONE, ENABLED) VALUES
  ('admin', 'adminp', 'Full Admin Name', '+79148959482', TRUE),
  ('reg', 'regp', 'Full Registrator Name', '+79148959482', TRUE);

INSERT INTO ROLES (USER_ID, ROLE) VALUES
  (1000, 'ROLE_ADMIN'),
  (1001, 'ROLE_REGISTRATOR');



