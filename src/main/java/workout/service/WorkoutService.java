package workout.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import workout.controller.model.ModelExercise;
import workout.controller.model.WorkoutData;
import workout.dao.ExerciseDao;
import workout.dao.WorkoutDao;
import workout.entity.Exercise;
import workout.entity.Workout;

@Service
public class WorkoutService {
	@Autowired
	private WorkoutDao workoutDao;
	@Autowired
	private ExerciseDao exerciseDao;

	@Transactional(readOnly = false)
	public WorkoutData saveWorkout(WorkoutData workoutData) {
		Long workoutId = workoutData.getWorkout_id();
		Workout workout = findOrCreateWorkout(workoutId);
		setFieldsInWorkout(workout, workoutData);
		return new WorkoutData(workoutDao.save(workout));
	}
	
	public ModelExercise saveExercise(ModelExercise modelExercise) {
		Long exerciseId = modelExercise.getExercise_id();
		Exercise exercise =  findOrCreateExercise(exerciseId);
		setFieldsInExercise(exercise, modelExercise);
		return new ModelExercise(exerciseDao.save(exercise));
	}

	

	private void setFieldsInExercise(Exercise exercise, ModelExercise modelExercise) {
		exercise.setExerciseName(modelExercise.getExerciseName());
		exercise.setCompound(modelExercise.getCompound());
		exercise.setCategories(modelExercise.getCategories());
	}

	private void setFieldsInWorkout(Workout workout, WorkoutData workoutData) {
		workout.setDate(workoutData.getDate());
		workout.setNotes(workoutData.getNotes());
		workout.setExerciseSets(workoutData.getExerciseSets());
		workout.setCardioExerciseSets(workoutData.getCardioExerciseSets());
	}

	private Workout findOrCreateWorkout(Long workoutId) {
		Workout workout;
		if(Objects.isNull(workoutId)) {
			workout = new Workout();
		} else {
			workout = findWorkoutById(workoutId);
		}
		return workout;
	}
	
	private Exercise findOrCreateExercise(Long exerciseId) {
		Exercise exercise;
		if(Objects.isNull(exerciseId)) {
			exercise = new Exercise();
		} else {
			exercise = findExerciseById(exerciseId);
		}
		return exercise;
	}
	
	private Exercise findExerciseById(Long exerciseId) {
		return exerciseDao.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("Exercise with ID=" + exerciseId + " was not found."));
	}

	public void deleteWorkoutById(Long workoutId) {
		Workout workout = findWorkoutById(workoutId);
		workoutDao.delete(workout);
	}

	private Workout findWorkoutById(Long workoutId) {
		return workoutDao.findById(workoutId).orElseThrow(() -> new NoSuchElementException("Workout with ID=" + workoutId + " was not found."));
	}

	@Transactional(readOnly = true)
	public WorkoutData retrieveWorkoutById(Long workoutId) {
		Workout workout;
		workout = findWorkoutById(workoutId);
		return new WorkoutData(workout);
	}

	


}
