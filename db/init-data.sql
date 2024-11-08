INSERT INTO users (user_id, first_name, last_name, email, password, phone_number, roles_id)
VALUES
    ('a1b2c3d4-e5f6-4789-8a7b-1234567890ab', 'Сергей', 'Кузнецов', 'admin@example.com', 'admin123', '+79990000000', 1),
    ('f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', 'Алексей', 'Петров', 'alexey.petrov@example.com', 'password123', '+79995432100', 3),
    ('d5b6a7b5-48ab-433d-b0a7-888763fa19b3', 'Иван', 'Иванов', 'ivan.ivanov@example.com', 'password123', '+79991234567', 3),
    ('c1e2d3f4-56a7-89b0-c1d2-456789ab0123', 'Марина', 'Лебедева', 'marina.lebedeva@example.com', 'password123', '+79993456789', 3),
    ('f9d6c8b7-2b48-4389-899f-876dcdc1f0e8', 'Дмитрий', 'Григорьев', 'dmitry.grigoryev@example.com', 'password123', '+79991234560', 2),
    ('b7c8d1f6-35e5-4112-869d-556b5b63c7c2', 'Елена', 'Смирнова', 'elena.smirnova@example.com', 'password123', '+79993456760', 2),
    ('3a5f6a7b-b1d1-426b-92c5-9ed45725a3a0', 'Ольга', 'Кузнецова', 'olga.kuznetsova@example.com', 'password123', '+79991234561', 2),
    ('60f7a0e0-9c5f-4c10-90de-b1db4c5eec82', 'Андрей', 'Федоров', 'andrey.fedorov@example.com', 'password123', '+79991234562', 2),
    ('7115e21f-6d3d-4d71-b97a-5de59727d3bc', 'Татьяна', 'Романова', 'tatiana.romanova@example.com', 'password123', '+79991234563', 2),
    ('9a27fc0f-bc9f-46a0-bd90-f732a836d88c', 'Егор', 'Попов', 'egor.popov@example.com', 'password123', '+79991234564', 2),
    ('510f9b2f-cb67-4294-8f53-63da55e6b123', 'Нина', 'Михайлова', 'nina.mikhaylova@example.com', 'password123', '+79991234565', 2),
    ('e6ffb724-dae3-4459-b292-5f3db051a6b5', 'Светлана', 'Новикова', 'svetlana.novikova@example.com', 'password123', '+79991234566', 2),
    ('634f1450-09fd-4c8b-9e6c-96fa22e153a6', 'Петр', 'Петров', 'peter.petrov@example.com', 'password123', '+79991234567', 2),
    ('8e3a34ac-119b-453d-bba7-478c23361d59', 'Мария', 'Сидорова', 'maria.sidorova@example.com', 'password123', '+79991234568', 2);

INSERT INTO procedures (procedure_id, procedure_name, procedure_price, procedure_duration)
VALUES
    (1, 'Классическая стрижка', 25.00, 30),
    (2, 'Коррекция бороды', 20.00, 30),
    (3, 'Массаж шеи и плеч', 40.00, 45),
    (4, 'Тонирование волос', 50.00, 90),
    (5, 'Коррекция усов', 15.00, 30),
    (6, 'Чистка лица (мужская)', 35.00, 60),
    (7, 'Мужской уход за руками', 20.00, 30),
    (8, 'Мужской уход за кожей лица', 30.00, 60),
    (9, 'Укладка волос (мужская)', 25.00, 45),
    (10, 'Массаж спины', 45.00, 60);

INSERT INTO master_has_procedures (master_execution_id , master_id, procedure_id)
VALUES
    (1, 'f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', 1),
    (2, 'f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', 2),
    (3, 'f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', 3),
    (4, 'f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', 6),
    (5, 'd5b6a7b5-48ab-433d-b0a7-888763fa19b3', 2),
    (6, 'd5b6a7b5-48ab-433d-b0a7-888763fa19b3', 4),
    (7, 'd5b6a7b5-48ab-433d-b0a7-888763fa19b3', 5),
    (8, 'd5b6a7b5-48ab-433d-b0a7-888763fa19b3', 7),
    (9, 'c1e2d3f4-56a7-89b0-c1d2-456789ab0123', 8),
    (10, 'c1e2d3f4-56a7-89b0-c1d2-456789ab0123', 9),
    (11, 'c1e2d3f4-56a7-89b0-c1d2-456789ab0123', 10);

INSERT INTO working_days (working_day_id, master_id, working_date, work_start, work_end)
VALUES
    (1, 'f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', '2024-11-24', '09:00', '21:00'),
    (2, 'd5b6a7b5-48ab-433d-b0a7-888763fa19b3', '2024-11-24', '09:00', '21:00'),
    (3, 'c1e2d3f4-56a7-89b0-c1d2-456789ab0123', '2024-11-24', '09:00', '21:00'),

    (4, 'f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', '2024-11-25', '09:00', '21:00'),
    (5, 'd5b6a7b5-48ab-433d-b0a7-888763fa19b3', '2024-11-25', '09:00', '21:00'),

    (6, 'f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', '2024-11-26', '09:00', '21:00'),
    (7, 'c1e2d3f4-56a7-89b0-c1d2-456789ab0123', '2024-11-26', '09:00', '21:00'),

    (8, 'd5b6a7b5-48ab-433d-b0a7-888763fa19b3', '2024-11-26', '09:00', '21:00'),
    (9, 'c1e2d3f4-56a7-89b0-c1d2-456789ab0123', '2024-11-26', '09:00', '21:00'),

    (10, 'f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', '2024-12-01', '09:00', '21:00'),
    (11, 'd5b6a7b5-48ab-433d-b0a7-888763fa19b3', '2024-11-26', '09:00', '21:00'),
    (12, 'c1e2d3f4-56a7-89b0-c1d2-456789ab0123', '2024-11-26', '09:00', '21:00'),

    (13, 'f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', '2024-12-08', '09:00', '21:00'),
    (14, 'd5b6a7b5-48ab-433d-b0a7-888763fa19b3', '2024-11-27', '09:00', '21:00'),

    (15, 'f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', '2024-11-28', '09:00', '21:00'),
    (16, 'd5b6a7b5-48ab-433d-b0a7-888763fa19b3', '2024-11-28', '09:00', '21:00'),
    (17, 'c1e2d3f4-56a7-89b0-c1d2-456789ab0123', '2024-11-28', '09:00', '21:00');

INSERT INTO bookings (booking_id,client_id, reservation_start, reservation_end, procedure_id, working_day_id)
VALUES
    (nextval('bookings_booking_id_seq'), 'a1b2c3d4-e5f6-4789-8a7b-1234567890ab', '09:00', '09:45', 1, 1),
    (nextval('bookings_booking_id_seq'), 'f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', '10:00', '10:30', 3, 1),
    (nextval('bookings_booking_id_seq'), 'd5b6a7b5-48ab-433d-b0a7-888763fa19b3', '11:00', '11:30', 6, 1),
    (nextval('bookings_booking_id_seq'), 'c1e2d3f4-56a7-89b0-c1d2-456789ab0123', '12:00', '12:45', 2, 1),

    (nextval('bookings_booking_id_seq'), 'f9d6c8b7-2b48-4389-899f-876dcdc1f0e8', '09:30', '10:15', 4, 2),
    (nextval('bookings_booking_id_seq'), 'b7c8d1f6-35e5-4112-869d-556b5b63c7c2', '11:00', '11:45', 5, 2),
    (nextval('bookings_booking_id_seq'), '3a5f6a7b-b1d1-426b-92c5-9ed45725a3a0', '12:30', '13:00', 1, 2),
    (nextval('bookings_booking_id_seq'), '60f7a0e0-9c5f-4c10-90de-b1db4c5eec82', '13:30', '14:00', 3, 2),

    (nextval('bookings_booking_id_seq'), '7115e21f-6d3d-4d71-b97a-5de59727d3bc', '09:00', '09:30', 2, 3),
    (nextval('bookings_booking_id_seq'), '9a27fc0f-bc9f-46a0-bd90-f732a836d88c', '10:00', '10:30', 4, 3),
    (nextval('bookings_booking_id_seq'), '510f9b2f-cb67-4294-8f53-63da55e6b123', '11:00', '12:00', 6, 3),
    (nextval('bookings_booking_id_seq'), 'e6ffb724-dae3-4459-b292-5f3db051a6b5', '12:30', '13:15', 5, 3),

    (nextval('bookings_booking_id_seq'), '634f1450-09fd-4c8b-9e6c-96fa22e153a6', '09:30', '10:00', 1, 4),
    (nextval('bookings_booking_id_seq'), '8e3a34ac-119b-453d-bba7-478c23361d59', '10:00', '11:00', 2, 4),
    (nextval('bookings_booking_id_seq'), 'a1b2c3d4-e5f6-4789-8a7b-1234567890ab', '11:30', '12:30', 3, 4),
    (nextval('bookings_booking_id_seq'), 'f2d4e0a1-16b3-4d9e-88d9-94a9cc93fe95', '12:30', '13:00', 4, 4),

    (nextval('bookings_booking_id_seq'), 'd5b6a7b5-48ab-433d-b0a7-888763fa19b3', '13:30', '14:15', 5, 5),
    (nextval('bookings_booking_id_seq'), 'c1e2d3f4-56a7-89b0-c1d2-456789ab0123', '15:00', '15:45', 1, 5),
    (nextval('bookings_booking_id_seq'), 'f9d6c8b7-2b48-4389-899f-876dcdc1f0e8', '16:00', '16:45', 3, 5),
    (nextval('bookings_booking_id_seq'), 'b7c8d1f6-35e5-4112-869d-556b5b63c7c2', '17:00', '17:45', 6, 5);