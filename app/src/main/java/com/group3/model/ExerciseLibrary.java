package com.group3.model;

import java.util.ArrayList;
import java.util.List;

public class ExerciseLibrary {
	private List<Exercise> lib;

	public ExerciseLibrary() {
		this.lib = new ArrayList<>();
	}
	
	public List<Exercise> getLib() {
		return lib;
	}

	public void addExercise(Exercise exercise) {
		if(exercise != null) lib.add(exercise);
	}
	public void removeExercise(Exercise exercise) {
		if(exercise != null) lib.remove(exercise);
	}
	public List<Exercise> getByCategory(ExerciseCategory cat){
		List<Exercise> result = new ArrayList<>();
		for(Exercise exercises : lib) {
			if(exercises.getCategory() == cat) {
				result.add(exercises);
			}
		}
		return result;
	}
	public Exercise searchExercise(String name) {
		for(Exercise exercises : lib) {
			if(exercises.getExerciseName().equalsIgnoreCase(name)) {
				return exercises;
			}
		}
		return null;
	}
}
