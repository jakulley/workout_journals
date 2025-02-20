package workout.controller.model;

import java.util.Objects;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.entity.CardioExerciseSet;
import workout.entity.ExerciseSet;
import workout.entity.Workout;

@Data
@NoArgsConstructor
public class WorkoutData {

	private Long workout_id;
	private String notes;
	private String date;
	private Set<ExerciseSet> exerciseSets;
	private Set<CardioExerciseSet> cardioExerciseSets;
	
	public WorkoutData(Workout workout) {
		this.workout_id = workout.getWorkout_id();
		this.date = workout.getDate();
		this.notes = workout.getNotes();
		if (Objects.nonNull(workout.getExerciseSets())) {
			for (ExerciseSet exerciseSet : workout.getExerciseSets()) {
				this.exerciseSets.add(exerciseSet);
			}
		}
		if (Objects.nonNull(workout.getCardioExerciseSets())) {
			for (CardioExerciseSet cardioeExerciseSet : workout.getCardioExerciseSets()) {
				this.cardioExerciseSets.add(cardioeExerciseSet);
			}
		}
	}
	
	@Data
	@NoArgsConstructor
	static class WorkoutResponse {
		private Long workout_id;
		private String date;
		private String notes;
		private Set<ExerciseSet> exerciseSets;
		private Set<CardioExerciseSet> cardioExerciseSets;
		
		WorkoutResponse(Workout workout) {
			this.workout_id = workout.getWorkout_id();
			this.date = workout.getDate();
			this.notes = workout.getNotes();
			if (Objects.nonNull(workout.getExerciseSets())) {
				for (ExerciseSet exerciseSet : workout.getExerciseSets()) {
					this.exerciseSets.add(exerciseSet);
				}
			}
			if (Objects.nonNull(workout.getCardioExerciseSets())) {
				for (CardioExerciseSet cardioeExerciseSet : workout.getCardioExerciseSets()) {
					this.cardioExerciseSets.add(cardioeExerciseSet);
				}
			}
		}
	}
}
