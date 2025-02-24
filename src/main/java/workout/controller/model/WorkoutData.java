package workout.controller.model;

import java.util.HashSet;
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
	private Set<ModelExerciseSet> exerciseSets = new HashSet<ModelExerciseSet>();
	private Set<ModelCardioExerciseSet> cardioExerciseSets = new HashSet<ModelCardioExerciseSet>();
	
	public WorkoutData(Workout workout) {
        this.workout_id = workout.getWorkout_id();
        this.date = workout.getDate();
        this.notes = workout.getNotes();
        
        this.exerciseSets = new HashSet<>(); // ✅ Ensure non-null collection
        if (workout.getExerciseSets() != null) {
            for (ExerciseSet set : workout.getExerciseSets()) {
            	ModelExerciseSet modelExerciseSet = new ModelExerciseSet(set);
                this.exerciseSets.add(modelExerciseSet);  // ✅ Convert to DTO
            }
        }

        this.cardioExerciseSets = new HashSet<>(); // ✅ Ensure non-null collection
        if (workout.getCardioExerciseSets() != null) {
            for (CardioExerciseSet set : workout.getCardioExerciseSets()) {
            	ModelCardioExerciseSet modelCardioExerciseSet = new ModelCardioExerciseSet(set);
                this.cardioExerciseSets.add(modelCardioExerciseSet);  // ✅ Convert to DTO
            }
        }
    }
	
}
