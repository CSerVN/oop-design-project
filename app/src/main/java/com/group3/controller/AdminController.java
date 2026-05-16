package com.group3.controller;

import java.util.List;

import com.group3.model.*;

public class AdminController {
	private ExerciseLibrary exerciseLibrary;
	private Admin currentAdmin;
	private JsonLibraryDatabase libraryDB;
    private JsonUserDatabase userDB;

	public AdminController(ExerciseLibrary exerciseLibrary, Admin currentAdmin, JsonLibraryDatabase libraryDB,
			JsonUserDatabase userDB) {
		this.exerciseLibrary = exerciseLibrary;
		this.currentAdmin = currentAdmin;
		this.libraryDB = libraryDB;
		this.userDB = userDB;
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
            
            libraryDB.saveData(exerciseLibrary.getLib());
            
            System.out.println("Đã thêm bài tập: " + newExercise.getExerciseName() + " thành công!");
            return true;

        } catch (IllegalStateException e) {
            System.out.println("Không thể tạo bài tập! Lỗi: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteExercise(String exerciseName) {
        if (currentAdmin == null) {
            System.out.println("Lỗi: Quyền truy cập bị từ chối!");
            return false;
        }

        Exercise target = exerciseLibrary.searchExercise(exerciseName);
        if (target != null) {
            exerciseLibrary.removeExercise(target);
            System.out.println("Đã xóa bài tập: " + exerciseName);
            return true;
        } else {
            System.out.println("Không tìm thấy bài tập cần xóa!");
            return false;
        }
    }

    public List<User> getUserList() {
        if (currentAdmin == null) {
            System.err.println("Lỗi: Quyền truy cập bị từ chối!");
            return null;
        }
        return userDB.loadData();
    }

}
