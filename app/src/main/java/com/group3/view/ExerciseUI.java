package com.group3.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

import com.group3.controller.WorkoutLogController;
import com.group3.controller.WorkoutHandling;
import com.group3.model.Exercise;
import com.group3.model.ExerciseLibrary;
import com.group3.model.WorkoutLog;
import com.group3.strategy.RecommendationResult;

public class ExerciseUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private WorkoutLogController logController;
    private WorkoutHandling handling;
    private ExerciseLibrary library;
    private JComboBox<Exercise> cbExercise;
    private JTextField txtWeight, txtReps, txtDistance, txtTime;
    private JButton btnSave;

    public ExerciseUI(WorkoutLogController logController, WorkoutHandling handling, ExerciseLibrary library) {
        this.logController = logController;
        this.handling = handling;
        this.library = library;
        initComponents();
        setupEvents();
    }

    private void initComponents() {
        setTitle("Ghi Nhật Ký Tập Luyện");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel lblTitle = new JLabel("💪 THÊM SET TẬP MỚI", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0; panel.add(new JLabel("Chọn bài tập:"), gbc);
        
        cbExercise = new JComboBox<>();
        if(library != null && library.getLib() != null) {
            for (Exercise ex : library.getLib()) {
                cbExercise.addItem(ex);
            }
        }
        gbc.gridx = 1; panel.add(cbExercise, gbc);

        String[] labels = {"Mức tạ (kg):", "Số hiệp (reps):", "Quãng đường (km):", "Thời gian (phút):"};
        JComponent[] fields = {
            txtWeight = new JTextField(), txtReps = new JTextField(),
            txtDistance = new JTextField(), txtTime = new JTextField()
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridy++;
            gbc.gridx = 0; panel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1; panel.add(fields[i], gbc);
        }

        btnSave = new JButton("Lưu & Xem Gợi Ý Set Tiếp Theo");
        btnSave.setBackground(new Color(0, 153, 76));
        btnSave.setForeground(Color.BLACK);
        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(btnSave, gbc);

        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupEvents() {
        btnSave.addActionListener(e -> {
            try {
                Exercise selectedEx = (Exercise) cbExercise.getSelectedItem();
                if (selectedEx == null) {
                    JOptionPane.showMessageDialog(this, "Chưa có bài tập trong thư viện!");
                    return;
                }

                Double weight = txtWeight.getText().isEmpty() ? null : Double.parseDouble(txtWeight.getText());
                Integer reps = txtReps.getText().isEmpty() ? null : Integer.parseInt(txtReps.getText());
                Double distance = txtDistance.getText().isEmpty() ? null : Double.parseDouble(txtDistance.getText());
                Double time = txtTime.getText().isEmpty() ? null : Double.parseDouble(txtTime.getText());

                WorkoutLog log = new WorkoutLog.WorkoutLogBuilder()
                        .setLogID((int) (System.currentTimeMillis() % 100000))
                        .setDate(LocalDateTime.now())
                        .setExercise(selectedEx)
                        .setWeight(weight).setReps(reps).setDistance(distance).setTime(time)
                        .build();

                if (logController.addWorkoutLog(log)) {
                    RecommendationResult recommendation = handling.calculateNextSet(log);
                    JOptionPane.showMessageDialog(this, 
                            "Đã lưu thành công!\n\n" + recommendation.toString() + "\nLời khuyên: " + recommendation.getMessage(), 
                            "Gợi ý từ Hệ thống", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Lỗi khi lưu file!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng chỉ nhập số hợp lệ vào các ô đo lường!");
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
    }
}