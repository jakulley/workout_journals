package workout.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import workout.entity.Workout;

public interface WorkoutDao extends JpaRepository<Workout, Long> {

}
