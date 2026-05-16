package com.group3.view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.group3.controller.ExerciseSuggestionService;
import com.group3.controller.AdminController;
import com.group3.model.Exercise;
import com.group3.model.ExerciseLibrary;
import com.group3.model.IAccount;
import com.group3.model.Admin;
import com.group3.model.User;

public class ExerciseLibraryUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private ExerciseLibrary library;
    private IAccount currentAccount;
    private ExerciseSuggestionService suggestionService;
    private AdminController adminController;

    private JTable exerciseTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> cbCategoryFilter;
    private JButton btnSuggest, btnDelete;

    public ExerciseLibraryUI(ExerciseLibrary library, IAccount account, ExerciseSuggestionService suggestionService, AdminController adminController) {
        this.library = library;
        this.currentAccount = account;
        this.suggestionService = suggestionService;
        this.adminController = adminController;
        initComponents();
        setupEvents();
        loadTableData("TẤT CẢ");
    }

    private void initComponents() {
        setTitle("Thư Viện Bài Tập - " + (currentAccount instanceof Admin ? "Quản Trị Viên" : "Người Dùng"));
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Lọc theo nhóm:"));
        cbCategoryFilter = new JComboBox<>(new String[]{"TẤT CẢ", "COMPOUND", "ISOLATE", "CARDIO", "FLEXIBILITY"});
        topPanel.add(cbCategoryFilter);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        String[] columns = {"ID", "Tên Bài Tập", "Thể Loại", "Cách Theo Dõi", "Nhóm Cơ Mục Tiêu"};
        tableModel = new DefaultTableModel(columns, 0);
        exerciseTable = new JTable(tableModel);
        exerciseTable.setRowHeight(25);
        mainPanel.add(new JScrollPane(exerciseTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSuggest = new JButton("Đề xuất bài tập");
        btnSuggest.setBackground(new Color(0, 102, 204));
        btnSuggest.setForeground(Color.BLACK);
        
        btnDelete = new JButton("Xóa bài tập đang chọn");
        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.BLACK);

        if (currentAccount instanceof User) {
            bottomPanel.add(btnSuggest);
        } else if (currentAccount instanceof Admin) {
            bottomPanel.add(btnDelete);
        }

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void setupEvents() {
        cbCategoryFilter.addActionListener(e -> loadTableData(cbCategoryFilter.getSelectedItem().toString()));

        btnSuggest.addActionListener(e -> {
            if (currentAccount instanceof User) {
                List<Exercise> suggested = suggestionService.suggest((User) currentAccount, library);
                if (suggested == null || suggested.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Không có đủ bài tập để đề xuất lúc này!");
                    return;
                }
                StringBuilder sb = new StringBuilder("Đề xuất bài tập cho mục tiêu " + ((User)currentAccount).getGoal() + ":\n\n");
                for (int i = 0; i < suggested.size(); i++) {
                    sb.append(i + 1).append(". ").append(suggested.get(i).getExerciseName()).append("\n");
                }
                JOptionPane.showMessageDialog(this, sb.toString(), "💡 Gợi Ý", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnDelete.addActionListener(e -> {
            int selectedRow = exerciseTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn bài tập trong bảng!");
                return;
            }
            String exName = tableModel.getValueAt(selectedRow, 1).toString();
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa: " + exName + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                if (adminController.deleteExercise(exName)) {
                    JOptionPane.showMessageDialog(this, "Đã xóa thành công!");
                    loadTableData(cbCategoryFilter.getSelectedItem().toString());
                }
            }
        });
    }

    private void loadTableData(String filter) {
        tableModel.setRowCount(0);
        if (library.getLib() == null) return;
        for (Exercise ex : library.getLib()) {
            if (filter.equals("TẤT CẢ") || ex.getCategory().name().equals(filter)) {
                tableModel.addRow(new Object[]{ex.getExerciseID(), ex.getExerciseName(), ex.getCategory(), ex.getTrackingType(), ex.getTargetMuscle()});
            }
        }
    }
}