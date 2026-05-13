package com.group3;

import java.time.LocalDateTime;

import com.group3.controller.LoginManager;
import com.group3.controller.NutritionLogController;
import com.group3.controller.RegisterManager;
import com.group3.controller.WorkoutHandling;
import com.group3.controller.WorkoutLogController;
import com.group3.model.Exercise;
import com.group3.model.ExerciseCategory;
import com.group3.model.JsonAdminDatabase;
import com.group3.model.JsonLogDatabase;
import com.group3.model.JsonUserDatabase;
import com.group3.model.NutritionLog;
import com.group3.model.OpenFoodFactsAdapter;
import com.group3.model.TrackingType;
import com.group3.model.User;
import com.group3.model.WorkoutGoal;
import com.group3.model.WorkoutLog;
import com.group3.strategy.RecommendationResult;

public class GymTracking {
	public static void main(String[] args) {
		JsonUserDatabase userDB = new JsonUserDatabase();
		JsonAdminDatabase adminDB = new JsonAdminDatabase();
		LoginManager loginManager = new LoginManager(userDB, adminDB);
		RegisterManager registerManager = new RegisterManager(userDB);

		User newUser = new User(0, "Nguyen Van A", "nguyenvana", "nguyenvana123", 22, "Nam", 1.76, 79.0,
				WorkoutGoal.MUSCLE_GAIN);
		User newUser1 = new User(0, "Nguyen A Van", "nguyenvana", "nguyenvana", 22, "Nữ", 1.56, 56.0,
				WorkoutGoal.MAINTENANCE);
		// Register test
		registerManager.register(newUser);

		// Try with the same username
		registerManager.register(newUser1);

		// Login test
		// Logon failed
		loginManager.login("thinh", "thinh123");

		// Logon successed
		loginManager.login("nguyenvana", "nguyenvana123");

		// Admin logon
		loginManager.login("admin", "123");

		JsonLogDatabase logDatabase = new JsonLogDatabase();
		OpenFoodFactsAdapter nutritionAPI = new OpenFoodFactsAdapter();

		NutritionLogController nutritionController = new NutritionLogController(logDatabase, nutritionAPI);
		WorkoutLogController workoutController = new WorkoutLogController(logDatabase);
		WorkoutHandling workoutHandling = new WorkoutHandling();

		workoutHandling.setGoal(newUser);

		System.out.println("\n--- Look up nutrition test ---");
		testNutritionFlow(nutritionController);

		System.out.println("\n--- Record workout test ---");
		testWorkoutFlow(workoutController, workoutHandling);
	}

	private static void testNutritionFlow(NutritionLogController controller) {
		NutritionLog foundItem = controller.lookupNutrition("coca cola");

		if (foundItem != null) {
			System.out.println(foundItem);

			System.out.println("\nTiến hành thêm món này vào Nhật ký...");
			NutritionLog logToSave = new NutritionLog.Builder().setProductID(foundItem.getProductID())
					.setProductName(foundItem.getProductName()).setEnergy(foundItem.getEnergy())
					.setProtein(foundItem.getProtein()).setFat(foundItem.getFat())
					.setCarbohydrates(foundItem.getCarbohydrates()).setQuantity(2).build();

			controller.addNutritionLog(logToSave);
		} else {
			System.out.println("Test Thất bại: Không tìm thấy hoặc API lỗi.");
		}
	}

	private static void testWorkoutFlow(WorkoutLogController controller, WorkoutHandling handling) {
		Exercise benchPress = new Exercise.ExerciseBuilder().setExerciseID(101)
				.setExerciseName("Bench Press").setCategory(ExerciseCategory.COMPOUND)
				.setTrackingType(TrackingType.WEIGHT_REP_TIME).setTargetMuscle("Ngực").build();

		System.out.println("Set 1: 50kg - 18 Reps: ");
		WorkoutLog set1Log = new WorkoutLog.WorkoutLogBuilder().setLogID((int) (System.currentTimeMillis() % 10000))
				.setDate(LocalDateTime.now()).setExercise(benchPress).setWeight(50.0).setReps(18).build();
		System.out.println("Gợi ý set tập kế tiếp: ");
		RecommendationResult nextSet = handling.calculateNextSet(set1Log);
		System.out.println(nextSet);

		System.out.println("\nTiến hành lưu Set 1 vào JSON...");
		controller.addWorkoutLog(set1Log);
	}

}