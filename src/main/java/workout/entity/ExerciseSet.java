package workout.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class ExerciseSet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long exercise_set_id;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "workout_id", nullable = false)
	@JsonIgnore
	private Workout workout;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.MERGE)
	private Exercise exercise;
	private Integer reps;
	private Integer weight;
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean superset = false;
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean failure = false;
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean two_sides = false;
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean bodyweight = false;
	private String notes;
}
