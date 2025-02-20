package workout.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Workout {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long workout_id;
	private String date;
	private String notes;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "exercise_set_id", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ExerciseSet> exerciseSets;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "cardio_exercise_set_id", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CardioExerciseSet> cardioExerciseSets;

}
