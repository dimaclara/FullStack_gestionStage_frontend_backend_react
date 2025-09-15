-- USERS (ADMIN déjà présent en id=1)
INSERT INTO public.users (email_verified, created_at, id, email, name, password, role) VALUES
                                                                                           (true, now(), 2, 'student1@test.com', 'Alice Student', '$2a$10$gAZ.zi2V/pz5h3NV5WHtR.pqB2uejaxI6CDygb3GX3eGc.xY5NDhO', 'STUDENT'),
                                                                                           (true, now(), 3, 'student2@test.com', 'Bob Student', '$2a$10$gAZ.zi2V/pz5h3NV5WHtR.pqB2uejaxI6CDygb3GX3eGc.xY5NDhO', 'STUDENT'),
                                                                                           (true, now(), 4, 'enterprise@test.com', 'Tech Corp', '$2a$10$gAZ.zi2V/pz5h3NV5WHtR.pqB2uejaxI6CDygb3GX3eGc.xY5NDhO', 'ENTERPRISE'),
                                                                                           (true, now(), 5, 'teacher@test.com', 'Dr Teacher', '$2a$10$gAZ.zi2V/pz5h3NV5WHtR.pqB2uejaxI6CDygb3GX3eGc.xY5NDhO', 'TEACHER');

-- STUDENTS (FK vers users.id)
INSERT INTO public.student (id, first_name, department, github_link, languages, linkedin_link, on_internship, sector)
VALUES
    (2, 'Alice', 'Informatique', 'https://github.com/alice', ARRAY['Java','SQL'], 'https://linkedin.com/in/alice', false, 'Développement'),
    (3, 'Bob', 'Réseaux', 'https://github.com/bob', ARRAY['Python','Linux'], 'https://linkedin.com/in/bob', false, 'Cybersécurité');

-- ENTERPRISE (FK vers users.id)
INSERT INTO public.enterprise (id, matriculation, sector, city, contact, country, enterprise_state, in_partnership, location, sector_of_activity)
VALUES
    (4, 'ENT-12345', 'Technologie', 'Paris', 'contact@techcorp.com', 'France', 'APPROVED', true, 'La Défense', 'Développement logiciel');

-- TEACHER (FK vers users.id)
INSERT INTO public.teacher (id, department, first_name)
VALUES
    (5, 'Informatique', 'John');

-- OFFER (FK vers enterprise + teacher)
INSERT INTO public.offer (end_date, start_date, enterprise_id, id, teacher_id, description, domain, offerstatus, title, job, number_of_places, paying, remote, requirements, status, type_of_internship)
VALUES
    ('2025-12-31', '2025-09-01', 4, 1, 5, 'Stage développement web', 'Informatique', 'APPROVED', 'Développeur Frontend', 'Stagiaire', 2, true, true, 'HTML, CSS, JS', 'APPROVED', 'Stage PFE');

-- APPLICATION (FK vers student + offer + enterprise)
INSERT INTO public.application (student_id, state, enterprise_id, offer_id)
VALUES
    (2, 'PENDING', 4, 1),
    (3, 'APPROVED', 4, 1);

-- CONVENTION (FK vers offer + teacher)
INSERT INTO public.convention (offer_id, teacher_id, convention_state, state)
VALUES
    (1, 5, 'PENDING', 'NOUVELLE');

-- MESSAGE (FK sender/receiver vers users)
INSERT INTO public.message (date, receiver_id, sender_id, content)
VALUES
    (current_date, 2, 4, 'Bonjour Alice, merci pour ta candidature'),
    (current_date, 3, 4, 'Bonjour Bob, nous validons ton stage');

-- NOTIFICATION (FK vers users)
INSERT INTO public.notification (created_at, message, seen, recipient_id)
VALUES
    (now(), 'Ton offre de stage a été validée', false, 3),
    (now(), 'Nouvelle candidature reçue', false, 4);

-- VERIFICATION TOKEN (FK vers users)
INSERT INTO public.verification_token (used, expiration_date, user_id, code)
VALUES
    (false, now() + interval '7 days', 2, 'CODE123'),
    (false, now() + interval '7 days', 3, 'CODE456');