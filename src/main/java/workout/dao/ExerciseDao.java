package workout.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import workout.entity.Exercise;

public interface ExerciseDao extends JpaRepository<Exercise, Long>{

}
