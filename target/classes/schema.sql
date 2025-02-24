-- Drop tables if they exist (to reset the schema)
DROP TABLE IF EXISTS exercise_category;
DROP TABLE IF EXISTS exercise_set;
DROP TABLE IF EXISTS cardio_exercise_set;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS exercise;
DROP TABLE IF EXISTS workout;

-- Create the workout table
CREATE TABLE workout (
    workout_id INT AUTO_INCREMENT PRIMARY KEY,  -- Auto-incremented ID
    date DATE NOT NULL,
    notes TEXT
);

-- Create the exercise table
CREATE TABLE exercise (
    exercise_id INT AUTO_INCREMENT PRIMARY KEY,
    exercise_name VARCHAR(255) NOT NULL,
    compound BOOLEAN NOT NULL
);

-- Create the category table
CREATE TABLE category (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) UNIQUE NOT NULL
);

-- Create the many-to-many relationship table for exercise and category
CREATE TABLE exercise_category (
    category_id INT NOT NULL,
    exercise_id INT NOT NULL,
    PRIMARY KEY (category_id, exercise_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE CASCADE,
    FOREIGN KEY (exercise_id) REFERENCES exercise(exercise_id) ON DELETE CASCADE
);

-- Create the exercise_set table
CREATE TABLE exercise_set (
    exercise_set_id INT AUTO_INCREMENT PRIMARY KEY,
    bodyweight BOOLEAN DEFAULT FALSE,
    failure BOOLEAN DEFAULT FALSE,
    reps INT CHECK (reps >= 0),
    superset BOOLEAN DEFAULT FALSE,
    two_sides BOOLEAN DEFAULT FALSE,
    weight DECIMAL(10,2) CHECK (weight >= 0),
    exercise_exercise_id INT NOT NULL,
    workout_id INT NOT NULL,
    notes TEXT,
    FOREIGN KEY (exercise_exercise_id) REFERENCES exercise(exercise_id) ON DELETE CASCADE,
    FOREIGN KEY (workout_id) REFERENCES workout(workout_id) ON DELETE CASCADE
);

-- Create the cardio_exercise_set table
CREATE TABLE cardio_exercise_set (
    cardio_exercise_set_id INT AUTO_INCREMENT PRIMARY KEY,
    avghr INT CHECK (avghr >= 0),
    duration INT CHECK (duration >= 0),  -- Duration in minutes
    intensity VARCHAR(50),
    exercise_exercise_id INT NOT NULL,
    workout_id INT NOT NULL,
    notes TEXT,
    FOREIGN KEY (exercise_exercise_id) REFERENCES exercise(exercise_id) ON DELETE CASCADE,
    FOREIGN KEY (workout_id) REFERENCES workout(workout_id) ON DELETE CASCADE
);
