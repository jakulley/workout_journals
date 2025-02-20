package workout.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.entity.Exercise;
import workout.entity.ExerciseSet;
import workout.entity.Workout;

@Data
@NoArgsConstructor
public class WorkoutExerciseSet {

	private Long exercise_set_id;
	private Workout workout;
	private Exercise exercise;
	private Integer reps;
	private Integer weight;
	private Boolean superset;
	private Boolean failure;
	private Boolean twoSides;
	private Boolean bodyWeight;
	private String notes;
	
	public WorkoutExerciseSet(ExerciseSet exerciseSet) {
		this.exercise_set_id = exerciseSet.getExercise_set_id();
		this.workout = exerciseSet.getWorkout();
		this.exercise = exerciseSet.getExercise();
		this.reps = exerciseSet.getReps();
		this.weight = exerciseSet.getWeight();
		this.superset = exerciseSet.getSuperset();
		this.failure = exerciseSet.getFailure();
		this.twoSides = exerciseSet.getTwoSides();
		this.bodyWeight = exerciseSet.getBodyWeight();
		this.notes = exerciseSet.getNotes();
	}
}
