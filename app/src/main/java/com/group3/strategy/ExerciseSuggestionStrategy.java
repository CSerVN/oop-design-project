package com.group3.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.group3.model.*;

public interface ExerciseSuggestionStrategy {
	public List<Exercise> suggest(User user, ExerciseLibrary lib);
	// Create a random list for each Exercise Suggestion Strategy
	default List<Exercise> pickRandom(List<Exercise> source, int count) {
        if (source == null || source.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Exercise> copy = new ArrayList<>(source);
        // Random shuffle
        Collections.shuffle(copy);
        
        int limit = Math.min(count, copy.size());
        return copy.subList(0, limit);
    }
}
