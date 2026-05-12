package com.group3.model;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class JsonLogDatabase implements DataConnection<LogCollection> {
	private final String workoutFile = "workoutlogs.json";
	private final String nutritionFile = "nutritionlogs.json";
	private final Gson gson;

	public JsonLogDatabase() {
		this.gson = new GsonBuilder()
				.registerTypeAdapter(LocalDateTime.class,
						(JsonSerializer<LocalDateTime>) (src, typeOfSrc,
								context) -> new JsonPrimitive(src.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)))
				.registerTypeAdapter(LocalDateTime.class,
						(JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> LocalDateTime
								.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME))
				.setPrettyPrinting().create();
	}

	@Override
	public LogCollection loadData() {
		List<WorkoutLog> wLogs = loadList(workoutFile, new TypeToken<ArrayList<WorkoutLog>>() {
		}.getType());
		List<NutritionLog> nLogs = loadList(nutritionFile, new TypeToken<ArrayList<NutritionLog>>() {
		}.getType());
		return new LogCollection(wLogs, nLogs);
	}

	@Override
	public boolean saveData(LogCollection data) {
		boolean wSuccess = saveList(workoutFile, data.getWorkoutLogs());
		boolean nSuccess = saveList(nutritionFile, data.getNutritionLogs());
		return wSuccess && nSuccess;
	}

	private <T> List<T> loadList(String file, Type type) {
		try (Reader reader = new FileReader(file)) {
			List<T> list = gson.fromJson(reader, type);
			return list != null ? list : new ArrayList<>();
		} catch (IOException e) {
			return new ArrayList<>();
		}
	}

	private <T> boolean saveList(String file, List<T> list) {
		try (Writer writer = new FileWriter(file)) {
			gson.toJson(list, writer);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}