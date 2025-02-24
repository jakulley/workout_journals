package workout.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import workout.entity.ExerciseSet;

public interface ExerciseSetDao extends JpaRepository<ExerciseSet, Long> {

}

