package workout.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
	@JsonManagedReference  
	@OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<ExerciseSet> exerciseSets;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonManagedReference  
	@OneToMany(mappedBy = "workout", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CardioExerciseSet> cardioExerciseSets;

}
