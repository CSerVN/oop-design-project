package com.group3.strategy;

import com.group3.model.*;

import java.util.*;

public class FitStrategy implements ExerciseSuggestionStrategy {

	@Override
	public List<Exercise> suggest(User user, ExerciseLibrary lib) {
		List<Exercise> result = new ArrayList<>();
		result.addAll(pickRandom(lib.getByCategory(ExerciseCategory.CARDIO), 2));
		result.addAll(pickRandom(lib.getByCategory(ExerciseCategory.COMPOUND), 2));
		result.addAll(pickRandom(lib.getByCategory(ExerciseCategory.FLEXIBILITY), 2));
		return result;
	}

}
