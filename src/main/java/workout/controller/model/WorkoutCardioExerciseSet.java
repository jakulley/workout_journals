package workout.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.entity.CardioExerciseSet;
import workout.entity.Exercise;
import workout.entity.Workout;

@Data
@NoArgsConstructor
public class WorkoutCardioExerciseSet {

	private Long cardio_exercise_set_id;
	private Workout workout;
	private Exercise exercise;
	private Long duration;
	private Long avgHR;
	private Long intensity;
	private String notes;
	
	public WorkoutCardioExerciseSet(CardioExerciseSet cardioExerciseSet) {
		this.cardio_exercise_set_id = cardioExerciseSet.getCardio_exercise_set_id();
		this.workout = cardioExerciseSet.getWorkout();
		this.exercise = cardioExerciseSet.getExercise();
		this.duration = cardioExerciseSet.getDuration();
		this.avgHR = cardioExerciseSet.getAvgHR();
		this.intensity = cardioExerciseSet.getIntensity();
		this.notes = cardioExerciseSet.getNotes();
	}
}
