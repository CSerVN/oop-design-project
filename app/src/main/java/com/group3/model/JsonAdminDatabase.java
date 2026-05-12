package com.group3.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonAdminDatabase implements DataConnection<List<Admin>> {
	private final String filePath = "admin.json";
	private final Gson gson;

	public JsonAdminDatabase() {
		this.gson = new GsonBuilder().setPrettyPrinting().create();
	}

	@Override
	public List<Admin> loadData() {
		try (Reader reader = new FileReader(filePath)) {
			Type listType = new TypeToken<ArrayList<Admin>>() {
			}.getType();
			List<Admin> admins = gson.fromJson(reader, listType);

			return admins != null ? admins : new ArrayList<>();
		} catch (FileNotFoundException e) {
			System.out.println("Không tìm thấy file " + filePath + ". Hệ thống sẽ tự tạo mới khi có dữ liệu.");
			return new ArrayList<>();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public boolean saveData(List<Admin> data) {
		try (Writer writer = new FileWriter(filePath)) {
			gson.toJson(data, writer);
			return true;
		} catch (IOException e) {
			System.out.println("Lỗi khi lưu file " + filePath);
			e.printStackTrace();
			return false;
		}
	}

	// Extra method for LoginManager
	public Admin checkLogin(String username, String password) {
		List<Admin> admins = loadData();
		for (Admin admin : admins) {
			if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
				return admin;
			}
		}
		return null;
	}
	public boolean isUsernameExist(String username) {
		List<Admin> admins = loadData();
		for (Admin admin : admins) {
			if (admin.getUsername().equalsIgnoreCase(username)) {
				return true;
			}
		}
		return false;
	}
}