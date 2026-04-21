package com.group3.model;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int userID;
	private String username;
	private String passwd;
	private int age;
	private String gender;
	private double height;
	private double weight;
	private WorkoutGoal goal;
	private List<WorkoutLog> workoutLog;
	private List<NutritionLog> nutritionLog;
	public User() {
		this.workoutLog = new ArrayList<>();
		this.nutritionLog = new ArrayList<>();
	}
	public User(int userID, String username, String passwd, int age, String gender, double height, double weight,
			WorkoutGoal goal) {
		this.userID = userID;
		this.username = username;
		this.passwd = passwd;
		this.age = age;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.goal = goal;
		this.workoutLog = new ArrayList<>();
		this.nutritionLog = new ArrayList<>();
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public WorkoutGoal getGoal() {
		return goal;
	}
	public void setGoal(WorkoutGoal goal) {
		this.goal = goal;
	}
	public List<WorkoutLog> getWorkoutLog() {
		return workoutLog;
	}
	public void setWorkoutLog(List<WorkoutLog> workoutLog) {
		this.workoutLog = workoutLog;
	}
	public List<NutritionLog> getNutritionLog() {
		return nutritionLog;
	}
	public void setNutritionLog(List<NutritionLog> nutritionLog) {
		this.nutritionLog = nutritionLog;
	}
	
}
