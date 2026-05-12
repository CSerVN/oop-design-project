package com.group3.strategy;

import java.util.List;

import com.group3.model.*;

public interface ExerciseSuggestionStrategy {
	public List<Exercise> suggest(User user, ExerciseLibrary lib);
}
