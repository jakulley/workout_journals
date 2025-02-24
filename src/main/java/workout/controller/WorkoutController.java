package workout.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import workout.controller.model.ModelCardioExerciseSet;
import workout.controller.model.ModelCategory;
import workout.controller.model.ModelExercise;
import workout.controller.model.ModelExerciseSet;
import workout.controller.model.WorkoutData;
import workout.service.WorkoutService;

@RestController
@RequestMapping("/workout_journals/workout_journals")
@Slf4j
public class WorkoutController {
	@Autowired
	private WorkoutService workoutService;
	
	@PostMapping("/workout")
	public WorkoutData insertWorkout(@RequestBody WorkoutData workoutData) {
		log.info("Created workout for date {}", workoutData.getDate());
		return workoutService.saveWorkout(workoutData);
	}
	
	@PutMapping("/exercise")
	public ModelExercise insertExercise(@RequestBody ModelExercise modelExercise) {
		log.info("Created exercise with id={} called {}", modelExercise.getExercise_id(), modelExercise.getExercise_name());
		return workoutService.saveExercise(modelExercise);
	}
	
	@PutMapping("/cardio_exercise_set")
	public ModelCardioExerciseSet insertCardioExerciseSet(@RequestBody ModelCardioExerciseSet modelCardioExerciseSet) {
		log.info("Created cardio exercise set with id={} with exercise {}", modelCardioExerciseSet.getCardio_exercise_set_id(), modelCardioExerciseSet.getExercise());
		return workoutService.saveCardioExerciseSet(modelCardioExerciseSet);
	}
	
	@PutMapping("/exercise_set")
	public ModelExerciseSet insertExerciseSet(@RequestBody ModelExerciseSet modelExerciseSet) {
		log.info("Created exercise set with id={} with exercise {}", modelExerciseSet.getExercise_set_id(), modelExerciseSet.getExercise());
		return workoutService.saveExerciseSet(modelExerciseSet);
	}
	
	@PutMapping("/category")
	public ModelCategory insertCategory(@RequestBody ModelCategory modelCategory) {
		log.info("Created category with id={} called {}", modelCategory.getCategory_id(), modelCategory.getCategory_name());
		return workoutService.saveCategory(modelCategory);
	}
	
	@PutMapping("/workout/{workout_id}")
	public WorkoutData modifyWorkout(@RequestBody WorkoutData workoutData, @PathVariable Long workout_id) {
		workoutData.setWorkout_id(workout_id);
		log.info("Modified workout on date {}", workoutData.getDate());
		return workoutService.saveWorkout(workoutData);
	}
	
	@DeleteMapping("/workout/{workout_id}")
	public Map<String, String> deleteWorkoutById(@PathVariable Long workout_id) {
		log.info("Deleting workout with ID={}", workout_id);
		
		workoutService.deleteWorkoutById(workout_id);
		return Map.of("message", "Deletion of workout with ID=" + workout_id + " was successful.");
	}
	
	@GetMapping("/workout/{workout_id}")
	public WorkoutData retrieveWorkoutById(@PathVariable Long workout_id) {
		log.info("Retrieving workout with ID={}", workout_id);
		return workoutService.retrieveWorkoutById(workout_id);
	}
	
	@GetMapping("/workout")
	public List<WorkoutData> retrieveAllWorkouts() {
		log.info("Retrieving all workouts.");
		return workoutService.retrieveWorkouts();
	}
	
}
