package com.group3.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonUserDatabase implements DataConnection<List<User>> {
	private final String filePath = "users.json";
	private final Gson gson;

	public JsonUserDatabase() {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
	}

	@Override
	public List<User> loadData() {
		try (Reader reader = new FileReader(filePath)) {
			Type listType = new TypeToken<ArrayList<User>>() {
			}.getType();
			List<User> users = gson.fromJson(reader, listType);
			return users != null ? users : new ArrayList<>();
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file " + filePath + ". Tiến hành tạo mới.");
			return new ArrayList<>();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public boolean saveData(List<User> data) {
		try (Writer writer = new FileWriter(filePath)) {
			gson.toJson(data, writer);
			return true;
		} catch (IOException e) {
			System.out.println("Lỗi khi lưu file " + filePath);
			e.printStackTrace();
			return false;
		}
	}

	// Supporter method for Login and Register functions
	public User checkLogin(String username, String password) {
		List<User> users = loadData();
		for (User user : users) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	// For register manager controller
	public boolean isUsernameExist(String username) {
		List<User> users = loadData();
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		return false;
	}

	public boolean addUser(User newUser) {
		List<User> users = loadData();

		int newId = 1;
		if (!users.isEmpty()) {
			newId = users.get(users.size() - 1).getUserID() + 1;
		}
		newUser.setUserID(newId);
		// Save to users.json file
		users.add(newUser);
		return saveData(users);
	}
}