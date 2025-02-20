package workout.controller.model;

import java.util.Objects;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.entity.Category;
import workout.entity.Exercise;

@Data
@NoArgsConstructor
public class ModelExercise {
	private Long exercise_id;
	private String exerciseName;
	private Boolean compound;
	private Set<Category> categories;
	
	public ModelExercise(Exercise exercise) {
		this.exercise_id = exercise.getExercise_id();
		this.exerciseName = exercise.getExerciseName();
		this.compound = exercise.getCompound();
		if (Objects.nonNull(exercise.getCategories())) {
			for(Category category : exercise.getCategories()) {
				this.categories.add(category);
			}
		}
	}
}
