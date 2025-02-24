package workout.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.entity.CardioExerciseSet;
import workout.entity.Exercise;
import workout.entity.Workout;

@Data
@NoArgsConstructor
public class ModelCardioExerciseSet {

	private Long cardio_exercise_set_id;
	private Long workout_id;
	private ModelExercise exercise;
	private Long duration;
	private Long avgHR;
	private Long intensity;
	private String notes;
	
	public ModelCardioExerciseSet(CardioExerciseSet cardioExerciseSet) {
		this.cardio_exercise_set_id = cardioExerciseSet.getCardio_exercise_set_id();
		this.workout_id = cardioExerciseSet.getWorkout().getWorkout_id();
		if (cardioExerciseSet.getExercise() != null) {
			this.exercise = new ModelExercise(cardioExerciseSet.getExercise());
		}
		this.duration = cardioExerciseSet.getDuration();
		this.avgHR = cardioExerciseSet.getAvgHR();
		this.intensity = cardioExerciseSet.getIntensity();
		this.notes = cardioExerciseSet.getNotes();
	}
}
