package com.group3.controller;

import com.group3.model.*;

public class AdminController {
	private ExerciseLibrary exerciseLibrary;
	private Admin currentAdmin;

	public AdminController(ExerciseLibrary exerciseLibrary, Admin currentAdmin) {
		this.exerciseLibrary = exerciseLibrary;
		this.currentAdmin = currentAdmin;
	}

	public boolean addExercise(int id, String name, ExerciseCategory category, TrackingType trackingType,
			String targetMuscle) {

		if (currentAdmin == null) {
			System.out.println("Lỗi: Bạn cần có quyền Admin để thực hiện thao tác này!");
			return false;
		}

		try {
			Exercise newExercise = new Exercise.ExerciseBuilder().setExerciseID(id).setExerciseName(name)
					.setCategory(category).setTrackingType(trackingType).setTargetMuscle(targetMuscle).build();

			exerciseLibrary.addExercise(newExercise);
			System.out.println("Đã thêm bài tập: " + newExercise.getExerciseName() + "thành công");
			return true;

		} catch (IllegalStateException e) {
			System.out.println("Không thể tạo bài tập! Lỗi: " + e.getMessage());
			return false;
		}
	}

}
