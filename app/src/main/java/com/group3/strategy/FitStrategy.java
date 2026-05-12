package com.group3.strategy;

import com.group3.model.*;

import java.util.*;


public class FitStrategy implements ExerciseSuggestionStrategy {

	@Override
	public List<Exercise> suggest(User user, ExerciseLibrary lib) {
		List<Exercise> result = new ArrayList<>();
		lib.getByCategory(ExerciseCategory.COMPOUND);
		lib.getByCategory(ExerciseCategory.ISOLATE);
		lib.getByCategory(ExerciseCategory.CARDIO);
		return result;
	}
	
}
