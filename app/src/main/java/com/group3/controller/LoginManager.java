package com.group3.controller;

import com.group3.model.IAccount;
import com.group3.model.User;
import com.group3.model.Admin;
import com.group3.model.JsonUserDatabase;
import com.group3.model.JsonAdminDatabase;

public class LoginManager {
    private JsonUserDatabase userDatabase;
    private JsonAdminDatabase adminDatabase;

    public LoginManager(JsonUserDatabase userDatabase, JsonAdminDatabase adminDatabase) {
        this.userDatabase = userDatabase;
        this.adminDatabase = adminDatabase;
    }

    public IAccount login(String username, String password) {
    	// Invoke checkLogin method to compare to account in our system
        Admin admin = adminDatabase.checkLogin(username, password);
        if (admin != null) {
            System.out.println("Đăng nhập vào hệ thống thành công! Bạn hiện là Quản Trị Viên!");
            return admin;
        }
        User user = userDatabase.checkLogin(username, password);
        if (user != null) {
            System.out.println("Đăng nhập vào hệ thống thành công! Chào mừng " + user.getName());
            return user;
        }
        
        System.out.println("Lỗi: Sai tên đăng nhập hoặc mật khẩu!");
        return null;
    }
}