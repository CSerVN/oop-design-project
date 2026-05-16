package com.group3.view;

import javax.swing.*;
import java.awt.*;
import com.group3.model.User;
import com.group3.model.ExerciseLibrary;
import com.group3.controller.*;

public class DashboardUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private User currentUser;
    private WorkoutLogController workoutCtrl;
    private WorkoutHandling workoutHandling;
    private ExerciseLibrary library;
    private NutritionLogController nutritionCtrl;
    private StatisticsPresenter statPresenter;
    private ExerciseSuggestionService suggestionService;

    public DashboardUI(User currentUser, WorkoutLogController workoutCtrl, WorkoutHandling workoutHandling, 
                       ExerciseLibrary library, NutritionLogController nutritionCtrl, 
                       StatisticsPresenter statPresenter, ExerciseSuggestionService suggestionService) {
        this.currentUser = currentUser;
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
        setTitle("Gym Tracking - Trang chủ");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblWelcome = new JLabel("Xin chào, " + currentUser.getName() + "!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 22));
        lblWelcome.setForeground(new Color(0, 102, 204));
        mainPanel.add(lblWelcome, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3, 2, 15, 15));
        
        JButton btnLibrary = new JButton("Thư Viện Bài Tập");
        JButton btnWorkout = new JButton("Tập Luyện");
        JButton btnNutrition = new JButton("Tra Cứu Dinh Dưỡng");
        JButton btnStatistics = new JButton("Thống Kê");
        JButton btnManageLog = new JButton("Xem Lịch Sử");
        
        Font btnFont = new Font("Arial", Font.BOLD, 14);
        JButton[] buttons = {btnLibrary, btnWorkout, btnNutrition, btnStatistics, btnManageLog};
        for (JButton btn : buttons) {
            btn.setFont(btnFont);
            btn.setFocusPainted(false);
            gridPanel.add(btn);
        }

        mainPanel.add(gridPanel, BorderLayout.CENTER);
        add(mainPanel);

        btnLibrary.addActionListener(e -> new ExerciseLibraryUI(library, currentUser, suggestionService, null).setVisible(true));
        btnWorkout.addActionListener(e -> new ExerciseUI(workoutCtrl, workoutHandling, library).setVisible(true));
        btnNutrition.addActionListener(e -> new NutritionUI(nutritionCtrl).setVisible(true));
        btnStatistics.addActionListener(e -> new StatisticsUI(statPresenter).setVisible(true));
        btnManageLog.addActionListener(e -> new ManageLogUI().setVisible(true));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupEvents() {}
}