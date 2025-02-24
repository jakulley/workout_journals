package workout.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.entity.Exercise;
import workout.entity.ExerciseSet;
import workout.entity.Workout;

@Data
@NoArgsConstructor
public class ModelExerciseSet {

	private Long exercise_set_id;
	private Long workout_id;
	private ModelExercise exercise;
	private Integer reps;
	private Integer weight;
	private Boolean superset;
	private Boolean failure;
	private Boolean two_sides;
	private Boolean bodyweight;
	private String notes;
	
	public ModelExerciseSet(ExerciseSet exerciseSet) {
		this.exercise_set_id = exerciseSet.getExercise_set_id();
		this.workout_id = exerciseSet.getWorkout().getWorkout_id();
		if (exerciseSet.getExercise() != null) {
			this.exercise = new ModelExercise(exerciseSet.getExercise());
		}
		this.reps = exerciseSet.getReps();
		this.weight = exerciseSet.getWeight();
		this.superset = exerciseSet.getSuperset();
		this.failure = exerciseSet.getFailure();
		this.two_sides = exerciseSet.getTwo_sides();
		this.bodyweight = exerciseSet.getBodyweight();
		this.notes = exerciseSet.getNotes();
	}
}
