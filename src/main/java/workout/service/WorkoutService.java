package workout.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import workout.controller.model.ModelCardioExerciseSet;
import workout.controller.model.ModelCategory;
import workout.controller.model.ModelExercise;
import workout.controller.model.ModelExerciseSet;
import workout.controller.model.WorkoutData;
import workout.dao.CardioExerciseSetDao;
import workout.dao.CategoryDao;
import workout.dao.ExerciseDao;
import workout.dao.ExerciseSetDao;
import workout.dao.WorkoutDao;
import workout.entity.CardioExerciseSet;
import workout.entity.Category;
import workout.entity.Exercise;
import workout.entity.ExerciseSet;
import workout.entity.Workout;

@Service
public class WorkoutService {
	
	private static final Logger log = LoggerFactory.getLogger(WorkoutService.class);

	@Autowired
	private WorkoutDao workoutDao;
	@Autowired
	private ExerciseDao exerciseDao;
	@Autowired
	private CardioExerciseSetDao cardioExerciseSetDao;
	@Autowired
	private ExerciseSetDao exerciseSetDao;
	@Autowired
	private CategoryDao categoryDao;

	@Transactional(readOnly = false)
	public WorkoutData saveWorkout(WorkoutData workoutData) {
		Workout workout = saveWorkoutEntity(workoutData);
		return new WorkoutData(workout);
	}
	
	@Transactional(readOnly = false)
	private Workout saveWorkoutEntity(WorkoutData workoutData) {
		Long workoutId = workoutData.getWorkout_id();
		Workout workout = findOrCreateWorkout(workoutId);
		setFieldsInWorkout(workout, workoutData);
		workout = workoutDao.save(workout);
		for (ModelExerciseSet set : workoutData.getExerciseSets()) {
			saveExerciseSet(set, workout);
		}
		for (ModelCardioExerciseSet set : workoutData.getCardioExerciseSets()) {
			saveCardioExerciseSet(set, workout);
		}
		workout.setExerciseSets(null);
		return workout;
	}
	
	@Transactional(readOnly = false)
	public ModelExercise saveExercise(ModelExercise modelExercise) {
		Exercise exercise = saveExerciseEntity(modelExercise);
		return new ModelExercise(exercise);
	}
	
	@Transactional(readOnly = false)
	private Exercise saveExerciseEntity(ModelExercise modelExercise) {
		Long exerciseId = modelExercise.getExercise_id();
		Exercise exercise =  findOrCreateExercise(exerciseId);
		setFieldsInExercise(exercise, modelExercise);
		return exerciseDao.save(exercise);
	}
	
	@Transactional(readOnly = false)
	public ModelCardioExerciseSet saveCardioExerciseSet(ModelCardioExerciseSet modelCardioExerciseSet, Workout workout) {
		CardioExerciseSet cardioExerciseSet = saveCardioExerciseSetEntity(modelCardioExerciseSet, workout);
		return new ModelCardioExerciseSet(cardioExerciseSet);
	}
	
	@Transactional(readOnly = false)
	private CardioExerciseSet saveCardioExerciseSetEntity(ModelCardioExerciseSet modelCardioExerciseSet, Workout workout) {
		Long modelCardioExerciseSetId = modelCardioExerciseSet.getCardio_exercise_set_id();
		CardioExerciseSet cardioExerciseSet =  findOrCreateCardioExerciseSet(modelCardioExerciseSetId);
		setFieldsInCardioExerciseSet(cardioExerciseSet, modelCardioExerciseSet, workout);
		return cardioExerciseSetDao.save(cardioExerciseSet);
	}
	
	@Transactional(readOnly = false)
	public ModelCardioExerciseSet saveCardioExerciseSet(ModelCardioExerciseSet modelCardioExerciseSet) {
		CardioExerciseSet cardioExerciseSet = saveCardioExerciseSetEntity(modelCardioExerciseSet);
		return new ModelCardioExerciseSet(cardioExerciseSet);
	}
	
	@Transactional(readOnly = false)
	private CardioExerciseSet saveCardioExerciseSetEntity(ModelCardioExerciseSet modelCardioExerciseSet) {
		Long modelCardioExerciseSetId = modelCardioExerciseSet.getCardio_exercise_set_id();
		CardioExerciseSet cardioExerciseSet =  findOrCreateCardioExerciseSet(modelCardioExerciseSetId);
		setFieldsInCardioExerciseSet(cardioExerciseSet, modelCardioExerciseSet);
		return cardioExerciseSetDao.save(cardioExerciseSet);
	}
	
	@Transactional(readOnly = false)
	public ModelExerciseSet saveExerciseSet(ModelExerciseSet modelExerciseSet, Workout workout) {
		ExerciseSet exerciseSet = saveExerciseSetEntity(modelExerciseSet, workout);
		return new ModelExerciseSet(exerciseSet);
	}
	
	@Transactional(readOnly = false)
	private ExerciseSet saveExerciseSetEntity(ModelExerciseSet modelExerciseSet, Workout workout) {
		Long modelExerciseSetId = modelExerciseSet.getExercise_set_id();
		ExerciseSet exerciseSet =  findOrCreateExerciseSet(modelExerciseSetId);
		setFieldsInExerciseSet(exerciseSet, modelExerciseSet, workout);
		return exerciseSetDao.save(exerciseSet);
	}
	
	@Transactional(readOnly = false)
	public ModelExerciseSet saveExerciseSet(ModelExerciseSet modelExerciseSet) {
		ExerciseSet exerciseSet = saveExerciseSetEntity(modelExerciseSet);
		return new ModelExerciseSet(exerciseSet);
	}
	
	@Transactional(readOnly = false)
	private ExerciseSet saveExerciseSetEntity(ModelExerciseSet modelExerciseSet) {
		Long modelExerciseSetId = modelExerciseSet.getExercise_set_id();
		ExerciseSet exerciseSet =  findOrCreateExerciseSet(modelExerciseSetId);
		setFieldsInExerciseSet(exerciseSet, modelExerciseSet);
		return exerciseSetDao.save(exerciseSet);
	}
	
	@Transactional(readOnly = false)
	public ModelCategory saveCategory(ModelCategory modelCategory) {
		Category category = saveCategoryEntity(modelCategory);
		return new ModelCategory(category);
	}
	
	@Transactional(readOnly = false)
	private Category saveCategoryEntity(ModelCategory modelCategory) {
		Long modelCategoryId = modelCategory.getCategory_id();
		Category category =  findOrCreateCategory(modelCategoryId);
		setFieldsInCategory(category, modelCategory);
		return categoryDao.save(category);
	}


	
	@Transactional(readOnly = false)
	private ExerciseSet findOrCreateExerciseSet(Long modelExerciseSetId) {
		ExerciseSet exerciseSet;
		if(Objects.isNull(modelExerciseSetId)) {
			exerciseSet = new ExerciseSet();
		} else {
			exerciseSet = findExerciseSet(modelExerciseSetId);
		}
		return exerciseSet;
	}
	
	@Transactional(readOnly = false)
	private Category findOrCreateCategory(Long modelCategoryId) {
		Category category;
		if(Objects.isNull(modelCategoryId)) {
			category = new Category();
		} else {
			category = findCategory(modelCategoryId);
		}
		return category;
	}
	
	@Transactional(readOnly = false)
	private CardioExerciseSet findOrCreateCardioExerciseSet(Long modelCardioExerciseSetId) {
		CardioExerciseSet cardioExerciseSet;
		if(Objects.isNull(modelCardioExerciseSetId)) {
			cardioExerciseSet = new CardioExerciseSet();
		} else {
			cardioExerciseSet = findCardioExerciseSet(modelCardioExerciseSetId);
		}
		return cardioExerciseSet;
	}
	
	@Transactional(readOnly = false)
	private void setFieldsInExercise(Exercise exercise, ModelExercise modelExercise) {
		exercise.setExercise_name(modelExercise.getExercise_name());
		exercise.setCompound(modelExercise.getCompound());
		Set<Category> categories = new HashSet<Category>();
		for (ModelCategory modelCategory : modelExercise.getCategories()) {
			Category category = new Category();
			setFieldsInCategory(category, modelCategory);
			categories.add(category);
		}
	}

	@Transactional(readOnly = false)
	private void setFieldsInWorkout(Workout workout, WorkoutData workoutData) {
		workout.setDate(workoutData.getDate());
		workout.setNotes(workoutData.getNotes());
		Set<ExerciseSet> exerciseSets = new HashSet<ExerciseSet>();
		for (ModelExerciseSet set : workoutData.getExerciseSets()) {
			ExerciseSet exerciseSet = new ExerciseSet();
			setFieldsInExerciseSet(exerciseSet, set, workout);
			exerciseSets.add(exerciseSet);
		}
		workout.setExerciseSets(exerciseSets);
		Set<CardioExerciseSet> cardioExerciseSets = new HashSet<CardioExerciseSet>();
		for (ModelCardioExerciseSet set : workoutData.getCardioExerciseSets()) {
			CardioExerciseSet cardioExerciseSet = new CardioExerciseSet();
			setFieldsInCardioExerciseSet(cardioExerciseSet, set, workout);
			cardioExerciseSets.add(cardioExerciseSet);
		}
		workout.setCardioExerciseSets(cardioExerciseSets);
	}
	
	@Transactional(readOnly = false)
	private void setFieldsInCardioExerciseSet(CardioExerciseSet cardioExerciseSet,
			ModelCardioExerciseSet modelCardioExerciseSet, Workout workout) {
		cardioExerciseSet.setWorkout(workout);
		cardioExerciseSet.setAvgHR(modelCardioExerciseSet.getAvgHR());
		cardioExerciseSet.setDuration(modelCardioExerciseSet.getDuration());
		if (modelCardioExerciseSet.getExercise() != null) {
			Exercise exercise = findExerciseById(modelCardioExerciseSet.getExercise().getExercise_id());
			cardioExerciseSet.setExercise(exercise);
		}
		
		cardioExerciseSet.setIntensity(modelCardioExerciseSet.getIntensity());
		cardioExerciseSet.setNotes(modelCardioExerciseSet.getNotes());			
	}
	
	@Transactional(readOnly = false)
	private void setFieldsInCardioExerciseSet(CardioExerciseSet cardioExerciseSet,
			ModelCardioExerciseSet modelCardioExerciseSet) {
		cardioExerciseSet.setAvgHR(modelCardioExerciseSet.getAvgHR());
		cardioExerciseSet.setDuration(modelCardioExerciseSet.getDuration());
		if (modelCardioExerciseSet.getExercise() != null) {
			Exercise exercise = findExerciseById(modelCardioExerciseSet.getExercise().getExercise_id());
			cardioExerciseSet.setExercise(exercise);
		}
		
		cardioExerciseSet.setIntensity(modelCardioExerciseSet.getIntensity());
		cardioExerciseSet.setNotes(modelCardioExerciseSet.getNotes());
		if (modelCardioExerciseSet.getWorkout_id() != null) {
			Workout workout = findWorkoutById(modelCardioExerciseSet.getWorkout_id());
			cardioExerciseSet.setWorkout(workout);
		}			
	}
	
	@Transactional(readOnly = false)
	private void setFieldsInExerciseSet(ExerciseSet exerciseSet, ModelExerciseSet modelExerciseSet, Workout workout) {
		exerciseSet.setWorkout(workout);
		exerciseSet.setBodyweight(modelExerciseSet.getBodyweight());
		exerciseSet.setFailure(modelExerciseSet.getFailure());
		if (modelExerciseSet.getExercise() != null) {
			Exercise exercise = findExerciseById(modelExerciseSet.getExercise().getExercise_id());
			exerciseSet.setExercise(exercise);
		}
		exerciseSet.setReps(modelExerciseSet.getReps());
		exerciseSet.setWeight(modelExerciseSet.getWeight());
		exerciseSet.setSuperset(modelExerciseSet.getSuperset());
		exerciseSet.setTwo_sides(modelExerciseSet.getTwo_sides());
		exerciseSet.setNotes(modelExerciseSet.getNotes());
	}
	
	@Transactional(readOnly = false)
	private void setFieldsInExerciseSet(ExerciseSet exerciseSet, ModelExerciseSet modelExerciseSet) {
		exerciseSet.setBodyweight(modelExerciseSet.getBodyweight());
		exerciseSet.setFailure(modelExerciseSet.getFailure());
		if (modelExerciseSet.getExercise() != null) {
			Exercise exercise = findExerciseById(modelExerciseSet.getExercise().getExercise_id());
			exerciseSet.setExercise(exercise);
		}
		exerciseSet.setReps(modelExerciseSet.getReps());
		exerciseSet.setWeight(modelExerciseSet.getWeight());
		exerciseSet.setSuperset(modelExerciseSet.getSuperset());
		exerciseSet.setTwo_sides(modelExerciseSet.getTwo_sides());
		exerciseSet.setNotes(modelExerciseSet.getNotes());
		if (modelExerciseSet.getWorkout_id() != null) {
			Workout workout = findWorkoutById(modelExerciseSet.getWorkout_id());
			exerciseSet.setWorkout(workout);
		}
	}
	
	@Transactional(readOnly = false)
	private void setFieldsInCategory(Category category, ModelCategory modelCategory) {
		category.setCategory_name(modelCategory.getCategory_name());
	}

	@Transactional(readOnly = false)
	private Workout findOrCreateWorkout(Long workoutId) {
		Workout workout;
		if(Objects.isNull(workoutId)) {
			workout = new Workout();
		} else {
			workout = findWorkoutById(workoutId);
		}
		return workout;
	}
	
	@Transactional(readOnly = false)
	private Exercise findOrCreateExercise(Long exerciseId) {
		Exercise exercise;
		if(Objects.isNull(exerciseId)) {
			exercise = new Exercise();
		} else {
			exercise = findExerciseById(exerciseId);
		}
		return exercise;
	}
	
	@Transactional(readOnly = false)
	public Exercise findExerciseById(Long exerciseId) {
		Exercise exercise = exerciseDao.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("Exercise with ID=" + exerciseId + " was not found."));
		return exercise;
	}
	
	@Transactional(readOnly = false)
	private CardioExerciseSet findCardioExerciseSet(Long modelCardioExerciseSetId) {
		return cardioExerciseSetDao.findById(modelCardioExerciseSetId).orElseThrow(() -> new NoSuchElementException("Cardio Exercise Set with ID=" + modelCardioExerciseSetId + " was not found."));

	}
	
	@Transactional(readOnly = false)
	private ExerciseSet findExerciseSet(Long modelExerciseSetId) {
		return exerciseSetDao.findById(modelExerciseSetId).orElseThrow(() -> new NoSuchElementException("Cardio Exercise Set with ID=" + modelExerciseSetId + " was not found."));

	}
	
	@Transactional(readOnly = false)
	private Category findCategory(Long modelCategoryId) {
		return categoryDao.findById(modelCategoryId).orElseThrow(() -> new NoSuchElementException("Category with ID=" + modelCategoryId + " was not found."));
	}

	@Transactional
	public void deleteWorkoutById(Long workoutId) {
	    Workout workout = findWorkoutById(workoutId);

	    log.info("Deleting workout ID {} with {} exercise sets and {} cardio sets.", 
	        workout.getWorkout_id(), 
	        workout.getExerciseSets().size(), 
	        workout.getCardioExerciseSets().size());

	    if (!workout.getExerciseSets().isEmpty()) {
	        exerciseSetDao.deleteAll(workout.getExerciseSets());
	        log.info("Deleted all ExerciseSets linked to workout ID {}", workout.getWorkout_id());
	    } else {
	        log.info("No ExerciseSets found for workout ID {}", workout.getWorkout_id());
	    }

	    if (!workout.getCardioExerciseSets().isEmpty()) {
	        cardioExerciseSetDao.deleteAll(workout.getCardioExerciseSets());
	        log.info("Deleted all CardioExerciseSets linked to workout ID {}", workout.getWorkout_id());
	    } else {
	        log.info("No CardioExerciseSets found for workout ID {}", workout.getWorkout_id());
	    }

	    workoutDao.delete(workout);
	    log.info("Workout ID {} deleted successfully.", workoutId);
	}


	@Transactional(readOnly = false)
	private Workout findWorkoutById(Long workoutId) {
		Workout workout = workoutDao.findById(workoutId).orElseThrow(() -> new NoSuchElementException("Workout with ID=" + workoutId + " was not found."));
		return workout;
	}

	@Transactional(readOnly = true)
	public WorkoutData retrieveWorkoutById(Long workoutId) {
		Workout workout = findWorkoutById(workoutId);
		return new WorkoutData(workout);
	}

	public List<WorkoutData> retrieveWorkouts() {
		List<WorkoutData> workoutDatas = new ArrayList<WorkoutData>();
		List<Workout> workouts = workoutDao.findAll();
		for (Workout workout : workouts) {
			WorkoutData newWorkoutData = new WorkoutData(workout);
			workoutDatas.add(newWorkoutData);
		}
		return workoutDatas;
	}

	
	

	

	

	


}
