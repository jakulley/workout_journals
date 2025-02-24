-- Insert sample workouts
INSERT INTO workout (date, notes) VALUES 
('2024-02-20', 'Leg day workout'),
('2024-02-21', 'Upper body strength training'),
('2024-02-22', 'Cardio endurance session'),
('2024-02-23', 'Full body functional training'),
('2024-02-24', 'Active recovery and mobility');

-- Insert sample exercises
INSERT INTO exercise (exercise_name, compound) VALUES 
('Barbell Back Squat', TRUE),
('Deadlift', TRUE),
('Bench Press', TRUE),
('Pull-Up', TRUE),
('Running', FALSE),
('Overhead Press', TRUE),
('Barbell Row', TRUE),
('Lunges', FALSE),
('Cycling', FALSE),
('Kettlebell Swing', TRUE);

-- Insert sample categories
INSERT INTO category (category_name) VALUES 
('Strength'),
('Endurance'),
('Bodyweight'),
('Cardio'),
('Olympic Lifts'),
('Mobility'),
('Power');

-- Insert sample many-to-many exercise-category relationships
INSERT INTO exercise_category (exercise_id, category_id) VALUES
(1, 1),  -- Barbell Back Squat -> Strength
(2, 1),  -- Deadlift -> Strength
(3, 1),  -- Bench Press -> Strength
(4, 3),  -- Pull-Up -> Bodyweight
(5, 2),  -- Running -> Endurance
(5, 4),  -- Running -> Cardio
(6, 1),  -- Overhead Press -> Strength
(7, 1),  -- Barbell Row -> Strength
(8, 1),  -- Lunges -> Strength
(8, 3),  -- Lunges -> Bodyweight
(9, 2),  -- Cycling -> Endurance
(9, 4),  -- Cycling -> Cardio
(10, 5), -- Kettlebell Swing -> Olympic Lifts
(10, 7); -- Kettlebell Swing -> Power

-- Insert sample exercise sets
INSERT INTO exercise_set (bodyweight, failure, reps, superset, two_sides, weight, exercise_exercise_id, workout_id, notes) VALUES
(FALSE, FALSE, 10, FALSE, FALSE, 100.00, 1, 1, 'Squat felt good'),
(FALSE, TRUE, 8, FALSE, FALSE, 120.00, 2, 1, 'Deadlift max effort'),
(FALSE, FALSE, 12, FALSE, FALSE, 80.00, 3, 2, 'Solid pressing'),
(FALSE, FALSE, 10, FALSE, FALSE, 50.00, 6, 2, 'Overhead pressing felt strong'),
(FALSE, FALSE, 12, TRUE, FALSE, 60.00, 7, 2, 'Rowing superset with pressing'),
(TRUE, FALSE, 15, FALSE, FALSE, NULL, 4, 2, 'Pull-ups were challenging'),
(FALSE, FALSE, 12, FALSE, TRUE, 40.00, 8, 4, 'Lunges for conditioning'),
(FALSE, FALSE, 15, FALSE, FALSE, 24.00, 10, 4, 'Kettlebell swings for power output');

-- Insert sample cardio exercise sets
INSERT INTO cardio_exercise_set (avghr, duration, intensity, exercise_exercise_id, workout_id, notes) VALUES
(140, 30, 3, 5, 3, 'Good endurance session'),
(155, 45, 5, 5, 3, 'Pushed the pace today'),
(135, 20, 2, 9, 4, 'Light cycling for recovery'),
(160, 35, 4, 9, 3, 'Cycling with sprint intervals'),
(145, 40, 3, 5, 5, 'Slow jogging for mobility day');
