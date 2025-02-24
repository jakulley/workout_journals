package workout.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class CardioExerciseSet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardio_exercise_set_id;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@JsonBackReference  
	@ManyToOne
	@JoinColumn(name = "workout_id", nullable = false)
	private Workout workout;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude  
	@ManyToOne(cascade = CascadeType.MERGE)
	private Exercise exercise;
	private Long duration;
	private Long avgHR;
	private Long intensity;
	private String notes;
}
