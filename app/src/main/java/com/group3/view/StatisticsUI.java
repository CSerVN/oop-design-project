package com.group3.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;
import com.group3.controller.StatisticsPresenter;
import com.group3.model.ExerciseCategory;

public class StatisticsUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private StatisticsPresenter presenter;
    private JTextArea txtReport;

    public StatisticsUI(StatisticsPresenter presenter) {
        this.presenter = presenter;
        initComponents();
        loadStatistics();
    }

    private void initComponents() {
        setTitle("Thống Kê Tổng Quan");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitle = new JLabel("TIẾN ĐỘ TẬP LUYỆN & DINH DƯỠNG", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblTitle, BorderLayout.NORTH);

        txtReport = new JTextArea();
        txtReport.setEditable(false);
        txtReport.setFont(new Font("Monospaced", Font.PLAIN, 14));
        panel.add(new JScrollPane(txtReport), BorderLayout.CENTER);

        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void loadStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append("====================================================\n");
        sb.append("                 THỐNG KÊ DINH DƯỠNG                \n");
        sb.append("====================================================\n");
        
        Map<LocalDate, Double> nutritionData = presenter.getNutritionCaloriesChartData();
        if(nutritionData.isEmpty()) {
            sb.append("Chưa có dữ liệu dinh dưỡng.\n");
        } else {
            for (Map.Entry<LocalDate, Double> entry : nutritionData.entrySet()) {
                sb.append(String.format(" Ngày %s : %.1f kcal\n", entry.getKey(), entry.getValue()));
            }
        }

        sb.append("\n====================================================\n");
        sb.append("                 THỐNG KÊ TẬP LUYỆN                 \n");
        sb.append("====================================================\n");
        
        Map<LocalDate, Map<ExerciseCategory, Double>> workoutData = presenter.getWorkoutVolumeChartData();
        if(workoutData.isEmpty()) {
            sb.append("Chưa có dữ liệu bài tập.\n");
        } else {
            for (Map.Entry<LocalDate, Map<ExerciseCategory, Double>> entry : workoutData.entrySet()) {
                sb.append(" Ngày ").append(entry.getKey()).append(":\n");
                for (Map.Entry<ExerciseCategory, Double> catEntry : entry.getValue().entrySet()) {
                    sb.append(String.format("   + Nhóm %-10s : %.1f kg Volume\n", catEntry.getKey(), catEntry.getValue()));
                }
            }
        }
        sb.append("====================================================\n");
        txtReport.setText(sb.toString());
    }
}