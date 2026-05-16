package com.group3.view;

import javax.swing.*;
import java.awt.*;
import com.group3.controller.RegisterManager;
import com.group3.model.User;
import com.group3.model.WorkoutGoal;

public class RegisterForm extends JFrame {
    private static final long serialVersionUID = 1L;
    private RegisterManager registerManager;
    private JTextField txtName, txtUsername;
    private JPasswordField txtPassword;
    private JTextField txtAge, txtHeight, txtWeight;
    private JRadioButton rdoMale, rdoFemale; 
    private ButtonGroup bgGender;
    private JComboBox<WorkoutGoal> cbGoal;
    private JButton btnRegister, btnCancel;

    public RegisterForm(RegisterManager registerManager) {
        this.registerManager = registerManager;
        initComponents();
        setupEvents();
    }

    private void initComponents() {
        setTitle("Gym Tracking - Đăng Ký Tài Khoản");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);

        JLabel lblTitle = new JLabel("TẠO TÀI KHOẢN MỚI", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(lblTitle, gbc);

        gbc.gridwidth = 1;
        String[] labels = {"Họ và Tên:", "Tên đăng nhập:", "Mật khẩu:", "Tuổi:", "Chiều cao (cm):", "Cân nặng (kg):"};
        JComponent[] fields = {
            txtName = new JTextField(), txtUsername = new JTextField(),
            txtPassword = new JPasswordField(), txtAge = new JTextField(),
            txtHeight = new JTextField(), txtWeight = new JTextField()
        };

        for (int i = 0; i < labels.length; i++) {
            gbc.gridy = i + 1;
            gbc.gridx = 0; panel.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1; panel.add(fields[i], gbc);
        }

        gbc.gridy++; gbc.gridx = 0; panel.add(new JLabel("Giới tính:"), gbc);
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        rdoMale = new JRadioButton("Nam");
        rdoFemale = new JRadioButton("Nữ");
        rdoMale.setSelected(true);
        bgGender = new ButtonGroup();
        bgGender.add(rdoMale); bgGender.add(rdoFemale);
        genderPanel.add(rdoMale); genderPanel.add(rdoFemale);
        gbc.gridx = 1; panel.add(genderPanel, gbc);

        gbc.gridy++; gbc.gridx = 0; panel.add(new JLabel("Mục tiêu:"), gbc);
        cbGoal = new JComboBox<>(WorkoutGoal.values());
        gbc.gridx = 1; panel.add(cbGoal, gbc);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnRegister = new JButton("Xác nhận Đăng ký");
        btnCancel = new JButton("Hủy bỏ");
        btnPanel.add(btnRegister); btnPanel.add(btnCancel);

        gbc.gridy++; gbc.gridx = 0; gbc.gridwidth = 2;
        panel.add(btnPanel, gbc);
        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupEvents() {
        btnCancel.addActionListener(e -> this.dispose());
        btnRegister.addActionListener(e -> {
            try {
                String name = txtName.getText().trim();
                String username = txtUsername.getText().trim();
                String password = new String(txtPassword.getPassword());
                int age = Integer.parseInt(txtAge.getText().trim());
                double height = Double.parseDouble(txtHeight.getText().trim());
                double weight = Double.parseDouble(txtWeight.getText().trim());
                String gender = rdoMale.isSelected() ? "Nam" : "Nữ";
                WorkoutGoal goal = (WorkoutGoal) cbGoal.getSelectedItem();

                if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin bắt buộc!");
                    return;
                }

                User newUser = new User(0, name, username, password, age, gender, height, weight, goal);
                boolean success = registerManager.register(newUser);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Đăng ký thành công! Vui lòng đăng nhập.");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Tên đăng nhập đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Tuổi, chiều cao và cân nặng phải là số!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}