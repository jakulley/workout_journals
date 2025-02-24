package workout.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import workout.entity.CardioExerciseSet;

public interface CardioExerciseSetDao extends JpaRepository<CardioExerciseSet, Long> {

}

