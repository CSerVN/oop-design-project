package com.group3.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.group3.controller.AdminController;
import com.group3.model.Admin;
import com.group3.model.ExerciseLibrary;
import com.group3.model.User;

public class AdminUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private AdminController controller;
    private Admin currentAdmin;
    private ExerciseLibrary library;

    private JTable userTable;
    private DefaultTableModel tableModel;
    private JButton btnRefreshUsers, btnOpenLibrary;

    public AdminUI(AdminController controller, Admin currentAdmin, ExerciseLibrary library) {
        this.controller = controller;
        this.currentAdmin = currentAdmin;
        this.library = library;
        initComponents();
        setupEvents();
        loadUserTableData(); 
    }

    private void initComponents() {
        setTitle("Gym Tracking - Admin Dashboard");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblTitle = new JLabel("XEM CHI TIẾT THÔNG TIN NGƯỜI DÙNG", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(lblTitle, BorderLayout.NORTH);

        String[] columns = {"ID", "Họ Tên", "Username", "Giới tính", "Mục tiêu"};
        tableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(tableModel);
        mainPanel.add(new JScrollPane(userTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnRefreshUsers = new JButton("Làm mới danh sách");
        btnOpenLibrary = new JButton("Quản lý Thư viện Bài tập");
        bottomPanel.add(btnRefreshUsers);
        bottomPanel.add(btnOpenLibrary);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupEvents() {
        btnRefreshUsers.addActionListener(e -> loadUserTableData());
        btnOpenLibrary.addActionListener(e -> {
        	// Run Library as Administrator
            new ExerciseLibraryUI(library, currentAdmin, null, controller).setVisible(true);
        });
    }

    private void loadUserTableData() {
        List<User> users = controller.getUserList();
        if (users != null) {
            tableModel.setRowCount(0);
            for (User u : users) {
                tableModel.addRow(new Object[]{u.getUserID(), u.getName(), u.getUsername(), u.getGender(), u.getGoal()});
            }
        }
    }
}