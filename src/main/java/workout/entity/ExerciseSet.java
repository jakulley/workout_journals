package workout.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	@ManyToOne(cascade = CascadeType.ALL)
	private Workout workout;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(cascade = CascadeType.ALL)
	private Exercise exercise;
	private Integer reps;
	private Integer weight;
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean superset = false;
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean failure = false;
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean twoSides = false;
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private Boolean bodyWeight = false;
	private String notes;
}
