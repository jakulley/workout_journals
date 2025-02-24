package workout.controller.model;

import java.util.Objects;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import workout.entity.Category;
import workout.entity.Exercise;

@Data
@NoArgsConstructor
public class ModelCategory {
	private Long category_id;
	private String category_name;
	private Set<Exercise> exercises;
	
	public ModelCategory(Category category) {
		this.category_id = category.getCategory_id();
		this.category_name = category.getCategory_name();
		if (Objects.nonNull(exercises)) {
			for (Exercise exercise : category.getExercises()) {
				this.exercises.add(exercise);
			}
		}
	}
}
