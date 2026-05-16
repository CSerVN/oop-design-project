package com.group3.view;

import javax.swing.*;
import java.awt.*;
import com.group3.controller.*;
import com.group3.model.*;

public class LoginForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private LoginManager loginManager;
    private RegisterManager registerManager;
    private JsonLibraryDatabase libraryDB;
    private JsonUserDatabase userDB;
    private WorkoutLogController workoutCtrl;
    private WorkoutHandling workoutHandling;
    private ExerciseLibrary library;
    private NutritionLogController nutritionCtrl;
    private StatisticsPresenter statPresenter;
    private ExerciseSuggestionService suggestionService;

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnRegister;

    public LoginForm(LoginManager loginManager, RegisterManager registerManager, 
                     JsonLibraryDatabase libraryDB, JsonUserDatabase userDB, 
                     WorkoutLogController workoutCtrl, WorkoutHandling workoutHandling, 
                     ExerciseLibrary library, NutritionLogController nutritionCtrl, 
                     StatisticsPresenter statPresenter, ExerciseSuggestionService suggestionService) {
        
        this.loginManager = loginManager;
        this.registerManager = registerManager;
        this.libraryDB = libraryDB;
        this.userDB = userDB;
        this.workoutCtrl = workoutCtrl;
        this.workoutHandling = workoutHandling;
        this.library = library;
        this.nutritionCtrl = nutritionCtrl;
        this.statPresenter = statPresenter;
        this.suggestionService = suggestionService;
        
        initComponents();
        setupEvents();
    }

    private void initComponents() {
        setTitle("Gym Tracking - Đăng Nhập");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitle = new JLabel("ĐĂNG NHẬP HỆ THỐNG", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(lblTitle, gbc);

        gbc.gridwidth = 1; gbc.gridy = 1;
        mainPanel.add(new JLabel("Tên đăng nhập:"), gbc);
        txtUsername = new JTextField(15);
        gbc.gridx = 1; mainPanel.add(txtUsername, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("Mật khẩu:"), gbc);
        txtPassword = new JPasswordField(15);
        gbc.gridx = 1; mainPanel.add(txtPassword, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        btnLogin = new JButton("Đăng nhập");
        btnRegister = new JButton("Đăng ký");
        btnPanel.add(btnLogin);
        btnPanel.add(btnRegister);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        mainPanel.add(btnPanel, gbc);

        add(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupEvents() {
        btnLogin.addActionListener(e -> handleLogin());
        txtPassword.addActionListener(e -> handleLogin());
        btnRegister.addActionListener(e -> new RegisterForm(registerManager).setVisible(true));
    }

    private void handleLogin() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        IAccount account = loginManager.login(username, password);
        
        if (account != null) {
            if (account instanceof Admin) {
                Admin admin = (Admin) account;
                AdminController adminController = new AdminController(library, admin, libraryDB, userDB);
                new AdminUI(adminController, admin, library).setVisible(true);
                
            } else if (account instanceof User) {
                User user = (User) account;
                workoutHandling.setGoal(user);
                new DashboardUI(user, workoutCtrl, workoutHandling, library, nutritionCtrl, statPresenter, suggestionService).setVisible(true);
            }
            
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}