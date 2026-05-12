package com.group3.strategy;

import java.util.*;

import com.group3.model.*;

public class FatStrategy implements ExerciseSuggestionStrategy {
	@Override
	public List<Exercise> suggest(User user, ExerciseLibrary lib) {
		List<Exercise> result = new ArrayList<>();
		lib.getByCategory(ExerciseCategory.COMPOUND);
		lib.getByCategory(ExerciseCategory.ISOLATE);
		lib.getByCategory(ExerciseCategory.CARDIO);
		return result;
	}
}
