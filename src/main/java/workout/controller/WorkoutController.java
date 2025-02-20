package workout.controller;

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
import workout.controller.model.ModelExercise;
import workout.controller.model.WorkoutData;
import workout.entity.Workout;
import workout.service.WorkoutService;

@RestController
@RequestMapping("/workout_journals")
@Slf4j
public class WorkoutController {
	@Autowired
	private WorkoutService workoutService;
	
	@PostMapping("/workout_journals/workout")
	public WorkoutData insertWorkout(@RequestBody WorkoutData workoutData) {
		log.info("Created workout for date {}", workoutData.getDate());
		return workoutService.saveWorkout(workoutData);
	}
	
	@PostMapping("/workout_journals/exercise")
	public ModelExercise insertExercise(@RequestBody ModelExercise modelExercise) {
		log.info("Created exercise with id={} called {}", modelExercise.getExercise_id(), modelExercise.getExerciseName());
		return workoutService.saveExercise(modelExercise);
	}
	
	@PutMapping("/workout_journals/{workoutId}")
	public WorkoutData modifyWorkout(@RequestBody WorkoutData workoutData, @PathVariable Long workoutId) {
		workoutData.setWorkout_id(workoutId);
		log.info("Modified workout on date {}", workoutData.getDate());
		return workoutService.saveWorkout(workoutData);
	}
	
	@DeleteMapping("/workout_journals/{workoutId}")
	public Map<String, String> deleteWorkoutById(@PathVariable Long workoutId) {
		log.info("Deleting workout with ID={}", workoutId);
		workoutService.deleteWorkoutById(workoutId);
		return Map.of("message", "Deletion of workout with ID=" + workoutId + " was successful.");
	}
	
	@GetMapping("/workout_journals/{workoutId}")
	public WorkoutData retrieveWorkoutById(@PathVariable Long workoutId) {
		log.info("Retrieving workout with ID={}", workoutId);
		return workoutService.retrieveWorkoutById(workoutId);
	}
}
