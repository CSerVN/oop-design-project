package com.group3;

import com.group3.controller.LoginManager;
import com.group3.controller.RegisterManager;
import com.group3.model.JsonAdminDatabase;
import com.group3.model.JsonUserDatabase;
import com.group3.model.User;
import com.group3.model.WorkoutGoal;

public class GymTracking {
    public static void main(String[] args) {
        JsonUserDatabase userDB = new JsonUserDatabase();
        JsonAdminDatabase adminDB = new JsonAdminDatabase();
        LoginManager loginManager = new LoginManager(userDB, adminDB);
        RegisterManager registerManager = new RegisterManager(userDB);

        User newUser = new User(0, "Nguyen Van A", "nguyenvana", "nguyenvana123", 
                                22, "Nam", 1.76, 79.0, WorkoutGoal.MUSCLE_GAIN);
        User newUser1 = new User(0, "Nguyen A Van", "nguyenvana", "nguyenvana", 
                22, "Nữ", 1.56, 56.0, WorkoutGoal.MAINTENANCE);
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
    }
}