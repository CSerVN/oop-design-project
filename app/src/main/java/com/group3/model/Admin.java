package com.group3.model;

import java.util.ArrayList;
import java.util.List;

public class Admin {
	private String name;
	private List<User> userList;
	public Admin(String name) {
		this.name = name;
		this.userList = new ArrayList<User>();
	}
	
}
