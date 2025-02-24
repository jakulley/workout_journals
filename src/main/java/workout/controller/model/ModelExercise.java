package workout.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.entity.Category;
import workout.entity.Exercise;

@Data
@NoArgsConstructor
public class ModelExercise {
	private Long exercise_id;
	private String exercise_name;
	private Boolean compound;
	private Set<ModelCategory> categories = new HashSet<ModelCategory>();
	
	public ModelExercise(Exercise exercise) {
		this.exercise_id = exercise.getExercise_id();
		this.exercise_name = exercise.getExercise_name();
		this.compound = exercise.getCompound();
		if (exercise.getCategories() != null) {
			for(Category category : exercise.getCategories()) {
				ModelCategory modelCategory = new ModelCategory(category);
				this.categories.add(modelCategory);
			}
		}
	}
}
